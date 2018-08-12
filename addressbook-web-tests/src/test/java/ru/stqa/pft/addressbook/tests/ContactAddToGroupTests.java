package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
//При проверке предусловий надо учитывать также наличие групп.
    app.goTo().homePage();

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13")
              .withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void addContactToGroup() {
    Contacts beforeContacts = app.db().contacts();
    Groups beforeGroups = app.db().groups();

    ContactData contactForAddToGroup = app.contact().getRandomContact(beforeContacts);
//группы контакта до добавления
    Groups beforeAddContactToGroup = contactForAddToGroup.getGroups();

//проверить все ли группы добавлены контакту
    GroupsOfContact(beforeGroups, contactForAddToGroup);
    //группу, которая еще не добавлена контакту
    GroupData freeGroup = findFreeGroup(contactForAddToGroup);

    app.goTo().homePage();
    app.contact().addContactToGroup(contactForAddToGroup.getId(), freeGroup.getId());

    Contacts afterContacts = app.db().contacts();

    ContactData modifiedContact = getModifiedContact(afterContacts, contactForAddToGroup);
    //группы контакта после добавления в свободную группу
    Groups afterAddContactToGroup = modifiedContact.getGroups();

//Кроме проверки размера списков, надо проверять изменившиеся списки групп и контактов.
    Assert.assertEquals(beforeAddContactToGroup.size() + 1, afterAddContactToGroup.size());
    Assert.assertEquals(beforeAddContactToGroup, afterAddContactToGroup.without(freeGroup));
  }


  private void GroupsOfContact(Groups beforeGroups, ContactData contactForAddToGroup) {
    if (contactForAddToGroup.getGroups().size() == beforeGroups.size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("NewTestGroup"));
    }
  }


  private GroupData findFreeGroup(ContactData contactForAddToGroup) {
    Groups groups = app.db().groups();

//    GroupData freeGroup="";
    GroupData freeGroup = null;
    for (GroupData group : groups) {
      if (group.getContacts().contains(contactForAddToGroup)) {
      } else {
        freeGroup = group;
      }
    }
    return freeGroup;
  }

  private ContactData getModifiedContact(Contacts afterContacts, ContactData contactForAddToGroup) {
    ContactData modifiedContact = null;
    for (ContactData contact : afterContacts) {
      if (contact.equals(contactForAddToGroup)) {
        modifiedContact = contact;
      }
    }

    return modifiedContact;
  }
}
