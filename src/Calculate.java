import java.util.ArrayList;

public class Calculate extends CalculateWithoutBracket {

    public static void Calculate(char[] array) {

        // ЗАПИСЫВАЕМ ЧИСЛА И ЗНАКИ В ОТДЕЛЬНЫЕ СПИСКИ //
        /////////////////////////////////////////////////////////////////////
        ArrayList<Double> NumberList = new ArrayList<>();       //Список всех чисел в выражении
        ArrayList<Character> SignList = new ArrayList<>();      //Список всех знаков в выражении
        String workStr = "";
        int pastIndex = 0;

        for (int i = 0; i < array.length; i++) {                                                //Заполнение SignList и NumberList
            if ((array[i] == '-' || array[i] == '+') && (i == 0 || array[i - 1] == '(')) {      //Проверка на унарые операции
                NumberList.add(0.0);
                SignList.add(array[i]);
                continue;
            }

            if ((array[i] == '*') || (array[i] == '/') || (array[i] == '+') || (array[i] == '-') || (array[i] == '(') || (array[i] == ')')) {
                SignList.add(array[i]);
                if (array[i] == '(' || array[i] == ')') {
                    continue;
                }
                NumberList.add((double) Integer.parseInt(workStr));
                workStr = "";
            } else {
                if (pastIndex == i - 1) {
                    pastIndex = i;
                    workStr += Character.toString(array[i]);
                } else {
                    pastIndex = i;
                    workStr = "";
                    workStr += Character.toString(array[i]);
                }
            }
        }
        NumberList.add((double) Integer.parseInt(workStr));             //Добавление последнего числа

        double answer = WorkWithBrackets(NumberList,SignList);
        System.out.println("Ответ: " + answer);
    }

    public static double WorkWithBrackets(ArrayList<Double> NumberList, ArrayList<Character> SignList){
        ArrayList<Character> currentSignList = new ArrayList<>();       //Список текущего SignList-а
        ArrayList<Double> currentNumberList = new ArrayList<>();        //Список текущего NumberList-а
        ArrayList<Integer> LeftBracketList = new ArrayList<>();         //Список левых скобок '('
        int currentSignListSize = SignList.size();

        for (int i = 0; i < currentSignListSize; ++i) {

            if (SignList.get(i) == '(') {
                LeftBracketList.add(i);
            }
            if (SignList.get(i) == ')') {
                int indexLastLeftBracket = LeftBracketList.get(LeftBracketList.size() - 1);     //Индекс последней левой скобки в LeftBracketList

                for (int j = indexLastLeftBracket + 1; j < i; j++) {                            //Заполнение текущего SignList-а БЕЗ СКОБОК
                    currentSignList.add(SignList.get(j));
                }

                int startIndex = indexLastLeftBracket - LeftBracketList.size() + 1;
                int endIndex = i - LeftBracketList.size();
                for (int j = startIndex; j <= endIndex; j++) {                                  //Заполнение текущего NumberList-а
                    currentNumberList.add(NumberList.get(j));
                }

                double currentResultInBrackets = CalculateWithoutBracket(currentNumberList, currentSignList);   //Текущее значение в скобках

                currentNumberList.remove(0);

                for (int d = i; d >= indexLastLeftBracket; d--) {           //Удаление использованных знаков
                    SignList.remove(d);
                }

                for (int k = endIndex; k >= startIndex; k--) {              //Удаление использованных чисел
                    NumberList.remove(k);
                }

                NumberList.add(startIndex, currentResultInBrackets);        //Добавление текущего значения в NumberList

                for (int j = LeftBracketList.size() - 1; j >= 0; j--) {     //Удаление всех элементов из LeftBracketList
                    LeftBracketList.remove(j);
                }
                currentSignListSize = SignList.size();
                i = -1;
            }
        }
        return CalculateWithoutBracket(NumberList, SignList);
    }
}
