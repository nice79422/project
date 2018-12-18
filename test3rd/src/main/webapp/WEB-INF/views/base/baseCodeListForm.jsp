<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>기본 코드</title>
<script>
$(document).ready(function(){

 $("#codeList").jqGrid({
		url : '${pageContext.request.contextPath}/base/basicCode.do',
		postData : {"method" : "findCodeList"},
		datatype : 'json',
		jsonReader :{
			page : 'page',
			total : 'total',
			root : 'list'
		},
		rowNum : 6,
		colNames : [
		            '기본 코드', '기본코드명 '
		            ],
		colModel : [
		{
			name : 'basicCode',
			width : 20,
			align : "center",
			editable : false
		},{
			name : 'basicCodeName',
			width : 30,
			align : "center",
			editable : false
		}
		],
		width : '700',
		height : '400',
		ondblClickRow: function(rowid) { //코드리스트에서 더블클릭 이벤트
            basicCode=$(this).getCell(rowid,"basicCode");
            basicCodeInfo(basicCode);
          },
		viewrecords : true,
		rowList : [3, 6, 9],
		pager : '#pager',
		caption : "기본 코드 리스트",
		multiselect : false,
		cache : false,
		error : function(error1, error2, error3){
			alert(error1+","+error2+","+error3);
		}
	});
});

 function basicCodeInfo(basicCode){
	 	  features = "width=900px; height=450px; titlebar=no; status=no";
		  window.open("${pageContext.request.contextPath}/base/detailCodeListForm.html?code="+basicCode,"직원상세정보",features);
}

</script>
</head>
<body>
<center>
	<table id="codeList"></table>
	<div id="pager"></div>
</center>
</body>
</html>