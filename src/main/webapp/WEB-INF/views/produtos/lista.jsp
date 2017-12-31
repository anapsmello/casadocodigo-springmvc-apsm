<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
<c:url value="/resources/css" var="cssPath"/>
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<style type="text/css">
 body {
 	padding-top: 60px;
 }
</style>
</head>

<body>
  <nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href='<c:url value="/"/>'>Casa do Código</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">	        
	        <li><a href='<c:url value="/produtos"/>'>Lista de Produtos</a></li>
	        <li><a href='<c:url value="/produtos/form"/>'>Cadastro de Produtos</a></li>
	        <security:authorize access="isAuthenticated()">
		        <li>
			        <a href="#">
			        	<security:authentication property="principal" var="usuario"/>
			        	Usuário: ${usuario.username}
			        </a>
		        </li>
	        </security:authorize>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
  </nav>
  <div class="container">
	<h1>Lista de Produtos</h1>
	<p>${sucesso}</p>
	<p>${falha}</p>
	<table class="table table-bordered table-striped table-hover">
		<tr>
			<th>Título</th>
			<th>Descrição</th>
			<th>Preços</th>
			<th>Páginas</th>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>
					<a href='<c:url value="/produtos/detalhe/${produto.id}"/>'>${produto.titulo}</a>
				</td>
				<td>${produto.descricao}</td>
				<td>${produto.precos }</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>
		
	</table>
  </div>
</body>
</html>