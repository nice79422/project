<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <jsp:include page="/decorator/head.jsp"/>
<script>
	$(document).ready(function(){
		$("#findAddr").click(zipcodeList);
		$("input:button").button().css({
			"font-size" : "10pt"
		});
	});

	function zipcodeList(){
		alert($("#searchAddr").val());
		$.ajax({
			url : '${pageContext.request.contextPath}/base/zipCode.do',
			cache : false,
			dataType : 'json' ,
			data : {"dong" : $("#searchAddr").val(), "method" : "searchDong"},
			success : function(msg){

				$("#zipcodeContainer").html("");
				var buffer=[];

				var zipcodeCol=["우편번호", "시/도", "구/군", "동", "리", "번지"];

				$("<table>",{
					"id" : "table1"
				}).appendTo("#zipcodeContainer");
				$("<tr>",{
					"id" : "colname"
				}).appendTo("#table1");
				$.each(zipcodeCol, function(index, value){
					$("<td>",{
						"html" : value
					}).appendTo("#colname");

				});

				$.each(msg.zipcodebean, function(index1, zipcode){
					$("<tr>",{
						"id" : "tr"+(index1+1)
					}).appendTo("#table1").hover(over,out);

					$("<td>",{
						"html" : zipcode.zipNo
					}).appendTo("#tr"+(index1+1));
					$("<td>",{
						"html" : zipcode.sido
					}).appendTo("#tr"+(index1+1));
					$("<td>",{
						"html" : zipcode.gugun
					}).appendTo("#tr"+(index1+1));
					$("<td>",{
						"html" : zipcode.dong
					}).appendTo("#tr"+(index1+1));
					$("<td>",{
						"html" : zipcode.ri
					}).appendTo("#tr"+(index1+1));
					$("<td>",{
						"html" : zipcode.bunji
					}).appendTo("#tr"+(index1+1));

					$("<input>",{
						"type" : "button",
						"value" : "+",
						"id" : "addZipcode"
					}).appendTo("#tr"+(index1+1)).button().css({
						"font-size" : "8pt"
					}).click(getZipcode);


				});

			},
			error : function(error1, error2, error3){
				alert(error1+" , "+error2+" , "+error3);
			},
		});
	}

	function over(){
		$(this).css("background","#edf870");
	}
	function out(){
		$(this).css("background","#fdfef3");
	}
	function getZipcode(){

	var fullAddr=$(this).siblings().eq(1).html()+" ";
	fullAddr+=$(this).siblings().eq(2).html()+" ";
	fullAddr+=$(this).siblings().eq(3).html()+" ";
	fullAddr+=$(this).siblings().eq(4).html()+" ";
	fullAddr+=$(this).siblings().eq(5).html();
	alert(fullAddr);


	 $("#postCode", opener.document).val($(this).siblings().eq(0).html());
	 $("#addr", opener.document).val(fullAddr);
	 window.close();
	}

</script>
<body>
<center>
<div id="findzipcodeContainer">
<input type="text" id="searchAddr"><input type="button" id="findAddr" value="우편번호찾기">
</div>
<br/>
<div id="zipcodeContainer">
</div>
</center>
</body>
</html>