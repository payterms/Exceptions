public class MainClass {
    public static final int REQUIRED_LEN = 4;

    public static int arrayMethod(String[][] array) throws MySizeArrayException, MyArrayDataException {
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
                throw new MySizeArrayException( "Размеры массива недопустимы для данной задачи.", array.length, array[i].length);
            } else {
                System.out.println("Размер массива верный");
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
                throw new MyArrayDataException("Массив содержит элемент с недопустимым значением.",i,j);
            }
        } catch (MySizeArrayException еxception) {
            throw еxception;
        } catch (MyArrayDataException еxception) {
            throw еxception;
        }
    }

    public static void main(String[] args) {

        String[][] testArray = new String[4][4];
        String[][] testArrayWrongSized = new String[3][4];
        int result = 0;

        /* Заполняем массив данными */
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                testArray[i][j] = "" + (i * testArray[i].length + j + 1);// нумеруем по порядку от 1 до 16
            }
        }

/*
3) В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
*/
        /* пытаемся обработать целочисленный массив */
        try {
            result = arrayMethod(testArray);
            System.out.println("Сумма элементов = " + result);
        } catch (MySizeArrayException еxception) {
            System.out.println(еxception);
        } catch (MyArrayDataException еxception) {
            System.out.println(еxception);// отображаем индексы элемента, который вызвал ошибку
        }

        testArray[2][3] = "MAMA";// для отладки заполняем строковыми данными
        try {
            result = arrayMethod(testArray);
            System.out.println("Сумма элементов = " + result);
        } catch (MySizeArrayException еxception) {
            System.out.println(еxception);
        } catch (MyArrayDataException еxception) {
            System.out.println(еxception);// отображаем индексы элемента, который вызвал ошибку
        }

        try {
            result = arrayMethod(testArrayWrongSized);
            System.out.println("Сумма элементов = " + result);
        } catch (MySizeArrayException еxception) {
            System.out.println(еxception);
        } catch (MyArrayDataException еxception) {
            System.out.println(еxception);// отображаем индексы элемента, который вызвал ошибку
        }

    }
}

class MySizeArrayException extends RuntimeException {
    int x;
    int y;

    public MySizeArrayException(String exceptionMessage, int x, int y) {
        super(String.format(exceptionMessage + "Размер массива [%d, %d]", x,y));
        this.x = x;
        this.y = y;
    }
}

class MyArrayDataException extends RuntimeException {
    // индексы элемнта, вызвавшего исключение
    int x;
    int y;

    public MyArrayDataException(String exceptionMessage, int x, int y) {
        super(String.format(exceptionMessage + "Индекс элемента:[%d, %d]", x,y));
        this.x = x;
        this.y = y;
    }
}