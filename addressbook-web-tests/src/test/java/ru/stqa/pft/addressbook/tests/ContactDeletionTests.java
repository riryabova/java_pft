package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.getContactHelper().all_contact();
    ContactData deletedContact = before.iterator().next();
//    int index = before.size() - 1;
//    app.getContactHelper().selectContact(deletedContact);
    app.getContactHelper().deleteSelectedContacts(deletedContact);
    app.getContactHelper().returnToHomePage();
    Assert.assertEquals(app.getContactHelper().count(), before.size() - 1);
    Contacts after = app.getContactHelper().all_contact();
//    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
