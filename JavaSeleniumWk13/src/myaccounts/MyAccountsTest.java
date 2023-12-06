package myaccounts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyAccountsTest {
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

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    @Before
    public void setup() {
        openBrowser();
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']/li[1]/a")).click();
        String expectedTextLogin = "Register Account";
        String actualTextLogin = driver.findElement(By.xpath("//div[@id = 'account-register']/div[1]/div/h1")).getText();
        //verify if expected equals actual
        Assert.assertEquals(expectedTextLogin, actualTextLogin);
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']/li[2]/a")).click();
        String expectedTextLogin = "Returning Customer";
        String actualTextLogin = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h2")).getText();

        Assert.assertEquals(expectedTextLogin, actualTextLogin);
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
        //to click on register button
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']/li[1]/a")).click();
        //3.3 Enter First Name
        sendTextToElement(By.name("firstname"), "Ayan");
        //3.4 Enter Last Name
        sendTextToElement(By.name("lastname"), "Roy");
        //3.5 Enter Email
        sendTextToElement(By.name("email"), "ayan_06@gmail.com");
        //3.6 Enter Telephone
        sendTextToElement(By.name("telephone"), "0894215855");
        //3.7 Enter Password
        sendTextToElement(By.name("password"), "Password");
        //3.8 Enter Password Confirm
        sendTextToElement(By.name("confirm"), "Password");
        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.name("newsletter"));
        //3.10 Click on Privacy Policy check box
        clickOnElement(By.name("agree"));
        //3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@type='submit']"));
        //expected text
        String expectedCreation = "Your Account Has Been Created!";
        //actual text
        String actualCreation = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        //3.12 Verify the message “Your Account Has Been Created!”
        Assert.assertEquals(expectedCreation, actualCreation);
        //3.13 Click on Continue button
        clickOnElement(By.xpath("//a[text()='Continue']"));
        //3.14 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a")).click();
        String expectedTextLogout = "Account Logout";
        String actualTextLogout = driver.findElement(By.xpath("//h1[contains(text(),'Account Logout')]")).getText();
        //3.16 Verify the text “Account Logout”
        Assert.assertEquals("not logged out", expectedTextLogout, actualTextLogout);
        //3.17 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']/li[2]/a")).click();
        //enter email
        sendTextToElement(By.name("email"), "ayan_06@gmail.com");
        //enter password
        sendTextToElement(By.name("password"), "Password");
        //click login
        clickOnElement(By.xpath("//input[@value='Login']"));
        String expectedTextMyAccount = "My Account";
        String actualTextMyAccount = driver.findElement(By.xpath("//div[@id='content']/h2[text()='My Account']")).getText();
        //verify if expected equals actual
        Assert.assertEquals(expectedTextMyAccount, actualTextMyAccount);
        //click my account
        clickOnElement(By.xpath("//a[@title='My Account']"));
        //choose logout
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a")).click();
        String expectedTextLogout = "Account Logout";
        String actualTextLogout = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        //verify if expected equals actual
        Assert.assertEquals(expectedTextLogout, actualTextLogout);
    }

}
