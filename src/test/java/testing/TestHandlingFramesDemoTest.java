package testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestHandlingFramesDemoTest {

	WebDriver driver;
	@BeforeMethod
	public void confiBeforeMethod() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
	}

	@Test
	public void frameHandling() throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Reporter.log("switching to packagelist frame");
        driver.switchTo().frame("packageListFrame");
        Reporter.log("clicking on org.openqa.selenium.bidi link");
        driver.findElement(By.linkText("org.openqa.selenium.bidi")).click();
        driver.switchTo().defaultContent();
        Reporter.log("switching to package frame");
        driver.switchTo().frame("packageFrame");
        Reporter.log("clicking on BiDiProvider link");
        driver.findElement(By.linkText("BiDiProvider")).click();
        driver.switchTo().defaultContent();
        Reporter.log("switching to class frame");
        driver.switchTo().frame("classFrame");
        driver.findElement(By.xpath("//ul[@id='allclasses_navbar_top']/../div//a[@href='#constructor.summary']")).click();  
	}
	@AfterMethod
	public void confiAfterMethod() {
		driver.manage().window().minimize();
		driver.quit();
	}
}
