(function() {
    'use strict';

    angular
        .module('secondApp')
        .controller('CheckfeeDetailController', CheckfeeDetailController);

    CheckfeeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Checkfee'];

    function CheckfeeDetailController($scope, $rootScope, $stateParams, previousState, entity, Checkfee) {
        var vm = this;

        vm.checkfee = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('secondApp:checkfeeUpdate', function(event, result) {
            vm.checkfee = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
