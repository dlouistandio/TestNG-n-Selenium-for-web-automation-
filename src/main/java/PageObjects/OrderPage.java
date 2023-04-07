package PageObjects;

import AbstractComponents.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Utils {
    WebDriver driver;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> orderItemList;

    public OrderPage(WebDriver driver){
        super(driver); //Buat bisa dipakai di Parent class (utils)
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName){
        Boolean match = orderItemList.stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
        return match;
    }
}
