package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }

      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
//    File photo = new File("src/test/resources/pic.png");
//    ContactData contact = new ContactData()
//            .withFirstName("new test12").withLastName("new test13").withAddress("newtest14").withMobilePhone("1 8888888").withEmail("new_mailTo@gmail.com").withGroup("test11").withPhoto(photo);
//    app.contact().create(contact, true);
    app.contact().create(contact);
    Assert.assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.db().contacts();

    assertThat(
            after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/pic.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
}
}

