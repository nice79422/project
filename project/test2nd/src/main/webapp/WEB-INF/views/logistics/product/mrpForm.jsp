<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
<style type="text/css">
input[type=text] {width: 130px;}
</style>
<script>
var emptyMpsBean;
var dataSetItem=[];
var mpsCode=[];

var features = "width=390px; height=340px; left=550px; top=150px; titlebar=no; status=no";
	$(document).ready(function() {
		$("#tabs").tabs();									//UI
		$("#workPlaceCode").click(workPlaceSearch);			//사업장 코드리스트
		$("#findMrpList").click(mrpListSearch);				//mps적용이 된 리스트 출력
		$("#unfold").click(mrpOpen);						//전개하기
		$("#findMrpList1").click(dateMrpList);				//소요량전개내역조회
		$("#gathering").click(doGatheringFunc);				//취합하기
		$("#saveAll").click(saveAllFunc);					//취합한 품목들 mrp테이블에 저장하기


		$('#startDate').datepicker({
			showMonthAfterYear : true,
			changeMonth : true,
			changeYear : true,
			dateFormat:"yy-mm-dd"
		});
		$('#endDate').datepicker({
			showMonthAfterYear : true,
			changeMonth : true,
			changeYear : true,
			dateFormat:"yy-mm-dd"
		});

		$('#startDate1').datepicker({
			showMonthAfterYear : true,
			changeMonth : true,
			changeYear : true,
			dateFormat:"yy-mm-dd"
		});
		$('#endDate1').datepicker({
			showMonthAfterYear : true,
			changeMonth : true,
			changeYear : true,
			dateFormat:"yy-mm-dd"
		});
		mrpTotalReviewListGrid();
	});

	function mrpListSearch(){
		$.jgrid.gridUnload('#mrpListGrid');
		var code=$('#workPlaceCode').val();
		var sDate=$('#startDate').val();
		var eDate=$('#endDate').val();
		$('#mrpListGrid').jqGrid({
			url : '${pageContext.request.contextPath}/logistics/product/mps.do',
			datatype : 'json',
			postData : {'method' : 'findMpsList',  'mpsStatus' : 'Y', 'mrpStatus' : 'N', 'lineNo' : code, 
						'startDate' : sDate , 'endDate' : eDate	},
			cache:false,
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
					emptyMpsBean = data.mpsBean;
				}
			},
			colNames : ['주문번호','품목코드','정미수량','생산완료일','리드타임','사업장','mps코드'],
			colModel : [
				{name:'contractDetailCode',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'itemBean.itemCode',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'planQuantity',
					 width:50,
					 align:"center",
					 editable:false
					},
					{name:'planDate',
					 width:100,
					 align:"center",
					 editable:false
					},
					{name:'itemBean.bomBean.leadTime',
					 width:50,
					 align:"center",
					 editable:false
					},
					{name:'workplaceCode',
					 width:60,
					 align:"center",
					 editable:false
					},
					{name:'mpsCode',
						hidden:true
					}
						
					
			],
			width : 890,
			height : 200,
			viewrecords : true,
			rowNum : 100,
			multiboxonly : true,
			cellsubmit : 'clientArray',
			rownumbers : true,
			pager : '#mrpListPager',
			caption : 'MRP 리스트'
		});
	}
	
	function dateMrpList(){
		$.jgrid.gridUnload('#mrpGrid1');
		var sDate=$('#startDate1').val();
		var eDate=$('#endDate1').val();
		$('#mrpGrid1').jqGrid({
			url : '${pageContext.request.contextPath}/logistics/product/mrp.do',
			datatype : 'json',
			postData : {'method' : 'dateMrpList', 'startDate' : sDate , 'endDate' : eDate },
			cache:false,
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
					emptyMpsBean = data.mpsBean;
				}
			},
			colNames :['레벨', '소요량전개코드' ,'주생산계획코드', '품목코드', '품목명','발주수량','발주일자','발주상태'],
			colModel : [
					{name:'level',
					 width:10,
					 align:"center",
					 editable:false
					},
					{name:'mrpCode',
					 width:80,
					 align:"center",
					 editable:false
					},
					{name:'mpsCode',
					 width:50,
					 align:"center",
					 editable:false
					},
					/* {name:'mrpCode',
					 width:90,
					 align:"center",
					 editable:false
					}, */
					{name:'itemCode',
					 width:60,
					 align:"center",
					 editable:false
					},
					{name:'itemName',
					 width:60,
					 align:"center",
					 editable:false
					},
					{name:'amount',
					 width:20,
					 align:"center",
					 editable:false
					},
					{name:'purchaseDate',
					 width:50,
					 align:"center",
					 editable:false
					},
					{name:'purchaseStatus',
					 width:40,
					 align:"center",
					 editable:false
					}
			],
			width : 890,
			height : 200,
			viewrecords : true,
			loadonce:true,
			rowNum:100,
			multiboxonly : true,
			cellsubmit : 'clientArray',
			rownumbers : true,
			rowList:[5,10,15],
			pager : '#mrpPager1',
			caption : 'MRP 리스트'
		});
	}
	
