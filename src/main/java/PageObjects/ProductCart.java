package PageObjects;

import AbstractComponents.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCart extends Utils {
    WebDriver driver;

    public ProductCart(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//button[.='Checkout']")
    private WebElement checkoutBtn;

    public Boolean validateCartList(String productName){
        Boolean match = productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout(){
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }
}
