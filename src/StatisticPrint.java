import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *<p>Отвечает за сбор статистики по каждому типу {@code String, float, int}.
 *<p>Содержит конструктор со значением {@code int (-s == 1; -f == 2; null == 0)}.
 * Если опция не задана, статистика выведена не будет.
 *<p>Содержит единственный публичный метод который принимает результат фильтрации типов классом {@code Filter}
 *  и передает дальше в зависсимости от аргумента {@code -s/-f}
 *
 *<p>При параметре {@code -s}: Будет выведена в коносоль краткая статистика по каждому типу данных, а также
 *    общее количество элементов
 *<p>При параметре {@code -f}: Будет выведена в консоль полная статистика для типа:
 *  <p>{@code int, float} минимальное и максимальное значения, сумма и среднее.
 *  <p>{@code String} помимо их количества, содержит также размер самой
 * короткой строки и самой длинной.
 */


public class StatisticPrint {

    private final int statisticType;

    public StatisticPrint(int isFool) {
        this.statisticType = isFool;
    }

    public void statisticPrint(Map<String, List<String>> maps) {
        if (statisticType == 1) {
            shortStatistic(maps);
        } else if (statisticType == 2) {
            foolStatistic(maps);
        }
    }

    private void foolStatistic(Map<String, List<String>> maps) {
        for (String key : maps.keySet()) {
            List<String> strings = maps.get(key);
            if (!strings.isEmpty()) {
                if (key.equals("strings")) {
                    System.out.println(stringsFoolStatistic(strings));
                } else {
                    System.out.println(digitFoolStatistic(key, strings));
                }
            }
        }
    }

    private String stringsFoolStatistic(List<String> strings) {
        strings.sort(Comparator.comparingInt(String::length));
        int minLineSize = strings.getFirst().length();
        int maxLineSize = strings.getLast().length();
        return String.format("*** strings statistic ***%n" +
                "total element: %d%n" +
                "min line size: %d%n" +
                "max line size: %d%n", strings.size(), minLineSize, maxLineSize);
    }


    private String digitFoolStatistic(String type, List<String> digits) {
        ArrayList<BigDecimal> decimals = new ArrayList<>();
        BigDecimal decimalTotal = new BigDecimal("0");
        for (String digit : digits) {
            BigDecimal dec = new BigDecimal(digit);
            decimalTotal = decimalTotal.add(dec);
            decimals.add(dec);
        }
        decimals.sort(new DigitComparator());
        BigDecimal min = decimals.getFirst();
        BigDecimal max = decimals.getLast();
        BigDecimal arithmeticMean = decimalTotal.divide(new BigDecimal(decimals.size()), RoundingMode.FLOOR);

        return String.format("*** %s statistic ***%n" +
                "total element: %d%n" +
                "min value: %s%n" +
                "max value: %s%n" +
                "sum/total: %s%n" +
                "arithmetic mean: %s%n", type, digits.size(), min, max, decimalTotal, arithmeticMean);
    }


    private void shortStatistic(Map<String, List<String>> maps) {
        int countElement = 0;
        for (String key : maps.keySet()) {
            List<String> lines = maps.get(key);
            if (!lines.isEmpty()) {
                int linesSize = lines.size();
                System.out.printf("%s : %d elements%n", key, linesSize);
                countElement += linesSize;
            }
        }
        System.out.printf("total elements: %d%n", countElement);
    }

    private static class DigitComparator implements Comparator<BigDecimal> {
        @Override
        public int compare(BigDecimal o1, BigDecimal o2) {
            return o1.compareTo(o2);
        }
    }
}
