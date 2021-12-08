package jUnitTest;
import static org.junit.Assert.*;
import org.junit.Test;

public class JUNIT4testcases
{
 @Test
 public void Addition()
 {
 AdditionJU t1 = new AdditionJU();
 int actual = t1.AddAll(10);
 int expected = 55;
 assertEquals(expected,actual);
 }
 @Test
 public void Multiplication()
 {
  MultiplicationJU t2 = new MultiplicationJU();
  int actual = t2.nums(20, 20);
  int expected = 400;
  assertEquals(expected,actual);
 }
 @Test
 public void PrimeN()
 {
  PrimeJU t3 = new PrimeJU();
  String actual = t3.Prime(7);
  String expected = "PRIME";
  assertEquals(expected,actual);
 }
 @Test
 public void Alphabet()
 {
  AlphabetsJU t4 = new AlphabetsJU();
  String actual = t4.character('a');
  String expected = "ALPHABETS CLEAR";
  assertEquals(expected,actual);
 }
 @Test
 public void Reverse()
 {
  StringReverse reversetest = new StringReverse();
  String actual = reversetest.reverse("sunshine");
  String expected = "enihsnus";
  assertEquals(expected,actual);

 }
}