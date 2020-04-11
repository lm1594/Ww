<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>
<!DOCTYPE html>
<html xmlns="https://www.w3.org/1999/xhtml" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="/assets/img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="colorlib">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>오디!오디? :: 테스트</title>

	<link href="https://fonts.googleapis.com/css?family=Oswald:300,500,600|Roboto:400,700" rel="stylesheet">
	<!--
			CSS
			============================================= -->
	<link rel="stylesheet" href="/assets/css/flaticon.css">
	<link rel="stylesheet" href="/assets/css/themify-icons.css">
	<link rel="stylesheet" href="/assets/css/bootstrap.css">
	<link rel="stylesheet" href="/assets/css/magnific-popup.css">
	<link rel="stylesheet" href="/assets/css/nice-select.css">
	<link rel="stylesheet" href="/assets/css/animate.min.css">
	<link rel="stylesheet" href="/assets/css/owl.carousel.css">
	<link rel="stylesheet" href="/assets/css/main.css">
</head>

<body>
	<script src="/assets/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="/assets/js/vendor/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="/assets/js/easing.min.js"></script>
	<script src="/assets/js/hoverIntent.js"></script>
	<script src="/assets/js/superfish.min.js"></script>
	<script src="/assets/js/mn-accordion.js"></script>
	<script src="/assets/js/jquery.ajaxchimp.min.js"></script>
	<script src="/assets/js/jquery.magnific-popup.min.js"></script>
	<script src="/assets/js/owl.carousel.min.js"></script>
	<script src="/assets/js/jquery.nice-select.min.js"></script>
	<script src="/assets/js/isotope.pkgd.min.js"></script>
	<script src="/assets/js/mail-script.js"></script>
	<script src="/assets/js/wow.min.js"></script>
	<script src="/assets/js/main.js"></script>
	
	<!-- Start Preloader Area -->
	<div class="preloader-area">
		<div class="loader-box">
			<div class="loader"></div>
		</div>
	</div>
	<!-- End Preloader Area -->
	
	<!-- Header Area -->
	<%@ include file="/view/layout/header-include.jsp" %>
	
	<!-- Content Area -->
	<tiles:insertAttribute name="body" />

	<!-- Footer Area -->
	<%@ include file="/view/layout/footer-include.jsp" %>

	<!-- ####################### Start Scroll to Top Area ####################### -->
	<div id="back-top">
		<a title="Go to Top" href="#">
			<i class="ti-angle-double-up"></i>
		</a>
	</div>
	<!-- ####################### End Scroll to Top Area ####################### -->
</body>

</html>