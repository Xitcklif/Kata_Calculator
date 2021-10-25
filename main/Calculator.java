/* main для проверок.
public static void main(String[] args)
    {
        String[] anum = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] rnum = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] sym = new String[] {" + ", " - ", " * ", " / "};

        for (byte i = 0; i < 10; i++)
        {
            for (byte j = 0; j < 10; j++)
            {
                for (byte k = 0; k < 4; k++)
                {
                    System.out.print(anum[i] + sym[k] + anum[j] + " = ");
                    String[] str = (anum[i] + sym[k] + anum[j]).split(" ");
                    byte check = check_string(str); //0 - Неверная строка, 2 - арабские числа, -2 - римские числа.
                    if (check != 0)
                        calculate(str, check);
                    else
                        System.out.println("throws Exception //Неверный формат строки");
                }
            }
        }
        System.out.println("##################");
        for (byte i = 0; i < 10; i++)
        {
            for (byte j = 0; j < 10; j++)
            {
                for (byte k = 0; k < 4; k++)
                {
                    System.out.print(rnum[i] + sym[k] + rnum[j] + " = ");
                    String[] str = (rnum[i] + sym[k] + rnum[j]).split(" ");
                    byte check = check_string(str); //0 - Неверная строка, 2 - арабские числа, -2 - римские числа.
                    if (check != 0)
                        calculate(str, check);
                    else
                        System.out.println("throws Exception //Неверный формат строки");
                }
            }
        }
}
 */

package main;
import java.util.Scanner;
import java.lang.String;

public class Calculator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        String[] str = in.nextLine().split(" ");
        byte check = check_string(str); //0 - Неверная строка, 2 - арабские числа, -2 - римские числа.
        if (check != 0)
            calculate(str, check);
        else
            System.out.println("throws Exception //Неверный формат строки");
    }

    public static byte check_string(String[] str)
    {
        String[] anum = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] rnum = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        byte check = 0;
        if (str.length != 3)
            return (0);
        for (byte i = 0; i < 10; i++)
        {
            if (str[0].equals(anum[i]) || str[2].equals(anum[i]))
                check++;
            if (str[0].equals(rnum[i]) || str[2].equals(rnum[i]))
                check--;
            if (str[0].equals(str[2]) && str[2].equals(anum[i]))
                check++;
            if (str[0].equals(str[2]) && str[2].equals(rnum[i]))
                check--;
            if (i == 9 && check != 2 && check != -2)
                return (0);
        }
        if (get_symbol(str[1]) == -1)
            return (0);
        return (check);
    }

    public static void calculate(String[] str, byte check)
    {
        byte result = 0;

        switch (get_symbol(str[1])) {
            case (0) -> result = (byte)(to_num(str[0]) + to_num(str[2]));
            case (1) -> result = (byte)(to_num(str[0]) - to_num(str[2]));
            case (2) -> result = (byte)(to_num(str[0]) * to_num(str[2]));
            case (3) -> result = (byte)(to_num(str[0]) / to_num(str[2]));
            default -> {
            }
        }
        switch (check) {
            case (2) -> System.out.println(result);
            case (-2) -> print_roman_number(result);
            default -> {
            }
        }
    }

    public static byte to_num(String str)
    {
        String[] anum = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] rnum = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (byte i = 0; i < 10; i++)
        {
            if (str.equals(anum[i]))
                return (byte)(i + 1);
            if (str.equals(rnum[i]))
                return (byte)(i + 1);
        }
        return (0);
    }

    public static byte get_symbol(String str)
    {
        String[] sym = new String[] {"+", "-", "*", "/"};

        for (byte i = 0; i < 4; i++)
            if (str.equals(sym[i]))
                return (i);
        return (-1);
    }

    public static void print_roman_number(byte num)
    {
        byte hundred, ten, units;

        if (num < 1)
            System.out.println("throws Exception //В римской системе нет отрицательных чисел и нуля");
        else
        {
            hundred = (byte)(num / 100);
            ten = (byte)(num / 10);
            units = (byte)(num % 10);
            print_num(hundred, (byte)4);
            print_num(ten, (byte)2);
            print_num(units, (byte)0);
            System.out.println();
        }
    }

    public static void print_num(byte num, byte indent)
    {
        String[] roman = new String[] {"I", "V", "X", "L", "C"};

        if (num >= 5 && num <= 8)
        {
            System.out.print(roman[1 + indent]);
            num -= 5;
        }
        if (num <= 3)
            for (byte i = 0; i < num; i++)
                System.out.print(roman[0 + indent]);
        else if (num == 4)
            System.out.print(roman[0 + indent] + roman[1 + indent]);
        else if (num == 9)
            System.out.print(roman[0 + indent] + roman[2 + indent]);
    }
}
