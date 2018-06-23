package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class EquationTests {
  public void test0() {
    Equation e = new Equation(100, 1, 2);
    Assert.assertEquals(e.rootNumber(),0);
  }

  public void test2() {
    Equation e = new Equation(1, 10, 2);
    Assert.assertEquals(e.rootNumber(),2);
  }

  public void test1() {
    Equation e = new Equation(1, 2, 1);
    Assert.assertEquals(e.rootNumber(),1);
  }
}


