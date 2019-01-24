//New Script File
﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿﻿
function baseTransaction(){
http.Sync=true;
	//transaction("findDeptList","svcBase::/department.do?method=findDeptList","","dsDepartment=dsDepartment","","callback");

	//transaction("findEmployeeList","svcBase::/employee.do?method=findEmployeeList","","dsEmployee=dsEmployee","","callback");	

	//transaction("findPositionList","svcBase::/position.do?method=findPositionList","","dsPosition=dsPosition","","callback");

	//transaction("findMenuList","svcBase::/menu.do?method=findMenuList","","dsMenu=dsMenu","","callback");

	//transaction("findMenuAuthorityList","svcBase::/authority.do?method=findMenuAuthorityList","","dsMenuAuthority=dsMenuAuthority","authorityCode="+g_authorityCode,"callback");
	
	//transaction("findAuthorityList","svcBase::/authority.do?method=findAuthorityList","","dsAuthority=dsAuthority","","callback");
	
	//transaction("findSalaryList","svcBase::/salary.do?method=findSalaryList","","dsEtcSal=dsEtcSal dsOvertimeSal=dsOvertimeSal","","callback");
	
	//transaction("findCodeList","svcBase::/code.do?method=findCodeList","","dsCode=dsCode dsDetailCode=dsDetailCode","","callback");

	//transaction("findHobongList","svcHrCircumstance::/hobong.do?method=findHobongList","","dsHobong=dsHobong","","callback");
	
	//transaction("findDeductionsInsuranceList","svcHrCircumstance::/deductionInsurance.do?method=findDeductionInsuranceList","","dsDeductionTax=dsDeductionTax dsIncomeTax=dsIncomeTax","","callback");
	
	//transaction("findBaseWorkTimeList","svcHrCircumstance::/baseWorkTime.do?method=findBaseWorkTimeList","","dsBaseWorkTime=dsBaseWorkTime","","callback");

	//transaction("findHolidayListAll","svcHrCircumstance::/holiday.do?method=findHolidayListAll","","dsHoliday=dsHoliday","","callback");

	//transaction("findDailyAttdList","svcHrAttendance::/dailyAttd.do?method=findDailyAttdList","","dsDailyAttd=dsDailyAttd","empCode='" + g_empCode + "'","callback");

	//transaction("findDailyAttdList","svcHrAttendance::/dailyAttd.do?method=findDailyAttdList","","dsDailyAttd=dsDailyAttd","empCode='" + g_empCode + "'","callback");
	
	http.Sync=false;
}

function callback(trid, ErrorCode, ErrorMsg) {
	if(ErrorCode<0){
		alert(ErrorMsg);
	}
}