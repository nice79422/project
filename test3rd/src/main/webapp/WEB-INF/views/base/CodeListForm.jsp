<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <jsp:include page="/decorator/head.jsp"/>
</head>
<script>
	$(document).ready(function(){
		codeList();
	});

	function codeList(){
		$("#codeList").jqGrid({
			url : '${pageContext.request.contextPath}/base/basicCode.do?code='+"${param.code}",
			postData : {"code" : "${param.code}",
				"method" : "findCodeList"},
					jsonReader :{
				page : 'page',
				total : 'total',
				root : 'list'			//map에 "list" , list넣으면 그 키 값을 써줘야지 데이터가 뿌려짐!
			},
			beforeProcessing : function(data){			//  그리드 뿌리기전에
				if(data.errorCode < 0){					//-1이면 에러 0이면 상태변화없고 1이면성공
					alert(data.errorMsg);
				}else{
					dataset = data.list;
				}
			},
			rowNum : 20,
			colNames : [
			            '코드', '코드명'
			            ],
			colModel : [
						{
							name : 'detailCode',
							width : 20,
							align : "center",
							editable : false
						},{
							name : 'detailCodeName',
							width : 20,
							align : "center",
							editable : false
						}],
			ondblClickRow : function(id){				//  더블클릭하는거
				index = id -1;					 	//  더블클릭한거 정보를 받고

				var code="${param.code}";
				if(code.match("HS-03"))					//부서
					insertDept();
				else if(code.match("HS-01"))					//직위
					insertJobTitle();
				else if(code.match("HS-02"))					//근무형태
					insertEmploymentTerm();
				else if(code.match("LO-02"))				//판매처
					insertClient();
				else if(code.match("LO-06"))				//구매처
					insertPurchasingPlace();
				else if(code.match("LO-08"))				//완제품
					insertItemGroup();
				else if(code.match("LO-07"))				//모든제품
					insertAllItem();
				else if(code.match("HS-06"))				//창고
					insertwarehouse();
				else if(code.match("HS-05"))				//창고
					insertworkPlace();
				
				
				
				
				else if(code.match("FP"))
					insertItem();
				else if(code.match("SP"))
					insertChildItem();
				else if(code.match("RC"))
					insertRegionNo();
				else if(code.match("BC"))
					insertBusinessCode();
				else if(code.match("PC"))
					insertPurchaseCode();
				else if(code.match("PW"))
					insertProductionLine();
				
			},
			width : 500,
			height : 300,
			viewrecords : true,
			rowList : [10,20,30],
			pager : '#pager',
			caption : "코드리스트",
			multiselect : false,
			cache : false,
			datatype : "json"
		});

		function insertworkPlace(){
			var workPlaceCode=dataset[index].detailCode;
			opener.document.getElementById("workPlaceCode").value=workPlaceCode;
			window.close();									// 담자마자 닫아줌
		}
		
		function insertwarehouse(){
			var warehouseCode=dataset[index].detailCode;
			opener.document.getElementById("warehouseCode").value=warehouseCode;
			window.close();									// 담자마자 닫아줌
		}
		
		function insertDept(){
			var deptCode=dataset[index].detailCode;
			opener.document.getElementById("deptCode").value=deptCode;
			window.close();									// 담자마자 닫아줌
		}

		function insertEmploymentTerm(){
			var employmentTerm=dataset[index].detailCode;
			opener.document.getElementById("employmentTerm").value=employmentTerm;
			window.close();									// 담자마자 닫아줌
		}

		function insertJobTitle(){
			var jobTitle=dataset[index].detailCode;
			opener.document.getElementById("jobTitle").value=jobTitle;
			window.close();
		}

		function insertVenDivision(){
			var division=dataset[index].detailCode;
			opener.document.getElementById("division").value=division;
			opener.document.getElementById("clientCode").value=division+"-";
			window.close();
		}
		function insertClient(){
			var clientCode=dataset[index].detailCode;
			opener.document.getElementById("clientCode").value=clientCode;
			window.close();
		}
		function insertPurchasingPlace(){
			var purchasingPlaceCode=dataset[index].detailCode;
			opener.document.getElementById("purchasingPlaceCode").value=purchasingPlaceCode;
			window.close();
		}
		function insertItemGroup(){
			var itemCode=dataset[index].detailCode;
			opener.document.getElementById("itemGroup").value=itemCode;
			window.close();
		}
		function insertAllItem(){
			var itemCode=dataset[index].detailCode;
			opener.document.getElementById("itemGroup").value=itemCode;
			window.close();
		}
		
		
		
		

		function insertItem(){
			//bom정전개 역전개
			if(opener.document.getElementById("searchBomT")){
				var bomItemCode = dataset[index].detailCode;
					opener.document.getElementById("searchBomT").value = bomItemCode;
				window.close();
			}else if(opener.document.getElementById("itemNo")){
			//orderRegis
			var OrderitemNo = dataset[index].detailCodeNo;
				opener.document.getElementById("itemNo").value = OrderitemNo;
			window.close();
			}else {
				var MpsItemName = dataset[index].detailCodeName;
				var MpsItemNo = dataset[index].detailCodeNo;
					opener.document.getElementById("MpsItemName").value = MpsItemName;
					opener.document.getElementById("MpsItemNo").value = MpsItemNo;
				window.close();
			}

		}
		function insertChildItem(){
			var childItemNo = dataset[index].detailCodeNo;
			opener.document.getElementById("searchRevBomT").value=childItemNo;
			window.close();
		}

		function insertBusinessCode(){
			var businessCode = dataset[index].detailCodeNo;
			opener.document.getElementById("CustomerNo").value = businessCode;
			window.close();
		}
		function insertPurchaseCode(){
			if(opener.document.getElementById("purchaseCode")){
				var purchaseCode = dataset[index].detailCodeNo;
				var purchaseName = dataset[index].detailCodeName;
				opener.document.getElementById("purchaseCode").value = purchaseCode;
				opener.document.getElementById("purchaseName").value = purchaseName;
				window.close();
			}
		}
		function insertProductionLine(){
			var ProductionLine = dataset[index].detailCode;
			opener.document.getElementById("lineT").value = ProductionLine;
			window.close();
		}
		function insertWorkplace(){
			var workplace = dataset[index].detailCode;
			opener.document.getElementById("workPlaceCode").value = workplace;
			window.close();
		}

	}

</script>
<body>
<table id="codeList"></table>
<div id="pager"></div>
</body>

</html>