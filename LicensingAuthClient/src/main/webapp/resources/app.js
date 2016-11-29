
angular.module('myApp',['ui.router','ngResource','drugApp.controllers','drugApp.services']);

angular.module('myApp').config(function($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    }).hashPrefix('!');
});

angular.module('myApp').config(['$httpProvider', function ($httpProvider) {
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

angular.module('myApp').controller('mainCtrl', function($scope,$resource,$http,$rootScope) {

    $scope.$on('oauth:login', function(event, token) {
        $http.defaults.headers.common.Authorization= 'Bearer ' + token.access_token;
        console.log('Authorized third party app with token', token.access_token);
    });

    $scope.foo = {id:0 , name:"sample foo"};
    $scope.foos = $resource("http://localhost:8082/spring-security-oauth-resource/foos/:fooId",{fooId:'@id'});

    $scope.getFoo = function(){
        $scope.foo = $scope.foos.get({fooId:$scope.foo.id});
    }

    $scope.createFoo = function(){
        if($scope.foo.name.length==0)
        {
            $rootScope.message = "Foo name can not be empty";
            return;
        }
        $scope.foo.id = null;
        $scope.foo = $scope.foos.save($scope.foo, function(){
            $rootScope.message = "Foo Created Successfully";
        });
    }

    // bar
    $scope.bar = {id:0 , name:"sample bar"};
    $scope.bars = $resource("http://localhost:8082/spring-security-oauth-resource/bars/:barId",{barId:'@id'});

    $scope.getBar = function(){
        $scope.bar = $scope.bars.get({barId:$scope.bar.id});
    }

    $scope.createBar = function(){
        if($scope.bar.name.length==0)
        {
            $rootScope.message = "Bar name can not be empty";
            return;
        }
        $scope.bar.id = null;
        $scope.bar = $scope.bars.save($scope.bar, function(){
            $rootScope.message = "Bar Created Successfully";
        });
    }


});