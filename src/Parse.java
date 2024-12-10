import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Содержит публичный метод, который принимает аргументы приходящие из метода main().
 * <p>В процессе парсинга аргументов изменяется состояние {@code WriterFiles}.
 * <p>В процессе парсинга обрабатывает только файлы с расширением ".txt".
 * <p>Для корректной работы утилиты достаточно одного пути к файлу с расширением ".txt"
 * <p>При отсутствии такового файла программа завершит выполнение.
 * <p>Вызывается метод {@code run()}, где пробрасываютя вызовы методов классам: {@code ReaderFiles},{@code Filter},{@code StatisticPrint}
 */


public class Parse {

    private final WriterFiles writerFiles;
    private int statisticType;

    public Parse(WriterFiles writerFiles) {
        this.writerFiles = writerFiles;
    }

    public void parse(String[] args) {
        boolean containsFile = false;
        ArrayList<String> files = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-help" -> new HelpPrintConsole();
                case "-s" -> statisticType = 1;
                case "-f" -> statisticType = 2;
                case "-a" -> writerFiles.setRewrite(true);
                case "-o" -> writerFiles.setFileWayResult(args[i + 1]);
                case "-p" -> writerFiles.setPrefix(args[i + 1]);
            }
            if (args[i].endsWith(".txt")) {
                files.add(args[i]);
                containsFile = true;
            }
        }
        if (!containsFile) {
            System.out.println("Error: Отсутсвуют искомые файлы.\nУкажите валидный путь к файлу!");
            System.exit(1);
        }
        run(files);
    }

    private void run(ArrayList<String> files) {
        ReaderFiles readerFiles = new ReaderFiles(files);
        List<String> lines = readerFiles.getResultLines();
        Map<String, List<String>> map = Filter.getFiltersLinesMap(lines);
        writerFiles.write(map);
        StatisticPrint statisticPrint = new StatisticPrint(statisticType);
        statisticPrint.statisticPrint(map);
    }
}
