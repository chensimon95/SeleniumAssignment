import java.awt.datatransfer.StringSelection;

import org.junit.*;
import java.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class UploadFile extends Base{
    private By camera = By.id("sttb");
    private By upload = By.id("uploadImg");


    public UploadFile(WebDriver driver){
        super(driver);
        this.driver.get("https://image.baidu.com/");
    }

    public ResultPage UploadImage(){
        String road = System.getProperty("user.dir") + "\\src\\test\\java\\7.jpg";

        StringSelection selection = new StringSelection(road);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        this.waitAndReturnElement(camera).click();
        this.waitAndReturnElement(upload).click();
        try{
                Thread.sleep(2000);
            }catch(InterruptedException ie){
            }
        Robot robot=null;

        try{
            robot = new Robot();
        }catch(Exception e){
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        try{
                Thread.sleep(20000);
            }catch(InterruptedException ie){
            }
        return new ResultPage(this.driver);
    }
}