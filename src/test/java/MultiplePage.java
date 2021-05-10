import org.junit.*;
import java.util.*;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class MultiplePage extends Base{
    public MultiplePage(WebDriver driver){
        super(driver);
    }

    public String getSubUrl(WebDriver subdriver){
        String subUrl = subdriver.getCurrentUrl();
        return subUrl;
    }

    public void openPages(List<String> subUrls){
        for(String subUrl : subUrls){
            ((JavascriptExecutor)this.driver).executeScript("window.open('"+subUrl+"')");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ie){

            }
        }
    }

}