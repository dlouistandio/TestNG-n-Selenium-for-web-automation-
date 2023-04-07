package PageObjects;

import AbstractComponents.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Utils {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='form-group']/input[@class='input txt text-validated']")
    private WebElement country;

    @FindBy(xpath = "//a[contains(.,'Place Order')]")
    private WebElement checkoutBtn;

    @FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted'][1]")
    private WebElement selectCountry;

    By result = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

    public void setSelectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitElementDisplay(result);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        checkoutBtn.click();
        return new ConfirmationPage(driver);
    }

}
