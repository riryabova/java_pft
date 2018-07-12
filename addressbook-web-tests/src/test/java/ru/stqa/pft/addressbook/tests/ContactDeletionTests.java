package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.getContactHelper().list().size() == 0) {
      app.getContactHelper().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com").withGroup("test11"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.getContactHelper().all_contact();
    ContactData deletedContact = before.iterator().next();
//    int index = before.size() - 1;
//    app.getContactHelper().selectContact(deletedContact);
    app.getContactHelper().deleteSelectedContacts(deletedContact);
    app.getContactHelper().returnToHomePage();
    Set<ContactData> after = app.getContactHelper().all_contact();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
