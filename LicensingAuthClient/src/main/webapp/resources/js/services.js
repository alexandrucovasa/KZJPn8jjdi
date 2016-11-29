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
});