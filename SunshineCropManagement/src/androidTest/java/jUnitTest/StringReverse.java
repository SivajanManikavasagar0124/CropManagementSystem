package jUnitTest;

public class StringReverse
{
 public static void main (String[] args)
 {
     StringReverse test = new StringReverse();
     String name = "sunshine";
     String text = test.reverse(name);
     System.out.println(text);
}
    public String reverse(String string){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = stringBuilder.length() - 1 ;i >= 0 ; i--){
            stringBuilder.append(string.charAt(i));
        }
        return stringBuilder.toString();
    }
}

