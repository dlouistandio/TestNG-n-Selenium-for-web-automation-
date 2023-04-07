package PageObjects;

import AbstractComponents.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsCatalog extends Utils {
    WebDriver driver;

    public ProductsCatalog(WebDriver driver){
        super(driver); //Buat bisa dipakai di Parent class (utils)
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = ".ng-animating")
    private WebElement loading;


    By productsBy = By.cssSelector(".mb-3");
    By toastMessage = By.xpath("//div[@class='row']//div[2]//div[1]//div[1]//button[1]");
    By addToCart = By.cssSelector(".card-body button:last-of-type");

    //Step untuk pick dynamic choice
    public List<WebElement> getProductList(){
        waitElementDisplay(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitElementDisplay(toastMessage);
        waitForElementDisappear(loading);
    }
}