function mrpOpen(){
		
		$.ajax({
		    url : '${pageContext.request.contextPath}/logistics/product/mrp.do',
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : 'post',
		    dataType : 'json',
		    cache : false,
		    data : {'method' : 'mrpOpenOut', 'code': $('#workPlaceCode').val(), 'startDate': $('#startDate').val(), 'endDate': $('#endDate').val()},
		    success : function(data, textStatus, jqXHR) {
				if(data.errorCode<0){
					alert(data.errorMsg);
				}else{
		    		alert("MRP 전개가 정상적으로 완료되었습니다.");
		    		dataSetItem=data.list;
		    		mrpListOpenFunc();
		    	}
		    },
		    error : function(jqXHR, textStatus, error) {
		     	alert("MRP 전개 오류입니다");
		    }
		});
	}

	function mrpListOpenFunc(){
		$.jgrid.gridUnload("#mrpGrid");
		/* $("#tabs").css("height","780px"); */
		$('#mrpGrid').jqGrid({
			data : dataSetItem,
			datatype : 'local',
			cache : false,
			colNames :['레벨','품목코드','손실률'/* ,'소요량전개코드' */,'품목명','주문번호','계획일자','계획수량','모품목코드','사업장'],
			colModel : [
				{name:'level',
					 width:20,
					 align:"center",
					 editable:false
					},
					{name:'itemCode',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'lossRate',
						 width:55,
						 align:"center",
						 editable:false
						},
					/* {name:'mrpCode',
					 width:90,
					 align:"center",
					 editable:false
					}, */
					{name:'itemBean.itemName',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'contractDetailCode',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'purchaseDate',
					 width:50,
					 align:"center",
						 editable:false
						},
						{name:'purchaseQuantity',
							 width:30,
							 align:"center",
							 editable:false
							},
							{name:'bomBean.parentCode',
								 width:90,
								 align:"center",
								 editable:false
								},
								{name:'workspaceCode',
									 width:90,
									 align:"center",
									 editable:false
									}
			],
			width : 890,
			height : 200,
			viewrecords : true,
			rowNum:100,
			multiboxonly : true,
			cellsubmit : 'clientArray',
			rownumbers : true,
			pager : '#mrpPager',
			caption : 'MRP 리스트'
		});
	}

	function doGatheringFunc(){
		var insertList = [];
		var rowid = $('#mrpGrid').jqGrid('getDataIDs');
		for(var i=1; i<=rowid.length; i++){
		  var tempList = $('#mrpGrid').jqGrid('getRowData', i);
		  var mrpBean=new Object();
			mrpBean.level=tempList.level;
			mrpBean.itemCode=tempList.itemCode;
			mrpBean.contractDetailCode=tempList.contractDetailCode;
			mrpBean.purchaseDate=tempList.purchaseDate;
			mrpBean.purchaseQuantity=tempList.purchaseQuantity;
			mrpBean.workPlaceCode=tempList.workPlaceCode;
			
			insertList.push(mrpBean);
		}
		var list='{"mrpBeanList":'+ $.toJSON(insertList)+'}';
		alert(list);
		mrpGathering(list);
	}

	function mrpGathering(list){
		$.jgrid.gridUnload("#mrpGrid");
		$('#mrpGrid').jqGrid({
			url : '${pageContext.request.contextPath}/logistics/product/mrp.do',
			postData : {'method':'getMrpGathering', 'supply' : $('#workPlaceCode').val()},
			datatype : 'json',
			cache : false,
			jsonReader :{
				page : 'page',
				total : 'total',
				root : 'list'			//map에 "list" , list넣으면 그 키 값을 써줘야지 데이터가 뿌려짐!
			},
			colNames :['품목코드','품목명','단위가격','계획수량','계획일자'],
			colModel : [
					{name:'itemCode',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'itemBean.itemName',
					 width:90,
					 align:"center",
					 editable:false
					},
					{name:'itemBean.unitPrice',
				     width:50,
					 align:"center",
					 editable:false
					},
					{name:'purchaseQuantity',
					 width:50,
					 align:"center",
					 editable:false
					},
					{name:'purchaseDate',
					 width:50,
					 align:"center",
					 editable:false
					}
					
			],
			width : 890,
			height : 200,
			viewrecords : true,
			rowNum:100,
			multiboxonly : true,
			cellsubmit : 'clientArray',
			rownumbers : true,
			pager : '#mrpPager',
			caption : 'MRP 리스트',
			success : function(data, textStatus, jqXHR) {

				    		alert("MRP 취합이 정상적으로 완료되었습니다.");

				    },
				    error : function(jqXHR, textStatus, error) {
				     	alert("MRP 취합 오류입니다");
				    }
		});
	}
	
	
	function saveAllFunc(){
		alert("소요량 전개 데이터가 정상적으로 등록되었습니다.");
		var mrpGatheringList=[];
		var rowid=$("#mrpGrid").jqGrid('getDataIDs');
		
		for(var i=1 ; i<=rowid.length ; i++){
			var tempList=$("#mrpGrid").jqGrid('getRowData', i);
			var mrpGatheringBean=new Object();
			mrpGatheringBean.itemCode=tempList.itemCode;
			/* mrpGatheringBean.mpsCode=tempList.mpsCode; */
			mrpGatheringBean.purchaseQuantity=tempList.purchaseQuantity;
			mrpGatheringBean.purchaseDate=tempList.purchaseDate;
			
			mrpGatheringList.push(mrpGatheringBean);
		}
		var mrpGatheringList='{"mrpGatheringBeanList":'+$.toJSON(mrpGatheringList)+'}';
		
		saveMrpGathering(mrpGatheringList);

		
		var mpsList=[];
		var rowid=$("#mrpListGrid").jqGrid('getDataIDs');
		for(var i=1 ; i<= rowid.length ; i++){
			var tempList=$("#mrpListGrid").jqGrid('getRowData', i);
			
			var mpsBean=new Object();
			mpsBean.contractDetailCode=tempList.contractDetailCode;
			mpsList.push(mpsBean);
		}
		var mpsList='{"mpsBeanList":'+$.toJSON(mpsList)+'}';
	
		modifyMrpUse(mpsList);
	}
	
	
	//mrp_gathering 테이블에 insert !!!
	function saveMrpGathering(mrpGatheringList){
		$.ajax({
		    url : '${pageContext.request.contextPath}/logistics/product/mrp.do',
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : 'post',
		    dataType:'json',
		    cache:false,
		    data :{'method':'regisGatheringMrp', 'list' : mrpGatheringList},
		    complete : function(){
		    	alert("소요량 취합 데이터가 정상적으로 등록되었습니다.");
		    }
		    
		});
	}
	
	
	//MPS테이블에 mrp_use 데이터 'Y'로 수정 !!!
	function modifyMrpUse(mpsList){
		$.ajax({
		    url : '${pageContext.request.contextPath}/logistics/product/mps.do',
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : 'post',
		    dataType:'json',
		    cache:false,
		    data :{'method' : 'modifyMps','list' : mpsList},
		    complete : function(){
		    	alert("MPS의  MRP여부가 Y로 변경되었습니다");
		    	location.href="${pageContext.request.contextPath}/logistics/product/mrpForm.html";
		    }
	
		});
	}




	//사업장 검색 시 코드확인 및 불러오는 새창열기
	function workPlaceSearch(){
		var feature="width=510px; height=390px; left=550px; top=150px; titlebar=no; status=no";
		window.open('${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-05','코드검색',feature);
	}
	//오브젝트 copy
	function ObjectCopy(obj){
		 return JSON.parse(JSON.stringify(obj));
	}
	
	function mrpTotalReviewListGrid() {
		$.jgrid.gridUnload("#mrpTotalReviewListGrid");
		$("#mrpTotalReviewListGrid").jqGrid({
							url : "${pageContext.request.contextPath}/logistics/product/mrp.do",
							mtype : "post",
							postData : {
								"method" : "findMrpTotalReviewList",
							},
							datatype : "json",
							jsonReader : {
								page : 'mrpTotalReviewList.pagenum',
								total : 'mrpTotalReviewList.pagecount',
								root : 'mrpTotalReviewList.list'
							},
							beforeProcessing : function(data) {
								if (data.errorCode < 0) {
									alert(data.errorMsg);
								}
							},
							align : "center",
							width : 1150,
							height : 300,
							rownumbers : true,
							cellsubmit : "clientArray",
							caption : "소요량취합내역",
							colNames : [ "MRP취합번호", "품목번호", "품목명", "단위",
									"발주일자", "발주수량"/* , "구매생산여부", "거래처" */, "발주상태" ],
							colModel : [ {
								name : "mrpGatheringCode",
								width : 30,
								editable : false,
							}, {
								name : "itemCode",
								width : 30,
								editable : false,
							}, {
								name : "itemName",
								width : 30,
								editable : false
							}, {
								name : "unit",
								width : 20,
								editable : false
							}, {
								name : "purchaseDate",
								width : 50,
								editable : false
							}, {
								name : "amount",
								width : 20,
								editable : false
							}, {
								name : "purchaseStatus",
								width : 20,
								editable : false
							}/* , {
								name : "accountCode",
								width : 70,
								editable : false
							}, {
								name : "purchaseOrderStatus",
								width : 70,
								editable : false
							} */ ],
							rowNum : 10,
							rowList : [ 10, 20, 30 ],
							pager : "#mrpTotalReviewListPager",
							onCellSelect : function(rowid, iCol) {
							}
						});
	}

