<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주생산계획</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
<script type="text/javascript">
	var lastID;
	var lastCol;
	var detailCodeList=[];
	$(document).ready(function() {
		$( function() {
			 $( "#MpsSearchTabs" ).tabs();
		});
		getOrderList();
		$("#orderT").click(getOrderList);
		$("#mpsTableT").click(getMpsList);
		$("input").button();
		$("#resisterMpsB").click(mpsBatchProcess);
		
	});
	
	
	
	function MpsBean(){
		 this.itemBean=new ItemBean();
		 this.contractDetailBean = new ContractDetailBean() ;
		 this.mpsCode="";
		 this.planQuantity="";
		 this.planDate="";
		 this.contractDetailCode="";
		 this.workplaceCode="";
		 this.mrpStatus="";
		 this.itemCode="";
	}

	function ItemBean(){
		this.itemCode="";
		this.quantity="";
		this.unit="";
		this.unitPrice="";
		this.comProductStatus="";
		this.pStatus="";
		
		this.stockBean=new StockBean();
		this.bomBean=new BomBean();
		this.clientBean={};
		this.contractDetailBean = new ContractDetailBean();
	}

	function StockBean(){
		this.itemBean={};
		this.stockCode="";
		this.warehouseCode="";
		this.stockAmount="";
		this.outputExpecAmount="";
		this.inoutAmount="";
		this.outputDate="";
		this.inoutDate="";
		this.itemCode="";
	}

	function ContractDetailBean(){

		this.contractDetailCode="";
		this.supplyDate="";
		this.supplyAmount="";
		this.contractCode="";
		this.mpsStatus="";
		this.outputStatus="";
	}

	function BomBean(){
		this.lossRate="";
		this.leadTime="";
	}


	function getOrderList(){//mps미적용 리스트 가져오기
		$.jgrid.gridUnload('#orderListTable');
		$('#orderListTable').jqGrid({
			url:'${pageContext.request.contextPath}/logistics/product/mps.do',
			postData : {"method" : "findContractDetailList", "mpsStatus" : "N" },
			datatype:'json',
			jsonReader:{
				page:'page',
				total:'total',
				root:'list'
			},
			rowNum:15,
			colNames:['주문코드', '품목코드', '손실률', '리드타임', '납기일', '주문수량', '재고수량', '계획수량', '출고예정수량', '재고코드', '완료계획일', 'MPS적용'],
			colModel:[
				{name:'itemBean.contractDetailBean.contractDetailCode',
				 width:160,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.itemCode',
				 width:100,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.bomBean.lossRate',
				 width:70,
				 align:"center",
				 editable:false,
				 hidden:true
				},
				{name:'itemBean.bomBean.leadTime',
				 width:55,
				 align:"center",
				 editable:false,
				 hidden:true
				},
				{name:'itemBean.contractDetailBean.supplyDate',
				 width:100,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.contractDetailBean.supplyAmount',
				 width:90,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.stockBean.stockAmount',
				 width:60,
				 align:"center",
				 editable:false,
				 hidden:true
				},
				{name:'planQuantity',
			     width:80,
			     align:"center",
			     editable:true,
			     hidden:true
			    },
				{name:'itemBean.stockBean.outputExpecAmount',
			     width:80,
			     align:"center",
			     editable:true,
			     hidden:true
			    },
				{name:'itemBean.stockBean.stockCode',
			     width:80,
			     align:"center",
			     editable:false
			    },
				{name:'planDate',
			     width:100,
			     align:"center",
			     editable:true,
				 editoptions:{ dataInit : function(e){
					$(e).datepicker({
						dateFormat: 'yy-mm-dd' ,
						yearSuffix: '년',
					    monthNames:[' 1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
					    dayNamesMin: ['일','월','화','수','목','금','토']
					 });
               	}
				}},
				{name:'itemBean.contractDetailBean.mpsStatus',
				 hidden:true
				 
				},
			],
			width : 1050,
			height: 280,
			rowNum:15,
			cache:false,
			viewrecords : true,
			rowList:[5,10,15],
			pager:'#orderListPager',
			multiselect:true,
			multiboxonly:true,
			onCellSelect:function(id, iCol, cellcontent, e){
				lastID=id;
				lastCol=iCol;
			 if(iCol==12 && cellcontent=='&nbsp;'){
					reset(id, iCol);
				}
			}
		});
		
	}

	function reset(id, iCol){//현재재고와 사용가능재고 그리고 주문수량을 동적으로 계산하는 함수
		var itemCode=($("#orderListTable").getRowData(id))['itemBean.itemCode'];
		var outputExpecAmount=($("#orderListTable").getRowData(id))['itemBean.stockBean.outputExpecAmount'];
		var availableAmount=($("#orderListTable").getRowData(id))['itemBean.stockBean.stockAmount'];
		var supplyAmount=($("#orderListTable").getRowData(id))['itemBean.contractDetailBean.supplyAmount'];
		var lossRate=($("#orderListTable").getRowData(id))['itemBean.bomBean.lossRate'];
		var ids = $("#orderListTable").getDataIDs();

		for(var index in ids){
			var plannedAmount;
			var comapareditemCode=($("#orderListTable").getRowData(ids[index]))['itemBean.itemCode'];
			if(ids[index]==id){
				if(Number(availableAmount)>=Number(supplyAmount)){
					planAmount=0;
					$("#orderListTable").jqGrid("setCell",id,iCol,planAmount);
					outputExpecAmount=Number(outputExpecAmount)+Number(supplyAmount);
					availableAmount=availableAmount-supplyAmount;
				}else{
					planAmount=supplyAmount-availableAmount;//계획수량
					$("#orderListTable").jqGrid("setCell",id,iCol,plannedAmount);
					outputExpecAmount=Number(outputExpecAmount)+Number(availableAmount);
					availableAmount=0;
				}
				$("#orderListTable").jqGrid("setCell",id,iCol-1,availableAmount);
				$("#orderListTable").jqGrid("setCell",id,iCol+1,outputExpecAmount);
			} else if (ids[index]>id && itemCode==comapareditemCode){
				$("#orderListTable").jqGrid("setCell",ids[index],iCol-1,availableAmount);
				$("#orderListTable").jqGrid("setCell",ids[index],iCol+1,outputExpecAmount);
			}
		}
	}

	function mpsBatchProcess(){//일괄처리
		
		$("#orderListTable").jqGrid('saveCell',lastID,lastCol);
		var mpsBeanList=[];
		var ids = $("#orderListTable").jqGrid('getGridParam', 'selarrrow');
		for(var i = 0; i < ids.length; i++){
			
			var mpsData=$("#orderListTable").getRowData(ids[i]);
				
			if($("input:checkbox[id='jqg_orderListTable_"+ids[i]+"']").is(":checked")){
				
				var mpsBean=new MpsBean();
				mpsBean.itemBean.contractDetailBean.contractDetailCode=mpsData['itemBean.contractDetailBean.contractDetailCode'];
				mpsBean.itemBean.itemCode=mpsData['itemBean.itemCode'];
				mpsBean.itemBean.bomBean.lossRate=mpsData['itemBean.bomBean.lossRate'];
				mpsBean.itemBean.bomBean.leadTime=mpsData['itemBean.bomBean.leadTime'];
				mpsBean.itemBean.contractDetailBean.supplyDate=mpsData['itemBean.contractDetailBean.supplyDate'];
				mpsBean.itemBean.contractDetailBean.supplyAmount=mpsData['itemBean.contractDetail.supplyAmount'];
				mpsBean.itemBean.stockBean.stockAmount=mpsData['itemBean.stockBean.stockAmount'];
				mpsBean.planQuantity=mpsData.planQuantity;
				mpsBean.itemBean.stockBean.outputExpecAmount=mpsData['itemBean.stockBean.outputExpecAmount'];
				mpsBean.itemBean.stockBean.stockCode=mpsData['itemBean.stockBean.stockCode'];
				mpsBean.planDate=mpsData.planDate;
				mpsBean.status="INSERT";
				
				mpsBean.mrpStatus="N";
				mpsBean.itemCode=mpsData['itemBean.itemCode'];
				mpsBean.contractDetailCode=mpsData['itemBean.contractDetailBean.contractDetailCode'];
				mpsBeanList.push(mpsBean);
				}
			}

		mpsList='{"mpsBeanList":'+$.toJSON(mpsBeanList)+'}';
		
		$.ajax({
		    url : '${pageContext.request.contextPath}/logistics/product/mps.do',
		    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : 'post',
		    cache:false,
		    data :{"method" : "batchMpsProcess", 'mpsList': mpsList},
		    success : function(data, textStatus, jqXHR){
				if(data.errorCode<0){
					alert(data.errorMsg);
				}else{
		    		alert("MPS가 등록 되었습니다.");
		    		location.href='${pageContext.request.contextPath}/logistics/product/mpsForm.html';
		    	}
		    },
		    error : function(jqXHR, textStatus, error){
		     	alert("일괄처리 오류입니다!>>");
		    }
		});
		mpsBeanList=[];
	}

	function getMpsList(){//MPS적용된 리스트 보기
		
		$.jgrid.gridUnload('#mpsTable');
		$('#mpsTable').jqGrid({
			url:'${pageContext.request.contextPath}/logistics/product/mps.do',
			postData : {'method' : 'findMpsList', 'mpsStatus' :'Y', 'mrpStatus' : 'N'},
			datatype:'json',
			jsonReader:{
				page:'page',
				total:'total',
				root:'list'
			},
			colNames:['주문코드', '품목코드', '손실률', '리드타임', '납기일', '주문수량', '재고수량', '계획수량', '출고예정수량', '재고코드', '완료계획일', 'MPS적용','MRP적용'],
			colModel:[
				{name:'itemBean.contractDetailBean.contractDetailCode',
				 width:160,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.itemCode',
				 width:100,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.bomBean.lossRate',
				 width:70,
				 align:"center",
				 editable:false,
				 hidden:true
				},
				{name:'itemBean.bomBean.leadTime',
				 width:55,
				 align:"center",
				 editable:false,
				 hidden:true
				},
				{name:'itemBean.contractDetailBean.supplyDate',
				 width:100,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.contractDetailBean.supplyAmount',
				 width:70,
				 align:"center",
				 editable:false
				},
				{name:'itemBean.stockBean.stockAmount',
				 width:60,
				 align:"center",
				 editable:false,
				 hidden:true
				 
				},
				{name:'planQuantity',
			     width:80,
			     align:"center",
			     editable:false,
			     hidden:true
			    },
				{name:'itemBean.stockBean.outputExpecAmount',
			     width:90,
			     align:"center",
			     editable:false,
			     hidden:true
			     
			    },
				{name:'itemBean.stockBean.stockCode',
			     width:80,
			     align:"center",
			     editable:false
			    },
				{name:'planDate',
			     width:100,
			     align:"center",
			     editable:false,
				 editoptions:{ dataInit : function(e){
					$(e).datepicker({
						dateFormat: 'yy-mm-dd' ,
						yearSuffix: '년',
					    monthNames:[' 1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
					    dayNamesMin: ['일','월','화','수','목','금','토']
					 });
               	}
				}},
				{name:'itemBean.contractDetailBean.mpsStatus',
				 hidden:true			
				},
				{name:'mrpStatus',
				width:70,
				align:"center",
				editables:false					
				}
				
			],
			width : 1050,
			height: 280,
			rowNum:15,
			cellEdit : true,
			loadonce:true,
			cellsubmit : 'clientArray',
			cache:false,
			viewrecords : true,
			rownumbers : true,
			rowList:[5,10,15],
			pager:'#mpsPager',
			onCellSelect:function(id, iCol, cellcontent, e){
			lastID=id;
			lastCol=iCol;
			if (iCol==13 && cellcontent=='&nbsp;'){
			reset(id, iCol);  
			}
		}  
		});
	}
</script>
</head>
<body id="body">
<center>
	<div id="MpsSearchTabs" style="float: center; width: 100%; height: 480px">
		<ul>
		    <li id="orderT"><a href="#orderTab">MPS 대기 수주</a></li>
		    <li id="mpsTableT"><a href="#mpsTableTab">MPS 조회</a></li>
		</ul>
		  <div id="orderTab">
		  	<input type="button" id="resisterMpsB" value="MPS 일괄 등록"><br><br>
		  	<table id="orderListTable"></table>
			<div id="orderListPager" style="z-index:3"></div>
		  </div>
		  <div id="mpsTableTab">
		  <input type="hidden"><br><br>
		 	<table id="mpsTable"></table>
			<div id="mpsPager"></div>
		  </div>
	</div>
<div id="detailCodeD" title="선택하세요"></div>
</center>
</body>
</html>