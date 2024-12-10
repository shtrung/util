import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>Отвечает за запись отфильтрованных типов.
 * Содержит сеттеры, поля изменяются исходя из входных параметров.
 * Если какой либо из параметров отсутсвует, {@code field == null}.
 * <p>Например: При отсутсвии параметра {@code "-o"}, результат будет записан в текущую дирректори,
 *    где находится утилита.
 *
 * <p>Создает валидный путь для записи с учетом преффикса (если он задан) и расширением {@code ".txt"}.
 * При отсутствие в результирующей структуре данных {@code Map} какого либо из типов данных - новый (пустой) файл
 *    создан не будет.
 */

public class WriterFiles {

    private String fileWayResult;
    private String prefix;
    private boolean isRewrite;

    public void write(Map<String, List<String>> maps) {
        for (String key : maps.keySet()) {
            String foolWay;
            String nameFile = key;
            if (notNull(prefix)) {
                nameFile = prefix + key;
            }
            nameFile += ".txt";

            if (notNull(fileWayResult)) {
                if (!fileWayResult.endsWith("\\")) {
                    fileWayResult = fileWayResult + "\\";
                }
                foolWay = fileWayResult + nameFile;
            } else {
                String currentDirectory = System.getProperty("user.dir") + "\\";
                foolWay = currentDirectory + nameFile;
            }
            if (!maps.get(key).isEmpty()) {
                createAndWriteFiles(foolWay, maps.get(key));
            }
        }
    }

    private void createAndWriteFiles(String foolWay, List<String> result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(foolWay, isRewrite))) {
            for (String line : result) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: Ошибка записи в файл\nПроверьте что путь для" +
                    " результата является абсолютным!");
            System.exit(1);
        }
    }

    private boolean notNull(String notNullLine) {
        return notNullLine != null;
    }

    public void setFileWayResult(String fileWayResult) {
        this.fileWayResult = fileWayResult;
    }

    public void setRewrite(boolean rewrite) {
        isRewrite = rewrite;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
