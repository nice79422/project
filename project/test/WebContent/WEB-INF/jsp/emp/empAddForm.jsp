<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>사원 등록</title>
<style type="text/css">
.addTable {display: inline-table;}
.detailTable {display: inline-table; width: 200px; font-size: 13px; position: relative; left: 80px;}
.addTable td:nth-child(2n+1) {width: 300px; height : 30px; text-align:center;}
.addTable td:nth-child(2n+2) {width: 400px;}
.addTable img {width: 200px; height: 250px;}
.ui-datepicker{ font-size: 20px; width: 300px; }

input[type=text] {
	width: 80%;
	text-align: center;
	font-size: 15px;
}

</style>
<script>
	var empBean;
	var features = "width=510px; height=390px; titlebar=no; status=no";

	$(document).ready(function(){
		//부서 직급 우편번호 코드 부르기
 	$("input").button();
		$("#deptCode").click(deptFunc);
		$("#jobTitle").click(jobTitleFunc);
		$("#postCode").click(showdialogzipcode);
		$("#employmentTerm").click(empEmploymentTermFunc);

		//등록버튼 누르고나서
		$("#addBt").button().click(settingEmp);

		//입사일자 달력
		$("#hireDate").datepicker({dateFormat: 'yy-mm-dd',});

		setEmpBean();

	});

	function jobTitleFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-01","직급검색",features);
	}

	function empEmploymentTermFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-02","근무형태",features);
	}

	function deptFunc(){
		window.open("${pageContext.request.contextPath}/base/CodeListForm.html?code=HS-03","부서코드검색",features);
	}

	function showdialogzipcode(){
		   daum.postcode.load(function(){
		   new daum.Postcode({
		   oncomplete: function(data){
		      alert(data.address+" "+data.buildingName+"("+data.bname+")");
		      $('#postCode').val(data.zonecode);
		      $('#addr').val(data.address);
		      $('#detailAddr').val(data.buildingName+"("+data.bname+")");
		   }
		   }).open();
		   });
		   }
	//수정할 내용 담을 빈객체 set
	function setEmpBean() {
		$.ajax({
			url : "${pageContext.request.contextPath}/emp/emp.do",
			data : {"method" : "setEmptyEmp"},
			cache : false,
			dataType : 'json',
			success : function(data) {
				
				if (data.errorCode < 0) {
					alert(errorMsg);
				} else {
					empBean = ObjectCopy(data.emptyEmpBean);
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

	function settingEmp(){

		empBean.empCode=$("#empCode").val();
		empBean.empName=$("#empName").val();
		empBean.jobTitle=$("#jobTitle").val();
		empBean.hireDate=$("#hireDate").val();
		empBean.employmentTerm=$("#employmentTerm").val();
		empBean.socialNumber=$("#socialNumber").val();
		empBean.tel=$("#tel").val();
		empBean.email=$("#email").val();
		empBean.postCode=$("#postCode").val();
		empBean.addr=$("#addr").val();
		empBean.detailAddr=$("#detailAddr").val();
		empBean.password=$("#password").val();
		empBean.image=$("#empImgSrc").val();
		empBean.deptCode=$("#deptCode").val();





		var EmpBean=JSON.stringify(empBean);


		
		doAddFunc(EmpBean);

	}

	function doAddFunc(EmpBean){

		$.ajax({
			url : "${pageContext.request.contextPath}/emp/emp.do",
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			data : {"EmpBean" : EmpBean,
				"method" : "registerEmp"
			},
			type : "post",
			success : function(data, textStatus, jqXHR){	//jqXHR은 jQuery HTTP Request : data와 거의 비슷한 값을 가지고 있음
															// 추가적으로 status와 readyStatus 등을 더보내줌
															//textstatus는 단순한 String 값  success가 들어있음
				if(data.errorCode<0){
					alert(errorMsg);
				}else{
					alert("사원등록이 성공했습니다!");
					alert("사원상세코드가 등록되었습니다!");
					location.href="${pageContext.request.contextPath}/emp/empAddForm.html";
				}
			},
			error : function(jqXHR, textStatus, error){
					alert("사원등록오류입니다");
			}

		});
	}

	function readURL(input) {
		
		$("#empId").val($("#empCode").val());
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
	            //(아래 코드에서 읽어들인 dataURL형식)
	            $("#empImg").attr("src", e.target.result);
	            
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
	        
	    }
	});

</script>
</head>
<body>
	<center>
		<!-- <h3 style="color: #8C8C8C">기본정보</h3> -->
		<br>
		<table class="addTable">
		<tr>
			<td rowspan="17" width="330px">
				<img src="" id="empImg" style="width:200px; height:200px">
        		<form id='addImgForm'  action="${pageContext.request.contextPath}/base/uploadImg.do?method=uploadImg" enctype="multipart/form-data" name="addImgForm" method="post">
        			<input type="hidden" name="empId" id="empId">
        			<input type="hidden" name="oper" value="image">
	    			<input type=file id="fileEmpImg" name="fileEmpImg" style="display: none;" onchange="readURL(this)">
	    			<div id = "UpdateImgDiv">
	    				<button type="button" onclick="document.getElementById('fileEmpImg').click();" id="btnSearchImg">사진찾기</button>
	    				<button type="submit" id="btnSaveImg">저장</button>
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
			<tr></tr>
			<tr>
				<td><input type="button" value="등록하기" id="addBt" style="font-size:12px" ></td>
			</tr>
			</table>
	</center>
</body>
</html>