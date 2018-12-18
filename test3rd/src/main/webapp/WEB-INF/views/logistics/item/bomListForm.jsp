<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자재명세서</title>
  <jsp:include page="/decorator/head.jsp"/>
  <style type="text/css">
	img { width:700px; height:700px; }
	body {
	background : url(${pageContext.request.contextPath}/scripts/images/background.jpg) no-repeat fixed ;
	background-size : cover; }
  </style>
	<script type="text/javascript">
	var searchStr;
	var feature="width=510px; height=390px; left=550px; top=150px; titlebar=no; status=no";
	$(document).ready(function() {
		$(function() {
			 $( "#BOMSearchTabs, #BOMViewTab" ).tabs();
		});
		$("input").button();
		$("#revBomLi").click(function(){
			$.jgrid.gridUnload('#BomTable');
		});
		$("#bomLi").click(function(){
			$.jgrid.gridUnload('#BomTable');
		});
		$("#searchBomB, #searchRevBomB").click(getBom);
		$("#searchBomT").click(ItemRightList);
		$("#searchRevBomT").click(ItemReverseList);
	});

	function ItemRightList(){
		window.open('${pageContext.request.contextPath}/logistics/item/itemList.do?code=P','코드검색',feature);
	}

	function ItemReverseList(){
		window.open('${pageContext.request.contextPath}/base/CodeListForm.html?code=SP','코드검색',feature);
	}

	function getBom(){//소요량 정전개, 역전개 동적으로 가져오기
		
		var method;
		$.jgrid.gridUnload('#BomTable');


		searchStr="${param.code}";
		

		if(event.srcElement.id=="searchBomB"){
			bom="bom";
		}else if(event.srcElement.id=="searchRevBomB"){
			bom="revBom";
		}
		$('#BomTable').jqGrid({
			url:'${pageContext.request.contextPath}/logistics/item/bom.do',
			datatype:'json',
			postData : { "method" : "findBomList" ,	"searchStr"	: searchStr, "bom": bom	},
			jsonReader:{
				page:'page',
				total:'total',
				root:'list'
			},
			rowNum:15,
			colNames:['레벨','품목코드','품목명','단가','대개','손실률','리드타임'],
			colModel:[
				{name:'level',
					 width:140,
					 align:"center",
					 editabl:true
				},
				{name:'itemCode',
				 width:80,
				 align:"center",
				 editable:true
				},
				{name:'itemName',
				 width:120,
				 align:"center",
				 editable:true
				},
				{name:'unitPrice',
				 width:100,
				 align:"center",
				 editable:false
				},
				{name:'quantity',
				 width:50,
				 align:"center",
				 editable:false
				},
				{name:'lossRate',
				 width:100,
				 align:"center",
				 editable:false
				},
				{name:'leadTime',
				 width:80,
				 align:"center",
				 editable:false
				},
			],
			width : '800',
			height : '300',
		//	cellEdit : true,
		//	cellsubmit : 'clientArray',
			cache:false,
			viewrecords : true,
			rownumbers : true,
			error : function(error1, error2, error3){
				alert(error1+","+error2+","+error3);
			}
		}).trigger("reloadGrid");
	}

	</script>
</head>
<body id="body">
<table style="width:800px">
<tr>
	<td style="height:auto">
		<div id="BOMSearchTabs">
		  <ul>
		    <li id="bomLi"><a href="#BomTab">BOM 정전개</a></li>
		    <li id="revBomLi"><a href="#revBomTab">BOM 역전개</a></li>
		  </ul>
		  <center>
		  <div id="BomTab">
		  	<input type="button" id="searchBomB" value="보기">
		  </div>
		  <div id="revBomTab">
		  	<input type="button" id="searchRevBomB" value="보기">
		  </div>
		  </center>
		</div>
	</td>
</tr>
<tr>
<td  style="height:auto">
	<div id="BOMViewTab">
		<ul>
		    <li><a href="#tableTab">BOM 상세</a></li>
		 </ul>
		 <div id="tableTab">
		 	<center><table id="BomTable"></table></center>
		 </div>
	</div>
</td>
</tr>
</table>
<div id="detailCodeNoD" title="선택하세요"></div>
</body>
</html>