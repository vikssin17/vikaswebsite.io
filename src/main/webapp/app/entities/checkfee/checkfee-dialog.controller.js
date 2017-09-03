(function() {
    'use strict';

    angular
        .module('secondApp')
        .controller('CheckfeeDialogController', CheckfeeDialogController);

    CheckfeeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Checkfee'];

    function CheckfeeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Checkfee) {
        var vm = this;

        vm.checkfee = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.checkfee.id !== null) {
                Checkfee.update(vm.checkfee, onSaveSuccess, onSaveError);
            } else {
                Checkfee.save(vm.checkfee, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('secondApp:checkfeeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
