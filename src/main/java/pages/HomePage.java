package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;

    private By userLoginIcon = By.xpath("//a[@data-id = 'user login']");
    private By headerLinks = By.xpath("//div[@class='header__navigation']/ul//li");
    private By title = By.xpath("//title[text()='fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen ']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public String getLoginTitle(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(title));
        return driver.getTitle();
    }

    public LoginPage clickUserLoginIcon(){
        driver.findElement(userLoginIcon).click();
        return new LoginPage(driver);
    }

    public void acceptCookiesAlert(){
        WebElement shadowHost = driver.findElement (By.id ("usercentrics-root"));
        SearchContext shadowRoot = getShadowRootElement(shadowHost);
        WebElement btnAccept = shadowRoot.findElement(By.cssSelector("  button[data-testid='uc-accept-all-button']"));
        if(btnAccept.isDisplayed()){
            btnAccept.click();
        }
    }
    public ItemListPage clickHeaderLink(String linkText){

        List<WebElement> hLinks = driver.findElements(headerLinks);
        for (WebElement ele:hLinks) {
            if(ele.getText().equalsIgnoreCase(linkText)){
                ele.findElement(By.tagName("a")).click();
                return new ItemListPage(driver);
            }
        }
        return null;
    }
    public SearchContext getShadowRootElement(WebElement element) {
        SearchContext ele = (SearchContext) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

}
