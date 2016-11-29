angular.module('drugApp.controllers',[])
.controller('mainCtrl', function($scope,$resource,$http) {

    $scope.$on('oauth:login', function(event, token) {
        $http.defaults.headers.common.Authorization= 'Bearer ' + token.access_token;
        console.log('Authorized third party app with token', token.access_token);
    });
}).controller('DrugListController',function($scope,$state,$stateParams, popupService,$window,Drug){

    $scope.drugs=Drug.query();

    $scope.deleteDrug = function(id, drug){
        if(popupService.showPopup('Confirm delete')){
            drug.$delete({id:id},function() {
                $window.location.href='';
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