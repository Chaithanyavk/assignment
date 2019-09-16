package wikipedia;

import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wikiassignment {

	static WebDriver driver;
	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chaithanya\\eclipse-workspace\\wiki\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		driver.manage().window().maximize();
		
		if(driver.findElement(By.xpath("//span[contains(text(),'External links')]")).isDisplayed()) {
			
		System.out.println("External link is present on the page");
		}
		else {
			System.out.println("External link is not present on the page");

		}
		
		driver.findElement(By.xpath("//td[@title='O, Oxygen']")).click();
		
		if(driver.findElement(By.xpath("//h1[contains(text(),'Oxygen')]")).isDisplayed()) {
			System.out.println("It is a featured article");
		}
		
		else {
			System.out.println("It is not a featured article");
		}
		
		WebElement ele =  driver.findElement(By.xpath("//img[@alt='A transparent beaker containing a light blue fluid with gas bubbles']"));

		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		
		Point point = ele.getLocation();

		
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
		    eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);

		
		File screenshotLocation = new File("C:\\images\\image_screenshot.png");
		FileUtils.copyFile(screenshot, screenshotLocation);
		
		
		WebElement linklist = driver.findElement(By.xpath("//ol[@class='references']"));
		System.out.println(linklist.getSize());
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("pluto");
		WebElement displaytext = driver.findElement(By.xpath("//div[@class='suggestions-results']"));
		System.out.println(displaytext.getText());
		
	}

}
