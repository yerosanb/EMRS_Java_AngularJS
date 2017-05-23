APP.directive('access', [
    'AuthSharedService',
    function (AuthSharedService) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var rights = attrs.access.split(',');
                if (rights.length > 0) {
                    if (AuthSharedService.isAuthorized(rights)) {
                        element.removeClass('hide');
                    } else {
                        element.addClass('hide');
                    }
                }
            }
        };
    }]);