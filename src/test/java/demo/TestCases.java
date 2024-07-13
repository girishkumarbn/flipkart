package demo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import java.time.Duration; 
import java.util.Collections;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
       @Test
       public void testCase01() throws InterruptedException{
       String url = "https://www.flipkart.com";
       Wrappers.wrapperNavigate(driver, url);
       
       /* search for washing machine */
       WebElement searchBar = driver.findElement(By.name("q"));
       String searchItem = "Washing Machine";
       searchBar.clear();
       Wrappers.wrapperEnterText(searchBar, searchItem);
       searchBar.sendKeys(Keys.ENTER);

       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='XQDdHH']")));
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='zg-M3Z _0H7xSG']")));

        /* click of popularity link */
       WebElement popularity = driver.findElement(By.xpath("//div[@class='zg-M3Z _0H7xSG']"));
	   Wrappers.wrapperClick(popularity);
	   System.out.println("Clicked on popularity");
		
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='XQDdHH']")));
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

       List<WebElement> starRating = driver.findElements(By.xpath("//div[@class='XQDdHH']"));
	   System.out.println("List Size"+ " "+starRating.size());
       int countItems=0;
	   for(WebElement stars:starRating){
			String ratings = stars.getText();
			//float starsRate = Integer.parseInt(ratings);
			float starsRate = Float.parseFloat(ratings);
			if(starsRate >= 4){
				countItems++;
			}
		}
		System.out.println("Total Items with Star Rating less than or equal to 4 -->"+" " +countItems);
     }

    @Test
    public void testCase02() throws InterruptedException{
    String url = "https://www.flipkart.com";
    Wrappers.wrapperNavigate(driver, url);
       
    /* search for washing machine */
    WebElement searchBar = driver.findElement(By.name("q"));
    searchBar.clear();
    String searchItem = "iPhone";
    Wrappers.wrapperEnterText(searchBar, searchItem);
    searchBar.sendKeys(Keys.ENTER);

    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

    List<WebElement> title = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
		for(WebElement titleText:title){
			String actualTitle = titleText.getText();
			WebElement percentOfOffer = driver.findElement(By.xpath("//div[@class='UkUFwK']//span"));
				 String offer =  percentOfOffer.getText();
				 int discount = Integer.parseInt(offer.replaceAll("[^0-9]", ""));
				 if(discount >=17){
					 System.out.println("Title "+" "+actualTitle);
					 System.out.println("Discount" +" "+ discount);
				 }
			}
	}

    @Test
    public void testCase03() throws InterruptedException {
    ArrayList<Integer> reviewList = new ArrayList<>();
    String url = "https://www.flipkart.com";
    Wrappers.wrapperNavigate(driver, url);
       
    /* search for coffee mug */
    WebElement searchBar = driver.findElement(By.name("q"));
    searchBar.clear();
    String searchItem = "Coffee Mug";
    Wrappers.wrapperEnterText(searchBar, searchItem);
    searchBar.sendKeys(Keys.ENTER);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

    Wrappers.wrapperAdvancedScroll(driver);
    Thread.sleep(3000);

    WebElement fourStarRating = driver.findElement(By.xpath("//div[@class='XqNaEv']"));
    Wrappers.wrapperAdvancedClick(fourStarRating);

    Thread.sleep(3000);

    List<WebElement> reviews = driver.findElements(By.xpath("//div//span[@class='Wphh3N']"));
    for (WebElement review : reviews) {
        String ratings = review.getText();
        int ratingsValue = Integer.parseInt(ratings.replaceAll("[^0-9]", ""));
        reviewList.add(ratingsValue);
    }
    
    Collections.sort(reviewList, Collections.reverseOrder());
    
    for (int i = 0; i < 5 && i < reviewList.size(); i++) {
        int reviewValue = reviewList.get(i);
        String formattedValue = Wrappers.formatNumber(reviewValue);
        System.out.println("Formatted Value" + formattedValue);
        
        // Note the use of single quotes around the formattedValue in the XPath
        WebElement titleOfMug = driver.findElement(By.xpath("//span[contains(text(),'" + formattedValue + "')]/../../a[@class='wjcEIp']"));
        System.out.println("Title of the Coffee Mug: " + titleOfMug.getAttribute("title"));
        System.out.println("URL of the Coffee Mug: " + titleOfMug.getAttribute("href"));
    }
}


     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}