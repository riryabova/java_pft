package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);

  }

  public void loginN(String username, String password) {
    type(By.xpath("//input[@name='username']"), username);
    click(By.xpath("//input[@value='Войти']"));
    type(By.xpath("//input[@name='password']"), password);
    click(By.xpath("//input[@value='Войти']"));
  }

  public void selectRandomUser() {
    click(By.linkText("управление"));

    wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    click(By.linkText("Управление пользователями"));
//    click(By.xpath("//a[text()='Управление пользователями']"));
    List<WebElement> rows = wd.findElements(By.xpath("//table//tbody//tr"));
    int count = rows.size();
    String randomUserNumber = getRandomUserNumber(1, count);
    click(By.xpath("//tbody//tr[" + randomUserNumber + "]//a"));


  }

  public static String getRandomUserNumber(int min, int max) {
    Random randomNum = new Random();
    String num = String.valueOf(randomNum.nextInt((max - min) + 1) + min);
    return num;

  }

  public String getRandomUserName() {
    String randomUserName = wd.findElement(By.xpath("//tr[1]//input[@name='username']")).getAttribute("value");
    return randomUserName;
  }

  public String getRandomUserEmail() {
    String randomUserEmail = wd.findElement(By.xpath("//tr[3]//input[@name='email']")).getAttribute("value");
    return randomUserEmail;
  }


  public void resetUserPassword() {

    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
//    click(By.xpath("//input[@value='Сбросить пароль']"));
  }

//  public void changePassword(String confirmationLink, String newPassword) {
//    wd.get(confirmationLink);
//    type(By.xpath("//tr[3]//input[@name='password']"), newPassword);
//    type(By.xpath("//tr[4]//input[@name='password_confirm']"), newPassword);
//    click(By.xpath("//span[text()='Update User']"));
//  }

  public void changePassword(String confrimationLink, String password) {
    wd.get(confrimationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//*[contains(text(), 'Изменить учетную запись')]"));
  }
}
