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
 * Factory to access todo resource
 */
module.factory('ToDo', function($log, $http, $q){
	return {
		/**
		 * Read all todos
		 */
		all:function(){
			var deferred = $q.defer();
		    var promise = deferred.promise;
		    $http.get('resource/todo').success(function(data) {
		    	$log.info(data);
		    	deferred.resolve(data);
	          })
	          .error(function(error){
	            deferred.reject(error);
	          });
		    return promise;
		}
	}
});
/**
 * Factory to access user data
 */
module.factory('User', function($log, $http, $q){
	return {
		/**
		 * Read all todos
		 */
		current:function(){
			var deferred = $q.defer();
		    var promise = deferred.promise;
		    $http.get('user').success(function(data) {
		    	$log.info(data);
		    	deferred.resolve(data);
	          })
	          .error(function(error){
	            deferred.reject(error);
	          });
		    return promise;
		},
		/**
		 * Logout the current user
		 */
		logout:function(){
			var deferred = $q.defer();
		    var promise = deferred.promise;
		    $http.post('logout',{}).success(function(data) {
		    	$log.info(data);
		    	deferred.resolve(data);
	          })
	          .error(function(error){
	            deferred.reject(error);
	          });
		    return promise;
		}
	}
});
/**
 * Navigation controller
 */
module.controller('navigation',function($rootScope, $scope, $location, $route, User) {
	$scope.credentials = {};
	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};
	/**
	 * Check authentication
	 */
	$scope.init=function(){
		User.current().then(
				function(data){
					if (data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
				}, function(error){
					$rootScope.authenticated = false;
				});
	};
	/**
	 * Logout user
	 */
	$scope.logout = function() {
		User.logout().then(
				function(data){
					$rootScope.authenticated = false;
					$location.path("/");
				}, function(error){
					$rootScope.authenticated = false;
				});
	};
	$scope.init();
});
/**
 * Home controller
 */
module.controller('home', function($scope, ToDo) {
	$scope.todos = null;
	$scope.init = function(){
		ToDo.all().then(
			function(data){
				$scope.todos=data;
			});
	};
	$scope.init();
});
