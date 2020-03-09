var app = angular.module("CarRentalApplication", []);
 

app.controller("carRentalController", function($scope, $http) {
 
 
    $scope.availableRentalCars = [];
    $scope.locations =[];
    $scope.selectedLocation ='MALVERN';
    $scope.fromDate = "";
    $scope.toDate = "";
    $scope.fromTime = "";
    $scope.toTime = "";
    
    // load available locations 
    refreshCarRentalLocations();
    
 
   // HTTP GET request to get available cars as per the selected location and dates 
    // Call: http://localhost:8080/rentalCars
    $scope.submitRequest = function() {
    	 $http({
             method: 'GET',
             url: '/rentalCars',
             params: {location: $scope.selectedLocation,
            	 fromDate: $scope.fromDate,
            	 fromTime: $scope.fromTime,
            	 toDate:   $scope.toDate,
            	 toTime:   $scope.toTime     }
         }).then(
             function(res) { // success
                 $scope.availableRentalCars = res.data.listOfCars;
                 $scope.previouslySelectedLocation = res.data.location;
                 console.log(res.data);
             },
             function(res) { // error
                 console.log("Error: " + res.status + " : " + res.data);
             }
         );
    };
    	 
 // HTTP POST request to update car reservation details  
    // Call: http://localhost:8080/updateReservation
    
    	 $scope.reserveCar = function(rentalCar) {
    		 var data = {location: $scope.selectedLocation,
            	     carName:  rentalCar.carName };
    		 
    	
    	 $http({
    	            method: 'POST',
    	            url: '/updateReservation',
    	            data: angular.toJson(data),
    	            headers: {
    	                'Content-Type': 'application/json'
    	            }
    	        }).then(success ,error);
    	    alert("Car is reserved successfully");
    	        
    	 };
  
    	// HTTP GET request to get all available locations on page load 
    	    // Call: http://localhost:8080/updateReservation
    function refreshCarRentalLocations() {
        $http({
            method: 'GET',
            url: '/locations'
        }).then(
            function(res) { // success
                $scope.locations = res.data;
                console.log(res.data);
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    var success = function () {
    	refresh();
 };
    
 	var error = function () {
    console.log("please check the java J2EE IDE console for the successful operation of POST method ");
 	};
 
 
    // Clear the past data once submitted
    function refresh() {
    	$scope.selectedLocation='';
        $scope.fromDate = '';
        $scope.toDate = "";
        $scope.fromTime = "";
        $scope.toTime = "";
        
    }
});