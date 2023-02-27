package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;
    String pageTitle = "fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen ";

    private By userLoginIcon = By.xpath("//a[@data-id = 'user login']");
    private By headerLinks = By.xpath("//div[@class='header__navigation']/ul//li");
    private By eleShadowHost = By.id ("usercentrics-root");
    private By btnAcceptAllCookies = By.cssSelector("  button[data-testid='uc-accept-all-button']");
    private By title = By.xpath("//title[text()='"+pageTitle+"']");

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
        WebElement shadowHost = driver.findElement (eleShadowHost);
        SearchContext shadowRoot = getShadowRootElement(shadowHost);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(shadowRoot.findElement(btnAcceptAllCookies)));
        WebElement btnAccept = shadowRoot.findElement(btnAcceptAllCookies);
        if(btnAccept.isDisplayed()){
            btnAccept.click();
        }
    }

    /**
     * This method is to select a link from the header list
     * @param linkText link name as in the header example: Taschen
     * @return ItemListPage object if link found, null if link not found
     */
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

    /**
     * This method is to capture the shadow root element
     * @param element shadow host element
     * @return search context element
     */
    public SearchContext getShadowRootElement(WebElement element) {
        return (SearchContext) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
    }

}