</script>
</head>
<body>
<br/><br/>
<center>
	<div id="tabs" top="50px">
		<ul>
			<li><a href="#regisMrpList" id="mrp">MRP관리</a></li>
			<li><a href="#MrpList">소요량전개/취합</a></li>
			<li><a href="#MrpList1">소요량전개내역조회</a></li>
			<li><a href="#MrpGatheringList">소요량취합내역조회</a></li>
		</ul>
		<div id="regisMrpList">
  			<input type="text" id="workPlaceCode" value="사업장 선택">
  			<input type="text" id="startDate" value="시작 날짜" readonly="readonly">
			<label>~</label>
			<input type="text" id="endDate" value="마지막 날짜" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="findMrpList">조회</button>&nbsp;&nbsp;&nbsp;
			<button id="unfold">전개</button>
			<br/><br/>
			<table id="mrpListGrid"></table>
			<div id="mrpListPager"></div>
		</div>
		<div id="MrpList">
			<button id="gathering">취합</button>
			<button id="saveAll">저장</button>

			<br/>
			<table id="mrpGrid"></table>
			<div id="mrpPager"></div>
		</div>
	
		<div id="MrpList1">
		<input type="text" id="startDate1" value="발주시작날짜" readonly="readonly">
			<label>~</label>
			<input type="text" id="endDate1" value="마지막 날짜" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="findMrpList1">조회</button>&nbsp;&nbsp;&nbsp;
			<br/>
			<table id="mrpGrid1"></table>
			<div id="mrpPager1"></div>
		</div>
		
		<div id="MrpGatheringList">
			<table id="mrpTotalReviewListGrid"></table>
			<div id="mrpTotalReviewListPager"></div>
	</div>
</center>
<br/>
</body>
</html>