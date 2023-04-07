package PageObjects;

import AbstractComponents.Utils;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends Utils {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//h1[@class='hero-primary']")
    private WebElement confirmationMsg;

    public String getConfirmationMsg(){
        return confirmationMsg.getText();
    }

}
