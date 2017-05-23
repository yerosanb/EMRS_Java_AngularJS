APP.controller('localeController', _localeController);

function _localeController($scope, $rootScope, $cookies, Locales, ChangeLocale, Messages, messageService, 
		localStorageService) {
  $scope.selectedLocale = $cookies.lang === undefined ? "en" : $cookies.lang;

  Locales.get({}, function (data) {
    $scope.locales = data.locales;
    messageService.populate();
  }, {});

  $scope.changeLocale = function (localeKey) {
	  console.log("locale being changed");
    $scope.selectedLocale = localeKey;
    ChangeLocale.update({locale: localeKey}, {}, function (data) {
      Messages.get({}, function (data) {
        for (var attr in data.messages) {
          var key = 'message.' + attr;
          localStorageService.remove(key);
          localStorageService.add(key, data.messages[attr]);
        }
        $rootScope.$broadcast('messagesPopulated');
      }, {});
    });
  };
}