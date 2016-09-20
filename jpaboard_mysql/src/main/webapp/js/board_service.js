'use strict';
 
App.factory('BoardService', ['$http', '$q', function($http, $q){
 
    return {
         
            list: function(curPage) {
                    return $http.get('http://localhost:8080/board/' +  curPage)
                            .then(
                                    function(response){
                                    	console.log("[service:list]server call  suceeded.");
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching contents');
                                        return $q.reject(errResponse);
                                    }
                            );
            } ,
            create: function(board){
                return $http.post('http://localhost:8080/board/', board)
                        .then(
                                function(response){
                                	console.log("[service:create]server call  suceeded.");
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while creating article');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            delete: function(id){
                return $http.delete('http://localhost:8080/board/'+id)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while deleting article');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            update: function(board, id){
                return $http.put('http://localhost:8080/board/'+id, board)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while updating board');
                                    return $q.reject(errResponse);
                                }
                        );
            }         
    };
 }]);