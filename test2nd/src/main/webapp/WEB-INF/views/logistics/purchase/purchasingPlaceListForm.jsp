<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>납품처 관리</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
<script>
var purchasingPlaceBean={};
	var customerBeanList=[];
	var feature = "width=510px; height=390px; titlebar=no; status=no";

	$(document).ready(function() {
		$("#tabs").tabs();
		purchasingPlaceList();
		$("#addBt").button().click(settingPurchasingPlace);
		
	});

	

	function purchasingPlaceList() {
		$("#purchasingPlaceList").jqGrid({
			url : '${pageContext.request.contextPath}/logistics/purchase/purchasingPlace.do',
			postData : {"method" : "findPurchasingPlaceList"},
			jsonReader : {
				page : 'page',
				total : 'total',
				root : 'list'
			},
			rowNum : 7,
			colNames : [  '구매처코드', '구매처명', '사업자번호', '업태', '대표자명', '우편번호', '주소', '상세주소' ,'전화번호','팩스번호'  ],
			colModel : [
				{
				name : 'purchasingPlaceCode',
				width : 30,
				align : "center",
				editable : false
			}, {
				name : 'purchasingPlaceName',
				width : 35,
				align : "center",
				editable : false
			}, {
				name : 'businessNumber',
				width : 35,
				align : "center",
				editable : false
			}, {
				name : 'businessConditions',
				width : 35,
				align : "center",
				editable : false
			}, {
				name : 'representName',
				width : 30,
				align : "center",
				editable : false
			}, {
				name : 'postNumber',
				width : 30,
				align : "center",
				editable : false
	 		}, {
				name : 'addr',
				width : 35,
				align : "center",
				editable : false
			}, {
				name : 'detailAddr',
				width : 30,
				align : "center",
				editable : false
			}, {
				name : 'tel',
				width : 30,
				align : "center",
				editable : false
			}, {
				name : 'fax',
				width : 30,
				align : "center",
				editable : false
			}

			],
			width : '850',
			height : '280',
			viewrecords : true,
			rowList : [ 3, 6, 9 ],
			pager : '#pager',
			caption : "구매처리스트",
			multiselect : false,
			cache : false,
			datatype : 'json',
			error : function(error1, error2, error3) {
				alert(error1 + "," + error2 + "," + error3);
			}
		});
	}
	//수정할 내용 담을 빈객체 set
	function setPurchasingPlaceBean() {
		$.ajax({
			url : "${pageContext.request.contextPath}/logistics/purchase/purchasingPlace.do",
			data : {"method" : "getEmptyPurchasingPlace"},
			cache : false,
			dataType : 'json',
			success : function(data) {
				alert(data);
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					purchasingPlaceBean = ObjectCopy(data.emptyPurchasingPlaceBean);
				}
			},
			error : function(error1, error2, error3){
				alert(error1+","+error2+","+error3);
			}
		});
	}

	function ObjectCopy(purchasingPlaceObject) {
		return JSON.parse(JSON.stringify(purchasingPlaceObject));
	}

	function settingPurchasingPlace(){

		purchasingPlaceBean.purchasingPlaceCode=$("#purchasingPlaceCode").val();
		purchasingPlaceBean.purchasingPlaceName=$("#purchasingPlaceName").val();
		purchasingPlaceBean.businessNumber=$("#businessNumber").val();
		purchasingPlaceBean.businessConditions=$("#businessConditions").val();
		purchasingPlaceBean.representName=$("#representName").val();
		purchasingPlaceBean.postNumber=$("#postNumber").val();
		purchasingPlaceBean.addr=$("#addr").val();
		purchasingPlaceBean.detailAddr=$("#detailAddr").val();
		purchasingPlaceBean.tel=$("#tel").val();
		purchasingPlaceBean.fax=$("#fax").val();
		
		
		
		
		

		var bean=JSON.stringify(purchasingPlaceBean);


		alert(bean);
		doAddFunc(bean);

	}

	function doAddFunc(purchasingPlaceBean){

		$.ajax({
			url : "${pageContext.request.contextPath}/logistics/purchase/purchasingPlace.do",
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			data : {"method" : "registerPurchasingPlace" ,"PurchasingPlaceBean" : purchasingPlaceBean},
			type : "post",
			success : function(data){

				if(data.errorCode<0){
					alert(errorMsg);
				}else{
					alert("구매처등록이 성공했습니다!");
					location.href="${pageContext.request.contextPath}/logistics/purchase/purchasingPlaceListForm.html";
				}
			},
			error : function(error){
					alert("구매처등록오류입니다");
			}

		});
	}

</script>
</head>

<body>
<center>
<div id="tabs" style="float:center; width:80%; height:440px; margin-top:30px;">
  <ul>
    <li><a href="#checkCustomerContainer">구매처조회</a></li>
    <li><a href="#regisCustomerContainer">구매처등록</a></li>
    

  </ul>
  <div id="regisCustomerContainer">
 		<table class="regisTable">
		
		<tr>
		<td><label>구매처 코드</label></td>
		<td><input type="text" id="purchasingPlaceCode" name="PurchasingPlaceCode"></td>
		</tr>
		<tr>
		<td><label>구매처명</label></td>
		<td><input type="text" id="purchasingPlaceName"></td>
		</tr>
		<tr>
		<td><label>사업자 번호</label></td>
		<td><input type="text" id="businessNumber"></td>
		</tr>
		<tr>
		<td><label>업 태</label></td>
		<td><input type="text" id="businessConditions"></td>
		</tr>
		<tr>
		<td><label>대표자명</label></td>
		<td><input type="text" id="representName"></td>
		</tr>
		<tr>
		<td><label>우편번호</label></td>
		<td><input type="text" id="postNumber"></td>
		</tr>
		<tr>
		<td><label>주소</label></td>
		<td><input type="text" id="addr"></td>
		</tr>
		<tr>
		<td><label>상세주소</label></td>
		<td><input type="text" id="detailAddr"></td>
		</tr>
		<tr>
		<td><label>전화번호</label></td>
		<td><input type="text" id="tel"></td>
		</tr>
		<tr>
		<td><label>팩스번호</label></td>
		<td><input type="text" id="fax"></td>
		</tr>
		
		
		


		<tr><td colspan="2" style="text-align:center;"><input type="button" value="등록하기" id="addBt" style="font-size:18px;" ></td></tr>

	</table>
  </div>
  <div id="checkCustomerContainer">
  <table id="purchasingPlaceList"></table>
	<div id="pager"></div>
  </div>

</div>
</center>
</body>
</html>