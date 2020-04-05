<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	function fnPageMove(pageName) {
		location.href='/ww/test/'+pageName+'.do';
	}
</script>
<section class="wrapper style1 align-center">
	<div class="inner medium">
		<h2>TEST7</h2>
		<form method="post" action="#">
			<div class="fields">
				<div class="field half">
					<label for="name">Name</label>
					<input type="text" name="name" id="name" value="" />
				</div>
				<div class="field half">
					<label for="email">Email</label>
					<input type="email" name="email" id="email" value="" />
				</div>
				<div class="field">
					<label for="message">Message</label>
					<textarea name="message" id="message" rows="6"></textarea>
				</div>
			</div>
			<ul class="actions special">
				<li><input type="submit" name="submit" id="submit" value="Send Message" /></li>
			</ul>
		</form>
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
</section>