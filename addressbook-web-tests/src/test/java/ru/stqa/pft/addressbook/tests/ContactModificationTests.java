package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("new test12", "new test13", "new test14", "1 8888888", "new_mailTo@gmail.com",null),false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }
}
