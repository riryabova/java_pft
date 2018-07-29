package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all_contact().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
  }

  @Test (enabled = false)
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all_contact().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  @Test(enabled = false)
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all_contact().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }



  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneTests::cleanedPhone).collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneTests::cleanedEmail).collect(Collectors.joining("\n"));
  }

  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneTests::cleanedAddress).collect(Collectors.joining("\n"));
  }


  public static String cleanedPhone(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

  public static String cleanedEmail(String email) {
    return email.replaceAll("\\s","").replaceAll("[-()]","");
  }

  public static String cleanedAddress(String address) {
    return address.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
