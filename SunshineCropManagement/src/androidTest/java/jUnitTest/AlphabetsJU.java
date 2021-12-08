package jUnitTest;


public class AlphabetsJU
{
    public String character(char a)
    {
      String result;
      if( (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z'))
      result =  "ALPHABETS CLEAR";
      else
      result = " ALPHABETS CLEAR";
      return result;
    }
     public static void main (String[] args)
     {
            AlphabetsJU test = new AlphabetsJU();
            char a = '*';
            String text = test.character(a);
            System.out.println(a + text);
     }
    }


