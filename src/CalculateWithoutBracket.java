import java.util.ArrayList;

public class CalculateWithoutBracket {

    public static double CalculateWithoutBracket(ArrayList<Double> NumberList, ArrayList<Character> SignList){
        double result = 0;
        int charListSize1 = SignList.size();

        for (int i = 0; i < charListSize1; ++i){                            //Поиск знаков '*', '/' и работа с ними
            switch (SignList.get(i)){
                case '*':
                    result += NumberList.get(i) * NumberList.get(i + 1);
                    NumberList.remove(i + 1);
                    NumberList.remove(i);
                    NumberList.add(i,result);
                    SignList.remove(i);
                    charListSize1--;
                    result = 0;
                    i = -1;
                    break;
                case '/':
                    result += NumberList.get(i) / NumberList.get(i + 1);
                    NumberList.remove(i + 1);
                    NumberList.remove(i);
                    NumberList.add(i,result);
                    SignList.remove(i);
                    charListSize1--;
                    result = 0;
                    i = -1;
                    break;
            }
        }

        int charListSize2 = SignList.size();
        for (int i = 0; i < charListSize2; i++) {                           //Поиск знаков '+', '-' и работа с ними
            switch (SignList.get(i)){
                case '+':
                    result += NumberList.get(i) + NumberList.get(i + 1);
                    NumberList.remove(i + 1);
                    NumberList.remove(i);
                    NumberList.add(i,result);
                    SignList.remove(i);
                    charListSize2--;
                    result = 0;
                    i = -1;
                    break;
                case '-':
                    result += NumberList.get(i) - NumberList.get(i + 1);
                    NumberList.remove(i + 1);
                    NumberList.remove(i);
                    NumberList.add(i,result);
                    SignList.remove(i);
                    charListSize2--;
                    result = 0;
                    i = -1;
                    break;
            }
        }
        result = NumberList.get(0);
        return result;
    }
}
