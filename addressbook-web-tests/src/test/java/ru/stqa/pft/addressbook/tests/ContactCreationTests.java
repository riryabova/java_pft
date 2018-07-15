package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all_contact();
    ContactData contact = new ContactData()
            .withFirstName("new test12").withLastName("new test13").withAddress("new test14").withMobilePhone("1 8888888").withEmail("new_mailTo@gmail.com").withGroup("test11");
    app.contact().create(contact, true);
    Assert.assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.contact().all_contact();


//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    assertThat(
            after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

