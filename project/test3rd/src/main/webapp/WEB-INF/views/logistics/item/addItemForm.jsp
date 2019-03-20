<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>품목추가</title>
  <jsp:include page="/decorator/head.jsp"/>
</head>
<script>
	var addItemList;
	var addBomList;
	var addStockList;
	var addItem;
	var addBom;
	var features = "width=510px; height=390px; left=550px; top=150px; titlebar=no; status=no";

	$(document).ready(function() {
		$("input").button();
		
		
		$("#addBt").click(settingItem);
		
		$("#purchasingPlaceCode").click(purchasingPlaceCodeFunc);
		$("#warehouseCode").click(warehouseCodeFunc);
		
	});

	function ItemBean(){
		this.itemCode="";
		this.itemName="";
		this.unit="";
		this.unitPrice="";
		this.productStatus="";
		this.purchasingPlaceCode="";
	}
	
	
	function BomBean(){
		this.lossRate="";
		this.leadTime="";
		this.parentCode="";
		this.nQuantity="";
		this.itemCode="";
	}
	
	function StockBean(){
		this.warehouseCode="";
		this.itemCode="";
		this.parentCode="";
	}

	
	function settingItem() {
		addItemList = [];
		var itemBean= new ItemBean();
		itemBean.itemCode = $("#itemCode").val();
		itemBean.itemName = $("#itemName").val();
		itemBean.unit = $("#unit").val();
		itemBean.unitPrice = $("#unitPrice").val();
		itemBean.productStatus = $("#productStatus").val();
		itemBean.purchasingPlaceCode = $("#purchasingPlaceCode").val();
		addItemList.push(itemBean);
		var itemlist = '{"addItemList" : ' + $.toJSON(addItemList) + '}';
		doAddItem(itemlist);
	}
	function settingBom() {
		
		addBomList = [];
		var bomBean= new BomBean();
		bomBean.parentCode = $("#parentCode").val();
		bomBean.nQuantity = $("#nQuantity").val();
		bomBean.lossRate = $("#lossRate").val();
		bomBean.leadTime = $("#leadTime").val();
		bomBean.itemCode = $("#itemCode").val();
		
		addBomList.push(bomBean);
		var bomlist = '{"addBomList" : ' + $.toJSON(addBomList) + '}';
		doAddBom(bomlist);
	}
	function settingStock() {
		
		addStockList = [];
		var stockBean= new StockBean();
		stockBean.warehouseCode = $("#warehouseCode").val();
		stockBean.itemCode = $("#itemCode").val();
		stockBean.parentCode = $("#parentCode").val();
		
		
		addStockList.push(stockBean);
		var stocklist = '{"addStockList" : ' + $.toJSON(addStockList) + '}';
		doAddStock(stocklist);
	}
	
	
	
	function doAddItem(itemlist) {
		$.ajax({
			url : "${pageContext.request.contextPath}/logistics/item/item.do",
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			data : {
				"list" : itemlist,
				"method" : "modifyItem"
			},
			type : "post",
			success : function(data, textStatus, jqXHR) {
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					alert("품목등록이 성공했습니다");
					alert("상세코드목록이 업데이트되었습니다");
					settingBom();

				}
			},
			error : function(jqXHR, textStatus, error) {
				alert("품목등록 오류입니다");
			}
		});
	}

	function doAddBom(bomlist) {
		$.ajax({
			url : "${pageContext.request.contextPath}/logistics/item/bom.do",
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			data : {
				"list" : bomlist,
				"method" : "modifyBom"
			},
			type : "post",
			
			success : function(data, textStatus, jqXHR) {
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					alert("bom등록이 성공했습니다");
					settingStock();
					window.close();
				}
			},
			error : function(jqXHR, textStatus, error) {
				alert("bom등록 오류입니다");
			}
		});
	}
	
	function doAddStock(stocklist) {
		$.ajax({
			url : "${pageContext.request.contextPath}/logistics/item/stock.do",
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			data : {
				"list" : stocklist,
				"method" : "modifyStock"
			},
			type : "post",
			async: false,
			success : function(data, textStatus, jqXHR) {
				
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					alert("재고등록이 성공했습니다");
					opener.document.location.reload(true);
					window.close();
				}
			},
			error : function(jqXHR, textStatus, error) {
				alert("재고등록 오류입니다");
			}
		});
	}

	
	
	
	
	function purchasingPlaceCodeFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=LO-06","구매처코드",features);
	}
	
	function warehouseCodeFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-06","창고코드",features);
	}

	
</script>
<body>
	<center>
		<h1 style="color: #5D5D5D">품목추가정보</h1>
		<table class="detailTable">

				<tr>
					<td width="100px"><label>품목코드 : </label></td>
					<td width="500px"><input type="text" id="itemCode"></td>
				</tr>
					<tr>
					<td width="100px"><label>품목명: </label></td>
					<td width="500px"><input type="text" id="itemName"></td>
				</tr>
				<tr>
					<td width="100px"><label>단위 : </label></td>
					<td width="500px"><input type="text" id="unit"></td>
				</tr>
				<tr>
					<td width="100px"><label>단위가격 : </label></td>
					<td width="500px"><input type="text" id="unitPrice"></td>
				</tr>
				<tr>
					<td width="100px"><label>완제품여부 : </label></td>
					<td width="500px"><input type="text" id="productStatus"></td>
				</tr>
				<tr>
					<td width="100px"><label>구매처코드 : </label></td>
					<td width="500px"><input type="text" id="purchasingPlaceCode"></td>
				</tr>
				<tr>
					<td width="100px"><label>창고코드 : </label></td>
					<td width="500px"><input type="text" id="warehouseCode"></td>
				</tr>
				<tr>
					<td width="100px"><label>모품목코드 : </label></td>
					<td width="500px"><input type="text" id="parentCode"></td>
				</tr>
				<tr>
					<td width="100px"><label>대개 : </label></td>
					<td width="500px"><input type="text" id="nQuantity"></td>
				</tr>
				<tr>
					<td width="100px"><label>로스율 : </label></td>
					<td width="500px"><input type="text" id="lossRate"></td>
				</tr>
				<tr>
					<td width="100px"><label>리드타임 : </label></td>
					<td width="500px"><input type="text" id="leadTime"></td>
				</tr>
				
				<tr>
					<td>
					<input type="button" value="저장" id="addBt" style="font-size: 12px">
	
					</td>
				</tr>

			</table>
	</center>
</body>
</html>