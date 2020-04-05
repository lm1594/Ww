<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	function fnPageMove(pageName) {
		location.href='/ww/test/'+pageName+'.do';
	}
</script>
<section class="spotlight style1 orient-right content-align-left image-position-center onscroll-image-fade-in" id="first">
	<div class="content">
		<h2>TEST2</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id ante sed ex pharetra lacinia sit amet vel massa. Donec facilisis laoreet nulla eu bibendum. Donec ut ex risus. Fusce lorem lectus, pharetra pretium massa et, hendrerit vestibulum odio lorem ipsum dolor sit amet.</p>
		<ul class="actions stacked">
			<li><a href="#" class="button">Learn More</a></li>
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
		<img src="/images/spotlight01.jpg" alt="" />
	</div>
</section>