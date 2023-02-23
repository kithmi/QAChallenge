package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private By inputEmail = By.xpath("//input[@name='email']");
    private  By inputPassword = By.xpath("//input[@name='password']");
    private By btnLogin = By.xpath("//button[@type='submit' and contains(text(),'Einloggen')]");
    private By title = By.xpath("//title[text()='Designertaschen und Accessoires | fashionette']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoginTitle(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(title));
        return driver.getTitle();
    }
    public void enterEmail(String email)
    {
        driver.findElement(inputEmail).sendKeys(email);
    }
    public void enterPassword(String password)
    {
        driver.findElement(inputPassword).sendKeys(password);
    }

    public CustomerAccountPage clickLogin(){
        driver.findElement(btnLogin).click();
        return new CustomerAccountPage(driver);
    }
}
