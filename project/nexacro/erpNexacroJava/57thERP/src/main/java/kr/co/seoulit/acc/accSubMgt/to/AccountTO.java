package kr.co.seoulit.acc.accSubMgt.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_account")
public class AccountTO extends BaseTO {

		private String 
		accountLevel,
		accountInnerCode,
		parentAccountInnerCode,
		accountCode,
	    accountCharacter,
	    accountName,
	    accountDispalyNameWithCode,
	    accountDisplayName,
	    accountDivision,
	    accountUseCheck,
	    accountDescription,
	    groupCode;

		
		public String getAccountLevel() {
			return accountLevel;
		}

		public void setAccountLevel(String accountLevel) {
			this.accountLevel = accountLevel;
		}

		public String getAccountInnerCode() {
			return accountInnerCode;
		}

		public void setAccountInnerCode(String accountInnerCode) {
			this.accountInnerCode = accountInnerCode;
		}

		public String getParentAccountInnerCode() {
			return parentAccountInnerCode;
		}

		public void setParentAccountInnerCode(String parentAccountInnerCode) {
			this.parentAccountInnerCode = parentAccountInnerCode;
		}

		public String getAccountCode() {
			return accountCode;
		}

		public void setAccountCode(String accountCode) {
			this.accountCode = accountCode;
		}

		public String getAccountCharacter() {
			return accountCharacter;
		}

		public void setAccountCharacter(String accountCharacter) {
			this.accountCharacter = accountCharacter;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getAccountDispalyNameWithCode() {
			return accountDispalyNameWithCode;
		}

		public void setAccountDispalyNameWithCode(String accountDispalyNameWithCode) {
			this.accountDispalyNameWithCode = accountDispalyNameWithCode;
		}

		public String getAccountDisplayName() {
			return accountDisplayName;
		}

		public void setAccountDisplayName(String accountDisplayName) {
			this.accountDisplayName = accountDisplayName;
		}

		public String getAccountDivision() {
			return accountDivision;
		}

		public void setAccountDivision(String accountDivision) {
			this.accountDivision = accountDivision;
		}

		public String getAccountUseCheck() {
			return accountUseCheck;
		}

		public void setAccountUseCheck(String accountUseCheck) {
			this.accountUseCheck = accountUseCheck;
		}

		public String getAccountDescription() {
			return accountDescription;
		}

		public void setAccountDescription(String accountDescription) {
			this.accountDescription = accountDescription;
		}

		public String getGroupCode() {
			return groupCode;
		}

		public void setGroupCode(String groupCode) {
			this.groupCode = groupCode;
		}
		
		
}
