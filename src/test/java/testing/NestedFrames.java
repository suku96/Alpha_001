package testing;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NestedFrames {
	  WebDriver driver;
	  @BeforeMethod
    public void confiBeforeMenthod() {
		  System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    	  driver=new ChromeDriver();
    	  driver.manage().window().maximize();
    	  driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width");    	  
    }
    @Test
	public void testName() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//identifying parent frame 
		driver.switchTo().frame("iframeResult");
		//from this navigating to the child frame 
	    WebElement childFrame = driver.findElement(By.xpath("//iframe[@title='Iframe Example']"));
	    driver.switchTo().frame(childFrame);
	    File textPoster = driver.findElement(By.tagName("h1")).getScreenshotAs(OutputType.FILE);
	    File tempFile = new File("./textposter/text.png");
	    //fileutils class from apche commons io package
	    FileUtils.copyFile(textPoster, tempFile);
	    //after doing required action with child frame navigate back to parent frame
	    driver.switchTo().parentFrame();
	     String parentText = driver.findElement(By.xpath("//body/p")).getText();
	     System.out.println(parentText);
	}
    @AfterMethod
    public void confiAfterMethod() {
    	driver.manage().window().minimize();
    	driver.quit();
    }
}
