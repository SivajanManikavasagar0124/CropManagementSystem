package jUnitTest;

public class MultiplicationJU
{
 public static void main(String[] args)
 {
  int num1 = 20;
  int num2 = 20;
  MultiplicationJU test = new MultiplicationJU();
  int product = test.nums(num1,num2);
  System.out.println(num1 + " x " + num2 + " = " + product);
 }
 public int nums(int num1, int num2)
 {
  int product = num1 * num2;
  return product;
 }
}
