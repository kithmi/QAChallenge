package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemListPage {
    private WebDriver driver;

    private By itemList = By.xpath("//div[@itemscope='itemscope']//div[contains(@class,'item product--list')]");

    private By linkItem = By.tagName("a");

    public ItemListPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * This method is to select an item on the items page
     * @param itemName Name of the item as in the webpage
     * @param itemBrand Brand name as in the webpage
     * @return ItemViewPage object if requested item found, null if requested item not found
     */
    public ItemViewPage selectAnItem(String itemName, String itemBrand){
        String linkText = (itemBrand + " "+ itemName).toLowerCase().replace(" ","-");
        List<WebElement> iList = driver.findElements(itemList);
        for (WebElement ele: iList) {

            if ((ele.findElement(linkItem).getAttribute("href").contains(linkText))){
                ele.findElement(linkItem).click();
                return new ItemViewPage(driver);
            }

        }
        return null;
    }
}
