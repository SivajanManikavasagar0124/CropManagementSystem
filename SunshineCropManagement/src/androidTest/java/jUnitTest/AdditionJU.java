package jUnitTest;

public class AdditionJU
{
 public static void main(String[] args)
 {
  AdditionJU  test = new AdditionJU ();
  int sum = test.AddAll(10);
  System.out.println(" 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = " + sum);
 }
 public int AddAll(int num)
 {
  int added = 0;
  for (int a = 0; a<= num; a++)
  {
   num += a;
  }
  return added;
 }}