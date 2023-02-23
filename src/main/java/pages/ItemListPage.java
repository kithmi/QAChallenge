package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ItemListPage {
    private WebDriver driver;

    private By itemList = By.xpath("//div[@itemscope='itemscope']//div[contains(@class,'item product--list')]");

    public ItemListPage(WebDriver driver){
        this.driver = driver;
    }

    public ItemViewPage selectAnItem(String itemName, String itemBrand){
        List<WebElement> iList = driver.findElements(itemList);
        for (WebElement ele:iList) {
            if((ele.findElement(By.xpath("//div[contains(@class,'product-brand')]")).getText()).contains(itemBrand) & (ele.findElement(By.xpath("//div[contains(@class,'item__name')]")).getText()).contains(itemName)){
                ele.findElement(By.xpath("//a[@itemprop='itemListElement']")).click();
                return new ItemViewPage(driver);
            }
        }
        return null;
    }
}
