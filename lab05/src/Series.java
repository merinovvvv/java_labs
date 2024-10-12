import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

abstract class Series {
    double firstElement;
    double step;
    int numOfElements;

    Series(double firstElement, double step, int numOfElements) {
        this.firstElement = firstElement;
        this.step = step;
        this.numOfElements = numOfElements;
    }

    public static String readFile(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
        }
        return sb.toString();
    }

    abstract double jElemCalc(int j);

    public double sumOfSeries() {
        double sum = 0;
        sum += firstElement;
        for (int i = 2; i <= numOfElements; i++) {
            sum += jElemCalc(i);
        }
        return sum;
    }

    public void writeToFile(String fileName) throws FileNotFoundException, IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(toString());
        }
    }

    public void writeJElemToFile(String fileName, int j) throws FileNotFoundException, IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            String jElem = String.valueOf(jElemCalc(j));
            fileWriter.write(jElem + "\n");
        }
    }

    public void writeSumToFile(String fileName) throws FileNotFoundException, IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            String sum = String.valueOf(sumOfSeries());
            fileWriter.write(sum + "\n");
        }
    }

    static public void clearFile(String fileName) throws FileNotFoundException, IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstElement).append(" ");
        for (int i = 2; i <= numOfElements; i++) {
            sb.append(jElemCalc(i)).append(" ");
        }
        return sb.toString() + "\n";
    }
}
