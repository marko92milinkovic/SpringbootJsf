var app = angular.module('app', []);

app.controller('carController', function ($http) {
    var self = this;
    $http.get('/rest/cars').then(function (response) {
        self.cars = response.data;
    });
});

app.controller('NavigationContro', function ($location) {
    this.val = "Show cars";
    this.showAvailableCars = function () {
        $location.path("/cars.html");
    };
});