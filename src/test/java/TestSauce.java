import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class TestSauce {
    private static WebDriver driver=new ChromeDriver();

    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @After
    public void closeDriver()
    {
        driver.close();
    }

    @Test
    public void phillipTest()
    {

        login();
        sortProducts();
        checkPriceOrder(checkPriceList());
    }

    private void checkPriceOrder(List<Double> checkPriceList) {
        List<Double> pricesList  = actualPriceList();
        for (int i = 0; i < pricesList.size(); i++) {
            double actualPrice =pricesList.get(i);
            double expectedPrice =checkPriceList.get(i);

            System.out.println("Do Assert ");
            Assert.assertEquals(expectedPrice,actualPrice,0.5);

        }
    }

    private List<Double> actualPriceList() {
        List<Double> list = new ArrayList<>();
        list.add(7.99);
        list.add(9.99);
        list.add(15.99);
        list.add(15.99);
        list.add(29.99);
        list.add(49.99);

        return list;
    }

    private List<Double> checkPriceList() {

        List<Double> pricesList = new ArrayList<>();

        for (int i = 1; i <7; i++) {
            By prices = By.xpath("//*[@id=\"inventory_container\"]/div/div["+i+"]/div[2]/div[2]/div");
            WebElement list = driver.findElement(prices);
            double temp =Double.parseDouble(list.getText().substring(1));

            pricesList.add(temp);
        }

        return pricesList;
    }

    private void sortProducts() {
        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")));
        dropdown.selectByValue("lohi");
    }

    private void login() {
        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login  = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        username.sendKeys(DynamicLoad.loadValue("username"));
        password.sendKeys(DynamicLoad.loadValue("password"));

        login.click();

    }

}
