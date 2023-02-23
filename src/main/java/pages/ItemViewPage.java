package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemViewPage {
    private WebDriver driver;
    private By btnAddToCart = By.xpath("//div[contains(@class,'add-to-cart')]//div[contains(text(),'In den Warenkorb')]");
    private By iconCartLink = By.className("header__cart-icon");

    public ItemViewPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickAddToCartButton(){
        driver.findElement(btnAddToCart).click();
    }
    public CartPage clickHeaderCartIcon(){
        driver.findElement(iconCartLink).click();
        return new CartPage(driver);
    }
}
