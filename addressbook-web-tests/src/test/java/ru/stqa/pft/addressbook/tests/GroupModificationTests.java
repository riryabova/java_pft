package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
//    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test11").withHeader("new test2").withFooter("new test3");
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();
//    assertEquals(after.size(), before.size());

//    before.remove(modifiedGroup);
//    before.add(group);
//    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
