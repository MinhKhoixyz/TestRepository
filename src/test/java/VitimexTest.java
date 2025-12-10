import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class VitimexTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Dữ liệu đăng ký (Sử dụng thời gian hiện tại để đảm bảo email là duy nhất)
    private final String EMAIL = "auto_user_" + System.currentTimeMillis() + "@mailinator.com";
    private final String PASSWORD = "12345678";
    private final String FULL_NAME = "Mkhoi";

    // Các bộ chọn ổn định (ID và XPath dựa trên văn bản)
    private final By REG_NAME_FIELD = By.id("first_name");
    private final By REG_EMAIL_FIELD = By.id("email");
    private final By REG_PASS_FIELD = By.id("create_password");
    private final By REG_BUTTON = By.xpath("//button[text()='Đăng ký']");

    private final By LOGOUT_LINK = By.xpath("//a[contains(text(), 'Đăng xuất')]");

    // Bộ chọn cho trang Đăng nhập
    private final By LOGIN_LINK = By.xpath("//a[text()='Đăng nhập']"); // Nút chuyển sang form Đăng nhập
    private final By LOGIN_EMAIL_FIELD = By.id("customer_email");
    private final By LOGIN_PASS_FIELD = By.id("customer_password");
    private final By LOGIN_BUTTON = By.xpath("//button[text()='Đăng nhập']");

    // Bộ chọn xác minh thành công
    private final By WELCOME_TEXT_LOCATOR = By.xpath("//a[contains(text(), '" + FULL_NAME + "')]");


    @BeforeClass
    public void setup() {
        // Cấu hình driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Khởi tạo Explicit Wait (Chờ Tường minh)
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // -------------------------------------------------------------

    @Test(priority = 1)
    public void testRegisterAndLogout() {
        System.out.println("--- Bắt đầu kiểm tra Đăng ký ---");

        // 1. Truy cập trực tiếp trang Đăng ký
        driver.get("https://vitimex.com.vn/account/register");
        System.out.println("Đã truy cập trang Đăng ký.");

        // 2. Nhập thông tin đăng ký
        System.out.println("2. Nhập thông tin đăng ký...");

        wait.until(ExpectedConditions.visibilityOfElementLocated(REG_NAME_FIELD)).sendKeys(FULL_NAME);
        driver.findElement(REG_EMAIL_FIELD).sendKeys(EMAIL);
        driver.findElement(REG_PASS_FIELD).sendKeys(PASSWORD);

        // 3. Nhấp vào nút Đăng ký
        System.out.println("3. Gửi form Đăng ký...");
        driver.findElement(REG_BUTTON).click();

        // 4. Xác minh Đăng ký thành công (Chờ chuyển hướng về trang tài khoản)
        // Kiểm tra xem tên người dùng có xuất hiện không
        wait.until(ExpectedConditions.visibilityOfElementLocated(WELCOME_TEXT_LOCATOR));
        String welcomeMessage = driver.findElement(WELCOME_TEXT_LOCATOR).getText();
        Assert.assertTrue(welcomeMessage.contains(FULL_NAME), "Xác minh Đăng ký thất bại.");
        System.out.println("✅ Đăng ký thành công! Người dùng: " + welcomeMessage);

        // 5. Đăng xuất để chuẩn bị cho testLogin
        System.out.println("Đang Đăng xuất...");
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();

        // Chờ trang chủ/trang đăng nhập tải lại
        wait.until(ExpectedConditions.urlContains("vitimex.com.vn/account"));
        System.out.println("✅ Đã Đăng xuất và chờ trang tải lại thành công.");
    }

    // -------------------------------------------------------------

    @Test(priority = 2)
    public void testLogin() {
        System.out.println("\n--- Bắt đầu kiểm tra Đăng nhập ---");

        // 1. Chuyển sang form Đăng nhập (Nếu chưa ở đó)
        // Nếu đã ở trang /account, có thể cần click vào link Đăng nhập nếu không tự chuyển

        try {
            // Thử click vào link 'Đăng nhập' nếu đang ở trang /account nhưng là form đăng ký
            wait.until(ExpectedConditions.elementToBeClickable(LOGIN_LINK)).click();
        } catch (Exception e) {
            // Có thể đã ở sẵn form Đăng nhập, bỏ qua
        }

        // 2. Nhập Email và Mật khẩu đã đăng ký
        System.out.println("2. Nhập thông tin đăng nhập...");

        // Chờ form đăng nhập xuất hiện
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_EMAIL_FIELD)).sendKeys(EMAIL);
        driver.findElement(LOGIN_PASS_FIELD).sendKeys(PASSWORD);

        // 3. Nhấp vào nút Đăng nhập
        System.out.println("3. Gửi form Đăng nhập...");
        driver.findElement(LOGIN_BUTTON).click();

        // 4. Xác minh Đăng nhập thành công
        wait.until(ExpectedConditions.visibilityOfElementLocated(WELCOME_TEXT_LOCATOR));
        String welcomeMessage = driver.findElement(WELCOME_TEXT_LOCATOR).getText();
        Assert.assertTrue(welcomeMessage.contains(FULL_NAME), "Xác minh Đăng nhập thất bại.");
        System.out.println("✅ Đăng nhập thành công! Người dùng: " + welcomeMessage);
    }

    // -------------------------------------------------------------

//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            System.out.println("\nĐã đóng trình duyệt.");
//            driver.quit();
//        }
//    }
}