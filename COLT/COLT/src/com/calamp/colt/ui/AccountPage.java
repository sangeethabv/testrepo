package com.calamp.colt.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
	WebDriver driver;
	
	public AccountPage(WebDriver driver){
		this.driver = driver;
	}
	public void waitForDasboardPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".main")));
	}
	
	public void waitForAccountPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("html/body/div[5]/div[2]")));
	}
	
	public void waitForSelectSubAccountPopup(){
		WebDriverWait wait = new WebDriverWait(driver,300);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForSuccessAlert(){
		WebDriverWait wait = new WebDriverWait(driver,300);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public WebElement getMyAccountTab(){
		return driver.findElement(By.linkText("My Account"));
	}
	public WebElement getCreateSubAccountButton(){
		return driver.findElement(By.id("create_sub_link"));
	}
	public WebElement getParentAccount(){
		return driver.findElement(By.cssSelector(".parent_name"));
	}
	public WebElement getAccountName(){
		return driver.findElement(By.id("account_name"));
	}
	public WebElement getFirstName(){
		return driver.findElement(By.id("fname"));
	}
	public WebElement getLastName(){
		return driver.findElement(By.id("lname"));
	}
	public WebElement getAddress1(){
		return driver.findElement(By.id("addr_1"));
	}
	public WebElement getAddress2(){
		return driver.findElement(By.id("addr_2"));
	}
	public WebElement getCity(){
		return driver.findElement(By.id("city"));
	}
	public WebElement getZip(){
		return driver.findElement(By.id("zip"));
	}
	public WebElement getPhone1(){
		return driver.findElement(By.id("phone1"));
	}
	public WebElement getStateDropdown(){
		return driver.findElement(By.id("state_select_text"));
	}
	public WebElement getCountryDropDown(){
		return driver.findElement(By.id("country_select_button"));
	}
	public WebElement getLanguageDropdown(){
		return driver.findElement(By.id("lang_select_button"));
	}
	public WebElement getAirtimeStoreCheckBox(){
		return driver.findElement(By.xpath(".//*[@id='allow_airtime_store']"));
	}
	public WebElement getHardwareStoreCheckBox(){
		return driver.findElement(By.xpath(".//*[@id='allow_airtime_store']"));
	}
	public WebElement getSkysmartCheckbox(){
		return driver.findElement(By.xpath(".//*[@id='check_enable_skysmart_upgrade']"));
	}
	public WebElement getSkipInstallProcessCheckBox(){
		return driver.findElement(By.xpath(".//*[@id='check_skip_install']"));
	}
	public WebElement getReceiveEmailCheckbox(){
		return driver.findElement(By.xpath(".//*[@id='check_install_notification']"));
	}
	public WebElement getMandatoryOdometerCheckbox(){
		return driver.findElement(By.xpath(".//*[@id='check_mandatory_inst_odometer']"));
	}
	public WebElement getLocValidationCheckboxx(){
		return driver.findElement(By.xpath(".//*[@id='check_enable_loc_validation']"));
	}
	public WebElement getEmail(){
		return driver.findElement(By.id("acc_email"));
	}
	public WebElement getAddAccountButton(){
		return driver.findElement(By.cssSelector(".stdbutt.b_green.update_account"));
	}
	public WebElement getChangeThisLink(){
		return driver.findElement(By.cssSelector(".change"));
	}
	public WebElement getPhone2(){
		return driver.findElement(By.id("phone2"));
	}
	public WebElement getCustomSupportCheckBox(){
		return driver.findElement(By.xpath(".//*[@id='use_custom_supp_page']"));
	}
	public WebElement getCommandRetryCheckbox(){
		return driver.findElement(By.xpath(".//*[@id='check_auto_retry']"));
	}
	public WebElement getManageAccInfoTab(){
		return driver.findElement(By.cssSelector("#man_acc_info>span"));
	}
	public WebElement getUpdateAccButton(){
		return driver.findElement(By.xpath(".//*[@id='address_content']/div[2]/div/div[10]/button[1]"));
	}
	public WebElement getSubAccButton(){
		return driver.findElement(By.cssSelector("#select_sub_link>span"));
	}
	public WebElement getSelectSubAccAlert(){
		return driver.findElement(By.xpath("html/body/div[9]"));
	}
	public WebElement getSearchTextbox(){
		return driver.findElement(By.id("act_search_input"));
	}
	public WebElement getSelectAccountButton(){
		return driver.findElement(By.xpath("html/body/div[9]/div[3]/button[2]"));
	}
}
