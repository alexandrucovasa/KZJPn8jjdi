
angular.module('drugApp',['ui.router','ngResource','drugApp.controllers','drugApp.services', "oauth"]);

angular.module('drugApp').config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push(function ($q,$rootScope) {
        return {
            'responseError': function (responseError) {
                $rootScope.message = responseError.statusText;
                console.log("error here");
                console.log(responseError);
                return $q.reject(responseError);
            }
        };
    });
}]);

angular.module('drugApp').config(function($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    }).hashPrefix('!');
});



angular.module('drugApp').config(function($stateProvider,$httpProvider){
    $stateProvider.state('drugs',{
        url:'/drugs',
        templateUrl:'drugs',
        controller:'DrugListController'
    }).state('viewDrug',{
       url:'/drugs/:id/view',
       templateUrl:'drug-view',
       controller:'DrugViewController'
    }).state('newDrug',{
        url:'/drugs/new',
        templateUrl:'drug-add',
        controller:'DrugCreateController'
    }).state('editDrug',{
        url:'/drugs/:id/edit',
        templateUrl:'drug-edit',
        controller:'DrugEditController'
    });
}).run(function($state){
   $state.go('drugs');
});