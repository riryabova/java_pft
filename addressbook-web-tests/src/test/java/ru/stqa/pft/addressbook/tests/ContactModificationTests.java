package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().homePage();
    if (app.db().contacts().size()==0){
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com"));
    }
  }

  @Test
  public void testContactModification() {
    File photo = new File("src/test/resources/pic.png");
    Contacts before =  app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com").withPhoto(photo);

    app.contact().modify(contact);
    app.goTo().homePage();
    Assert.assertEquals(app.contact().count(), before.size());
    Contacts after =  app.db().contacts();
//    assertEquals(after.size(), before.size());
    MatcherAssert.<Contacts>assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
