package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
//    int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().list();
    int index = before.size() - 1;
    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToHomePage();
//    int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
