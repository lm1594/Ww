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
	<div class="inner">
		<h2>TEST5</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id ante sed ex pharetra lacinia sit amet vel massa. Donec facilisis laoreet nulla eu bibendum. Donec ut ex risus. Fusce lorem lectus, pharetra pretium massa et, hendrerit vestibulum odio lorem ipsum.</p>
	</div>

	<!-- Gallery -->
		<div class="gallery style2 medium lightbox onscroll-fade-in">
			<article>
				<a href="/images/gallery/fulls/01.jpg" class="image">
					<img src="/images/gallery/thumbs/01.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Ipsum Dolor</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/02.jpg" class="image">
					<img src="/images/gallery/thumbs/02.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Feugiat Lorem</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/03.jpg" class="image">
					<img src="/images/gallery/thumbs/03.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Magna Amet</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/04.jpg" class="image">
					<img src="/images/gallery/thumbs/04.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Sed Tempus</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/05.jpg" class="image">
					<img src="/images/gallery/thumbs/05.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Ultrices Magna</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/06.jpg" class="image">
					<img src="/images/gallery/thumbs/06.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Sed Tempus</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/07.jpg" class="image">
					<img src="/images/gallery/thumbs/07.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Ipsum Lorem</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/08.jpg" class="image">
					<img src="/images/gallery/thumbs/08.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Magna Risus</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/09.jpg" class="image">
					<img src="/images/gallery/thumbs/09.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Tempus Dolor</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/10.jpg" class="image">
					<img src="/images/gallery/thumbs/10.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Sed Etiam</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/11.jpg" class="image">
					<img src="/images/gallery/thumbs/11.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Magna Lorem</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
			<article>
				<a href="/images/gallery/fulls/12.jpg" class="image">
					<img src="/images/gallery/thumbs/12.jpg" alt="" />
				</a>
				<div class="caption">
					<h3>Ipsum Dolor</h3>
					<p>Lorem ipsum dolor amet, consectetur magna etiam elit. Etiam sed ultrices.</p>
					<ul class="actions fixed">
						<li><span class="button small">Details</span></li>
					</ul>
				</div>
			</article>
		</div>
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

</section>