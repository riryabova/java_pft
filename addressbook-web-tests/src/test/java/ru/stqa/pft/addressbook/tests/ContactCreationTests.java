package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.getContactHelper().all_contact();
    ContactData contact = new ContactData()
            .withFirstName("new test12").withLastName("new test13").withAddress("new test14").withMobilePhone("1 8888888").withEmail("new_mailTo@gmail.com").withGroup("test11");
    app.getContactHelper().create(contact, true);
    Set<ContactData> after = app.getContactHelper().all_contact();
    Assert.assertEquals(after.size(), before.size() + 1);

//    int max = 0;
//    for (ContactData g : after) {
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }

//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}

