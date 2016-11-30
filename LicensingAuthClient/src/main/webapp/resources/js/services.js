angular.module('drugApp.services',[]).factory('Drug',function($resource){
    return $resource('http://localhost:8082/LicensingAuthResource/drugs/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        },
        remove: { method: "DELETE"}
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
}).service('AuthService',function($window){
    var self = this;

    self.parseJwt = function(token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse($window.atob(base64));
    }

    self.saveToken = function(token) {
        $window.localStorage['jwtToken'] = token;
    }

    self.getToken = function() {
        return $window.localStorage['jwtToken'];
    }

    self.isAuthenticated = function() {
        var token = self.getToken();
        if(token) {
            var params = self.parseJwt(token);
            return Math.round(new Date().getTime() / 1000) <= params.exp;
        } else {
            return false;
        }
    }

    self.getUserRoles = function(){
        var token = self.getToken();
        if (token){
            var tokenJson = self.parseJwt(token);
            return tokenJson.authorities;
        }
        else {
            return [];
        }
    }

    self.hasRole = function(role){
        var roles = self.getUserRoles();


        if(roles.indexOf(role) !== -1) {
           return true;
        }
        return false;
    }

    self.hasPermission = function(permission){
        var permissions = self.getUserPermissions();
        if(permissions.indexOf(permission) !== -1) {
            return true;
        }
        return false;
    }

    self.getUserPermissions = function(){
        var token = self.getToken();
        if (token){
            var tokenJson = self.parseJwt(token);
            return tokenJson.scope;
        }
        else {
            return [];
        }
    }

    self.logout = function() {
        $window.localStorage.removeItem('jwtToken');
    }
}).directive('back', ['$window', function($window) {
    return {
        restrict: 'A',
        link: function (scope, elem, attrs) {
            elem.bind('click', function () {
                $window.history.back();
            });
        }
    };
}]);