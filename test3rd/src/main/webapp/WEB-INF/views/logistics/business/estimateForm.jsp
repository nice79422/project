
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>견적 관리</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
  <script type="text/javascript">
  var lastID;
  var lastCol;
  var row;
  var lastEstimateID;
  var eventObj;
  var estimateList=[];
  var estimateDetailCodeList=[];
  var estimateCode;
  
  var standByStatus; 

  $(document).ready(function(){
	/* getEstimateList(); */
    /* getEstimateDateList(); */
    $("#addEstimateB, #addEstimateDetailB, #batchProcessB, #saveEstimateDetailB, #pdfB ").button().css("font-size","15px");
    $( "#estimateListTab,#estimateDetailListTab" ).tabs();
    $("#addEstimateB").click(addEstimate);
    $("#addEstimateDetailB").click(addestimateDetail);
    $("#saveEstimateDetailB").click(saveEstimateDetailList);
    $("#batchProcessB").click(batchProcess);
    $( "#pdfB" ).click(getPdfForm);
    
    $("#estimateReviewStartDate").datepicker({
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
	$("#estimateReviewEndDate").datepicker({
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
	
	$("#estimateReviewBtn").button().click(getEstimateDateList);
  });

  function EstimateDetailBean(){
    this.itemBean={};
    this.itemCode="";
    this.estimateAmount="";
    this.estimateCode="";
    this.estimateDetailUnitPrice="";
    this.estimateDetailCode="";
    
  }

  function EstimateBean(){
    this.estimateDetailList=[];
    this.estimateCode="";
    this.estimateDate="";
    this.clientCode="";
    this.contractStatus="";
    this.standByStatus="";
    this.status="";
  }

  
	//견적내역조회
	function getEstimateDateList() {
		$.ajax({
				url : "${pageContext.request.contextPath}/logistics/business/estimate.do",
				type : "post",
				dataType : "json",
				data : {
					"method" : "findEstimateReviewList",
					"startDate" : $("#estimateReviewStartDate").val(),
					"endDate" : $("#estimateReviewEndDate").val()
				},
				success : function(data) {
				estimateReviewListGrid(data.estimateItemList);
				}
			});
		}

  
  function estimateReviewListGrid(estimateReviewList){	//견적리스트 가져오기
	$.jgrid.gridUnload("#estimateListTable");
	$('#estimateListTable').jqGrid(
    {
      data : estimateReviewList,
      datatype:'local',
      jsonReader:{page:'page',total:'total',root:'list'},
      rowNum:15,
      colNames:['견적번호', '견적날짜', '납품처코드', '수주여부', '처리여부'],
      colModel:[
        {name:'estimateCode', width:200, align:"center",editable:true},
        {name:'estimateDate',align:"center",editable:true,
         editoptions:{ dataInit : function(e){
            		   $(e).datepicker({
                       dateFormat: 'yy-mm-dd' ,
                       yearSuffix: '년',
                       monthNames:[' 1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                       dayNamesMin: ['일','월','화','수','목','금','토'] });} } },
        {name:'clientCode',width:140,align:"center",editable:false},
        {name:'contractStatus',align:"center",editable:false},
        {name:'standByStatus',align:"center",hidden:true} ],
	  width : 450,
      height: 300,
      cellEdit : true,
      cellsubmit : 'clientArray',
      cache:false,
      viewrecords : true,
      rownumbers : true,
      rowList:[5,10,15],
      pager:'#estimateListPager',
      caption:'견적 리스트 ',
	  onCellSelect:function(id, iCol){
		standByStatus=$(this).jqGrid ('getCell', id, 'standByStatus');
		  
        $("#estimateListTable").jqGrid('saveCell',lastID,lastCol);
        lastID=id;
        lastCol=iCol;
        lastEstimateID=id;
        estimateCode = $("#estimateListTable").getRowData(id).estimateCode;
        if(iCol==3){
          row=id;
          codeInfo("LO-02","납품처");
          $("#codeContainer").dialog();
        }else if(iCol==1){
          eventObj=event.srcElement;
        }
        $.jgrid.gridUnload('#estimateDetailListTable');
        getEstimateDetailList(estimateCode);
      },
            afterSaveCell : function(rowid, name, val, iRow, iCol) {
                 if (name == "estimateDate") {
                    var estimateDate = $("#estimateListTable").getCell(rowid, "estimateDate").replace(/\-/g, "");
                    var estimateCode = estimateDate + lpad(rowid, '0', 5);
                    $("#estimateListTable").setCell(rowid,"estimateCode", estimateCode);
                }
            }
    });
  }
  function lpad(s, c, n) {
      if (!s || !c || s.length >= n) {return s;}
      var max = (n - s.length) / c.length;
      for (var i = 0; i < max; i++) {s = c + s;}
      return s;
   }
 
  function getEstimateDetailList(estimateCode){
    $.jgrid.gridUnload("#estimateDetailListTable");
    $('#estimateDetailListTable').jqGrid({//견적리스트 클릭시 주문리스트 가져오기
      url:'${pageContext.request.contextPath}/logistics/business/estimate.do',
      postData : {'method' : 'getEstimateDetailList', 'searchCode' : estimateCode},
      datatype:'json',
      jsonReader:{
        page:'page',
        total:'total',
        root:'list'
      },
      rowNum:15,
      colNames:['견적상세번호', '품목', '수량', '견적단가'],
      colModel:[
        {name:'estimateDetailCode',
           width:130,
           align:"center",
           editable:false
          },
          {name:'itemCode',
           width:130,
           align:"center",
           editable:false
          },
          {name:'estimateAmount',
             width:100,
             align:"center",
             editable:true
            },
            {name:'estimateDetailUnitPrice',
         width:130,
         align:"center",
         editable:true,
                }

      ],
      width : 750,
      height: 300,
      cellEdit : true,
      cellsubmit : 'clientArray',
      cache:false,
      viewrecords : true,
      rownumbers : true,
      rowList:[5,10,15],
      pager:'#estimateDetailListPager',
      caption:estimateCode+' 주문 상세 리스트 입니다.',
      onCellSelect:function(id, iCol, cellcontent, e){
        lastID=id;
        lastCol=iCol;
        if(iCol==2){
          row=id;
          codeInfo("LO-08","완제품");
          $("#codeContainer").dialog();
        }
      }
    });
  }

  function codeInfo(baseCode,baseName){
    $.jgrid.gridUnload("#showCode");		//그리드초기화  안해주면 코드 입력이 한개뿐이안됨
    $("#showCode").jqGrid({
      url : '${pageContext.request.contextPath}/base/basicCode.do',
      postData : {"method" : "findCodeList", "code" : baseCode},
      datatype : 'json',
      jsonReader : {page:'page', root:'list', total:'total'},
      colNames:['코드', '코드명'],
      colModel:[
        {name:'detailCode', width:50, align:'center', editable:false},
        {name:'detailCodeName', width:50, align:'center', editable:false}
      ],
      width : 250,
      onSelectRow : function(id){
        if(baseCode=='LO-02'){
          $("#estimateListTable").setCell(row, "clientCode", $("#showCode").getCell(id, 'detailCode'));
        }else if(baseCode=='LO-08'){
        $("#estimateDetailListTable").setCell(row, "itemCode", $("#showCode").getCell(id, 'detailCode'));
      }
        $("#codeContainer").dialog('close');
      },
      rowNum : 10,
      caption : baseName + '코드리스트',
      rowList : [3,6,9]
    });
  }

/*   function addEstimate(){//견적 추가 셋팅
    var newRowNum = $('#estimateReviewListGrid').jqGrid('getDataIDs').length+1;
    $('#estimateReviewListGrid').addRowData(newRowNum,{"contractStatus":"N"});
    var estimateBean = new EstimateBean();
  } */
   function addEstimate(){//견적 추가 셋팅
    var newRowNum = $('#estimateListTable').jqGrid('getDataIDs').length+1;
    $('#estimateListTable').addRowData(newRowNum,{"contractStatus":"N","standByStatus":"N"});
    var estimateBean = new EstimateBean();
  } 

  function addestimateDetail(){//견적품목 추가하기
	  
    var newRowNum = $('#estimateDetailListTable').jqGrid('getDataIDs').length+1;
	$('#estimateDetailListTable').addRowData(newRowNum,{"estimateAmount" :"1"});
  }

  function saveEstimateDetailList(){//수주리스트와 주문리스트를 CTO 형식으로 담음

	  var selectedRowId =  $("#estimateListTable").getGridParam('selrow');
	    var estimateData=$("#estimateListTable").getRowData(selectedRowId);
	    var estimateCode=estimateData.estimateCode;
	    var estimateDetailListIds = $("#estimateDetailListTable").getDataIDs();
	    $("#estimateDetailListTable").jqGrid('saveCell',lastID,lastCol);
	    var estimateBean = new EstimateBean();
		 estimateBean.estimateCode=estimateCode;
	     estimateBean.estimateDate=estimateData.estimateDate;
	     estimateBean.clientCode=estimateData.clientCode;
	     estimateBean.contractStatus=estimateData.contractStatus;
	     estimateBean.standByStatus=estimateData.standByStatus;
	     estimateBean.status="INSERT";
		for(var index in estimateDetailListIds){
	     var estimateDetailData=$("#estimateDetailListTable").getRowData(estimateDetailListIds[index]);
	      var estimateDetailBean = new EstimateDetailBean();
	      estimateDetailBean.estimateCode=estimateCode;
	      estimateDetailBean.estimateDetailCode=estimateDetailData.estimateDetailCode;
	      estimateDetailBean.itemCode=estimateDetailData['itemCode'];
	      estimateDetailBean.estimateAmount=estimateDetailData.estimateAmount;
		  estimateDetailBean.estimateDetailUnitPrice=estimateDetailData.estimateDetailUnitPrice;
		  estimateBean.estimateDetailList.push(estimateDetailBean);
		}
	    estimateList.push(estimateBean);
	  	$("#estimateListTable").jqGrid("setCell",lastEstimateID,4,"N");
	 	
	    alert("임시 저장되었습니다.");
	  }

  function batchProcess(){//일괄 처리
	  if(standByStatus!='Y'){
    $("#estimateDetailListTable").jqGrid('saveCell',lastID,lastCol);
    var estimateBeanList;
	var ids = $("#estimateTable").getDataIDs();
	for(var index in ids){
		var estimateData=$("#estimateTable").getRowData(ids[index]);
		if(estimateData['contractStatus']=="Y"){
			var estimateBean=new EstimateBean();
			    estimateBean.estimateCode=estimateCode;
			    estimateBean.estimateDate=estimateData.estimateDate;
			    estimateBean.clientCode=estimateData.clientCode;
			    estimateBean.contractStatus=estimateData.contractStatus;
			    estimateBean.standByStatus=estimateData.standByStatus;
				estimateBean.status="UPDATE";
		}
		   estimateList.push(estimateBean);
		    alert("임시 저장");
		}
    var check=confirm("일괄처리 하시겠습니까?");
    if(estimateList.length==0){
      alert("처리할 내용이 없습니다")
      return;
    };
    if(check){}else{
      location.href='${pageContext.request.contextPath}/logistics/business/estimateForm.html';
      return false;
    }
    estimateBeanList=$.toJSON(estimateList);

    $.ajax({
         url : '${pageContext.request.contextPath}/logistics/business/estimate.do',
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        type : 'post',
        data :{"method" : 'registerEstimate', 'estimateBeanList':estimateBeanList},
        cache:false,
        success : function(data, textStatus, jqXHR){
        if(data.errorCode<0){
          alert(data.errorMsg);
        }else{
            alert("견적등록이  완료되었습니다.");
               location.reload();
               estimateList=[];
               
            var rowid=$("#estimateListTable").jqGrid('getGridParam','selrow');
           	var tempList=$("#estimateListTable").jqGrid('getRowData', rowid);
            var estimateBean=new EstimateBean();
           	estimateBean.estimateCode=tempList.estimateCode;
           	var newEstimate = $.toJSON(estimateBean);
           	standByModifyEstimate(newEstimate);
          //	location.href='${pageContext.request.contextPath}/logistics/business/contractListForm.html';
          }
        },
        error : function(jqXHR, textStatus, error){
           alert("일괄처리 오류입니다!");
        }
    });
	  }else{
		alert("처리가완료된 견적은 수정이 불가능합니다");
	}
	
  }
	function standByModifyEstimate(newEstimate){

     $.ajax({
	    url : "${pageContext.request.contextPath}/logistics/business/estimate.do",
	    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	    type : 'post',
	    dataType:'json',
	    cache:false,
	    data :{'method' : 'standByEstimate', 'bean':newEstimate},
	    complete : function(){
/* 	    	alert("견적의 처리여부가  Y로 변경되었습니다."); */
	    	location.href="${pageContext.request.contextPath}/logistics/business/estimateForm.html";
	    }
	}); 
  }

	function getPdfForm(){//pdf 출력하기
		var rowid=$("#estimateListTable").jqGrid('getGridParam','selrow');
		var estimateData=$("#estimateListTable").jqGrid('getRowData', rowid);
		var estimateCode=estimateData.estimateCode;
		alert(estimateCode);
	    window.open("${pageContext.request.contextPath}/report.do?method=estimateReport&format=pdf&orderDraftNo="+estimateCode, "window", "width=1600, height=1000");
	 }
  </script>
</head>
<body>

  <div id="estimateListTab" style=" width: 500px; height: 500px " >
    <ul>
        <li><a href="#estimateListDiv">견적 관리</a></li>
    </ul>
    <div id="estimateListDiv">
   	 견적일자 선택&nbsp;&nbsp;
   	 <input type="text"	id="estimateReviewStartDate" size='8'>&nbsp;~&nbsp;
   	 <input	type="text" id="estimateReviewEndDate" size='8'>
	 <input	type="button" id="estimateReviewBtn" size='7'  value="검색">
		
     <input type="button" id="addEstimateB" value="견적추가" >&nbsp;&nbsp;
     <input type="button" id="batchProcessB" value="일괄처리">&nbsp;&nbsp;
     <input type="button" id="pdfB" value="PDF 출력">&nbsp;&nbsp;
     <center>
       <table id="estimateListTable"></table>
    <div id="estimateListPager" ></div>
      </center>
    </div>
  </div>
  <div id="estimateDetailListTab" style=" width: 800px; height: 500px">
    <ul>
        <li><a href="#estimateDetailListTableTab">견적 품목 조회/등록</a></li>
     </ul>
     <div id="estimateDetailListTableTab">
       <input type="button" id="addEstimateDetailB"  value="견적품목추가">&nbsp;&nbsp;
       <input type="button" id="saveEstimateDetailB" value="저장">
       <center>
         <table id="estimateDetailListTable"></table>
        <div id="estimateDetailListPager" ></div>
       </center>
     </div>
  </div>
<div id="codeContainer" title="선택하세요">
  <center>
    <table id="showCode"></table>
  </center>
</div>

</body>
</html>
