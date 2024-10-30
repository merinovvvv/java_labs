import java.util.Comparator;

public interface StudentComparator {

    default Comparator<Student> compareByAverageGrade() {
        return Comparator.comparingDouble(Student::getAverageGrade);
    }

    default Comparator<Student> compareByName() {
        return Comparator.comparing(Student::getName);
    }

    default Comparator<Student> compareByGradeThenName() {
        return compareByAverageGrade().thenComparing(compareByName());
    }
}
