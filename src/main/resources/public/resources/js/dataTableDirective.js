//module.exports = function($timeout) {
//  return {
//    restrict: 'E',
//    scope: {
//      data: '=',
//      options: '='
//    },
//    template: '<table class="table table-striped table-bordered dt-responsive nowrap" width="100%"></table>',
//    link: function(scope, element, attrs) {
//      // Implement the data table directive logic as shown in the previous example
//    }
//  };
//};

//module.exports = function($timeout) {
//  return {
//    restrict: 'A', // Use as an attribute directive
//    scope: {
//      data: '=' // Bound to the 'data' attribute
//    },
//    link: function(scope, element, attrs) {
//      // Implement the data table directive logic
//      $timeout(function() {
//        // Initialize the data table
//        $(element).DataTable({
//          data: scope.data,
//          columns: [
//            { data: 'id' },
//            { data: 'name' },
//            { data: 'address' },
//            {
//              data: 'enabled',
//              render: function(data, type, row) {
//                return data
//                  ? '<span class="label label-success">Active</span>'
//                  : '<span class="label label-danger">Disabled</span>';
//              }
//            }
//          ]
//        });
//      });
//    }
//  };
//};

angular.module('myApp')
.directive('dataTable', ['$timeout', function($timeout) {
  return {
    restrict: 'A',
    scope: {
      data: '=medical',
      options: '=myOptions'
    },
    link: function(scope, element, attrs) {
      var table;
      
      // Initialize and update the DataTable
      function initAndUpdateTable() {
        if (!table) {
        	
          // Initialize the DataTable
          scope.options.data = scope.data;
          table = $(element).DataTable(scope.options);
          table.buttons().container()
            .appendTo($(element).closest('.dataTables_wrapper').find('.pull-right'));
        } else {
          // Update the table data
          table.clear().rows.add(scope.data).draw();
        }
      }

      // Watch for data changes and update the table
      scope.$watch('data', function(newData) {
        initAndUpdateTable();
      });

      // Initialize the table
      initAndUpdateTable();
    }
  };
}]);