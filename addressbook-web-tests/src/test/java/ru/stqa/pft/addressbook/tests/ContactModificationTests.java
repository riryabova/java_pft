package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper().goToHomePage();


    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test12", "test13", "test14", "8888888", "mailTo@gmail.com", "test11"), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("new test12", "new test13", "new test14", "1 8888888", "new_mailTo@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }
}
