package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountActivityPage extends BasePage{
    @FindBy(id = "aa_accountId")
    public WebElement accountDropDown;

    @FindBy(xpath = "//div[@id = 'filtered_transactions_for_account']//tbody//tr/td[1]")
    public List<WebElement> transactionDates;

    @FindBy(linkText = "Find Transactions")
    public WebElement findTransactionsTab;

    @FindBy(id = "aa_fromDate")
    public WebElement fromDateBox;

    @FindBy(id = "aa_toDate")
    public WebElement toDateBox;

    @FindBy(css = "[type='Submit']")
    public WebElement findButton;

    @FindBy(xpath = "//div[@id = 'filtered_transactions_for_account']//tbody//tr/td[2]")
    public List<WebElement> transactionDescriptions;

    @FindBy(id = "aa_description")
    public WebElement descriptionInputBox;

    @FindBy(id = "aa_type")
    public WebElement typeDropdown;

    @FindBy(xpath = "//div[@id = 'filtered_transactions_for_account']//tbody//tr/td[3]")
    public List<WebElement> depositColumn;

    @FindBy(xpath = "//div[@id = 'filtered_transactions_for_account']//tbody//tr/td[4]")
    public List<WebElement> withdrawalColumn;

        public void accountDropDown(String option){
            Select select = new Select(accountDropDown);
            select.selectByVisibleText(option);
        }

        public boolean recentDateSorting(){

            List<Date> dateList = BrowserUtils.webElementListToDateList(new AccountActivityPage().transactionDates, "-");
            boolean flag = true;
            for (int i = 0; i < dateList.size()-1; i++) {
                if(dateList.get(i).before(dateList.get(i+1))){
                    flag = false;
                }

            }
            return flag;
        }
        public boolean datesBetween(Date date, Date lowerDate, Date upperDate){
            if(date.before(upperDate) && date.after(lowerDate)){
                return true;
            } else if( date.equals(upperDate)){
                return true;
            }
            else if(date.equals(lowerDate)){
                return true;
            }
            else {
                return false;
            }
        }
}
