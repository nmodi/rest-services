var app = angular.module('inspirationApp', []);

app.controller('Inspire', function ($scope, $http) {
	$http.get('/apps/services/inspiration').
	success(function(data) {
		$scope.inspiration = data;
	});
}); 
