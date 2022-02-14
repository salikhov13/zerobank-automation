package com.zerobank.step_definitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;

public class Login {


    @Given("the user is logged in")
    public void the_user_is_loggen_in() {
        new LoginPage().login();
    }

}
