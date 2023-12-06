package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class TopMenuTest {
    public static WebDriver driver;
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    public void closeBrowser(){
        driver.close();
    }

    public static void selectMenu(String menu){
        WebElement element = driver.findElement(By.xpath(menu));
        element.click();
    }

    @Before
    public void setup() {
        openBrowser();
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){
        WebElement Desktops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/a"));
        Desktops.click();
        WebElement showAllDesktops = driver.findElement(By.xpath("//li[@class='dropdown open']/div/a"));
        showAllDesktops.click();

        String verify = driver.findElement(By.xpath("//div[@id = 'content']/h2")).getText();
        String actualText = "Desktops";
        Assert.assertEquals(verify, actualText);
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully(){
        WebElement laptops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[2]/a"));
        laptops.click();
        WebElement showAllLaptops = driver.findElement(By.xpath("//li[@class='dropdown open']/div/a"));
        showAllLaptops.click();

        String verify = driver.findElement(By.xpath("//div[@id = 'content']/h2")).getText();
        String actualText = "Laptops & Notebooks";
        Assert.assertEquals(verify, actualText);
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        WebElement laptops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[3]/a"));
        laptops.click();
        WebElement showAllLaptops = driver.findElement(By.xpath("//li[@class='dropdown open']/div/a"));
        showAllLaptops.click();

        String verify = driver.findElement(By.xpath("//div[@id = 'content']/h2")).getText();
        String actualText = "Components";
        Assert.assertEquals(verify, actualText);
    }

    @After
    public void endTest(){
        closeBrowser();
    }
}
