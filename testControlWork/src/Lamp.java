import java.util.ArrayList;
import java.util.List;

abstract public class Lamp {
    String producer;
    double power;
    int price;
    List<Lamp> lampList;

    @Override
    public String toString() {
        return "Lamp{" +
                "producer='" + producer + '\'' +
                ", power=" + power +
                ", price=" + price +
                '}';
    }

    Lamp(String producer, double power, int price) {
        this.producer = producer;
        this.power = power;
        this.price = price;
    }

    abstract void calculatePrice();
}
