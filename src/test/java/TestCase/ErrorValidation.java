package TestCase;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {
    @Test
    public void errMessageValidation(){
        landingPage.loginApp("agus@gmail.com", "Asgus123@");
        Assert.assertEquals(landingPage.getErrorTxt(),"Incorect email or password.");
    }
}
