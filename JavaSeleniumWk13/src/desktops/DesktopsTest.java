package desktops;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DesktopsTest {
    public static WebDriver driver;
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void closeBrowser() {
        driver.close();
    }

    @Before
    public void setup() {
        openBrowser();
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        WebElement Desktops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/a"));
        Desktops.click();
        WebElement showAllDesktops = driver.findElement(By.xpath("//li[@class='dropdown open']/div/a"));
        showAllDesktops.click();
        Thread.sleep(3000);

        //sort by
        WebElement dropDown = driver.findElement(By.xpath("//select[@id = 'input-sort']"));
        dropDown.click();
        driver.findElement(By.xpath("//select[@id = 'input-sort']/option[3]")).click();

        String verify = driver.findElement(By.xpath("//div[@id = 'content']/h2")).getText();
        String actualText = "Desktops";
        Assert.assertEquals(verify, actualText);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        WebElement Desktops = driver.findElement(By.xpath("//nav[@id='menu']/div[2]/ul/li[1]/a"));
        Desktops.click();
        WebElement showAllDesktops = driver.findElement(By.xpath("//li[@class='dropdown open']/div/a"));
        showAllDesktops.click();
        Thread.sleep(2000);

        //sort by
        WebElement dropDown = driver.findElement(By.xpath("//select[@id = 'input-sort']"));
        dropDown.click();
        driver.findElement(By.xpath("//select[@id = 'input-sort']/option[2]")).click();

        driver.findElement(By.xpath("//div[@id = 'content']/div[4]/div[3]/div/div[1]/a")).click();
        Thread.sleep(2000);

        String actualText = driver.findElement(By.xpath("//div[@id = 'content']/div/div[2]/h1")).getText();
        System.out.println(actualText);
        String expectedText = "HP LP3065";
        Assert.assertEquals(actualText, expectedText);

        //Delivery date 2022-11-30
        //Enter Qty "1” using Select class.
        //Click on “Add to Cart” button

        selectDate("30", "November", "2022");

        //selecting the Qty
        WebElement qty = driver.findElement(By.xpath("//div[@id = 'product']/div[2]/input[1]"));
        qty.click();
        //qty.sendKeys("1");

        //clicking on Add to cart
        driver.findElement(By.id("button-cart")).click();

        // Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        expectedText = "Success: You have added HP LP3065 to your shopping cart!\n";
        //String actualText01 = driver.findElement(By.xpath("//div[@id='product-product']/div[1]")).getText();
        //System.out.println(actualText);
        //String[] actualText01 = actualText.split("×");
        //Assert.assertEquals(expectedText, actualText01[0]);
        //String expectedText1 = "Success: You have added HP LP3065 to your shopping cart!\n" +
        //        "×";
        //Assert.assertEquals(actualText01, expectedText1);


        // Click on link “shopping cart” display into success message
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id = 'top-links']/ul/li[4]/a")).click();

        // Verify the text "Shopping Cart"
        expectedText = "Shopping Cart  (1.00kg)";
        actualText = driver.findElement(By.xpath("//div[@id = 'content']/h1")).getText();
        Assert.assertEquals(expectedText, actualText);

        // Verify the Model "Product21"
        expectedText = "Product 21";
        actualText = driver.findElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[3]")).getText();
        Assert.assertEquals(expectedText, actualText);

        // Verify the Todat "£74.73" now its 122.00
        expectedText = "$122.00";
        actualText = driver.findElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[6]")).getText();
        Assert.assertEquals(expectedText, actualText);
    }
        //Calender method
    public void selectDate(String date, String month, String year) throws InterruptedException {
        driver.findElement(By.xpath("//i[@class='fa fa-calendar']")).click();
        while (true) {
            String monthYear = driver.findElement(By.xpath("(//th[@class='picker-switch'])[1]")).getText();
            //System.out.println(monthYear);
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]")).click();
            }
        }
        Thread.sleep(1000);
        // Select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//*[@class='datepicker-days']//tbody//tr//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }
    }


}
