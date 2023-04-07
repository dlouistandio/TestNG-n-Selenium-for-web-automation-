package TestCase;

import PageObjects.*;
import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {

    String productName = "ADIDAS ORIGINAL";
    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        //kenapa ini ProductsCatalog karena nantin setelah login akan menuju page productCatalog
        ProductsCatalog productsCatalog = landingPage.loginApp(input.get("email"), input.get("password"));

        List<WebElement> product = productsCatalog.getProductList();
        productsCatalog.addProductToCart(input.get("product"));
        ProductCart productCart = productsCatalog.goToCartPage();

        Boolean match = productCart.validateCartList(input.get("product"));
        Assert.assertTrue(match); //gabisa dilakukan di pageobject
        CheckoutPage checkoutPage = productCart.goToCheckout();

        checkoutPage.setSelectCountry("Indonesia");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMsg = confirmationPage.getConfirmationMsg();
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"submitOrder"}) //Test ini akan dijalankan setelah class submitOrder selesai
    public void orderHistoryTest() throws InterruptedException {
        ProductsCatalog productsCatalog = landingPage.loginApp("agus@gmail.com", "Agus123@");

        OrderPage orderPage = productsCatalog.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<String,String>();
//        map.put("email","agus@gmail.com");
//        map.put("password", "Agus123@");
//        map.put("product", "ADIDAS ORIGINAL");
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/Data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}
