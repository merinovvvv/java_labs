public class IncandescentLamp extends Lamp {
    int operatingTime;
    final double CONST = 2;

    IncandescentLamp(String producer, double power, int price, int operatingTime) {
        super(producer, power, price);
        this.operatingTime = operatingTime;
    }

    @Override
    public String toString() {
        return "IncandescentLamp " + super.toString() + " operatingTime=" + operatingTime;
    }

    @Override
    void calculatePrice() {
        price = (int) Math.ceil(power * operatingTime * CONST);
    }
}
