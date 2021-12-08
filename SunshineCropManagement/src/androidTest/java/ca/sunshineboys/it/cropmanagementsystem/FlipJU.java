package ca.sunshineboys.it.cropmanagementsystem;

public class FlipJU
{
 public static void main (String[] args)
 {
  FlipJU test = new FlipJU();
  String w1 = "Jonah";
  String word = test.Flip(w1);
  System.out.println(" Jonah flipped is  " + word);
}
  public String Flip(String word)
  {
   String Flip = "";
   for(int w = word.length() - 1; w >= 0; w--)
   {
    Flip = Flip + word.charAt(w);
   }
   return word;
  }
}

