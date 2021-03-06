package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.*;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }


  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

//  public void fillContactForm(ContactData contactData, boolean creation) {
//    type(By.name("firstname"), contactData.getFirstName());
//    type(By.name("lastname"), contactData.getLastName());
//    type(By.name("address"), contactData.getAddress());
//    type(By.name("mobile"), contactData.getMobilePhone());
//    type(By.name("email"), contactData.getEmail());
//   attach(By.name("photo"), contactData.getPhoto());
//
//    if (creation) {
//
//      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//    } else {
//      Assert.assertFalse(isElementPresent(By.name("new_group")));
//    }
//  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
//    attach(By.name("photo"), contactData.getPhoto());
  }

  public void initContactCreation() {
    click((By.linkText("add new")));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedContacts(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
  }

//  public void addToGroup(ContactData contact, String newGroupName) {
//    selectContactById(contact.getId());
//    Select nGroup = new Select(wd.findElement(By.name("to_group")));
//    nGroup.selectByVisibleText(newGroupName);
//    wd.findElement(By.name("add")).click();
////    contactCache = null;
//  }
//
//  public void RemoveFromGroup(ContactData contact, String newGroupName) {
//    selectContactById(contact.getId());
//    Select nGroup = new Select(wd.findElement(By.name("group")));
//    nGroup.selectByVisibleText(newGroupName);
//    wd.findElement(By.name("remove")).click();
////    contactCache = null;
//  }


  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModification(int id) {
//    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    wd.findElement(By.xpath("//input[@value='" + id + "']//ancestor::td//following-sibling::td//img[@title='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

//  public void create(ContactData contact, boolean creation) {
//    initContactCreation();
//    fillContactForm(contact, creation);
//    submitContactCreation();
//    contactCache = null;
//    returnToHomePage();
//  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact);

    submitContactModification();
    contactCache = null;
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {

    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) { //сами строки контатов
      List<WebElement> cells = element.findElements(By.tagName("td")); //ячейки
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      int id = Integer.parseInt(cells.get(0).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all_contact() {
    if (contactCache != null) {

      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) { //сами строки контатов
      List<WebElement> cells = element.findElements(By.tagName("td")); //ячейки
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      int id = Integer.parseInt(cells.get(0).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
//      String[] phones = cells.get(5).getText().split("\n");
      String allEmails = cells.get(4).getText();
      String allAddress = cells.get(3).getText();
      String allPhones = cells.get(5).getText();


      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(allAddress));
    }
    return contactCache;

  }

  public ContactData infoFromEditForm(ContactData contact) {

    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData getRandomContact(Set beforeContacts) {
    ContactData contact = (ContactData) beforeContacts.iterator().next();
    return contact;
  }

  public void addContactToGroup(int contactID, int GroupID) {
    selectContactById(contactID);
    wd.findElement(By.name("to_group")).click();
    wd.findElement(By.xpath("//*[@id=\"content\"]//div[4]//*[@value='" + GroupID + "']")).click();
    wd.findElement(By.xpath("//*[@id=\"content\"]/form[2]/div[4]/input")).click();
  }

  public void removeContactFromGroup(int contactID, int GroupID) {
    wd.findElement(By.xpath(".//*[@id=\"right\"]/select")).click();
    wd.findElement(By.xpath(".//*[@id=\"right\"]/select//*[@value='" + GroupID + "']")).click();
    selectContactById(contactID);
    wd.findElement(By.name("remove")).click();
  }

}


