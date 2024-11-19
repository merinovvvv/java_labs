import java.util.List;

abstract public class Pupil {
    String surname;
    String educationalInstitution;
    double educationalInstitutionRating;
    double avgGrade;
    double rating;

    List<Pupil> studentList;

    @Override
    public String toString() {
        return "Pupil{" +
                "surname='" + surname + '\'' +
                ", educationalInstitution=" + educationalInstitution +
                ", educationalInstitutionRating=" + educationalInstitutionRating +
                ", avgGrade=" + avgGrade +
                '}';
    }

    Pupil(String surname, String educationalInstitution, double educationalInstitutionRating, double avgGrade) {
        this.surname = surname;
        this.educationalInstitution = educationalInstitution;
        this.educationalInstitutionRating = educationalInstitutionRating;
        this.avgGrade = avgGrade;
    }

    public String getSurname() {
        return surname;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public double getEducationalInstitutionRating() {
        return educationalInstitutionRating;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public double getRating() {
        return rating;
    }

    abstract void calculateRating();
}
