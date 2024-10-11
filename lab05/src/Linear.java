public class Linear extends Series {
    Linear(double firstElement, double step, int numOfElements) {
        super(firstElement, step, numOfElements);
    }

    @Override
    double jElemCalc(int j) {
        if (j == 1) {
            return firstElement;
        }
        return firstElement + (j - 1) * step;
    }

    @Override
    public double sumOfSeries() {
        return (firstElement + jElemCalc(numOfElements)) / 2 * numOfElements;
    }
}
