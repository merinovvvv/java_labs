import java.util.Comparator;

public class Student implements Comparable <Student>{
    private final String name;
    private final double averageGrade;

    Student() {
        name = "anonymous";
        averageGrade = 4.0;
    }

    Student(String name, double averageGrade) {
        this.name = name;
        this.averageGrade = averageGrade;
    }

//    @Override
//    public int compareTo(Student student) {
//        int result = Double.compare(this.averageGrade, student.averageGrade);
//        if (result == 0) {
//            return this.name.compareTo(student.name);
//        }
//        return result;
//    }

    @Override
    public int compareTo(Student student) {
        return Comparator
                .comparingDouble(Student::getAverageGrade)
                .thenComparing(Student::getName)
                .compare(this, student);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}
