package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  //Задание 2. Пункт 4. Метод вычисления расстояния между двумя точками в классе Point
  //AB = √(xb - xa)2 + (yb - ya)2
  public double distance(Point P2) {
    return Math.sqrt(Math.pow((P2.x - this.x), 2) + Math.pow((P2.y - this.y), 2));
  }
}
