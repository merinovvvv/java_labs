import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LampUtils {

    public static List<Lamp> sortByAscendingPrice(List<Lamp> lamps) {
        return lamps.stream()
                .sorted(Comparator.comparingInt(l -> l.price))
                .collect(Collectors.toList());
    }

    public static List<Lamp> sortByDescendingPricePowerRatio(List<Lamp> lamps) {
        return lamps.stream()
                .sorted((l1, l2) -> Double.compare(l2.price / l2.power, l1.price / l1.power))
                .collect(Collectors.toList());
    }

    public static List<String> getProducersStartingWithC(List<Lamp> lamps) {
        return lamps.stream()
                .map(lamp -> lamp.producer)
                .filter(producer -> producer.startsWith("C"))
                .distinct()
                .collect(Collectors.toList());
    }

    public static double calculateAveragePriceByProducer(List<Lamp> lamps, String producer) {
        return lamps.stream()
                .filter(lamp -> lamp.producer.equals(producer))
                .mapToInt(lamp -> lamp.price)
                .average()
                .orElse(0.0);
    }
}