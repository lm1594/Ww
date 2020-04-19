<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/layout/taglib-include.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		
	});

</script>

<c:choose>
	<c:when test="${!empty list}">
		<c:forEach var="rvo" items="${list}" varStatus="st">
			<h3 class="mb-30">${rvo.mntiname}</h3>
			<div class="row">
				<div class="col-md-3">
					<c:if test='${!empty rvo.imgfilename}'>
						<img style="width: 200px; height:200px;" src="http://${rvo.imgfilename}" alt="산 이미지" class="img-fluid">
					</c:if>
					<c:if test='${empty rvo.imgfilename}'>
						<img style="width: 100px; height:100px;" src="/assets/img/no-image.gif" alt="산 이미지" class="img-fluid">
					</c:if>
				</div>
				<div class="col-md-9 mt-sm-20 left-align-p">
					<c:if test='${rvo.mntidetails eq "( - )"}'>
						<p>정보없음</p>
					</c:if>
					<c:if test='${rvo.mntidetails ne "( - )"}'>
						<p>${rvo.mntidetails}</p>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<h3 class="mb-30">조회된 목록이 없습니다.</h3>
	</c:otherwise>
</c:choose>