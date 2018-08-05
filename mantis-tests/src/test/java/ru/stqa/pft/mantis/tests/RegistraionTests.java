package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;

public class RegistraionTests extends TestBase {

  @Test
  public void testReRegistration() {
//    long now = System.currentTimeMillis();
//    String email = String.format("user@localhost%s.localdomain", now);
//    String user1 = String.format("user%s" , now);
//    String password = ("password");
    app.registration().start("user1","user1@localhost.localdomain");
//    app.registration().start(user1, email);
//    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
//    String confrimationLink = findConfrimationLink(mailMessages, email);
//    app.registration().finish(confrimationLink, password);
//    Assert.assertTrue(app.newSession().login(user1, password));

  }

}
