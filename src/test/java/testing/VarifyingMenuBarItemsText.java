package testing;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VarifyingMenuBarItemsText {
	WebDriver driver;
	@BeforeMethod
    public void confiBeforeMethod() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    	driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://www.flipkart.com/"); 
    	Reporter.log("opening flipkart home page...");
    }
	@Test
	public void screenShot() throws Throwable {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		String timeStamp = LocalDateTime.now().toString().replace(":", "-");
		List<WebElement> menuBarItems = driver.findElements(By.xpath("//div[@class='eFQ30H']"));
		//int i=menuBarItems.size();
		Reporter.log("Taking screenshot of menu bar items...");
		for (WebElement menu : menuBarItems) {
			WebElement itemImg = menu.findElement(By.tagName("img"));
			File tempFile = itemImg.getScreenshotAs(OutputType.FILE);
			WebElement itemImgText = menu.findElement(By.className("xtXmba"));
			String itemText = itemImgText.getText();
			File destFile = new File("./errorreport/"+timeStamp+itemText+".png");
			FileUtils.copyFile(tempFile, destFile);
					
		}
		Reporter.log("Succesfully taken screen shot and store it errorreport folder with timestamp");
			
	}
	@AfterMethod
	public void confiAfterMethod() {
		driver.manage().window().minimize();
		driver.quit();
	}
}
