package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSummaryPage extends BasePage{
    @FindBy(linkText = "Savings")
    public WebElement savings;
    @FindBy(linkText = "Brokerage")
    public WebElement brokerageLink;
    @FindBy(linkText = "Checking")
    public WebElement checkingLink;
    @FindBy(linkText = "Credit Card")
    public WebElement creditCardLink;
    @FindBy(linkText = "Loan")
    public WebElement loanLink;
}
