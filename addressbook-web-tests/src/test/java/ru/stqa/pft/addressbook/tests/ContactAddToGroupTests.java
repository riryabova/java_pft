package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com"));
    }
  }

  @Test
  public void addContactToGroup() {
    Contacts beforeContact = app.db().contacts();
    Groups beforeGroups = app.db().groups();
    ContactData addedToGroupContact = beforeContact.iterator().next();
//    ContactData contact = new ContactData().withId(addedToGroupContact.getId());


    GroupData addedToGroupGroup = beforeGroups.iterator().next();
    String newGroupName = addedToGroupGroup.getName();
    app.contact().addToGroup(addedToGroupContact, newGroupName);
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), beforeContact.size());
  }


}
