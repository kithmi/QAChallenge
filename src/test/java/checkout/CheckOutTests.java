package checkout;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.*;

public class CheckOutTests extends BaseTests {
    String expectedTitle = "fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen";
    @Test
    public void testApplyAVoucher(){
        login();
        goHome();

        //verify success navigation to the home page
        assertEquals(myHomePage.getLoginTitle(),expectedTitle,"Incorrect Home Page title");
        ItemListPage itemListPage = myHomePage.clickHeaderLink("Taschen");
        ItemViewPage itemViewPage = itemListPage.selectAnItem("Enisa High Society Satchel Stone","GUESS");
        itemViewPage.clickAddToCartButton();
        CartPage cartPage = itemViewPage.clickHeaderCartIcon();

        //verify successful item addition to the cart
        assertTrue(cartPage.getCartItem("Enisa High Society Satchel Stone","GUESS"),"Item not found in cart");

        cartPage.clickVoucherLink();

        //verify successful voucher code submition
        assertTrue(cartPage.enterVoucherCodeAndSubmit("QACHALLENGE"),"Voucher code verification unsuccessful!");

        //reverse the changes
        cartPage.removeVoucherCode("QACHALLENGE");
        assertTrue(cartPage.clickRemoveCartIcon("Enisa High Society Satchel Stone","GUESS"),"Item removal unsuccessful");
    }
}
