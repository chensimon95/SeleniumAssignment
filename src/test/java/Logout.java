import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;



class Logout extends Base {
    private By logout = By.xpath("//a[@id='nav-item-signout']/span");
    
    private By stop = By.id("nav-link-accountList");

    public Logout(WebDriver driver){
        super(driver);
    }

    public ResultPage doLogout(){
        //Logout can be clicked after hovering the mouse
        Actions action = new Actions(this.driver);
        action.moveToElement(this.waitAndReturnElement(stop)).perform();

        this.waitAndReturnElement(logout).click();

        return new ResultPage(this.driver);
    }

}