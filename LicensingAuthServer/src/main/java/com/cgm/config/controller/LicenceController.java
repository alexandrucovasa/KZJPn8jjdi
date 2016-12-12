package com.cgm.config.controller;

import com.cgm.config.token.CgmTokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Collection;
import java.util.List;

@Controller
public class LicenceController {
    private static final Logger logger = LoggerFactory.getLogger(LicenceController.class);


    @Autowired
    CgmTokenStore cgmTokenStore;

    @GetMapping("/licence/{clientid}/{username}")
    @ResponseBody
    public ResponseEntity<File> getLicence(@PathVariable String clientId, @PathVariable String username) {

        //TODO extract data and create a file
        File f = new File("licence.txt");

        Collection<OAuth2AccessToken> oAuth2AccessTokens = cgmTokenStore.findTokensByClientIdAndUserName(clientId, username);

        //serialize the List
        try (
                OutputStream file = new FileOutputStream(f);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer)
        ) {
            output.writeObject(oAuth2AccessTokens);
        } catch (IOException ex) {
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getName() + "\"")
                .body(f);
    }

    @GetMapping("/licence")
    public String uploadLicence() {

        File f = new File("licence.txt");

        // TODO Decrypt and store file content to DB
        try (
                InputStream fileInputStream = new FileInputStream(f);
                InputStream buffer = new BufferedInputStream(fileInputStream);
                ObjectInput input = new ObjectInputStream(buffer)
        ) {
            //deserialize the List
            List<OAuth2AccessToken> oAuth2AccessTokens = (List<OAuth2AccessToken>) input.readObject();
            //display its data
            for (OAuth2AccessToken oAuth2AccessToken : oAuth2AccessTokens) {
                logger.debug("Recovered data: ");
                logger.debug(oAuth2AccessToken.getValue());
                logger.debug(oAuth2AccessToken.getTokenType());
            }
        } catch (ClassNotFoundException | IOException ex) {
        }

        return "redirect:/";
    }
}
