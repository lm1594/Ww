<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>

<!-- 메인페이지 -->
<form id="form1" name="form1">
	<!-- start banner Area -->
    <!-- <section class="banner-area relative">
      <div class="container">
        <div class="row d-flex align-items-center justify-content-center">
          <div class="about-content col-lg-12">
            <h1 class="text-white">Tourist Spot</h1>
            <p>한국의 아름다운 장소</p>
          </div>
        </div>
      </div>
    </section> -->
    <!-- End banner Area -->

	<!-- Start Work Area Area -->
	<section class="work-area section-gap-top" id="work">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title text-center">
						<h3>Infomation</h3>
						<h2><span>우리나라</span> 여행정보</h2>
					</div>
				</div>

				<div class="col-lg-12 d-none">
					<div class="filters">
						<ul>
							<li class="active" data-filter=".all"></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="filters-content">
				<div class="row grid">
					<div class="col-lg-4 col-md-6 grid-sizer"></div>

					<div class="col-lg-8 col-md-12 grid-item all wow fadeIn" data-wow-duration="2s" data-wow-delay="0s">
						<div class="single-work">
							<div class="relative">
								<div class="thumb">
									<img class="image img-fluid" src="/assets/img/work/w1.jpg" alt="">
								</div>
								<div class="middle">
									<h4>Korea Mountain 산</h4>
									<div class="cat">More Infomation</div>
								</div>
								<a class="overlay" href="/ww/mntInfo/mntInfoList.do"></a>
							</div>
						</div>
					</div>

					<div class="col-lg-4 grid-item col-md-6 all wow fadeIn" data-wow-duration="2s" data-wow-delay="0.4s">
						<div class="single-work">
							<div class="relative">
								<div class="thumb">
									<img class="image img-fluid" src="/assets/img/work/w2.jpg" alt="">
								</div>
								<div class="middle">
									<h4>Korea Ocean 바다</h4>
									<div class="cat">More Infomation</div>
								</div>
								<a class="overlay" href="project-details.html"></a>
							</div>
						</div>
					</div>

					<div class="col-lg-4 grid-item col-md-6 all wow fadeIn" data-wow-duration="2s" data-wow-delay="0.4s">
						<div class="single-work">
							<div class="relative">
								<div class="thumb">
									<img class="image img-fluid" src="/assets/img/work/w3.jpg" alt="">
								</div>
								<div class="middle">
									<h4>Korea Tranditional 전통</h4>
									<div class="cat">More Infomation</div>
								</div>
								<a class="overlay" href="project-details.html"></a>
							</div>
						</div>
					</div>

					<div class="col-lg-8 grid-item col-md-12 all wow fadeIn" data-wow-duration="2s" data-wow-delay="0.4s">
						<div class="single-work">
							<div class="relative">
								<div class="thumb">
									<img class="image img-fluid" src="/assets/img/work/w4.jpg" alt="">
								</div>
								<div class="middle">
									<h4>Korea Culture 문화</h4>
									<div class="cat">More Infomation</div>
								</div>
								<a class="overlay" href="project-details.html"></a>
							</div>
						</div>
					</div>

					<!-- <div class="col-lg-4 text-center grid-item col-md-12 all creative wow fadeIn" data-wow-duration="2s">
						<a href="#" class="primary-btn" data-text="View More">
							<span>V</span>
							<span>i</span>
							<span>e</span>
							<span>w</span>
							<span> </span>
							<span>M</span>
							<span>o</span>
							<span>r</span>
							<span>e</span>
						</a>
					</div> -->

				</div>
			</div>
		</div>
	</section>
	<!-- End Work Area Area -->
</form>