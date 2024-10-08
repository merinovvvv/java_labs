public class Exponential extends Series{
    Exponential(double firstElement, double step, int numOfElements) {
        super(firstElement, step, numOfElements);
    }

    @Override
    double jElemCalc(int j) {
        if (j == 1) {
            return firstElement;
        }
        return firstElement * Math.pow(step, j - 1);
    }

    @Override
    public double sumOfSeries() {
        return firstElement * (1 - Math.pow(step, numOfElements)) / (1 - step);
    }
}
