(function() {
    'use strict';
    angular
        .module('secondApp')
        .factory('Checkfee', Checkfee);

    Checkfee.$inject = ['$resource'];

    function Checkfee ($resource) {
        var resourceUrl =  'api/checkfees/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
