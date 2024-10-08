import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "series.txt";
        Series.clearFile(fileName);

        Liner liner = new Liner(1, 2, 3);
        double secondElement = liner.jElemCalc(3);
        liner.writeJElemToFile(fileName, 3);
        liner.writeSumToFile(fileName);
        liner.writeToFile(fileName);
        System.out.println(secondElement);
        System.out.println(liner);

        Exponential exp = new Exponential(1, 2, 10);
        double seventhElement = exp.jElemCalc(7);
        exp.writeJElemToFile(fileName, 3);
        exp.writeSumToFile(fileName);
        exp.writeToFile(fileName);
        System.out.println(seventhElement);
        System.out.println(exp);
    }
}