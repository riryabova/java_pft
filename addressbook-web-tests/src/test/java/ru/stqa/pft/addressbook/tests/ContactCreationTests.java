package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
//    int before = app.getContactHelper().getContactCount();

    app.getContactHelper().createContact(new ContactData("test12", "test13", "test14", "8888888", "mailTo@gmail.com", "test11"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    //    int after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
  }

}
