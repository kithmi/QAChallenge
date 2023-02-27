package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private By linkItem = By.tagName("a");
    private By labelEmptyCart = By.xpath("//div[@class='page-content']//h2");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * This method check a specific item is in the cart
     * @param itemName Name of the item as in the webpage
     * @param itemBrand Brand name as in the webpage
     * @return true if requesting cart item found, false if requesting cart item not found
     */
    public boolean getCartItem(String itemName, String itemBrand){
        String linkText = (itemBrand + " "+ itemName).toLowerCase().replace(" ","-");
        List<WebElement> cItems = driver.findElements(cartItems);
        for (WebElement ele:cItems) {
            if (ele.findElement(linkItem).getAttribute("href").contains(linkText)){
                return true;
            }
        }
        return false;
    }
    public void clickVoucherLink(){
        driver.findElement(linkVoucher).click();
    }

    /**
     * This method enter a voucher code and submit that
     * @param voucherCode voucher code in uppercase example: VOUCHERCODE
     * @return true if voucher code adding successful, false if voucher code adding unsuccessful
     */
    public boolean enterVoucherCodeAndSubmit(String voucherCode){
        driver.findElement(inputVoucher).clear();
        driver.findElement(inputVoucher).sendKeys(voucherCode);
        driver.findElement(btnSubmitVoucher).click();
        By successVoucherEle = By.xpath("//*[@data-code='"+voucherCode+"']");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(btnSubmitVoucher));
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(successVoucherEle));
        return (element != null);
    }
    /**
     * This method remove a specific item in the cart
     * @param itemName Name of the item as in the webpage
     * @param itemBrand Brand name as in the webpage
     * @return true if requesting cart item removal success, false if requesting cart item removal unsuccessful
     */
    public boolean clickRemoveCartIcon(String itemName,String itemBrand){
        String linkText = (itemBrand + " "+ itemName).toLowerCase().replace(" ","-");
        By linkCartItem = By.xpath("//a[contains(@href,'"+linkText+"')]");
        String ItemCode = driver.findElement(linkCartItem).getAttribute("data-product-configure");
        if(!ItemCode.isEmpty()){
            By removeCartIcon = By.xpath("//div[contains(@data-product-id,'"+ItemCode+"')]/button/i");
            WebElement cIcon = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(driver.findElement(removeCartIcon)));
            cIcon.click();
            WebElement lblEmptyCart = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(labelEmptyCart));
            return lblEmptyCart.getText().contains("OH, DEIN WARENKORB IST LEER");
        }
        else {
            return false;
        }
    }
    /**
     * This method remove an existing voucher code
     * @param voucherCode voucher code in uppercase example: VOUCHERCODE
     */
    public void removeVoucherCode(String voucherCode){
        By btnRemoveVoucherCode = By.xpath("//th[@data-code='"+voucherCode+"']//span[@class='cart__sum-voucher-delete-link']/i");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnRemoveVoucherCode)).click();
        new VoucherDeleteModel().clickDeleteVoucherYesButton(voucherCode);

    }

    private class VoucherDeleteModel{
        /**
         * This method is to click yes in the voucher delete modal
         * @param voucher voucher code in uppercase example: VOUCHERCODE
         */
        private void clickDeleteVoucherYesButton(String voucher){
            By btnDeleteVoucherYes = By.xpath("//b[contains(text(),'"+voucher+"')]/parent::p[@class='address-delete__content__text']/following-sibling::div[@class='address-delete__content__button-confirm']");
            driver.findElement(btnDeleteVoucherYes).click();
        }
    }
}
