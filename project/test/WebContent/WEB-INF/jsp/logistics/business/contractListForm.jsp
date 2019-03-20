<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수주 관리</title>
  <%-- <jsp:include page="/decorator/head.jsp"/> --%>
   <script type="text/javascript">
   var lastID;
   var lastCol;
   var row;
   var lastContractID;
   var eventObj;
   var contractList=[];
   var detailCodeList=[];
   var contractCode;
   var estimateCode;
   var flag = 0;
   var estimateList = [];
   var emptyContract = {};
   var emptyContractItem = {};
   var estimateDetailList = [];
   var estimateList = [];
   
   var standByStatus;
   
   $(document).ready(function(){
      getOrderList();

      $("#addContractB, #addOrderB, #batchProcessB, #saveOrderB,#addAutoContractB,#addEstimateOrderB").button().css("font-size","10px");
      $("#contractListTab,#orderListTab" ).tabs();
      $("#addAutoContractB").click(callEstimate);
      $("#addContractB").click(addContract);
/*    $("#addOrderB").click(addOrder); */
      $("#saveOrderB").click(saveOrderList);
      $("#batchProcessB").click(batchProcess);
      
      $("#contractReviewStartDate").datepicker({
         showMonthAfterYear : true,
         changeMonth : true,
         changeYear : true,
         dateFormat : "yy-mm-dd"
      });
      $("#contractReviewEndDate").datepicker({
         showMonthAfterYear : true,
         changeMonth : true,
         changeYear : true,
         dateFormat : "yy-mm-dd"
      });
       $("#contractReviewBtn").button().click(getContractDateList); 
   });

   function ContractBean(){
      this.contractDetailList=[];
      this.contractCode="";
      this.contractDate="";
      this.estimateCode="";
      this.produceStatus="";
      this.standByStatus="";
      this.status="";
   }
   
   
   function ContractDetailBean(){
      this.itemBean={};
      this.itemCode="";
      this.contractQuantity="";
      this.deliveryScheduleDate="";
      this.contractCode="";
      this.clientCode="";
      this.contractDetailCode="";
      this.mpsStatus="";
   }
   function EstimateDetailBean(){
      this.estimateAmount="";
      this.itemCode="";
   }
   function EstimateBean(){
      this.estimateCode="";
   }
    function getContractDateList() {
         $.ajax({
               url : "${pageContext.request.contextPath}/logistics/business/contract.do",
               type : "post",
               dataType : "json",
               data : {
                  "method" : "findContractReviewList",
                  "startDate" : $("#contractReviewStartDate").val(),
                  "endDate" : $("#contractReviewEndDate").val()
               },
               success : function(data) {
               getContractList(data.contractItemList);
               }
            });
         }
   
   function getContractList(){   //수주리스트 가져오기
      $.jgrid.gridUnload("#contractListTable");
      $('#contractListTable').jqGrid(
      {   url:'${pageContext.request.contextPath}/logistics/business/contract.do',      
            postData : {"method" : "findContractList"},
         datatype:'json',
         jsonReader:{page:'page',total:'total',root:'list'},
         rowNum:15,
         colNames:['수주코드', '견적코드', '수주날짜', '생산여부', '처리여부'],
         colModel:[
            {name:'contractCode',width:160,align:"center",editable:false},
            {name:'estimateCode',width:160,align:"center",editable:false},
            {name:'contractDate',width:110,align:"center",editable:true,
             editoptions:{ dataInit : function(e){
                         $(e).datepicker({
                        dateFormat: 'yy-mm-dd' ,
                        yearSuffix: '년',
                          monthNames:[' 1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                          dayNamesMin: ['일','월','화','수','목','금','토']});}}},
            {name:'produceStatus',width:80 ,align:"center", editable:false},
            {name:'standByStatus',width:80 ,align:"center", hidden:true}],
         width : 450,
         height: 300,
         cellEdit : true,
         cellsubmit : 'clientArray',
         cache:false,
         viewrecords : true,
         rownumbers : true,
         rowList:[5,10,15],
         pager:'#contractListPager',
         caption:'수주 리스트 ',
         onCellSelect:function(id, iCol){            
            $("#contractListTable").jqGrid('saveCell',lastID,lastCol);
            lastID=id;
            lastCol=iCol;
            lastContractID=id;
            estimateCode= $("#contractListTable").getRowData(id).estimateCode;
            contractCode = $("#contractListTable").getRowData(id).contractCode;
            
         if(iCol==1){
                  eventObj=event.srcElemtent;
               }   
               
               
               if(contractCode == ""){
                  applyEstimate();
               }else{   
                  if(iCol==5||iCol==3||iCol==4){applyEstimate();
                  }else{ 
                  getOrderList(contractCode);
                  }}
               },
            afterSaveCell : function(rowid, name, val, iRow,
                    iCol) {
                 if (name == "contractDate") {
                    var contractDate = $("#contractListTable")
                          .getCell(rowid, "contractDate")
                          .replace(/\-/g, "");
                    var contractCode = contractDate
                          + lpad(rowid, '0', 5);
                    $("#contractListTable").setCell(rowid,
                          "contractCode", contractCode);
                 }
              
            if (flag == 1) {
               applyEstimate();
               flag = 0;
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

   function getOrderList(contractCode){
      $.jgrid.gridUnload("#orderListTable");
      $('#orderListTable').jqGrid({
         url:'${pageContext.request.contextPath}/logistics/business/contract.do',
         postData : {'method' : 'findContractDetailList', 'searchCode' : contractCode},
         datatype:'json',
         jsonReader:{page:'page',total:'total',root:'list'},
         rowNum:15,
         colNames:['수주품목번호', '품목', '수량', '납품일', '납풉처', 'MPS여부'],
         colModel:[
            {name:'contractDetailCode',width:160,align:"center",editable:false},
            {name:'itemBean.itemCode',width:130,align:"center",editable:false},
            {name:'contractQuantity',width:100,align:"center",editable:true},
            {name:'deliveryScheduleDate',width:130,align:"center",editable:true,
             editoptions:{ dataInit : function(e){
                        $(e).datepicker({
                         dateFormat: 'yy-mm-dd' ,
                         yearSuffix: '년',
                          monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                          dayNamesMin: ['일','월','화','수','목','금','토']});}}},
            {name:'clientCode',width:100,align:"center",editable:false},
            {name:'mpsStatus',width:80, align:"center", editable:false}],
         width : 750,
         height: 300,
         cellEdit : true,
         cellsubmit : 'clientArray',
         cache:false,
         viewrecords : true,
         rownumbers : true,
         rowList:[5,10,15],
         pager:'#orderListPager',
         caption:contractCode+' 주문 상세 리스트 입니다.',
         onCellSelect:function(id, iCol, cellcontent, e){
            lastID=id;
            lastCol=iCol;
            if(iCol==2){
               row=id;
               codeInfo("LO-08");
               $("#codeContainer").dialog();
            }else if(iCol==5){
               row=id;
               codeInfo("LO-02");
               $("#codeContainer").dialog();
               
            }
         }
      });
   }

   function codeInfo(baseCode){
      $.jgrid.gridUnload("#showCode");   //그리드초기화  안해주면 코드 입력이 한개뿐이안됨
      $("#showCode").jqGrid({
         url : '${pageContext.request.contextPath}/base/basicCode.do',
         postData : {"method" : "findCodeList", "code" :baseCode},
         datatype : 'json',
         jsonReader : {page:'page', root:'list', total:'total'},
         colNames:['코드', '코드명'],
         colModel:[
            {name:'detailCode', width:50, align:'center', editable:false},
            {name:'detailCodeName', width:50, align:'center', editable:false}
         ],
         width : 250,
         onSelectRow : function(id){
            if(baseCode=='LO-08'){
            $("#orderListTable").setCell(row, "itemBean.itemCode", $("#showCode").getCell(id, 'detailCode'));
         }else if(baseCode=='LO-02'){
                $("#orderListTable").setCell(row, "clientCode", $("#showCode").getCell(id, 'detailCode'));
         }
              $("#codeContainer").dialog('close');
         },
         rowNum : 10,
         caption : '코드리스트',
         rowList : [3,6,9]
      });
   }
   
   function addContract(){//수주 추가 셋팅
      var newRowNum = $('#contractListTable').jqGrid('getDataIDs').length+1;
      $('#contractListTable').addRowData(newRowNum,{"produceStatus":"N","standByStatus":"N"});
      var contractBean = new ContractBean();
   }
   function addOrder(){//수주 row 추가하기
      var newRowNum = $('#orderListTable').jqGrid('getDataIDs').length+1;
      $('#orderListTable').addRowData(newRowNum,{"mpsStatus":"N"});
   }
   
   

   
   
   function saveOrderList(){//수주리스트와 주문리스트를 CTO 형식으로 담음
      var selectedRowId =  $("#contractListTable").getGridParam('selrow');
      var contractData=$("#contractListTable").getRowData(selectedRowId);
      var contractCode=contractData.contractCode;
      var orderListIds = $("#orderListTable").getDataIDs();
      $("#orderListTable").jqGrid('saveCell',lastID,lastCol);
      var contractBean = new ContractBean();
      contractBean.contractCode=contractData.contractCode;
      contractBean.contractDate=contractData.contractDate;
      contractBean.estimateCode=contractData.estimateCode;
      contractBean.produceStatus=contractData.produceStatus;
      contractBean.standByStatus=contractData.standByStatus;
      contractBean.status="완료";
      for(var index in orderListIds){
         var orderData=$("#orderListTable").getRowData(orderListIds[index]);
         var contractDetailBean = new ContractDetailBean();
         contractDetailBean.itemCode=orderData['itemBean.itemCode'];
         contractDetailBean.contractQuantity=orderData.contractQuantity;
         contractDetailBean.deliveryScheduleDate=orderData.deliveryScheduleDate;
         contractDetailBean.clientCode=orderData.clientCode;
         contractDetailBean.contractCode=contractCode;
         contractDetailBean.contractDetailCode=orderData.contractDetailCode;
         contractDetailBean.mpsStatus=orderData.mpsStatus;
         contractBean.contractDetailList.push(contractDetailBean);
      }
      contractList.push(contractBean);
      
      $("#contractListTable").jqGrid("setCell",lastContractID,4,"N");
      alert("임시 저장되었습니다.");
   }

   function batchProcess(){//일괄 처리
     if(standByStatus!='Y'){ 
          $("#estimateDetailListTable").jqGrid('saveCell',lastID,lastCol);
      $("#orderListTable").jqGrid('saveCell',lastID,lastCol);
      var contractBeanListY;
      var check=confirm("일괄처리 하시겠습니까?");
      if(contractList.length==0){
         alert("처리할 내용이 없습니다")
         return;
      };
      if(check){}else{
         location.href='${pageContext.request.contextPath}/logistics/business/contractListForm.html';
         return false;
      }
      contractBeanList=$.toJSON(contractList);
   
      $.ajax({
         url : '${pageContext.request.contextPath}/logistics/business/contract.do',
          contentType : "application/x-www-form-urlencoded; charset=UTF-8",
          type : 'post',
          data :{"method" : 'registerContract', 'contractBeanList':contractBeanList},
          cache:false,
          success : function(data, textStatus, jqXHR){
            if(data.errorCode<0){
               alert(data.errorMsg);
            }else{
                alert("수주등록이 완료되었습니다.");
                  location.reload();
                  contarctList=[];
                  
                 var rowid=$("#contractListTable").jqGrid('getGridParam','selrow');
                var tempList=$("#contractListTable").jqGrid('getRowData', rowid);
                var estimateBean=new EstimateBean();
                var contractBean=new ContractBean();
                estimateBean.estimateCode=tempList.estimateCode;
                contractBean.contractCode=tempList.contractCode;
                var newEstimate = $.toJSON(estimateBean);
                var newContract = $.toJSON(contractBean);
                modifyEstimate(newEstimate);
                standByContract(newContract);
             //   location.href='${pageContext.request.contextPath}/logistics/business/contractListForm.html';
             }
          },
          error : function(jqXHR, textStatus, error){
              alert("일괄처리 오류입니다!");
          }
      });
       }else{
         alert("처리가완료된 수주는 수정이 불가능합니다");
      } 
      
   }
   function modifyEstimate(newEstimate){
      $.ajax({
          url : "${pageContext.request.contextPath}/logistics/business/estimate.do",
          contentType : "application/x-www-form-urlencoded; charset=UTF-8",
          type : 'post',
          dataType:'json',
          cache:false,
          data :{'method' : 'modifyEstimate','bean' : newEstimate},
          complete : function(){
             alert("견적의 수주여부가  Y로 변경되었습니다.");
             location.href="${pageContext.request.contextPath}/logistics/business/contractListForm.html";
          }
      });
   }
   function callEstimate(rowid, iCol, code){
      flag = 1;
      $("#EstimateContainer").dialog({width:"550px"});
      $("#EstimateContainer").dialog("open");
      $.ajax({
         url : "${pageContext.request.contextPath}/logistics/business/estimate.do",
         type : "post",
         data : {"method" : "findEstimate"},
         dataType : "json",
         success : function(data) {
            if (data.errorCode < 0) {
               alert(data.errorMsg);
            } else {
               estimateList = data.estimateList;
               estimateListGrid();
            }
         }
      });
   }
   //견적목록
   function estimateListGrid(){
      $.jgrid.gridUnload("#showEstimate");
      $("#showEstimate").jqGrid({
                     data : estimateList,
                     datatype : "local",
                     align : "center",
                     width : 500,
                     height : 200,
                     rownumbers : true,
                     cellEdit : true,
                     loadonce:true,
                     cellsubmit : "clientArray",
                     caption : "견적",
                     colNames : [ "견적번호","견적날짜","납품처코드", "수주적용", "상세" ],
                     colModel : [{name : "estimateCode",width:40, align:"center", editable:false}, 
                              {name : "estimateDate",width:30, align:"center", editable:false},
                              {name : "clientCode",width:30, align:"center", editable:false}, 
                              {name : "contractStatus",width:20, align:"center",editable:false}, 
                              {name : "estimateDetailList",hidden:true} ],
                     rowNum : 10,
                     rowList : [ 5, 10, 15 ],
                     pager : "#estimateListPager",
                     ondblClickRow : function(rowid) {
                        estimateCode = $("#showEstimate").getCell(rowid,"estimateCode");
                        contractStatus= $("#showEstimate").getCell(rowid,"contractStatus");
                        if (contractStatus=="Y"){
                           alert("이미 수주가 적용된 것은 등록할 수 없습니다.");
                           callEstimate();
                        }else{
                        
                        estimateDetailList = estimateList[rowid - 1].estimateDetailList;
                        
                     $("#EstimateContainer").dialog("close");

                        //수주리스트에 견적번호 넣기적용                        
                        var conData = $("#showEstimate").getRowData(
                              rowid);
                        var newRowNum = $('#contractListTable').jqGrid(
                              'getDataIDs').length + 1;
                        $('#contractListTable').addRowData(newRowNum, {
                           "clientCode" : conData.clientCode,
                           "produceStatus" : "N",
                           "estimateCode" : conData.estimateCode,
                           "standByStatus" : "N"
                        });

                        applyEstimate();                  
                     
                     }}
                  });
      }
   //수주품목에견적품목적용
   function applyEstimate(){
      $("#orderListTable").clearGridData();
      for (var i = 0; i < estimateDetailList.length; i++) {
      var newRowNum = $('#orderListTable').jqGrid('getDataIDs').length+1;
      $('#orderListTable').addRowData(newRowNum,{"mpsStatus":"N","clientCode":estimateDetailList[i].clientCode,"contractQuantity":estimateDetailList[i].estimateAmount,"itemBean.itemCode":estimateDetailList[i].itemCode});
      }
      }

   function standByContract(newContract){
      $.ajax({
          url : "${pageContext.request.contextPath}/logistics/business/contract.do",
          contentType : "application/x-www-form-urlencoded; charset=UTF-8",
          type : 'post',
          dataType:'json',
          cache:false,
          data :{'method' : 'standByContract','bean' : newEstimate},
          complete : function(){
/*              alert("견적의 처리여부가  Y로 변경되었습니다."); */
             location.href="${pageContext.request.contextPath}/logistics/business/contractListForm.html";
          }
      });
   }
   

   


   </script>
</head>

<body>

<center>
   <div id="contractListTab" style=" width: 500px; height: 500px">
      <ul>
          <li><a href="#contractListDiv">수주 조회/등록</a></li>
      </ul>
      <div id="contractListDiv">
           수주일자 선택&nbsp;&nbsp;
             <input type="text"   id="contractReviewStartDate" size='8'>&nbsp;~&nbsp;
            <input type="text" id="contractReviewEndDate" size='8'>
          <input type="button" id="contractReviewBtn"  value="검색">
      
         <input type="button" id="addAutoContractB" value="견적수주추가">&nbsp;&nbsp;
           <input type="button" id="addContractB" value="수주추가">&nbsp;&nbsp;
         <input type="button" id="batchProcessB" value="일괄처리">&nbsp;&nbsp;
         <center>
            <table id="contractListTable"></table>
            <div id="contractListPager"></div>
         </center>
      </div>
   </div>

   <div id="orderListTab" style=" width: 800px; height: 500px">
      <ul>
          <li><a href="#orderListTableTab">수주 품목 조회/등록</a></li>
       </ul>
       <div id="orderListTableTab">
       
          <input type="button" id="addOrderB" value="수주품목추가">&nbsp;&nbsp;
          <input type="button" id="saveOrderB" value="저장">&nbsp;&nbsp;
          <center>
             <table id="orderListTable"></table>
            <div id="orderListPager" ></div>
          </center>
       </div>
   </div>
   
<div id="codeContainer" title="선택하세요">
   <center>
      <table id="showCode"></table>
      
   </center>
</div>

<div id="EstimateContainer" title="수주등록할 견적을 선택하세요">
  <center>
    <table id="showEstimate"></table>
    <div id="estimateListPager"></div>
  </center>
</div>
</center>

</body>
</html>