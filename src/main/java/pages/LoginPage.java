package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    String pageTitle = "Designertaschen und Accessoires | fashionette";

    private By inputEmail = By.xpath("//div[contains(@class,'form--login')]//input[@name='email']");
    private  By inputPassword = By.xpath("//div[contains(@class,'form--login')]//input[@name='password']");
    private By btnLogin = By.xpath("//button[@type='submit' and contains(text(),'Einloggen')]");
    private By iconValidPassword = By.xpath("//div[@class='form__item text__left inputfield login--password inputfield--required input--valid']//input[@name='password']");
    private By iconValidEmail = By.xpath("//div[@class='form__item text__left inputfield input--valid']//input[@name='email']");
    private By title = By.xpath("//title[text()='"+pageTitle+"']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoginTitle(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(title));
        return driver.getTitle();
    }
    public void enterEmail(String email)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
        driver.findElement(inputEmail).clear();
        driver.findElement(inputEmail).sendKeys(email);
    }
    public boolean getValidEmailTick(){
        driver.findElement(inputEmail).sendKeys(Keys.TAB);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(iconValidEmail));
        return driver.findElement(inputEmail).getAttribute("aria-invalid").contains("false");
    }
    public void enterPassword(String password)
    {
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);
    }
    public boolean getValidPasswordTick(){
        driver.findElement(inputEmail).sendKeys(Keys.TAB);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(iconValidPassword));
        return driver.findElement(inputPassword).getAttribute("aria-invalid").contains("false");
    }
    public CustomerAccountPage clickLogin(){
        driver.findElement(btnLogin).click();
        return new CustomerAccountPage(driver);
    }
}
