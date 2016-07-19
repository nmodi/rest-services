var app = angular.module('inspirationApp', []);

app.controller('Inspire', function ($scope, $http) {
	$http.get('http://localhost:8080/apps/services/inspiration').
	success(function(data) {
		$scope.inspiration = data;
	});
}); 
