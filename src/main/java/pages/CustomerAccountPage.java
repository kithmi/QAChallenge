package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerAccountPage {
    WebDriver driver;

    private By welcomeMessage = By.xpath("//span[@class='account-firstname']/parent::div");
    public CustomerAccountPage(WebDriver driver){
        this.driver = driver;
   }

    public String getWelcomeCustomerMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
        return driver.findElement(welcomeMessage).getText();
    }
}
