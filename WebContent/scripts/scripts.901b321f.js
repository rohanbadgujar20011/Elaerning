"use strict";

angular.module("seatReservationApp").controller("MainCtrl",
		["$scope",function(a)
		 {a.rows=["A","B","C","D","E","F","G","H","I","J"],
			a.cols=[1,2,3,4,5,6,7,8];
		 var b=["A2","A3","C5","C6","C7","C8","J1","J2","J3","J4"],
		 c=[];
		 a.seatClicked=function(a){
			 console.log("Selected Seat: "+a);
			 var b=c.indexOf(a);-1!=b?c.splice(b,1):c.push(a)},a.getStatus=function(a){return b.indexOf(a)>-1?"reserved":c.indexOf(a)>-1?"selected":void 0},a.clearSelected=function(){c=[]},a.showSelected=function(){alert(c.length>0?"Selected Seats: \n"+c:"No seats selected!")}}]);