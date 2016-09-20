'use strict';

App.controller('BoardController', ['$scope', 'BoardService', 
      function($scope, BoardService) {
          var self = this;
          self.board={id:null, name:'',passwd:'',title:'',content:''};
          self.page=[];
               
          //리스트 보기
          self.list = function(curPage){
              BoardService.list(curPage)
              .then(
                 function(data) {
                     self.page = data;
                     console.log("[controller:list]", self.page);
                     //alert("목록보기 성공!");
                 },
                 function(errResponse){
                     console.error('Error while fetching page...');
                 }
              );
          };   
          
          //글 입력
          self.create = function(board) {
              BoardService.create(board)
              .then(
                  function() {
                	  alert("Save OK!");
                	  self.list(0);
                  }, 
                  function(errResponse){
                     console.error('Error while creating Article.');
                  } 
               );    
           }; 
           
           //글 수정
           self.update = function(board, id){
               BoardService.update(board, id)
                       .then(
                    		   function() {
                             	  alert("Update OK!");
                             	  self.list(self.page.number);  //현재 페이지 리로드
                               }, 
                               function(errResponse){
                                    console.error('Error while updating User.');
                               } 
                   );
           };
           
           //글 삭제
           self.delete = function(id){
               BoardService.delete(id)
                       .then(
                               function() {
                            	   alert("Delete OK!");
                            	   self.list(self.page.number);  //현재 페이지 리로드
                               }, 
                               function(errResponse){
                                    console.error('Error while deleting Article.');
                               } 
                   );
           };
           
          self.list(0);
          
          // ADD or UPDATE 버튼 클릭
          self.submit = function() {
              if(self.board.id===null){                      
                  self.create(self.board);          
                  console.log("[controller:create]", self.board);
              }else{
                  self.update(self.board, self.board.id);
                  console.log('Article updated with id ', self.board.id);
              }
              self.reset();              
          };   
          
          //글읽기
          self.edit = function(id){
              console.log('[controller:edit]', id);
              console.log("3333", self.page);
              for(var i = 0; i < self.page.content.length; i++){
                  if(self.page.content[i].id === id) {
                     self.board = angular.copy(self.page.content[i]);
                     console.log("[read article]", self.board);
                     break;
                  }
              }
          };  
          
          //글 삭제
          self.remove = function(id){
        	  if (confirm('Are you sure you want to delete this article?')) {
        		  console.log('[controller:remove]', id);
                  //글입력(수정)화면 CLEAR
                  if(self.board.id === id) {  
                     self.reset();
                  }
                  self.delete(id);
        		} else {
        		    return;
        		}              
          };
          
          self.reset = function(){
        	  self.board={id:null, name:'',passwd:'',title:'',content:''};
              $scope.myForm.$setPristine(); //reset Form
          };
          
}]);
      