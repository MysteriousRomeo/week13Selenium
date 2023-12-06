package laptopsandnotebooks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LaptopsAndNotebooksTest {
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

    //mouse Hover
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        WebElement text1 = driver.findElement(by);
        WebElement text2 = driver.findElement(by);
        actions.moveToElement(text1).moveToElement(text2).build().perform();
    }

    public void mouseHoverAndClickOnElement(By by){
        Actions actions = new Actions(driver);
        WebElement text1 = driver.findElement(by);
        WebElement text2 = driver.findElement(by);
        actions.moveToElement(text1).moveToElement(text2).click().build().perform();
    }

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    @Before
    public void setup() {
        openBrowser();
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //hover over Laptops And Notebooks tab click on it
        mouseHoverToElement(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]"));
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));
        //once the page has loaded, select the option to order by price high to low
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");
        String expectedText = "Price (High > Low)";
        String actualText = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[3]/div[3]/div[1]/select[1]/option[5]")).getText();
        //verify the ordering is priced high to low
        Assert.assertEquals("not price high to low", expectedText, actualText);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //hover over Laptops And Notebooks tab and click on it
        mouseHoverToElement(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]"));
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));

        //once the page has loaded, select the option to order by price high to low
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

        //Select Product “MacBook”
        clickOnElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[4]/div[4]/div[1]/div[2]/div[1]/h4[1]/a[1]"));

        //define expected text
        String expectedTextProduct = "MacBook";

        //get actual text
        String actualTextProduct = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/h1[1]")).getText();

        //2.5 Verify the text “MacBook”
       // Assert.assertEquals("not on Macbook page", expectedTextProduct, actualTextProduct);
        Assert.assertEquals(expectedTextProduct,actualTextProduct);

        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        Thread.sleep(2000);
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        String actualText1 = driver.findElement(By.xpath("//div[@id='product-product']/div[1]")).getText();
        String expectedText1 = "Success: You have added MacBook to your shopping cart!\n" +
                "×";
        Assert.assertEquals(actualText1, expectedText1);

        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//body[1]/div[2]/div[1]/a[2]"));

        //define expected
        String expectedTextShoppingCart = "Shopping Cart  (0.00kg)";

        //get actual
        String actualTextShoppingCart = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h1[1]")).getText();

        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals("not on shopping cart page", expectedTextShoppingCart, actualTextShoppingCart);

        //define expected
        String expectedProductName = "MacBook";

        //get actual
        String actualProductName = driver.findElement(By.xpath("//div[@id='content']/form/div/table[1]/tbody[1]/tr[1]/td[2]/a[1]")).getText();

        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals("not on shopping cart", expectedProductName, actualProductName);
        //clear 1 from textbox
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/input")).clear();
        //2.11 Change Quantity "2"
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/input")).sendKeys("2");
        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));
        //define expectedModificationSuccessMessage
        String expectedModificationSuccessMessage = "Success: You have modified your shopping cart!" + "\n×";
        //get actualModificationSuccessMessage
        String actualModificationSuccessMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("not modified correctly", expectedModificationSuccessMessage, actualModificationSuccessMessage);
        //2.14 Verify the Total $1204.00
        String expectedTotal = "$1,204.00";
        String actualTotal = driver.findElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]")).getText();
        Assert.assertEquals("total not displayed", expectedTotal, actualTotal);
        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
        //2.16 Verify the text “Checkout”
        String expectedCheckout = "Checkout";
        String actualCheckout = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals("not on checkout", expectedCheckout, actualCheckout);

        //2.17 Verify the Text “New Customer”
        Thread.sleep(2000);
        String expectedNewCustomer = "New Customer";
        String actualNewCustomer = driver.findElement(By.xpath("//div[@id = 'content']/div/div/div[2]/div[1]/div/div[1]/h2")).getText();
        Assert.assertEquals(expectedNewCustomer, actualNewCustomer);

        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));

        //2.19 Click on “Continue” tab
        mouseHoverAndClickOnElement(By.id("button-account"));
        //2.20 Fill the mandatory fields
        //first name
        Thread.sleep(2000);
        driver.findElement(By.id("input-payment-firstname")).sendKeys("Ayan");
        //last name
        driver.findElement(By.id("input-payment-lastname")).sendKeys("Roy");
        //email
        driver.findElement(By.id("input-payment-email")).sendKeys("ayan01@gmail.com");
        //telephone
        driver.findElement(By.id("input-payment-telephone")).sendKeys("07897896289");
        //address
        driver.findElement(By.id("input-payment-address-1")).sendKeys("420 kent road");
        //city
        driver.findElement(By.id("input-payment-city")).sendKeys("Kent");
        //postcode
        driver.findElement(By.id("input-payment-postcode")).sendKeys("MOY MOY");
        //Region
        selectByVisibleTextFromDropDown(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/fieldset[1]/div[6]/select[1]"), "United Kingdom");
        //city
        selectByVisibleTextFromDropDown(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/fieldset[1]/div[7]/select[1]"), "Aberdeen");
        //2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//div[@class='buttons']/div[@class='pull-right']/input[@value='Continue']"));

        //2.22 Add Comments About your order into text area
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='panel-body']/p[2]/textarea[@name='comment']")).sendKeys("!!! Moye Moye !!!");
        //2.23 Check the Terms & Conditions check box
        mouseHoverAndClickOnElement(By.xpath("//input[@name='agree']"));
        //2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));

        //2.25 Verify the message “Warning: Payment method required!”
        Thread.sleep(2000);
        String expectedWarningMessage = "Warning: Payment method required!\n" +
                "×";
        String actualWarningMessage = driver.findElement(By.xpath("//div[@id = 'content']/div/div[3]/div[2]/div/div[1]")).getText();

        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
    }
}
