package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test12", "test13", "test14", "8888888", "mailTo@gmail.com", "test11"), true);
  }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToHomePage();
  }

}
