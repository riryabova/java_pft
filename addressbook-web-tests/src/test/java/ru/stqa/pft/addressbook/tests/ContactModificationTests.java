package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.getContactHelper().list().size() == 0) {
      app.getContactHelper().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com").withGroup("test11"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.getContactHelper().all_contact();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com");

    app.getContactHelper().modify(contact);
    app.goTo().homePage();
    Contacts after = app.getContactHelper().all_contact();
    assertEquals(after.size(), before.size());
    MatcherAssert.<Contacts>assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
