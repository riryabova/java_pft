package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test11", "test12", "test13"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
