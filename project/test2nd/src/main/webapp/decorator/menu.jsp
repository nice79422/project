<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.empCode ne null}">
	<ul class="nav">

		<li><a href="${pageContext.request.contextPath}/emp/login.html">홈</a></li>

		<li class="button-dropdown"><a href="#" class="dropdown-toggle">관리 <span>▼</span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="${pageContext.request.contextPath}/base/baseCodeListForm.html">코드</a></li>
				<li><a href="${pageContext.request.contextPath}/logistics/item/itemListForm.html">자재명세서(BOM)</a></li>
				<li><a href="${pageContext.request.contextPath}/base/stockListForm.html">재고 </a></li>
			</ul></li>
		<li class="button-dropdown"><a href="#" class="dropdown-toggle">인사 관리<span>▼</span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="${pageContext.request.contextPath}/emp/empAddForm.html">사원등록</a></li>
				<li><a href="${pageContext.request.contextPath}/emp/empListForm.html">사원조회</a></li>
			</ul></li>
		<li class="button-dropdown"><a href="#" class="dropdown-toggle">영업 관리<span>▼</span>
		</a>
			 <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/logistics/business/estimateForm.html">견적</a></li>
                    <li><a href="${pageContext.request.contextPath}/logistics/business/contractListForm.html">수주</a></li>
                    <li><a href="${pageContext.request.contextPath}/logistics/business/clientListForm.html">납품처</a></li> 
                </ul></li>
        <li class="button-dropdown"><a href="#" class="dropdown-toggle">생산 관리<span>▼</span>
        </a>	
			 <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/logistics/product/mpsForm.html">주 생산 계획(MPS)</a></li>
                    <li><a href="${pageContext.request.contextPath}/logistics/product/mrpForm.html">소요량 전개/취합(MRP)</a></li>
                </ul></li>
        <li class="button-dropdown"><a href="#" class="dropdown-toggle">구매 관리 <span>▼</span>
        </a>	
			 <ul class="dropdown-menu">
			 <li><a href="${pageContext.request.contextPath}/logistics/purchase/purchaseForm.html">발주</a></li>
             <li><a href="${pageContext.request.contextPath}/logistics/purchase/purchasingPlaceListForm.html">구매처</a></li>
		</ul></li>
	</ul>
</c:if>


