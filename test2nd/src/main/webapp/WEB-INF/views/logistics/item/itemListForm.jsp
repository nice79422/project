<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>품목 조회</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
<script>
$(document).ready(function(){
	itemList();
	$("#searchButton").click(gridReload);
	$("#addItemB").click(addItem);
	
});
 
	
function itemList(){
 $.jgrid.gridUnload('#itemList');
 $("#itemList").jqGrid({
    url: '${pageContext.request.contextPath}/logistics/item/item.do',
    postData : {"method" : "findItemList"},
 	datatype: "json",
 	width : '800',
	height : '350',
	jsonReader :{
		page : 'page',
		total : 'total',
		root : 'list'
	},
	colNames:['품목코드','품목명','단위','표준단가', '완제품여부','구매처코드'],
    	colModel : [
    	      {
    			name : 'itemCode',
    			width : 50,
    			align : "center",
    			editable : true
    		},{
    			name : 'itemName',
    			width : 50,
    			align : "center",
    			editable : true
    		},{
    			name : 'unit',
    			width : 30,
    			align : "center",
    			editable : true
    		},{
    			name : 'unitPrice',
    			width : 30,
    			align : "center",
    			editable : true
    		},
    		{
    			name : 'productStatus',
    			width : 30,
    			align : "center",
    			editable : true
    		},
    		{
    			name : 'purchasingPlaceCode',
    			width : 30,
    			align : "center",
    			editable : true
    		}
    		],
    	rowNum:10,
    	rowList:[10,20,30],
    	mtype: "POST",
    	pager: '#pager',
    	viewrecords: true,
   		cellEdit:false,
	    sortorder: "asc",
	    caption : "품목 리스트",
	    cache : false,
		error : function(error1, error2, error3){
			alert(error1+","+error2+","+error3);
		},
		ondblClickRow : function(rowid){ //  더블클릭하는거
		//  더블클릭한거 정보를 받고
			/* alert("품목코드 "+$(this).getCell(rowid,"itemCode")+"의  BOM입니다. "); */
			bomInfo($(this).getCell(rowid,"itemCode"));


		}
 })
};


function gridReload(){
 	var itemCode = $("#itemCode").val();
 	var itemName = $("#itemName").val();
 	$("#itemList").jqGrid('setGridParam', {
 		url:"${pageContext.request.contextPath}/logistics/item/item.do",
		datatype : 'json',
 		postData : { "itemCode" : itemCode,
 					"itemName"	: itemName,
 					"method" : "findItemList"
 					}
		}).trigger("reloadGrid");

}

 function bomInfo(itemCode){
	features = "width=900px; height=600px; titlebar=no; status=no";
  window.open("${pageContext.request.contextPath}/logistics/item/bomListForm.html?code="+itemCode,"제품상세정보",features);
}
 
 function addItem(){
	  features = "width=500px; height=500px; titlebar=no; status=no";
	  window.open("${pageContext.request.contextPath}/logistics/item/addItemForm.html","품목추가",features);
}
 
 

</script>
</head>
<body>
<center>
		<div class="h">검색조건:
<!-- 			<input type="checkbox" id="autosearch"
				onclick="enableAutosubmit(this.checked)"> Enable Autosearch -->
			 제품 코드 <input type="text" id="itemCode" />
			제품명 <input type="text" id="itemName" />
			<input type="submit" id="searchButton" value="검색">&nbsp;&nbsp;
			<input type="button" id="addItemB" value="품목추가">
			
		</div>

		<br />
			<table id="itemList"></table>
	<div id="pager"></div>
	</center>




</body>
</html>