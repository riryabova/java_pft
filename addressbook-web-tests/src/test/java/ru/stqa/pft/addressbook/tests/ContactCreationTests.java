package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.getContactHelper().list();
    ContactData contact = new ContactData()
            .withFirstName("new test12").withLastName("new test13").withAddress("new test14").withMobilePhone("1 8888888").withEmail("new_mailTo@gmail.com").withGroup("test11");
    app.getContactHelper().create(contact, true);
    List<ContactData> after = app.getContactHelper().list();
    Assert.assertEquals(after.size(), before.size() + 1);

//    int max = 0;
//    for (ContactData g : after) {
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }
    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId=(g1, g2)->Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}

