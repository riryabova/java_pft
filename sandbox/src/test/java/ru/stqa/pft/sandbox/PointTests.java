package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PointTests {
  public void distanceTestPositiveNumber() {
    Point P1 = new Point(1, 1);
    Point P2 = new Point(3, 3);
    Assert.assertEquals(P1.distance(P2), 2.8284271247461903);
  }

  public void distanceTestNegativeNumber() {
    Point P1 = new Point(-1, -1);
    Point P2 = new Point(-3, -3);
    Assert.assertEquals(P1.distance(P2), 2.8284271247461903);
  }

  public void distanceTestZero() {
    Point P1 = new Point(0, 0);
    Point P2 = new Point(0, 0);
    Assert.assertEquals(P1.distance(P2), 0.);
  }

  public void distanceTestDouble() {
    Point P1 = new Point(1.33, 1.45);
    Point P2 = new Point(1.11, 5.34);
    Assert.assertEquals(P1.distance(P2), 3.896216113102557);
  }
}


