<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	function fnPageMove(pageName) {
		location.href='/ww/test/'+pageName+'.do';
	}
</script>

<!-- One -->
<section class="banner style1 orient-left content-align-left image-position-right fullscreen onload-image-fade-in onload-content-fade-right">
	<div class="content">
		<h1>TEST1</h1>
		<p class="major">A (modular, highly tweakable) responsive one-page template designed by <a href="https://html5up.net">HTML5 UP</a> and released for free under the <a href="https://html5up.net/license">Creative Commons</a>.</p>
		<ul class="actions stacked">
			<li><a href="#first" class="button big wide smooth-scroll-middle">Get Started</a></li>
		</ul>
		<hr/>
		<ul class="actions stacked">
			<li><a href="javascript:void(0);" onclick="fnPageMove('test1');" class="button big wide smooth-scroll-middle">TEST1</a></li>
			<li><a href="javascript:void(0);" onclick="fnPageMove('test2');" class="button big wide smooth-scroll-middle">TEST2</a></li>
			<li><a href="javascript:void(0);" onclick="fnPageMove('test3');" class="button big wide smooth-scroll-middle">TEST3</a></li>
			<li><a href="javascript:void(0);" onclick="fnPageMove('test4');" class="button big wide smooth-scroll-middle">TEST4</a></li>
			<li><a href="javascript:void(0);" onclick="fnPageMove('test5');" class="button big wide smooth-scroll-middle">TEST5</a></li>
			<li><a href="javascript:void(0);" onclick="fnPageMove('test6');" class="button big wide smooth-scroll-middle">TEST6</a></li>
			<li><a href="javascript:void(0);" onclick="fnPageMove('test7');" class="button big wide smooth-scroll-middle">TEST7</a></li>
		</ul>
	</div>
	<div class="image">
		<img src="/images/banner.jpg" alt="" />
	</div>
</section>