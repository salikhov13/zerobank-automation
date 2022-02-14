package com.zerobank.step_definitions;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Hooks {
    protected Actions actions;
    protected WebDriverWait wait;
    protected WebDriver driver;
    @Before
    public void setup(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.get("url"));
    }
    @After
    public void tearDown(Scenario scenario){
     if(scenario.isFailed()){
      final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "screenshot");
     }
     Driver.closeDriver();
    }
}
