package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartItems = By.xpath("//div[contains(@class,'cart__container')]//div[@class='cart-item__wrapper']");
    private By linkVoucher = By.partialLinkText("eingeben");
    private By inputVoucher = By.name("voucherCode");
    private By btnSubmitVoucher = By.cssSelector("#form-cart-voucher button");


    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public boolean getCartItem(String itemName, String itemBrand){
        List<WebElement> cItems = driver.findElements(cartItems);
        for (WebElement ele:cItems) {
            if(ele.findElement(By.xpath("//div[@class='cart-item--description']//div[@class='cart-item--brand']")).getText().equalsIgnoreCase(itemBrand) && ele.findElement(By.xpath("//div[@class='cart-item--description']//div[@class='cart-item--name']")).getText().equalsIgnoreCase(itemName)){
                return true;
            }
        }
        return false;
    }
    public void clickVoucherLink(){
        driver.findElement(linkVoucher).click();
    }

    public boolean enterVoucherCodeAndSubmit(String voucherCode){
        driver.findElement(inputVoucher).clear();
        driver.findElement(inputVoucher).sendKeys(voucherCode);
        driver.findElement(btnSubmitVoucher).click();
        By successVoucherEle = By.xpath("//*[@data-code='"+voucherCode+"']");
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(successVoucherEle));
        if (element!= null){
            return true;
        }
        return false;
    }
}
