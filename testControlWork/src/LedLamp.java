public class LedLamp extends Lamp {
    int ledNumber;
    private static final double CONST = 2;

    LedLamp(String producer, double power, int price, int ledNumber) {
        super(producer, power, price);
        this.ledNumber = ledNumber;
    }

    @Override
    public String toString() {
        return "LedLamp " + super.toString() + " ledNumber=" + ledNumber;
    }

    @Override
    void calculatePrice() {
        price = (int)Math.ceil(power * ledNumber / CONST);
    }
}
