public class MainClass {
    public static int arrayMethod(String[][] array) throws MySizeArrayException, MyArrayDataException {
        final int REQUIRED_LEN = 4;
        boolean isArrayGood = true;
        int i=0, j=0, sum = 0;
        /*
         * 1) Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
         * */
        try {
            if (array.length == REQUIRED_LEN) {
                for (i = 0; i < REQUIRED_LEN; i++) {
                    if (array[i].length != REQUIRED_LEN) {
                        isArrayGood = false;
                        break;
                    }
                }
            } else {
                isArrayGood = false;
            }

            if (!isArrayGood) {
                throw new MySizeArrayException();
            } else {
                System.out.println("Array is good");
            }
            /*
            2) Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
            * */
            try {

                for (i = 0; i < array.length; i++) {
                    for (j = 0; j < array[i].length; j++) {
                        sum += Integer.parseInt(array[i][j]);
                    }
                }
                return sum;
            } catch (NumberFormatException еxception) {
                throw new MyArrayDataException(i,j);
            }
        } catch (MySizeArrayException еxception) {
            throw еxception;
        } catch (MyArrayDataException еxception) {
            throw еxception;
        }
    }

    public static void main(String[] args) {

        String[][] testArray = new String[4][4];
        int result = 0;

        /* Заполняем массив данными */
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                testArray[i][j] = "" + (i * testArray[i].length + j + 1);// нумеруем по порядку от 1 до 16
            }
        }

        testArray[2][3] = "MAMA";
/*
3) В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
*/
        try {
            result = arrayMethod(testArray);
            System.out.println("Sum is " + result);
        } catch (MySizeArrayException еxception) {
            System.out.println("Size of array is incorrect!");
        } catch (MyArrayDataException еxception) {
            System.out.println(еxception);
        }
    }
}

class MySizeArrayException extends RuntimeException {
}

class MyArrayDataException extends RuntimeException {
    int x;
    int y;

    public MyArrayDataException(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "Array element [" + this.x +"," + this.y + "] is incorrect";
    }
}