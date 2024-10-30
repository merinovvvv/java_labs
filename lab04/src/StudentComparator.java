import java.util.Comparator;

public interface StudentComparator extends Comparator<Student> {

    @Override
    default int compare(Student s1, Student s2) {
        return Comparator
                .comparingDouble(Student::getAverageGrade)
                .thenComparing(Student::getName)
                .compare(s1, s2);
    }
}
