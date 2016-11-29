package com.cgm.controller;

import com.cgm.dto.Drug;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DrugController {
    Map<String, Drug> drugs = new HashMap<>();
    {
        drugs.put("0", new Drug("0", "1%", "Aspirina"));
        drugs.put("1", new Drug("1", "100 ml", "Paracetamol"));
        drugs.put("2", new Drug("2", "22 cl",  "Coldrex"));
        drugs.put("3", new Drug("3", "21 % + 100ml", "Random medicine"));

    }

    // API - read by id
    @PreAuthorize("permitAll()")
    @RequestMapping(method = RequestMethod.GET, value = "/drugs/{id}")
    @ResponseBody
    public Drug findById(@PathVariable final String id) {
        System.out.println("id=" + id);
        Drug drug = drugs.get(id);

        return drug;
    }

    // API - read all
    @PreAuthorize("permitAll()")
    @RequestMapping(method = RequestMethod.GET, value = "/drugs")
    @ResponseBody
    public List<Drug> list() {
        List result=new ArrayList();
        result.addAll(drugs.values());
        return result;
    }

    // API - Create
    @PreAuthorize("#oauth2.hasScope('admin') and #oauth2.hasScope('write') and hasRole('ROLE_ADMIN') or  #oauth2.hasScope('authenticated') and #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.POST, value = "/drugs")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Drug create(@RequestBody final Drug drug) {
        drugs.put(drug.getId(), drug);
        return drug;
    }


    // API - Update
    @PreAuthorize("#oauth2.hasScope('admin') and #oauth2.hasScope('write') and hasRole('ROLE_ADMIN') or  #oauth2.hasScope('authenticated') and #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.PUT, value = "/drugs")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Drug update(@RequestBody final Drug drug) {
        drugs.put(drug.getId(), drug);
        return drug;
    }


    // API - Delete
    @PreAuthorize("#oauth2.hasScope('admin') and #oauth2.hasScope('write') and hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/drugs/{id}")
    @ResponseBody
    public void delete(@PathVariable final String id) {


        drugs.remove(id);
    }


}
