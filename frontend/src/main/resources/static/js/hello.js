var module = angular.module('hello', [ 'ngRoute' ]);
/**
 * Configure routing
 */
module.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).otherwise('/');
});
/**
 * Configure debug logging
 */
module.config(function($logProvider){
  $logProvider.debugEnabled(true);
});
/**
 * Navigation controller
 */
module.controller('navigation',function($rootScope, $scope, $http, $location, $route) {
	$scope.credentials = {};
	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};
	/**
	 * Check authentication
	 */
	$http.get('user').success(function(data) {
		if (data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	}).error(function() {
		$rootScope.authenticated = false;
	});
	/**
	 * Logout user
	 */
	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		}).error(function(data) {
			console.log("Logout failed")
			$rootScope.authenticated = false;
		});
	}
});
/**
 * Home controller
 */
module.controller('home', function($scope, $http, $log) {
	$scope.todos = null;
	$scope.init = function(){
		$http.get('resource/todo').success(function(data) {
			$scope.todos = data;
			$log.info($scope.todos);
		});
	};
	$scope.init();
});
