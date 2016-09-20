<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Spring JPA 게시판</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<style>
.name.ng-valid {
	background-color: lightgreen;
}

.name.ng-dirty.ng-invalid-required {
	background-color: red;
}

.name.ng-dirty.ng-invalid-maxlength {
	background-color: yellow;
}

.passwd.ng-valid {
	background-color: lightgreen;
}

.passwd.ng-dirty.ng-invalid-required {
	background-color: red;
}

.passwd.ng-dirty.ng-invalid-maxlength {
	background-color: yellow;
}

.title.ng-valid {
	background-color: lightgreen;
}

.title.ng-dirty.ng-invalid-required {
	background-color: red;
}

.title.ng-dirty.ng-invalid-maxlength {
	background-color: yellow;
}

body, #mainWrapper {
	height: 70%;
	background-color: rgb(245, 245, 245);
}

body, .form-control {
	font-size: 12px !important;
}

.floatRight {
	float: right;
	margin-right: 18px;
}

.has-error {
	color: red;
}

.formcontainer {
	background-color: #DAE8E8;
	padding: 20px;
}

.tablecontainer {
	padding-left: 20px;
}

.generic-container {
	width: 80%;
	margin-left: 20px;
	margin-top: 20px;
	margin-bottom: 20px;
	padding: 20px;
	background-color: #EAE7E7;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-shadow: 0 0 30px black;
}

.custom-width {
	width: 80px !important;
}
.pointer {
    cursor: pointer;
}
</style>

<!--  For AngularJS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/js/app.js' />"></script>
<script src="<c:url value='/js/board_service.js' />"></script>
<script src="<c:url value='/js/board_controller.js' />"></script>
</head>
<body ng-app="myBoard" class="ng-cloak" ng-controller="BoardController as ctrl">
	<div class="generic-container" ng-controller="BoardController as ctrl"
		style="width: 800px;">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Spring Data JPA 게시판 글 쓰기 </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
					<input type="hidden" ng-model="ctrl.board.id" />													
					<div class="row">
						<div class="form-group col-md-6">
							<label class="col-md-2 control-lable" for="name">Name : </label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.board.name" id="name"
									class="name form-control input-sm"
									placeholder="Enter your Name" required 
									ng-maxlength="20" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.name.$error.required">This is a
										required field</span> <span ng-show="myForm.name.$error.maxlength">Maximum
										length is 20</span> <span ng-show="myForm.name.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label class="col-md-2 control-lable" for="passwd">Password
								:</label>
							<div class="col-md-7">
								<input type="password" ng-model="ctrl.board.passwd" id="passwd"
									class="passwd form-control input-sm"
									placeholder="Enter your Password" required ng-maxlength="20" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.passwd.$error.required">This is a
										required field</span> <span ng-show="myForm.passwd.$error.maxlength">Maximum
										length is 20</span> <span ng-show="myForm.passwd.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label class="col-md-2 control-lable" for="title">Title :</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.board.title" id="title"
									class="title form-control input-sm"
									placeholder="Enter your Title" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.title.$error.required">This is a
										required field</span> <span ng-show="myForm.title.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label class="col-md-2 control-lable" for="content">Contents
								:</label>
							<div class="col-md-7">
								<textarea rows="4"
									ng-model="ctrl.board.content" id="content"
									class="content form-control input-sm"
									placeholder="Enter your Contents" required >
									</textarea>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.content.$error.required">This is a
										required field</span> <span ng-show="myForm.content.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.board.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default" style="float: center;">
			<div class="panel-heading">
				<span class="lead">Spring Data JPA 게시판 리스트보기 </span>
			</div>
			<h5>
				총 {{ctrl.page.totalElements}}</span>건
			</h5>
			<div class="tablecontainer">
				<table width="600" border="1" align="left" class="table table-hover">
					<tr align="left">
						<th align="center">순번</th>
						<th align="center">글번호</th>
						<th align="center">제목</th>
						<th align="center">글쓴이</th>
						<th align="center">등록일</th>
						<th align="center">조회수</th>
						<th align="center">조회/삭제</th>
					</tr>

					<tr data-ng-repeat="board in ctrl.page.content">
						<td align="center"><span ng-bind="{{$index+1}}"></span></td>
						<td align="center"><span ng-bind="board.id"></span></td>
						<td align="left">
							<!-- 레벨의 수만큼 글을 뒤로 민다 --> <span
							ng-repeat="n in [].constructor(board.replylevel) track by $index">
								&nbsp;&nbsp; </span> <span ng-bind="board.title"></span>
						</td>
						<td align="center"><span ng-bind="board.name"></span></td>
						<td align="center">{{board.regdate | date:"yy.MM.dd hh:mm"}}</td>
						<td align="center"><span ng-bind="board.readcount"></span></td>
						<td>
							<button type="button" ng-click="ctrl.edit(board.id)"
								class="btn btn-success custom-width">Edit</button>
							<button type="button" ng-click="ctrl.remove(board.id)"
								class="btn btn-danger custom-width">Remove</button>
						</td>
					</tr>
					</tbody>
				</table>
				<div>
				    <!--  게시판 페이징 -->
					<ul class="pagination">						
						<li ng-class="{disabled: ctrl.page.number === 0}"><a
							ng-show="ctrl.page.number !== 0" 
							class="pointer"
							ng-click="ctrl.list(ctrl.page.number-1)">Prev</a>
							<span ng-show="ctrl.page.number === 0">Prev</span></li>
						<li ng-class="{disabled: ctrl.page.number === ctrl.page.totalPages - 1}">
							<a ng-show="ctrl.page.number !== ctrl.page.totalPages - 1"
							class="pointer"
							ng-click="ctrl.list(ctrl.page.number+1)">Next</a> 
							<span ng-show="ctrl.page.number === ctrl.page.totalPages - 1">Next</span>
						</li>						
					</ul>					
				</div>
			</div>
		</div>
	</div>

</body>
</html>