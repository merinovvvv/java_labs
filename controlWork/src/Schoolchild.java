public class Schoolchild extends Pupil{

    int grade;
    int behaviour;

    final double K1 = 2;
    final double K2 = 3;

    Schoolchild(String surname, String educationalInstitution, double educationalInstitutionRating, double avgGrade, int grade, int behaviour) {
        super(surname, educationalInstitution, educationalInstitutionRating, avgGrade);
        this.grade = grade;
        this.behaviour = behaviour;
    }

    @Override
    void calculateRating() {
        rating = K1 * educationalInstitutionRating * avgGrade * grade + K2 * behaviour;
    }

    @Override
    public String toString() {
        return "Student " + super.toString() + " grade=" + grade + " behaviour=" + behaviour;
    }
}
