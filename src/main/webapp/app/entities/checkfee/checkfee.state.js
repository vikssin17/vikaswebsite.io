(function() {
    'use strict';

    angular
        .module('secondApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('checkfee', {
            parent: 'entity',
            url: '/checkfee',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Checkfees'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/checkfee/checkfees.html',
                    controller: 'CheckfeeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('checkfee-detail', {
            parent: 'checkfee',
            url: '/checkfee/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Checkfee'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/checkfee/checkfee-detail.html',
                    controller: 'CheckfeeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Checkfee', function($stateParams, Checkfee) {
                    return Checkfee.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'checkfee',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('checkfee-detail.edit', {
            parent: 'checkfee-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/checkfee/checkfee-dialog.html',
                    controller: 'CheckfeeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Checkfee', function(Checkfee) {
                            return Checkfee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('checkfee.new', {
            parent: 'checkfee',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/checkfee/checkfee-dialog.html',
                    controller: 'CheckfeeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                feeamount: null,
                                reason: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('checkfee', null, { reload: 'checkfee' });
                }, function() {
                    $state.go('checkfee');
                });
            }]
        })
        .state('checkfee.edit', {
            parent: 'checkfee',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/checkfee/checkfee-dialog.html',
                    controller: 'CheckfeeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Checkfee', function(Checkfee) {
                            return Checkfee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('checkfee', null, { reload: 'checkfee' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('checkfee.delete', {
            parent: 'checkfee',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/checkfee/checkfee-delete-dialog.html',
                    controller: 'CheckfeeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Checkfee', function(Checkfee) {
                            return Checkfee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('checkfee', null, { reload: 'checkfee' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
