<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>직원상세보기</title>
  <jsp:include page="/decorator/head.jsp"/>
</head>
<script>
	var empUpdateList;
	var empUpdate;
	var features = "width=510px; height=390px; left=550px; top=150px; titlebar=no; status=no";

	$(document).ready(function() {
		$("input").button();
		empDetailList();
		$("#updateBt").click(settingEmp);
		setEmpBean();
		$("#deleteBt").click(empDeleteFunc);
		$("#hireDate").datepicker({dateFormat: 'yy-mm-dd',});
		$("#jobTitle").click(jobTitleFunc);
		$("#employmentTerm").click(empEmploymentTermFunc);
		$("#deptCode").click(deptFunc);

	});

	//상세정보 불러오기
	function empDetailList() {
		/* alert("사원코드 "+"${param.empCode}"+"사원의 상세정보입니다."); */
		var empCode = "${param.empCode}";
		$.ajax({
			url : '${pageContext.request.contextPath}/emp/emp.do',
			cache : false,
			dataType : 'json',
			data : {
				'method' : "findEmpDetail",
				"empCode" : empCode
			},
			success : function(data) {
				
				$("#empCode").val(data.empBean.empCode);
				$("#empName").val(data.empBean.empName);
				$("#jobTitle").val(data.empBean.jobTitle);
				$("#hireDate").val(data.empBean.hireDate);
				$("#employmentTerm").val(data.empBean.employmentTerm);
				$("#socialNumber").val(data.empBean.socialNumber);
				$("#tel").val(data.empBean.tel);
				$("#email").val(data.empBean.email);
				$("#postCode").val(data.empBean.postCode);
				$("#addr").val(data.empBean.addr);
				$("#detailAddr").val(data.empBean.detailAddr);
				$("#password").val(data.empBean.password);
				$("#deptCode").val(data.empBean.deptCode);
				
				
				
				var date = new Date();
			    if(data.empBean.image == null || data.empBean.image == "")
			        $("#empImg").attr("src", "${pageContext.request.contextPath}/images/noimage.jpg");
			    else{
			    	$("#empImgSrc").val(data.empBean.image);
			        $("#empImg").attr("src", data.empBean.image + "?time=" + date.getTime()); 
			        }
			},
			error : function(error1, error2, error3){
				alert(error1+","+error2+","+error3);
			}

		});
	}

	//수정할 내용 담을 빈객체 set
	function setEmpBean() {
		$.ajax({
			url : "${pageContext.request.contextPath}/emp/emp.do",
			data : {"method" : "setEmptyEmp" },
			cache : false,
			dataType : 'json',
			success : function(data) {
				
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					empUpdate = ObjectCopy(data.emptyEmpBean);
				}
			},
			error : function(error1, error2, error3){
				alert(error1+","+error2+","+error3);
			}
		});
	}

	//빈객체 카피
	function ObjectCopy(empObject) {
		return JSON.parse(JSON.stringify(empObject));
	}

	function settingEmp() {
		empUpdateList = [];

		empUpdate.empCode = $("#empCode").val();
		empUpdate.empName = $("#empName").val();
		empUpdate.jobTitle = $("#jobTitle").val();
		empUpdate.hireDate = $("#hireDate").val();
		empUpdate.employmentTerm = $("#employmentTerm").val();
		empUpdate.socialNumber= $("#socialNumber").val();
		empUpdate.tel = $("#tel").val();
		empUpdate.email = $("#email").val();
		empUpdate.postCode = $("#postCode").val();
		empUpdate.addr = $("#addr").val();
		empUpdate.detailAddr = $("#detailAddr").val();
		empUpdate.password = $("#password").val();
		empUpdate.deptCode = $("#deptCode").val();
		empUpdate.image=$("#empImgSrc").val();
		empUpdateList.push(empUpdate);
		

		var list = '{"empUpdateList" : ' + $.toJSON(empUpdateList) + '}';

		doUpdateEmp(list);

	}

	function doUpdateEmp(list) {
		
		$.ajax({
			url : "${pageContext.request.contextPath}/emp/emp.do",
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			data : {
				"list" : list,
				"method" : "modifyEmp"
			},
			type : "post",
			success : function(data, textStatus, jqXHR) {
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					alert("사원수정이 성공했습니다");
					alert("사원코드정보가 업데이트됬습니다")
					opener.document.location.reload(true);
					window.close();

				}
			},
			error : function(jqXHR, textStatus, error) {
				alert("사원수정오류입니다");
			}
		});
	}

	function empDeleteFunc() {
		$.ajax({
			url : "${pageContext.request.contextPath}/emp/emp.do",
			cache : false,
			dataType : 'json',
			data : {
				"deleteCode" : $("#empCode").val(),
				"method" : "removeEmp"
			},
			complete : function() {
				alert("삭제가 완료되었습니다.");
				opener.document.location.reload(true);
				window.close();
			}

		});
	}

	function jobTitleFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-01","직급검색",features);
	}
	
	function empEmploymentTermFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-02","근무형태",features);
	}

	
	function deptFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-03","부서코드검색",features);
	}



	function readURL(input) {
		$("#empId").val($("#empCode").val());
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
	            //(아래 코드에서 읽어들인 dataURL형식)
	            $("#empImg").attr("src", e.target.result);
	            //alert(e.target.result);
	        }
	        //File내용을 읽어 dataURL형식의 문자열로 저장
	        reader.readAsDataURL(input.files[0]);
	    }
	}

	$("#addImgForm").ajaxForm({
	    dataType: "json",
	    success: function(responseText, statusText, xhr, $form){
	        alert(responseText.errorMsg);
	        $("#empImgSrc").val(responseText.url);
	        //alert("ajaxForm:"+$("#empImgSrc").val());
	    }
	});

