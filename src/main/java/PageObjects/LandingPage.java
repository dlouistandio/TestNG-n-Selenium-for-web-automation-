package PageObjects;

import AbstractComponents.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Utils {
    WebDriver driver;

    //Init driver
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement submitBtn;

    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorTxt;

    public ProductsCatalog loginApp(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitBtn.click();
        ProductsCatalog productsCatalog = new ProductsCatalog(driver);
        return productsCatalog;
    }

    public String  getErrorTxt(){
        waitWebElementDisplay(errorTxt);
        return errorTxt.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
