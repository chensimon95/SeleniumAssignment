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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class DropDown extends Base{
    private By drop = By.id("s-result-sort-select");
    private By cl = By.className("a-dropdown-container");

    public DropDown(WebDriver driver){
        super(driver);
    }

    public List<String> droptext(){
        this.waitAndReturnElement(cl).click();
        Select select = new Select(this.waitAndReturnElement(drop));
        List<WebElement> webElements = select.getOptions();
        List<String> texts = new ArrayList<>();
        for (WebElement webElement : webElements){
            texts.add(webElement.getText());
        }
        return texts;
    }


}