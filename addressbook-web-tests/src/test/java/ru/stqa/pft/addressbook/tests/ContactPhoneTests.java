package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com").withGroup("test11"), true);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all_contact().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getHomePhone(),equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(),equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(),equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }


  public String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
