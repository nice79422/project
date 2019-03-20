<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>발주 관리</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>

<script>
var row;
var lastselect;
var emptyMrpBean;
var features = "width=390px; height=340px; left=550px; top=150px; titlebar=no; status=no";
	$(document).ready(function() {
		$("#tabs").tabs();														//UI
		$("#purchasingPlaceCode").click(purchasingPlaceCodeSearch);			//거래처코드 받아오기
		$("#findPurchaseList").click(purchaseListSearch);		//구매계획 리스트
		$("#savePurchase").click(savePurchaseFunc);
		$("#comPurchaseListTableT").click(comPurchaseList);


	});
	function MrpBean(){
		 this.itemBean=new ItemBean();
		 this.itemCode="";
		 this.itemName="";
		 this.purchaseQuantity="";
		 this.purchaseDate="";
		 this.mrpCode="";
		 this.status="";
		
	}
	function ItemBean(){
		this.purchasingPlaceCode="";
		this.unitPrice="";
		
	}
	
	
	
	
	function purchaseListSearch(){
		alert("선택한 구매처코드 "+$('#purchasingPlaceCode').val()+"의 발주목록입니다.");
		$.jgrid.gridUnload("#purchaseListGrid");
		$('#purchaseListGrid').jqGrid({
			url : '${pageContext.request.contextPath}/logistics/purchase/purchase.do',
			datatype : 'json',
			postData : {'method':'getPurchaseList', 'code': $('#purchasingPlaceCode').val()},
			cache : false,
			jsonReader : {
				page : 'page',
				total : 'total',
				root : 'list'
			},
		 	beforeProcessing : function(data) {
				if (data.errorCode < 0) {
					alert(data.errorMsg);
				} else {
					dataset = data.list;
					emptyMrpBean = data.mrpBean;
					
				}
			},
			colNames : [ '구매처', '품목코드', '품목명','수량','납기일','취합번호'],
			colModel : [
				{name : 'itemBean.purchasingPlaceCode', width : 6, editable : false, align : "center"},
				{name : 'itemCode',	width : 10,	editable : false, align : "center"},
				{name : 'itemName',	width : 10,	editable : false, align : "center"},
				{name : 'amount', width : 4, editable : false, align : "center"},
				{name : 'purchaseDate', width : 12, editable : false, align : "center"},
				{name : 'mrpGatheringCode',	width : 10,	editable : false, align : "center"},
				
				],
			width : 900,
			height : 200,
			viewrecords : true,
			rowNum : 10,
			rowList : [10,20,30],
			multiboxonly : true,
			multiselect:true,
			editurl : 'clientArray',
			pager : '#purchaseListPager',
			caption : '구매계획 리스트',
		   
		});
	}

	function savePurchaseFunc(){
		var insertList = [];
		var rowid = $("#purchaseListGrid").jqGrid('getGridParam', 'selarrrow');
		
		for(var i=0; i<=rowid.length; i++){
		  var tempList = $('#purchaseListGrid').jqGrid('getRowData', rowid[i]);
		 
		  if($("input:checkbox[id='jqg_purchaseListGrid_"+rowid[i]+"']").is(":checked")){
			  
			var mrpBean=new MrpBean();
			mrpBean.itemCode=tempList.itemCode;
		 	mrpBean.itemName=tempList.itemName;
			mrpBean.amount=tempList.amount;
			mrpBean.purchaseDate=tempList.purchaseDate;
			mrpBean.mrpGatheringCode=tempList.mrpGatheringCode;
			mrpBean.itemBean.purchasingPlaceCode=tempList['itemBean.purchasingPlaceCode'];
			insertList.push(mrpBean);
			}
		}
		var list='{"mrpBeanList":'+ $.toJSON(insertList)+'}';
		
		purchaseCrud(list);
	}

	function purchaseCrud(list){
		var check=confirm("등록하시겠습니까?");
		if(check){}else{
			location.href='${pageContext.request.contextPath}/logistics/purchase/purchaseForm.html';
			return false;
		}
		$.ajax({
		    url : '${pageContext.request.contextPath}/logistics/purchase/purchase.do',
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : 'post',
		    cache : false,
		    data :{'list' : list, 'method' : 'regisPurchase'},
		    success : function(data, textStatus, jqXHR) {
				if(data.errorCode<0){
					alert(data.errorMsg);
				}else{
		    		alert("발주 완료 되었습니다.");
		    		location.href="${pageContext.request.contextPath}/logistics/purchase/purchaseForm.html";
		    	}
		    },
		    error : function(jqXHR, textStatus, error) {
		     	alert("일괄처리 오류입니다!");
		    }
		});
	}



	//거래처 검색 시 코드확인 및 불러오는 새창열기
	function purchasingPlaceCodeSearch(){
			features = "width=600px; height=400px; titlebar=no; status=no";
	        window.open('${pageContext.request.contextPath}/base/CodeListForm.html?code=LO-06',
	        '거래처코드 검색',features);
	}

	//오브젝트 copy
	function ObjectCopy(obj){
		 return JSON.parse(JSON.stringify(obj));
	}
	
	function comPurchaseList(){
		
		$.jgrid.gridUnload('#comPurchaseListTable');
		$('#comPurchaseListTable').jqGrid({
			url : '${pageContext.request.contextPath}/logistics/purchase/purchase.do',
			postData : {'method' : 'findPurchaseList'},
			datatype:'json',
			jsonReader:{page:'page',total:'total',root:'list'},
			rowNum:15,
			colNames:['발주코드', '소요량취합코드', '구매처코드', '품목코드', '발주날짜', '발주수량'],
			colModel:[
				{name:'purchaseCode',width:110,align:"center",editable:false},
				{name:'mrpGatheringCode',width:100,align:"center",editable:false},
				{name:'purchasingPlaceCode',width:90,align:"center",editable:false},
				{name:'itemCode',width:60,align:"center",editable:false},
				{name:'purchaseDate',width:100,align:"center",editable:false},
				{name:'purchaseAmount',width:80,align:"center",editable:false}
			],
			width : 700,
			height: 350,
			rowNum:15,
			cellEdit : true,
			cellsubmit : 'purchaseArray',
			cache:false,
			viewrecords : true,
			rownumbers : true,
			rowList:[5,10,15],
			pager:'#comPurchaseListPager',
		});
	}

</script>
</head>
<body>
<br/><br/>
<center>
	<div id="tabs" top="50px">
		<ul>
			<li><a href="#purchaseList">구매관리</a></li>
			<li id="comPurchaseListTableT"><a href="#comPurchaseList">발주조회</a></li>
		</ul>
		<div id="purchaseList">
  			<input type="text" id="purchasingPlaceCode" value="구매처 선택">
			<button id="findPurchaseList">조회</button>&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;
			<button id="savePurchase">선택발주</button>
			<br/><br/>
			<table id="purchaseListGrid"></table>
			<div id="purchaseListPager"></div>
		</div>
		<div id="#comPurchaseList">
  			<table id="comPurchaseListTable"></table>
			<div id="comPurchaseListPager" style="z-index:3"></div>
		  </div>
		</div>
</center>
<br/>
</body>
</html>