</script>
<body>
	<center>
		<h1 style="color: #5D5D5D">사원상세정보</h1>
		<table class="addTable">
		<tr>
			<td rowspan="17" width="330px">
				<img src="" id="empImg" style="width:330px; height:400px">
        		<form id='addImgForm'  action="${pageContext.request.contextPath}/base/uploadImg.do?method=uploadImg" enctype="multipart/form-data" name="addImgForm" method="post">
        			<input type="hidden" name="empId" id="empId">
        			<input type="hidden" name="oper" value="image">
	    			<input type=file id="fileEmpImg" name="fileEmpImg" style="display: none;" onchange="readURL(this)">
	    			<div id = "UpdateImgDiv">
	    				<button type="button" onclick="document.getElementById('fileEmpImg').click();" id="btnSearchImg">사진찾기</button>
	    				<button type="submit" id="btnSaveImg" >저장</button>
        			</div>
        		</form>
        	<input type="hidden" id="empImgSrc">
			</td>


			<td>
			<table class="detailTable">

			<tr>
					<td width="250px"><label>사원코드 : </label></td>
					<td width="500px"><input type="text" id="empCode"></td>
				</tr>
					<tr>
					<td width="250px"><label>이름 : </label></td>
					<td width="500px"><input type="text" id="empName"></td>
				</tr>
				<tr>
					<td width="250px"><label>직급 : </label></td>
					<td width="500px"><input type="text" id="jobTitle"></td>
				</tr>
				<tr>
					<td width="250px"><label>입사일 : </label></td>
					<td width="500px"><input type="text" id="hireDate"></td>
				</tr>
				<tr>
					<td width="250px"><label>고용형태 : </label></td>
					<td width="500px"><input type="text" id="employmentTerm"></td>
				</tr>
				<tr>
					<td width="250px"><label>주민번호 : </label></td>
					<td width="500px"><input type="text" id="socialNumber"></td>
				</tr>
				<tr>
					<td width="250px"><label>전화번호 : </label></td>
					<td width="500px"><input type="text" id="tel"></td>
				</tr>
				<tr>
					<td width="250px"><label>이메일 : </label></td>
					<td width="500px"><input type="text" id="email"></td>
				</tr>
				<tr>
					<td width="250px"><label>우편번호 : </label></td>
					<td width="500px"><input type="text" id="postCode"></td>
				</tr>
				<tr>
					<td width="250px"><label>주소 : </label></td>
					<td width="500px"><input type="text" id="addr"></td>
				</tr>
				<tr>
					<td width="250px"><label>상세주소 : </label></td>
					<td width="500px"><input type="text" id="detailAddr"></td>
				</tr>
				<tr>
					<td width="250px"><label>비밀번호 : </label></td>
					<td width="500px"><input type="text" id="password"></td>
				</tr>
				<tr>
					<td width="250px"><label>부서코드 : </label></td>
					<td width="500px"><input type="text" id="deptCode"></td>
				</tr>

			</table>
			<tr>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정" id="updateBt" style="font-size: 12px">
					<input type="button" value="삭제" id="deleteBt" style="font-size: 12px">
				</td>
			</tr>
			</table>
	</center>
</body>
</html>