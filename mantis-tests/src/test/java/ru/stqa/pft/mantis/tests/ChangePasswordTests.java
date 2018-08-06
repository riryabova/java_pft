package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  // 1. Администратор входит в систему, переходит на страницу управления пользователями, выбирает заданного пользователя
// и нажимает кнопку Reset Password
// 2. Отправляется письмо на адрес пользователя, тесты должны получить это письмо,
// извлечь из него ссылку для смены пароля, пройти по этой ссылке и изменить пароль.
// 3. Затем тесты должны проверить, что пользователь может войти в систему с новым паролем.
  @Test
  public void testChangePassword() throws IOException, MessagingException {
    app.changePasswordHelper().loginN("administrator", "root");

    app.changePasswordHelper().selectRandomUser();
    String randomUserName = app.changePasswordHelper().getRandomUserName();
    String randomUserEmail = app.changePasswordHelper().getRandomUserEmail();
    app.changePasswordHelper().resetUserPassword();


    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, randomUserEmail);
    String newPassword = "newPassword";
    app.changePasswordHelper().changePassword(confirmationLink, newPassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(randomUserName, newPassword));
    assertTrue(session.isLoggedInAs(randomUserName));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {

    app.mail().stop();
  }

}
