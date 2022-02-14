package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "user_login")
    public WebElement usernameInput;

    @FindBy(id = "user_password")
    public WebElement passwordInput;

    @FindBy(name = "submit")
    public WebElement loginButton;
    public void login(){
      usernameInput.sendKeys(ConfigurationReader.get("username"));
      passwordInput.sendKeys(ConfigurationReader.get("password"));
      loginButton.click();
      Driver.get().navigate().to("http://zero.webappsecurity.com/bank/account-summary.html");
    }
}
