(function() {
    'use strict';

    angular
        .module('secondApp')
        .controller('CheckfeeController', CheckfeeController);

    CheckfeeController.$inject = ['Checkfee'];

    function CheckfeeController(Checkfee) {

        var vm = this;

        vm.checkfees = [];

        loadAll();

        function loadAll() {
            Checkfee.query(function(result) {
                vm.checkfees = result;
                vm.searchQuery = null;
            });
        }
    }
})();
