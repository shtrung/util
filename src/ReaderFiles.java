import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Отвечает за считывание всех строк из файлов без учета типа.
 * <p>Возвращает единственный метод {@code getResultLines()} с резултатом считывания.
 */

public class ReaderFiles {

    private final List<String> files;
    private final List<String> resultLines;

    public ReaderFiles(ArrayList<String> files) {
        this.files = files;
        resultLines = linesGetFiles();
    }

    private List<String> linesGetFiles() {
        ArrayList<String> results = new ArrayList<>();
        for (String file : files) {
            Path path = Path.of(file);
            try {
                results.addAll(Files.readAllLines(path));
            } catch (IOException e) {
                System.out.println("I/O Exception: Ошибка считывания файла");
            }
        }
        return results;
    }

    public List<String> getResultLines() {
        return Collections.unmodifiableList(resultLines);
    }
}
