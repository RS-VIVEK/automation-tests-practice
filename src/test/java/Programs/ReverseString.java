package Programs;

import java.util.Scanner;

public class ReverseString {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the String");
        String str = scn.nextLine();
        char ch;
        String sr = "";
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            sr = ch + sr;
        }
        System.out.println(sr);
    }
}
