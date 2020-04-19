<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>
<!-- Start Header Area -->
<header id="header">
	<div class="container main-menu">
		<div class="row align-items-center d-flex">
			<div id="logo">
				<a href="/ww/main/main.do"><img src="/assets/img/logo.png" alt="" title="" /></a>
			</div>
			<nav id="nav-menu-container">
				<ul class="nav-menu">
					<li class=""><a class="active" href="/ww/main/main.do">Home</a></li>
					<li><a href="javascript:void(0);" onclick="location.href='/ww/mntInfo/mntInfoList.do'">산</a></li>
					<li><a href="javascript:void(0);" onclick="alert('준비중입니다');">바다</a></li>
					<li class="menu-has-children"><a href="#">전통</a>
						<ul>
							<li><a href="javascript:void(0);" onclick="alert('준비중입니다');">Elements</a></li>
							<li><a href="javascript:void(0);" onclick="alert('준비중입니다');"">Contact</a></li>
							<li><a href="javascript:void(0);" onclick="alert('준비중입니다');"">Pricing</a></li>
							<li><a href="javascript:void(0);" onclick="alert('준비중입니다');"">Project Details</a></li>
						</ul>
					</li>
					<li class="menu-has-children"><a href="">문화</a>
						<ul>
							<li><a href="javascript:void(0);" onclick="alert('준비중입니다');"">Blog Home</a></li>
							<li><a href="javascript:void(0);" onclick="alert('준비중입니다');"">Blog Single</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<!-- End Header Area -->