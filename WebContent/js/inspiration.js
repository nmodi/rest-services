var app = angular.module('inspirationApp', []);

app.controller('Inspire', function ($scope, $http) {
	$http.get('/web/services/inspiration').
	success(function(data) {
		$scope.inspiration = data;
	});
}); 
