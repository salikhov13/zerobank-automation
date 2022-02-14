package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AccountActivity {
    @Then("the {string} page should be displayed")
    public void the_page_should_be_displayed(String title) {
        Driver.get().getTitle();
        Assert.assertTrue("Verifying " + title +  " is displayed",Driver.get().getTitle().contains(title));
    }
    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String option) {
        Select select = new Select(new AccountActivityPage().accountDropDown);
        Assert.assertEquals("Verifying " + option +  " is selected",option, select.getFirstSelectedOption().getText());
    }

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {
        new LoginPage().login();
        new AccountSummaryPage().navigateToTab("Account Activity");
        new AccountActivityPage().findTransactionsTab.click();
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        accountActivityPage.fromDateBox.clear();
        accountActivityPage.toDateBox.clear();
        accountActivityPage.fromDateBox.sendKeys(fromDate);
        accountActivityPage.toDateBox.sendKeys(toDate);
    }

    @When("clicks search")
    public void clicks_search() {
       new AccountActivityPage().findButton.click();
       BrowserUtils.waitFor(1);
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        Assert.assertTrue(new AccountActivityPage().recentDateSorting());
    }

    @Then("the results table should only show transactions dates between {string} to {string}")
    public void the_results_table_should_only_show_transactions_dates_between_to(String date1, String date2) {
        List<Date> datesList = BrowserUtils.webElementListToDateList(new AccountActivityPage().transactionDates, "-");
        Date dateBefore = BrowserUtils.stringToDate(date1, "-");
        Date dateAfter = BrowserUtils.stringToDate(date2, "-");
        for (Date date : datesList) {
            Assert.assertTrue("Assert" + date,new AccountActivityPage().datesBetween(date, dateBefore, dateAfter));
        }
    }

    @Then("the results table should not contain transactions dated {string}")
    public void the_results_table_should_not_contain_transactions_dated(String date) {
        List<Date> datesList = BrowserUtils.webElementListToDateList(new AccountActivityPage().transactionDates, "-");
       Date date2 = BrowserUtils.stringToDate(date, "-");
        for (Date date3 : datesList) {
            Assert.assertFalse("Verifying that datelist does not contain " + date,date3.equals(date));
        }
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String str) {
        new AccountActivityPage().descriptionInputBox.clear();
        new AccountActivityPage().descriptionInputBox.sendKeys(str);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String str) {
        List<String> elementsText = BrowserUtils.getElementsText(new AccountActivityPage().transactionDescriptions);
        Assert.assertTrue("No results",elementsText.size()!= 0);
        for (String s : elementsText) {
            Assert.assertTrue(s+ " message contains " + str,s.contains(str));
        }

    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String str) {
        List<String> elementsText = BrowserUtils.getElementsText(new AccountActivityPage().transactionDescriptions);
        for (String s : elementsText) {
            Assert.assertFalse(s+ " message contains " + str,s.contains(str));
        }
    }


    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_Deposit() {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<String> depositText = BrowserUtils.getElementsText(accountActivityPage.depositColumn);
        boolean flag = false;
        for (String s : depositText) {
            if(!s.isEmpty()){
                flag = true;
            }
        }
       Assert.assertTrue("Verifying the number of deposit transactions",flag);
    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_Withdrawal() {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<String> withdrawalText = BrowserUtils.getElementsText(accountActivityPage.withdrawalColumn);
        boolean flag = false;
        for (String s : withdrawalText) {
            if(!s.isEmpty()){
                flag = true;
            }
        }
        Assert.assertTrue("Verifying the number of withdrawal transactions",flag);
    }


    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_Withdrawal() {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<String> withdrawalText = BrowserUtils.getElementsText(accountActivityPage.withdrawalColumn);
        boolean flag = true;
        for (String s : withdrawalText) {
            if(!s.isEmpty()){
                flag = false;
            }
        }
        Assert.assertTrue("Verifying the withdrawal transactions are empty",flag);
    }
    @When("user selects type {string}")
    public void user_selects_type(String str) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        Select select = new Select(accountActivityPage.typeDropdown);
        select.selectByVisibleText(str);
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_Deposit() {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<String> depositText = BrowserUtils.getElementsText(accountActivityPage.depositColumn);
        boolean flag = true;
        for (String s : depositText) {
            if(!s.isEmpty()){
                flag = false;
            }
        }
        Assert.assertTrue("Verifying the deposit transactions are empty",flag);
    }

}
