package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        // Initialize Extent Report before the suite starts
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        // Write the report to file
        extent.flush();

        // Auto-open the report in browser
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        try {
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
                System.out.println("Extent Report opened in browser: " + reportPath);
            } else {
                System.out.println("Report file not found: " + reportPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setup() {
        // Setup RestAssured base URI before each test class
        RestAssured.baseURI = "http://localhost:3000";
    }
}
