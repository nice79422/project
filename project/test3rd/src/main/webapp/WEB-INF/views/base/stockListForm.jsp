<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>재고 관리</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
 <style type="text/css">
	input[type="text"] {
		border-radius : 5px;
		font-size : 20px;
		height : 20px;
		margin-left : 10px;
		margin-right : 10px;
		text-align : center;
	}
	.letterStyle{
		font-size : 2em;
	}
	.ui-datepicker{ font-size: 20px; width: 300px; }
	 #gridTable{
		height : 300px;
	}

</style>
<script>
	$(document).ready(function(){
		$("#tabs").tabs();
		$("#startDate").datepicker({dateFormat: 'yy-mm-dd'});
		$("#endDate").datepicker({dateFormat: 'yy-mm-dd'});
		$("#dateStockListBt").button().click(dateStockList);
		$("#itemStockListBt").button().click(itemStockList);
		$("#itemGroup").click(ItemGroupList);
	});


	function ItemGroupList(){
		var feature = "width=510px; height=390px; titlebar=no; status=no";
		window.open('${pageContext.request.contextPath}/base/CodeListForm.html?code=LO-07','코드검색',feature);
	}


	function itemStockList(){
		$.jgrid.gridUnload("#itemStockListGrid");
		$("#itemStockListGrid").jqGrid({
			url : '${pageContext.request.contextPath}/logistics/item/stock.do',
			postData : {"method" : "findItemStockList", "item" : $("#itemGroup").val()},
			datatype:'json',
			jsonReader : {page:"page", root:"list", total:"total"},
			colNames:['재고코드','품목코드','입고수량','입고날짜','입고예정수량','입고예정날짜','출고수량','출고날짜','출고예정수량','출고예정날짜','재고수량','생산여부','창고코드'],
			colModel:[
					{name:"stockCode", width:35, align:"center", editable : true},
					{name:"itemCode", width:35, align:"center", editable : true},
					{name:"inputAmount", width:35, align:"center", editable : true},
					{name:"inputDate", width:35, align:"center", editable : true},
					{name:"inputExpecAmount", width:40, align:"center", editable : true},
					{name:"inputExpecDate", width:40, align:"center", editable : true},
					{name:"outputAmount", width:35, align:"center", editable : true},
					{name:"outputDate", width:35, align:"center", editable : true},
					{name:"outputExpecAmount", width:40, align:"center", editable : true},
					{name:"outputExpecDate", width:40, align:"center", editable : true},
					{name:"stockAmount", width:35, align:"center", editable : true},
					{name:"produceStatus", width:35, align:"center", editable : true},
					{name:"warehouseCode", width:35, align:"center", editable : true}
			         ],
			width:850,
			height : 260,
			editurl: "clientArray",
			viewrecords:true,
			caption : "재고리스트"
		});

		$("#itemStockListGrid").navGrid("#pager",{
			add:true, del:true, edit:true, search:false, refresh:false
		});
	}

	function dateStockList(){
		$.jgrid.gridUnload("#dateStockListGrid");
		$("#dateStockListGrid").jqGrid({
			url : '${pageContext.request.contextPath}/logistics/item/stock.do',
			postData : 	{"method" : "findDateStockList", "start" : $("#startDate").val(), "end" : $("#endDate").val()},
			datatype:'json',
			jsonReader : {page:"page", root:"list", total:"total"},
			colNames:['재고코드','품목코드','입고수량','입고날짜','입고예정수량','입고예정날짜','출고수량','출고날짜','출고예정수량','출고예정날짜','재고수량','생산여부','창고코드'],
			colModel:[
					{name:"stockCode", width:35, align:"center", editable : true},
					{name:"itemCode", width:35, align:"center", editable : true},
					{name:"inputAmount", width:35, align:"center", editable : true},
					{name:"inputDate", width:35, align:"center", editable : true},
					{name:"inputExpecAmount", width:40, align:"center", editable : true},
					{name:"inputExpecDate", width:40, align:"center", editable : true},
					{name:"outputAmount", width:35, align:"center", editable : true},
					{name:"outputDate", width:35, align:"center", editable : true},
					{name:"outputExpecAmount", width:40, align:"center", editable : true},
					{name:"outputExpecDate", width:40, align:"center", editable : true},
					{name:"stockAmount", width:35, align:"center", editable : true},
					{name:"produceStatus", width:35, align:"center", editable : true},
					{name:"warehouseCode", width:35, align:"center", editable : true}
			         ],
			width:850,
			height : 260,
			editurl: "clientArray",
			viewrecords:true,
			caption : "재고리스트"
		});

		$("#dateStockListGrid").navGrid("#pager",{
			add:true, del:true, edit:true, search:false, refresh:false
		});
	}
	
	
</script>
</head>
<body>
<center>
	<div id="tabs" style="float: center; width: 700px; height: 440px; ">
		<ul>
			<li><a href="#itemStockList">품목별 재고조회</a></li>
			<li><a href="#dateStockList">기간별 재고조회</a></li>
		</ul>


		<div id="itemStockList">
			<table>
				<tr>
					<td style="text-align : center;">
						<table style="display : inline;">
							<tr>
								<td><input type="text" id="itemGroup" value="품목군을 선택해주세요"></td>
								<td><input type="button" value="조회" id="itemStockListBt"></td>
							</tr>
						</table>
 					</td>
				</tr>
				<tr>
 					<td id="gridTable">
						<table id="itemStockListGrid"></table>
						<div id="pager"></div>
 			 		</td>
 				</tr>
			</table>
		</div>



		<div id="dateStockList">
			<table>
				<tr>
					<td style="text-align : center;">
						<table style="display : inline;">
							<tr>
								<td><input type="text" id="startDate"></td>
								<td class="letterStyle">~</td>
								<td><input type="text" id="endDate"></td>
								<td><input type="button" value="조회" id="dateStockListBt"></td>
							</tr>
						</table>
 					</td>
 				</tr>
				<tr>
 					<td id="gridTable">
					<table id="dateStockListGrid"></table>
					<div id="pager"></div>
 			 		</td>
 				</tr>
			</table>
		</div>
	</div>
</center>
</body>
</html>