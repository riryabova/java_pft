package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = 0;
  private String firstName;
  private String lastName;
  private String address;
  private String mobilePhone;
  private String email;
  private String group;


//  public ContactData(int id, String firstName, String lastName, String address, String mobilePhone, String email, String group) {
//    this.id = id;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.address = address;
//    this.mobilePhone = mobilePhone;
//    this.email = email;
//    this.group = group;
//  }
//
//
//  public ContactData(String firstName, String lastName, String address, String mobilePhone, String email, String group) {
//    this.id = 0;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.address = address;
//    this.mobilePhone = mobilePhone;
//    this.email = email;
//    this.group = group;
//  }

  public ContactData withId(int id) {

    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {
    return id;
  }


  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
