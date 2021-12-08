package jUnitTest;

public class PrimeJU
{
 public static void main(String[] args)
 {
  PrimeJU test = new PrimeJU();
  int num = 7;
  String solution = test.Prime(num);
  System.out.println(num + solution);
 }
 public String Prime(int num)
 {
  boolean prime = false;
  String solution;
  for (int p = 2; p <= num / 2; ++p)
  {
   if (num % p == 0)
   {
    prime = true;
    break;
   }}
   if (!prime)
   solution = "PRIME";
   else
   solution = "NOT PRIME";
   return solution;
 }}
