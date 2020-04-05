<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<!-- 산정보 리스트 -->
<div class="inner">
	<h2>리스트</h2>
	<p>This is an <strong>Items</strong> element, and it's basically just a grid for organizing items of various types. You can customize its <span class="demo-controls"><span class="property" data-name="style"><a href="#" class="title tooltip">style</a><span class="classes"><span data-class="style1" class="active">, style1</span><span data-class="style2">, style2</span><span data-class="style3">, style3</span></span> </span><span class="property active" data-name="size" data-requires="style1"><span>and </span><a href="#" class="title">size</a><span class="classes"><span data-class="small">, small</span><span data-class="medium" class="default active">, medium</span><span data-class="big">, big</span></span></span><span class="property" data-name="size" data-requires="style2"><span>and </span><a href="#" class="title">size</a><span class="classes"><span data-class="small">, small</span><span data-class="medium" class="default">, medium</span><span data-class="big">, big</span></span></span><span class="property" data-name="size" data-requires="style3"><span>and </span><a href="#" class="title">size</a><span class="classes"><span data-class="small">, small</span><span data-class="medium" class="default">, medium</span><span data-class="big">, big</span></span></span></span>, as well as assign it an optional <code>onload</code> or <code>onscroll</code> transition modifier (<a href="#reference-items">details</a>).</p>
	<div class="items style1 medium onscroll-fade-in">
		<section><div class="inner">
			<span class="icon style2 major fa-gem"></span>
			<h3>One</h3>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi dui turpis, cursus eget orci amet aliquam congue semper. Etiam eget ultrices risus nec tempor elit.</p>
		</div></section>
	</div>
</div>