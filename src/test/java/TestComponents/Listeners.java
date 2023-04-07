package TestComponents;

import Resources.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReport.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//Untuk pararel exection biar report tidak bentrok

    @Override
    public void onTestStart(ITestResult result){
       test = extent.createTest(result.getMethod().getMethodName());//Buat memulai report
       extentTest.set(test); // ini untuk set thread local
    }

    @Override
    public void onTestSuccess(ITestResult result){
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        extentTest.get().fail(result.getThrowable());//extentTest.get() akan get method yang ada di extentTest

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver") // to init driver in screenshot class
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = null;
        try {
           filePath = getScreenshot(result.getMethod().getMethodName(),driver);// driver yang sudah di init dimasukan ke class screenshot
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
