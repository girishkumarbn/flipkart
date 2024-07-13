package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import java.util.stream.Collectors;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static boolean wrapperNavigate(ChromeDriver driver, String url){
        try{
            if(driver.getCurrentUrl().equals(url)){
            return true;
        }else{
            driver.get(url);
            return driver.getCurrentUrl() == url;
        }
     }catch(Exception e){
        e.printStackTrace();
        return false;
         }
    }

    public static boolean wrapperClick(WebElement elememt){
        try{
            elememt.click();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean wrapperEnterText(WebElement textBox, String inputText) throws InterruptedException {
		try {
			textBox.clear();
			textBox.sendKeys(inputText);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

    public static boolean wrapperAdvancedClick(WebElement button) throws InterruptedException{
		try{
			button.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

    public static boolean wrapperAdvancedScrollWithOutElement(ChromeDriver driver, WebElement element) throws InterruptedException {
		try {
			 	JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView();", element);
		        return true;
		}catch(Exception e) {
			return false;
		}
	}

    public static boolean wrapperAdvancedScroll(ChromeDriver driver) throws InterruptedException{
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollBy(0,230)");
            return true;
        }catch(Exception e){
            return false;
        }
    }

    // public static int formatNumber(int value){
    //     NumberFormat myFormat = NumberFormat.getInstance(); 
    //     myFormat.setGroupingUsed(true);  
    //     String formatValue = myFormat.format(value);
    //     return value;
    // }

        public static String formatNumber(int value){
            NumberFormat myFormat = NumberFormat.getInstance(); 
            myFormat.setGroupingUsed(true);  
            String formatValue = myFormat.format(value);
            //System.out.println("Formatted Value: " + formatValue);
            return formatValue;
        }

    // public static int parseFormattedNumber(String formattedValue) throws ParseException {
    //     NumberFormat myFormat = NumberFormat.getInstance();
    //     myFormat.setGroupingUsed(true);
    //     Number number = myFormat.parse(formattedValue);
    //     return number.intValue();
    // }
}