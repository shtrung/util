/**
 * <p>Выводит в консоль сообщение о том с какими параметрами может быть запущена утилита.
 */

public class HelpPrintConsole {

    public HelpPrintConsole() {
        System.out.println(helpMessage());
        System.exit(0);
    }

    private String helpMessage(){
        return """
                Параметры с которыми может быть запущенна утилита "util"
                -s : Выводит в консоль краткую статистику по обработанным файлам.
                -f : Выводит в консоль полную статистику по обработанным файлам.
                -a : Записывает в конец существующего файла новый результат.
                     При отсутвии данного флага результат будет перезаписан.
                -o : Задает абсолютный путь к директории для записи результирующих файлов.
                     Следующим параметром через пробел необходимо передать сам путь к директории.
                     При отсутвии данного флага результат будет записан в директорию где расположена данная утилита.
                -p : Задает префикс имен выходных файлов.
                     Следующим параметром необходимо указать значение префикса.
                Пример запуска: java -jar util.jar [-s/-f -a -o [file path] -p [prefix] .../in1.txt .../in2.txt]""";
    }
}
