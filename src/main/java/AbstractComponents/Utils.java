package AbstractComponents;

import PageObjects.OrderPage;
import PageObjects.ProductCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    WebDriver driver;

    //Constructor buat nangkap driver dari child class
    public Utils(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Cart 1')]")
    private WebElement viewCart;

    @FindBy(xpath = "//button[contains(.,'ORDERS')]")
    private WebElement orderHeader;

    public void waitElementDisplay(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitWebElementDisplay(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementDisappear(WebElement element) throws InterruptedException {
        Thread.sleep(2000);
    }

    public ProductCart goToCartPage(){
        viewCart.click();
        ProductCart productCart = new ProductCart(driver);
        return productCart;
    }

    public OrderPage goToOrderPage(){
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
