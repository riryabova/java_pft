package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com").withGroup("test11"), true);
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all_contact();
    ContactData deletedContact = before.iterator().next();
//    int index = before.size() - 1;
//    app.contact().selectContact(deletedContact);
    app.contact().deleteSelectedContacts(deletedContact);
    app.contact().returnToHomePage();
    Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.contact().all_contact();
//    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
