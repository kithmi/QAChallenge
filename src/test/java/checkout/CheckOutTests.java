package checkout;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckOutTests extends BaseTests {
    @Test
    public void testApplyAVoucher(){
        assertEquals(myHomePage.getLoginTitle(),"fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen","Incorrect Home Page");
        LoginPage loginPage = myHomePage.clickUserLoginIcon();
        assertEquals(loginPage.getLoginTitle(),"Designertaschen und Accessoires | fashionette","Incorrect login page title");
        loginPage.enterEmail("QA@fashionette.de");
        loginPage.enterPassword("!8Ntr*BM@!#G3VH");
        CustomerAccountPage customerAccountPage = loginPage.clickLogin();
        assertTrue(customerAccountPage.getWelcomeCustomerMessage().contains("Hallo john!"),"Incorrect Login Success Message");
        goHome();
        assertEquals(myHomePage.getLoginTitle(),"fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen","Incorrect Home Page");
        ItemListPage itemListPage = myHomePage.clickHeaderLink("Taschen");
        ItemViewPage itemViewPage = itemListPage.selectAnItem("Liz Shopper Medium Cognac","MCM");
        itemViewPage.clickAddToCartButton();
        CartPage cartPage = itemViewPage.clickHeaderCartIcon();
        assertTrue(cartPage.getCartItem("Liz Shopper Medium Cognac","MCM"),"Item not found in cart");
        cartPage.clickVoucherLink();
        assertTrue(cartPage.enterVoucherCodeAndSubmit("QACHALLENGE"),"Voucher code verification unsuccessful!");
    }
}
