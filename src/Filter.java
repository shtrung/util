import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Утилитный класс с единственным публичным методом {@code getFiltersLinesMap()}.
 * <p>Возравращает тип данных {@code Map} с ключами по каждому типу.
 * <p>Содержит логику фильтрации типа данных {@code int, float, String}.
 */

public class Filter {

    public static Map<String, List<String>> getFiltersLinesMap(List<String> resultsRead) {
        Map<String, List<String>> maps = new HashMap<>();
        maps.put("integers", new ArrayList<>());
        maps.put("strings", new ArrayList<>());
        maps.put("floats", new ArrayList<>());

        for (String line : resultsRead) {
            String key = getKey(line);
            if (key != null) {
                maps.get(key).add(line);
            }
        }
        return maps;
    }

    private static String getKey(String line) {
        for (char c : line.toCharArray()) {
            if (Character.isLetter(c)) {
                return "strings";
            } else if (Character.isDigit(c) && line.contains(".")) {
                return "floats";
            } else if (Character.isDigit(c)) {
                return "integers";
            }
        }
        return null;
    }

}
