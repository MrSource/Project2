var app = angular.module('store-directives', []);

app.directive("descriptions", function() {
  return {
    restrict: "E",
    templateUrl: "product-description.html"
  }
});

app.directive("reviews", function() {
  return {
    restrict: "E",
    templateUrl: "product-review.html"
  }
});

app.directive("updates", function() {
  return {
    restrict: "E",
    templateUrl: "product-update.html"
  }
});

app.directive("specs", function() {
  return {
    restrict: "E",
    templateUrl: "product-specs.html"
  }
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