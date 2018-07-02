package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test11", null, "test13"));
    List<GroupData> after = app.getGroupHelper().getGroupList();
//    int beforeNew=before.size()+1;
    Assert.assertEquals(after.size(),before.size()+1);
//    Assert.assertEquals(after.size(),beforeNew);
  }

}
