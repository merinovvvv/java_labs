public class Student extends Pupil {
    int courseNumber;

    final double K1 = 2;
    final double K2 = 3;


    Student(String surname, String educationalInstitution, double educationalInstitutionRating, double avgGrade, int courseNumber) {
        super(surname, educationalInstitution, educationalInstitutionRating, avgGrade);
        this.courseNumber = courseNumber;
    }

    @Override
    void calculateRating() {
        rating = K1 * educationalInstitutionRating * avgGrade + K2 * courseNumber;
    }

    @Override
    public String toString() {
        return "Student " + super.toString() + " courseNumber=" + courseNumber;
    }
}
