import java.util.Scanner;
public class test {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String oldString = scanner.nextLine();
        String newString = oldString.toUpperCase();
        // System.out.println(newString+" строковая операция");
        // ниже перечисляю корректные значения для ввода (newRome и newArab)
        // для двузначного вывода римскими числами для десятков допустимо использование чисел из массива "tensRome"
        String[] newArab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] newRome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] tensRome = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] newOperation = newString.split(" ");
        // System.out.println(newOperation+" массив операции");
        if(newOperation.length != 3){
            throw new Exception("Некорректный формат ввода.");
        }
        String strX = newOperation[0];
        String strY = newOperation[2];
        String newOperator = newOperation[1];
        // System.out.println(strX+" первая строковая переменная");
        // System.out.println(strY+" вторая строковая переменная");
        // System.out.println(newOperator+" математический оператор");
        int indRome = 0;
        int q1 = 0;
        int q2 = 0;
        // ниже проверяю вводимые числа, фиксирую попадания
        // (оба арабские - индекс равен нулю, одно из чисел римское - единице, оба числа римские - двойке)
        for (int i = 0; i < newRome.length; i++) {
            if (newRome[i].equals(strX)) {
                indRome++;
                q1 = i;
            }
            if (newRome[i].equals(strY)) {
                indRome++;
                q2 = i;
            }
        }
        // если одно из чисел - арабское, а другое - римское, прогр возвращает исключение и завершает работу
        if (indRome == 1) {
            throw new Exception("Некорректный формат ввода.");
        }
        // ниже проверяю вводимые числа на соответствие допустимым
        int strXIndex = 10;
        int strYIndex = 10;
        switch(indRome){
            case 0:
                for(int i = 0; i < newArab.length; i++){
                    if(strX.equals(newArab[i])){
                        strXIndex = i;
                    }
                    if(strY.equals(newArab[i])){
                        strYIndex = i;
                    }
                }
                // System.out.println(strXIndex+" индекс первой переменной");
                // System.out.println(strYIndex+" индекс второй переменной");
                if(strXIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                if(strYIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                break;
            case 2:
                for(int i = 0; i < newRome.length; i++){
                    if(strX.equals(newRome[i])){
                        strXIndex = i;
                    }
                    if(strY.equals(newRome[i])){
                        strYIndex = i;
                    }
                }
                // System.out.println(strXIndex+" индекс первой переменной");
                // System.out.println(strYIndex+" индекс второй переменной");
                if(strXIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                if(strYIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                strX = newArab[q1];
                strY = newArab[q2];
                break;
        }
        // ниже перевожу значения введенных чисел в целочисленный тип
        // далее провожу арифметические действия
        int intX = Integer.parseInt(strX);
        int intY = Integer.parseInt(strY);
        // System.out.println(intX+" первая числовая переменная");
        // System.out.println(intY+" вторая числовая переменная");
        int myResult = 0;
        switch (newOperator) {
            case "+":
                myResult = intX + intY;
                break;
            case "-":
                myResult = intX - intY;
                break;
            case "*":
                myResult = intX * intY;
                break;
            case "/":
                myResult = intX / intY;
                break;
            default:
                throw new Exception("Некорректный формат ввода.");
        }
        String myResultString = Integer.toString(myResult);
        // System.out.println(myResult+" промежуточный результат");
        String myResultStringRome = null;
        String[] myResultStringArray;
        // System.out.println(indRome+" индекс арабо-римских отношений");
        switch(indRome) {
            case 0:
                System.out.println(myResultString);
                break;
            case 2:
                if(myResult<=0){
                    throw new Exception("Некорректный формат вывода.");
                }
                switch(myResultString.length()) {
                    case 3:
                        System.out.println("C"); //" итоговый результат");
                        break;
                    case 2:
                        myResultStringArray = myResultString.split("");
                        for(int i = 0; i < newArab.length; i++){
                            if(myResultStringArray[0].equals(newArab[i])){
                                myResultStringArray[0] = tensRome[i];
                            }
                        }
                        for(int i = 0; i < newArab.length; i++){
                            if(myResultStringArray[1].equals(newArab[i])){
                                myResultStringArray[1] = newRome[i];
                            }
                        }
                        // System.out.println(myResultStringArray[0]);
                        // System.out.println(myResultStringArray[1]);
                        myResultStringRome = myResultStringArray[0] + myResultStringArray[1];
                        System.out.println(myResultStringRome);
                        break;
                    case 1:
                        for(int i = 0; i < newArab.length; i++){
                            if(myResultString.equals(newArab[i])){
                                myResultStringRome = newRome[i];
                                System.out.println(myResultStringRome); //" итоговый результат");
                            }
                        }
                        break;
                }
                break;
        }
    }
}