import java.util.Scanner;

public class calculator {
    public static void main(String[] args) throws Exception {
        // инициализируем процесс получения строки
        input newInput = new input();
        // получаем на вход строку, проверяем корректность формата введённой строки
        String[] myArray = newInput.readInput();
        // выделяем из введённой строки переменные и оператор
        xyz XYZ = new xyz();
        String strX = XYZ.strX(myArray);
        String strY = XYZ.strY(myArray);
        String strZ = XYZ.strZ(myArray);
        // инициализируем проверку переменных и оператора на корректность формата ввода
        check newCheck = new check();
        // проверяем значения переменных на принадлежность одной и той же системе счисления
        int[] newIndArray = newCheck.checkStrXandZ(strX, strZ);
        int indRome = newIndArray[0];
        int q1 = newIndArray[1];
        int q2 = newIndArray[2];
        newCheck.indRome = indRome;
        // проверяем значение каждой переменной на соответствие допустимым значениям
        int[] newVarArray = newCheck.checkStrXorZ(strX, strZ, indRome, q1, q2);
        int intX = newVarArray[0];
        int intZ = newVarArray[1];
        // проверяем оператор на принадлежность массиву допустимых арифметических операций
        newCheck.checkY(strY);
        // инициализируем осуществление арифметической операции
        operation newOperation = new operation();
        // осуществляем арифметическую операцию
        int newResult = newOperation.operationXYZ(intX, intZ, strY);
        // инициализируем вывод результата арифметической операции
        output newOutput = new output();
        // выводим результат арифметической операции в зависимости от принадлежности исходных переменных
        // римской или арабской системе счисления
        newOutput.writeOutput(indRome, newResult);
    }
}
class input {
    String[] readInput() throws Exception {
        Scanner newScanner = new Scanner(System.in);
        // получаем на вход строку
        String oldString = newScanner.nextLine();
        // переводим полученную строку в верхний регистр
        // чтобы избежать исключений при вводе римских чисел
        String newString = oldString.toUpperCase();
        // переводим полученную строку в массив строк
        String[] newStringArray = newString.split(" ");
        // проверяем длину полученного массива. Массив должен быть равен 3.
        // две переменные + один арифметический оператор
        int newStringArrayLength = newStringArray.length;
        if (newStringArrayLength != 3) {
            throw new Exception("Некорректный формат ввода.");
        }
        return newStringArray;
    }
}

class xyz {
    String strX(String[] myArray) { // получаем строковое значение первой переменной
        String strX = myArray[0];
        return strX;
    }
    String strY(String[] myArray) { // получаем строковое значение арифметического оператора
        String strY = myArray[1];
        return strY;
    }
    String strZ(String[] myArray) { // получаем строковое значение второй переменной
        String strZ = myArray[2];
        return strZ;
    }
}

class check {
    String[] newArab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] newRome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    String[] newOperator = {"+", "-", "*", "/"};
    int indRome = 0;
    int q1 = 0;
    int q2 = 0;
    // проверяем обе переменные на принадлежность одной и той же системе счисления
    int[] checkStrXandZ(String strX, String strZ) throws Exception {
        for (int i = 0; i < newRome.length; i++) {
            if (newRome[i].equals(strX)) {
                indRome++;
                q1 = i;
            }
            if (newRome[i].equals(strZ)) {
                indRome++;
                q2 = i;
            }
        }
        if (indRome == 1) {
            throw new Exception("Некорректный формат ввода.");
        }
        int[] newOutput = new int[3];
        newOutput[0] = indRome;
        newOutput[1] = q1;
        newOutput[2] = q2;
        return newOutput;
    }
    int[] checkStrXorZ(String strX, String strZ, int indRome, int q1, int q2) throws Exception {
        int strXIndex = 10;
        int strZIndex = 10;
        switch(indRome){
            case 0:
                for(int i = 0; i < newArab.length; i++){
                    if(strX.equals(newArab[i])){
                        strXIndex = i;
                    }
                    if(strZ.equals(newArab[i])){
                        strZIndex = i;
                    }
                }
                if(strXIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                if(strZIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                break;
            case 2:
                for(int i = 0; i < newRome.length; i++){
                    if(strX.equals(newRome[i])){
                        strXIndex = i;
                    }
                    if(strZ.equals(newRome[i])){
                        strZIndex = i;
                    }
                }
                if(strXIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                if(strZIndex == 10){
                    throw new Exception("Некорректный формат ввода.");
                }
                strX = newArab[q1];
                strZ = newArab[q2];
                break;
        }
        int intX = Integer.parseInt(strX);
        int intZ = Integer.parseInt(strZ);
        int[] newOutput = new int[2];
        newOutput[0] = intX;
        newOutput[1] = intZ;
        return newOutput;
    }
    void checkY(String strY) throws Exception {
        int strYIndex = 4;
        for(int i = 0; i < newOperator.length; i++){
            if(strY.equals(newOperator[i])){
                strYIndex = i;
            }
        }
        if(strYIndex == 4){
            throw new Exception("Некорректный формат ввода.");
        }
    }
}

class operation {
    int operationXYZ(int intX, int intZ, String strY) {
        int myResult = 0;
        switch (strY) {
            case "+":
                myResult = intX + intZ;
                break;
            case "-":
                myResult = intX - intZ;
                break;
            case "*":
                myResult = intX * intZ;
                break;
            case "/":
                myResult = intX / intZ;
                break;
        }
        return myResult;
    }
}

class output {
    String[] newArab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] newRome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    String[] tensRome = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    void writeOutput(int indRome, int myResult) throws Exception {
        String myResultString = Integer.toString(myResult);
        String myResultStringRome = null;
        String[] myResultStringArray;
        switch(indRome) {
            case 0: // оба исходных числа - арабские
                System.out.println(myResultString);
                break;
            case 2: // оба исходных числа - римские
                if(myResult<=0){ // в результате выполнения арифметической операции не получено положительное число
                    throw new Exception("Некорректный формат вывода.");
                }
                switch(myResultString.length()) { // проверяем длину результата арифметической операции
                    case 3: // нужно вывести трёхзначное число
                        System.out.println("C");
                        break;
                    case 2: // нужно вывести двузначное число
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
                        myResultStringRome = myResultStringArray[0] + myResultStringArray[1];
                        System.out.println(myResultStringRome);
                        break;
                    case 1: // нужно вывести однозначное число
                        for(int i = 0; i < newArab.length; i++){
                            if(myResultString.equals(newArab[i])){
                                myResultStringRome = newRome[i];
                                System.out.println(myResultStringRome);
                            }
                        }
                        break;
                }
                break;
        }
    }
}