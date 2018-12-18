<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세코드보기</title>
  <jsp:include page="/decorator/head.jsp"/>
    <style type="text/css">
	img { width:700px; height:700px; }
	body {
	background : url(${pageContext.request.contextPath}/scripts/images/background.jpg) no-repeat fixed ;
	background-size : cover; }
  </style>
</head>
<script>
$(document).ready(function(){
	var code= "${param.code}";
	/* alert("기본코드 "+code+"의 상세코드 정보입니다."); */
	 $("#detailCodeList").jqGrid({
			url : '${pageContext.request.contextPath}/base/basicCode.do',
			postData : { "code" : code , "method" : "findCodeList"},
			datatype : 'json',
			jsonReader :{
				page : 'page',
				total : 'total',
				root : 'list'
			},
			rowNum : 6,
			colNames : [
			           '기본코드', '상세 코드', '상세코드명 '
			            ],
			colModel : [
				{
					name : 'basicCode',
					width : 20,
					align : "center",
					editable : false
				},
				{
					name : 'detailCode',
					width : 20,
					align : "center",
					editable : false
				},
				{
					name : 'detailCodeName',
					width : 30,
					align : "center",
					editable : false
				}
				],
				width : '800',
				height : '350',
				ondblClickRow: function(rowid) {
	            basicCode=$(this).getCell(rowid,"detailCode");
	            basicCodeInfo(basicCode);
	    	    },
				viewrecords : true,
				rowList : [3, 6, 9],
				pager : '#pager',
				caption : "상세 코드 리스트",
				multiselect : false,
				cache : false,
				success : function(data) {
				alert(data.empBean);},
				error : function(error1, error2, error3){
				alert(error1+","+error2+","+error3);
			}
		});
	 $("#detailCodeList").navGrid("#pager",
			 {add:true,del:true,edit:true,search:false,refresh:false});
	 });


</script>
<body>
<center>
	<table id="detailCodeList"></table>
	<div id="pager"></div>
</center>
</body>
</html>