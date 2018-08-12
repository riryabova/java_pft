package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
//При проверке предусловий надо учитывать также наличие групп.
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test12").withLastName("test13").withAddress("test14").withMobilePhone("8888888").withEmail("mailTo@gmail.com"));
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void RemoveContactFromGroup() {


    Contacts beforeContacts = app.db().contacts();
    Groups beforeGroups = app.db().groups();

    ContactData contactForRemoveFromGroup = app.contact().getRandomContact(beforeContacts);


//    Groups beforeAddContactToGroup = contactForRemoveFromGroup.getGroups();

//проверить группы у контакта
    GroupsOfContactForRemove(beforeGroups, contactForRemoveFromGroup);


    ContactData modifiedContactBefore = getRemovedContact(beforeContacts, contactForRemoveFromGroup);

    //группы контакта до удаления
    Groups beforeRemoveContactFromGroup = modifiedContactBefore.getGroups();

    //группа, которую будем удалять
//    GroupData RemoveGroup = findGroupForRemove(contactForRemoveFromGroup);
    GroupData RemoveGroup = contactForRemoveFromGroup.getGroups().iterator().next();


    //удаляем контакт из группы
    app.goTo().homePage();
    app.contact().removeContactFromGroup(contactForRemoveFromGroup.getId(), RemoveGroup.getId());

    Contacts сontactsAfter = app.db().contacts();
    ContactData modifedContact = getRemovedContact(сontactsAfter, contactForRemoveFromGroup);

    Groups afterRemoveContactFromGroup = modifedContact.getGroups();


    Assert.assertEquals(beforeRemoveContactFromGroup.size(), afterRemoveContactFromGroup.size()+1);
    Assert.assertEquals(beforeRemoveContactFromGroup, afterRemoveContactFromGroup.withAdded(RemoveGroup));
  }

  private void GroupsOfContactForRemove(Groups beforeGroups, ContactData contactForRemoveFromGroup) {
//Если нет групп добавляем
    if (contactForRemoveFromGroup.getGroups().size() == 0) {
      GroupsOfContact(beforeGroups, contactForRemoveFromGroup);
      GroupData freeGroup = findFreeGroup(contactForRemoveFromGroup);
      app.goTo().homePage();
      app.contact().addContactToGroup(contactForRemoveFromGroup.getId(), freeGroup.getId());
    }
  }

  private ContactData getRemovedContact(Contacts beforeContacts, ContactData contactForRemoveFromGroup) {
    ContactData removedContact = null;
    for (ContactData contact : beforeContacts) {
      if (contact.equals(contactForRemoveFromGroup)) {
        removedContact = contact;

      }
    }
    return removedContact;
  }

  private GroupData findGroupForRemove(ContactData contactForRemoveFromGroup) {

    Groups groups = app.db().groups();
    GroupData RemoveGroup = null;
    for (GroupData group : groups) {
      if (group.getContacts().contains(contactForRemoveFromGroup)) {
        RemoveGroup = group;
        return RemoveGroup;
      }
    }
    return RemoveGroup;
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
    GroupData RemoveGroup = null;
    for (GroupData group : groups) {
      if (group.getContacts().contains(contactForAddToGroup)) {
      } else {
        RemoveGroup = group;
      }
    }
    return RemoveGroup;
  }


}
