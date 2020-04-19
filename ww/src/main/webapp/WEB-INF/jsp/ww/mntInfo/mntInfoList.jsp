<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("input[type=text]").keydown(function(e) {
			if(e.keyCode == 13) {
				fnList(1);
			}
		});
	});
	
	function fnList(){
		
		// 검색어 필수입력
		if(util_IsNullOrBlank($("#mntiname").val())) {
			alert("검색어를 입력해주세요.");
			return;
		}
		
		$.ajax({
			url: "/ww/mntInfo/mntInfoListAjax.do",
			data: $("#form1").serialize(),
			dataType: "html",
			type: "post",
			success: function(result) {
				$("#divList").html(result);
			},
			error: function(result) {
				alert("서버와의 통신이 원활하지 않습니다. \n잠시 후 다시 시도해주십시오.");
			}
		});
	}
</script>

<!-- 메인페이지 -->
<form id="form1" name="form1" onsubmit="return false">
	<input type="hidden" id="pageNo" name="pageNo" value="${pvo.pageNo}" />
	<input type="hidden" id="numOfRows" name="numOfRows" value="${pvo.numOfRows}" />
	
	<div class="container">
		<!-- 검색 -->
		<div class="section-top-border text-center">
			<div class="row">
				<div class="col-lg-8 col-md-8">
					<div class="mt-10">
						<input type="text" id="mntiname" class="single-input" name="mntiname" autocomplete="off" placeholder="산이름 검색" onfocus="this.placeholder = ''" onblur="this.placeholder = '산이름 검색'"
						 required >
					</div>
				</div>
				<div class="col-lg-4 col-md-4">
					<div class="button-group-area">
						<a href="javascript:void(1);" class="genric-btn success circle" onclick="fnList();">Search</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 산정보 목록 -->
		<div class="section-top-border">
			<div id="divList"></div>
		</div>
	</div>
</form>