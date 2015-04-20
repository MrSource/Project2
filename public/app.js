(function() {
  var app = angular.module('gemStore', []);



  app.directive("productGallery", function(){
    return {
      restrict: 'E',
      templateUrl: "product-gallery.html",
      controller: function(){
        this.current=0;
        this.setCurrent = function(imageNumber){
          this.current = imageNumber ||0;
        };
      },
      controllerAs: 'gallery'
    };
    
  });


//controller that saves all the list, este es el original in case yo screw up!
//  app.controller('StoreController', function() {
//    this.products = gems;  //this is where the list is stored 
//  });

//it gets injected as an argument the http in the function($http) 
app.controller('StoreController', ['$http', function($http) {
   //this.products = gems;  //this is where the list is stored 
   var store = this;  //this is app 
   
   store.products = []; //this is so the page doesn't give an error when loading
   $http.get("http://peaceful-journey-3820.herokuapp.com/getGems").success(function (data) {
 
   store.products = data; 
   });

store.addGem = function(gem){
//when the function is sucesful resets the products 
	$http.post("http://peaceful-journey-3820.herokuapp.com/gem", gem).success(function name(data) {
//data es lo que devuelve 
	store.products.push(data); 
  location.reload();
  })
}
store.deleteGem = function(gemId){
//when the function is sucesful resets the products 
    $http.delete("http://peaceful-journey-3820.herokuapp.com/gem/"+gemId);
    
    $http.get("http://peaceful-journey-3820.herokuapp.com/getGems").success(function (data) {
      store.products = data; 
      });
}

store.updateGem = function(gemId, gem){
	
    $http.put("http://peaceful-journey-3820.herokuapp.com/gem/"+gemId, gem).success(function name(data){
    });
  
    $http.get("http://peaceful-journey-3820.herokuapp.com/getGems").success(function (data) {
      store.products = data; 
      });
    location.reload();
}

store.getGems = function(){
 $http.get("http://peaceful-journey-3820.herokuapp.com/getGems").success(function (data) {
 store.products = data; 
   });
 location.reload();
}
 }]);
 
 



  app.controller("ReviewController", function(){
    this.review = {};

    this.addReview = function(product){
      product.review.push(this.review);
      this.review = {};
    };

  });

  app.directive("productDescriptions", function() {
    return {
      restrict: 'E',
      templateUrl: "product-description.html"
    };
  });

  app.directive("productReviews", function() {
    return {
      restrict: 'E',
      templateUrl: "product-review.html"
    };
  });

  app.directive("updateReviews", function() {
    return {
      restrict: 'E',
      templateUrl: "product-update.html"
    };
  });

  app.directive("productSpecs", function() {
    return {
      restrict:"A",
      templateUrl: "product-specs.html"
    };
  });

  app.directive("productTabs", function() {
    return {
      restrict: "E",
      templateUrl: "product-tabs.html",
      controller: function() {
        this.tab = 1;

        this.isSet = function(checkTab) {
          return this.tab === checkTab;
        };

        this.setTab = function(activeTab) {
          this.tab = activeTab;
        };
      },
      controllerAs: "tab"
    };
  });

  var gems = [
    {
      name: 'Azurite',
      description: "Some gems have hidden qualities beyond their luster, beyond their shine... Azurite is one of those gems.",
      shine: 8,
      price: 110.50,
      rarity: 7,
      color: '#CCC',
      faces: 14,
      images: [
        "images/gem-02.gif",
        "images/gem-05.gif",
        "images/gem-09.gif"
      ],
      reviews: [{
        stars: 5,
        body: "I love this gem!",
        author: "joe@example.org"
      }, {
        stars: 1,
        body: "This gem sucks.",
        author: "tim@example.org"
      }]
    }, {
      name: 'Bloodstone',
      description: "Origin of the Bloodstone is unknown, hence its low value. It has a very high shine and 12 sides, however.",
      shine: 9,
      price: 22.90,
      rarity: 6,
      color: '#EEE',
      faces: 12,
      images: [
        "images/gem-01.gif",
        "images/gem-03.gif",
        "images/gem-04.gif"
      ],
      reviews: [{
        stars: 3,
        body: "I think this gem was just OK, could honestly use more shine, IMO.",
        author: "JimmyDean@example.org"
      }, {
        stars: 4,
        body: "Any gem with 12 faces is for me!",
        author: "gemsRock@example.org"
      }]
      }, {
        name: 'Zircon',
        description: "Zircon is our most coveted and sought after gem. You will pay much to be the proud owner of this gorgeous and high shine gem.",
        shine: 70,
        price: 1100,
        rarity: 2,
        color: '#000',
        faces: 6,
        images: [
          "images/gem-06.gif",
          "images/gem-07.gif",
          "images/gem-08.gif"
        ],
        reviews: [{
          stars: 1,
          body: "This gem is WAY too expensive for its rarity value.",
          author: "turtleguyy@example.org"
        }, {
          stars: 1,
          body: "BBW: High Shine != High Quality.",
          author: "LouisW407@example.org"
        }, {
          stars: 1,
          body: "Don't waste your rubles!",
          author: "nat@example.org"
        }]
    }
  ];
})();

