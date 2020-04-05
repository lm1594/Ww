<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<!DOCTYPE html>
<html xmlns="https://www.w3.org/1999/xhtml">
<head>
	<title>오디?오디! :: 가자</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/assets/css/main.css" />
	<noscript><link rel="stylesheet" href="/assets/css/noscript.css" /></noscript>
</head>

<body class="is-preload">
	<!-- Scripts -->
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/jquery.scrollex.min.js"></script>
	<script src="/assets/js/jquery.scrolly.min.js"></script>
	<script src="/assets/js/browser.min.js"></script>
	<script src="/assets/js/breakpoints.min.js"></script>
	<script src="/assets/js/util.js"></script>
	<script src="/assets/js/main.js"></script>
	
	<!-- Wrapper -->
	<div id="wrapper" class="divided">
		<tiles:insertAttribute name="body" />
		
		<!-- Footer -->
		<%@ include file="/view/layout/footer-include.jsp" %>
	</div>
	
	
</body>
</html>