(function() {
    'use strict';

    angular
        .module('secondApp')
        .controller('StoreController', StoreController);

    StoreController.$inject = ['Store'];

    function StoreController(Store) {

        var vm = this;

        vm.stores = [];

        loadAll();

        function loadAll() {
            Store.query(function(result) {
                vm.stores = result;
                vm.searchQuery = null;
            });
        }
    }
})();
