<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="titulo" required="true"%>
<%@ attribute name="bodyClass" required="false"%>
<%@ attribute name="extraScripts" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	
	<title>${titulo} - Casa do Código</title>
	<c:url value="/resources/css" var="cssPath"/>
	<link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
	<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
	<link href="${cssPath}/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
	<link href="${cssPath}/fonts.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/fontello.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${cssPath}/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
    <link href="${cssPath}/produtos.css" rel="stylesheet" type="text/css"  media="all"  />
	<link href="${cssPath}/book-collection.css" rel="stylesheet" type="text/css" media="all" />	
	<link rel="canonical" href="http://www.casadocodigo.com.br/" />
</head>
<body class="${bodyClass}">

	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	
	<jsp:doBody />

	<%@ include file="/WEB-INF/views/rodape.jsp" %>

	<jsp:invoke fragment="extraScripts"></jsp:invoke>

</body>
</html>