<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">


     
  </style>
<title>직원리스트</title>
<script>
$(document).ready(function(){

$("#searchButton").click(gridReload);
$.jgrid.gridUnload("#empList");
$("#empList").jqGrid({
		url : '${pageContext.request.contextPath}/emp/emp.do',
		postData : {"method" : "findEmpList"},
		datatype : 'json',
		jsonReader :{
			page : 'page',
			total : 'total',
			root : 'list'
		},
		rowNum : 5,
		colNames : [
		            '사원코드', '사원이름 ', '부서번호', '직급'
		            ],
		colModel : [
		{
			name : 'empCode',
			width : 20,
			align : "center",
			editable : false
		},{
			name : 'empName',
			width : 30,
			align : "center",
			editable : false
		},{
			name : 'deptCode',
			width : 30,
			align : "center",
			editable : false
		},{
			name : 'jobTitle',
			width : 30,
			align : "center",
			editable : false
		}

		],
		width : '700',
		height : '350',
		ondblClickRow: function(rowid) {
            empCode=$(this).getCell(rowid,"empCode");
            detailEmpInfo(empCode);
          },
		viewrecords : true,
		rowList : [3, 6, 9],
		pager : '#pager',
		caption : "직원리스트",
		multiselect : false,
		cache : false,
		error : function(error1, error2, error3){
			alert(error1+","+error2+","+error3);
		}
	});
});

 function detailEmpInfo(id){
	features = "width=800px; height=650px; titlebar=no; status=no";
  window.open("${pageContext.request.contextPath}/emp/empDetailForm.html?empCode="+empCode,"직원상세정보",features);
}

 function gridReload(){
	 	var empCode = $("#empCode").val();
	 	var empName = $("#empName").val();
	 	$("#empList").jqGrid('setGridParam', {
	 		url:"${pageContext.request.contextPath}/emp/emp.do",
			datatype : 'json',
	 		postData : { "empCode" : empCode ,
	 					"empName"	: empName,
	 					"method" : "findEmpList"
	 					}
			}).trigger("reloadGrid");

	}

</script>
</head>
<body>
<center>
		<div class="h">검색조건:
<!-- 			<input type="checkbox" id="autosearch"
				onclick="enableAutosubmit(this.checked)"> Enable Autosearch -->
			사원코드 <input type="text" id="empCode" />
			사원이름 <input type="text" id="empName" />
			<input type="submit" id="searchButton" value="검색">

		</div>

		<br />
	<table id="empList"></table>
	<div id="pager"></div>
</center>

</body>
</html>