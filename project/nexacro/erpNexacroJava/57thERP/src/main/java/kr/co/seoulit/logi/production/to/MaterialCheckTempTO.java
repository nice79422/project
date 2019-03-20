package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_materialCheckTempList")
public class MaterialCheckTempTO extends BaseTO{
String itemCode
,itemName
,stocktaking
,safetyStocktaking
,stockStatus
,safetyStockStatus;

public String getItemCode() {
	return itemCode;
}

public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getStocktaking() {
	return stocktaking;
}

public void setStocktaking(String stocktaking) {
	this.stocktaking = stocktaking;
}

public String getSafetyStocktaking() {
	return safetyStocktaking;
}

public void setSafetyStocktaking(String safetyStocktaking) {
	this.safetyStocktaking = safetyStocktaking;
}

public String getStockStatus() {
	return stockStatus;
}

public void setStockStatus(String stockStatus) {
	this.stockStatus = stockStatus;
}

public String getSafetyStockStatus() {
	return safetyStockStatus;
}

public void setSafetyStockStatus(String safetyStockStatus) {
	this.safetyStockStatus = safetyStockStatus;
}

}