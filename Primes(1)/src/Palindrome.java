import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        System.out.println("Введите строку: ");
        System.out.println(isPalindrome(str.nextLine()));
        }


   public static String reverseString(String str) {

        StringBuilder rev = new StringBuilder(); // Создание модифицируемой строки

        for(int i = str.length() - 1; i>=0; i--){
        rev.append(str.charAt(i));          // Прибавление символов исходной строки к новой, начиная с конца

       }
        return(rev.toString());     // Возвращение полученной строки
    }

    public static boolean isPalindrome(String s) {
    return s.equals(reverseString(s));  // Сравнение введённой и полученной строк

    }
}
