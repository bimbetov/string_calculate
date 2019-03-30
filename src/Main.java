import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Calculate{

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Программа работает с:\n" +
                "- бинарными функциями: +, -, ×, ÷.\n" +
                "- унарными функциями: - (минус).\n" +
                "- произвольно вложенными скобками.\n" +
                "Пример: 1+(-3*2-(1+2))/3\n" +
                "Ответ: -2.0\n" +
                "Введите арифметическое выражение: ");

        try {
            char[] symbol;
            String str;
            do {
                str = reader.readLine();
                symbol = str.toCharArray();
            } while (!Checked(symbol));

            Calculate(symbol);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean Checked(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (((array[i] >= '0' && array[i] <= '9') || array[i] == '+' || array[i] == '-' || array[i] == '*' ||
                    array[i] == '/' || array[i] == '(' || array[i] == ')') && (array[0] != '*') && (array[0] != '/')) {
                continue;
            } else {
                System.out.println("Вы ввели некорректные данные!!!\nПопробуйте снова");
                return false;
            }
        }
        return true;
    }
}
