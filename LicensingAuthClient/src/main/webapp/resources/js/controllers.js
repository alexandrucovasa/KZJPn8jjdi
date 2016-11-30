angular.module('drugApp.controllers',[])
.controller('mainCtrl', function($scope, $rootScope, $resource,$http, AuthService) {

    $scope.$on('oauth:login', function(event, token) {
        $http.defaults.headers.common.Authorization= 'Bearer ' + token.access_token;
        console.log('Authorized third party app with token', token.access_token);
        console.log(AuthService.parseJwt(token.access_token));
        AuthService.saveToken(token.access_token);

        $rootScope.isAdmin = AuthService.hasRole("ROLE_ADMIN");
        $rootScope.isAuthenticated= AuthService.hasRole("ROLE_USER") || AuthService.hasRole("ROLE_ADMIN");


        $rootScope.canRead = AuthService.hasPermission("read");
        $rootScope.canWrite = AuthService.hasPermission("write");


        $rootScope.roles = AuthService.getUserRoles();
        $rootScope.permissions = AuthService.getUserPermissions();
    });
}).controller('DrugListController',function($scope,$state,$stateParams, popupService,$window,Drug){

    $scope.drugs=Drug.query();

    $scope.deleteDrug = function(id, drug){
        if(popupService.showPopup('Confirm delete')){
            drug.$delete({id:id.id},function() {
                $scope.drugs=Drug.query();
                $state.go('drugs');
            });
        };
    }

}).controller('DrugViewController',function($scope,$stateParams,Drug){
    $scope.drug = Drug.get({id:$stateParams.id});

}).controller('DrugCreateController',function($scope,$state,$stateParams,Drug){

    $scope.drug = new Drug();

    $scope.addDrug=function(){
        $scope.drug.$save(function(){
            $state.go('drugs');
        });
    }

}).controller('DrugEditController',function($scope,$state,$stateParams,Drug){

    $scope.updateDrug=function(){
        $scope.drug.$update(function(){
            $state.go('drugs');
        });
    };

    $scope.loadDrug=function(){
        $scope.drug=Drug.get({id:$stateParams.id});
    };

    $scope.loadDrug();
});