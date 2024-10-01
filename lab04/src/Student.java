public class Student implements Comparable <Student> {
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

    @Override
    public int compareTo(Student student) {
        double result = Double.compare(this.averageGrade, student.averageGrade);
        if (result == 0) {
            return this.name.compareTo(student.name);
        }
        return (int) result;
    }

    @Override
    public String toString() {
        return name;
    }
}
