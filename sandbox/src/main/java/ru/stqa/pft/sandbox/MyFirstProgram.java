package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("Rita");
    double l = 6;
    double m = 7;
    System.out.println("Площадь квадрата со стороной "+l+" = "+ area(l));
    System.out.println("Площадь прямоугольника со сторонами "+l+" и "+m+" = "+ area(l,m));
  }

  public static void hello(String sombody) {
    System.out.println("Hello, " + sombody + " !");
  }

  public static double area(double l){
    return l*l;
  }

  public static double area(double l, double m){
    return l*m;
  }
}