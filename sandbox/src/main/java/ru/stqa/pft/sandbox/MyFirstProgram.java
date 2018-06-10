package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("Rita");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 7.2);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());



    Point P1 = new Point(0, 0);
    Point P2 = new Point(2, 2);
    double distance = distance(P1, P2);

    System.out.println("Расстояние =" + distance);

  }

  public static void hello(String sombody) {
    System.out.println("Hello, " + sombody + " !");
  }
  //AB = √(xb - xa)2 + (yb - ya)2
  public static double distance(Point P1, Point P2) {
    return Math.sqrt(Math.pow((P2.x - P1.x), 2) + Math.pow((P2.y - P1.y), 2));
  }

}