
APP.factory('messageService', function (Messages, localStorageService, $rootScope, version) {

  var populate = function () {
    if (localStorageService.get('version') != version) {
      localStorageService.add('version', version);
      Messages.get({}, function (data) {
        for (var attr in data.messages) {
          localStorageService.add('message.' + attr, data.messages[attr]);
        }
        $rootScope.$broadcast('messagesPopulated');
      }, {});
    }
  };

  var get = function () {
    var keyWithArgs = Array.prototype.slice.call(arguments);
    var displayMessage =  localStorageService.get('message.' + keyWithArgs[0]);
    if(keyWithArgs.length > 1 && displayMessage) {
      $.each(keyWithArgs, function (index, arg) {
        if (index > 0) {
          displayMessage = displayMessage.replace("{" + (index-1) + "}", arg);
        }
      });
    }
    return displayMessage || keyWithArgs[0];
  };

  return{
    populate:populate,
    get:get
  };
});

var update = {update: {method: 'PUT'}};

APP.factory('Messages', function ($resource) {
	  return $resource('/messages.json', {}, {});
	});

APP.factory('Locales', function ($resource) {
	  return $resource('/locales.json', {}, {});
	});

APP.factory('ChangeLocale', function ($resource) {
	  return $resource('/changeLocale.json', {}, update);
	});