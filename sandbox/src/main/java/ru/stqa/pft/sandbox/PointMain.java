package ru.stqa.pft.sandbox;

public class PointMain {
  public static void main(String[] args) {
    Point P1 = new Point(0, 0);
    Point P2 = new Point(1, 1);
//Задание 2. Пункт 3. С вызовом функции:
    System.out.println("Расстояние между точками при помощи функции: P1(" + P1.x + "," + P1.y + ") и P2(" + P2.x + "," + P2.y + ") = " + distance(P1, P2));

//Задание 2. Пункт 4. Вызов метода из класса Point
    System.out.println("Расстояние между точками при помощи метода: P1(" + P1.x + "," + P1.y + ") и P2(" + P2.x + "," + P2.y + ") = " + P1.distance(P2));
  }

  //AB = √(xb - xa)2 + (yb - ya)2
  public static double distance(Point P1, Point P2) {
    return Math.sqrt(Math.pow((P2.x - P1.x), 2) + Math.pow((P2.y - P1.y), 2));
  }
}
