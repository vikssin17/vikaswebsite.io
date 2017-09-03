(function() {
    'use strict';

    angular
        .module('secondApp')
        .controller('CheckfeeDeleteController',CheckfeeDeleteController);

    CheckfeeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Checkfee'];

    function CheckfeeDeleteController($uibModalInstance, entity, Checkfee) {
        var vm = this;

        vm.checkfee = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Checkfee.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
