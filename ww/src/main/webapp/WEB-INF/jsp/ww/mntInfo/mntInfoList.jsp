<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
	});
	
	function fnListPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#numOfRows").val('10');
		
		$.ajax({
			url: "/ww/mntInfo/mntInfoListAjax.do",
			type: "post",
			data: $("#form1").serialize(),
			dataType: "html",
			success: function(result) {
				$("#divList").html(result);
			},
			error: function(request,status,error) {
				alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도해주십시오.");
			}
			
		});
	}
	
	function fnNumOfRows(numOfRows) {
		$("#numOfRows").val(numOfRows);
		fnListPage(1);
	}
</script>

<!-- 메인페이지 (산정보 목록 조회화면) -->
<form id="form1" name="form1">
	<input type="hidden" id="pageNo" name="pageNo" value="${pvo.pageNo}" />
	<input type="hidden" id="numOfRows" name="numOfRows" value="${pvo.numOfRows}" />
	
	<section class="wrapper style1 align-center">
		<div class="content align-center">
			<span class="image fit">
				<img src="/images/ww/ww_image.png" ondblclick="location.reload();" alt="오디!오디?" />
			</span>
			<h1>산 이름 조회하기</h1>
			<ul class="actions stacked">
				<div class="fields">
					<div class="field" style="margin:5px;">
						<input type="text" name="mntiname" id="mntiname" value="">
					</div>
				</div>
				<li>
					<a href="javascript:void(0);" class="button primary icon solid fa-search" onclick="fnListPage(1);">
						<font style="vertical-align: inherit;">
							<font style="vertical-align: inherit;">조회</font>
						</font>
					</a>
					<a href="javascript:void(0);" class="button primary icon solid fa-list" onclick="fnNumOfRows(99999);">
						<font style="vertical-align: inherit;">
							<font style="vertical-align: inherit;">전체조회</font>
						</font>
					</a>
				</li>
			</ul>
		</div>
	</section>
</form>

<!-- 산정보 리스트 -->
<section class="wrapper style1 align-center">
	<div id="divList"></div>
</section>