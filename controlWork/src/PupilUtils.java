import java.util.*;
import java.util.stream.Collectors;

public class PupilUtils {

    public static List<Pupil> sortByInstitutionAndSurname(List<Pupil> pupils) {
        return pupils.stream()
                .sorted(Comparator.comparing(Pupil::getEducationalInstitution)
                        .thenComparing(Comparator.comparing(Pupil::getSurname).reversed()))
                .collect(Collectors.toList());
    }

    public static long countPupilsInInstitution(List<Pupil> pupils, String institution) {
        return pupils.stream()
                .filter(pupil -> pupil.getEducationalInstitution().equals(institution))
                .count();
    }

    public static Optional<Pupil> binarySearch(List<Pupil> pupils, Pupil target) {
        List<Pupil> sortedPupils = pupils.stream()
                .sorted(Comparator.comparing(Pupil::getSurname)
                        .thenComparing(Pupil::getEducationalInstitution)
                        .thenComparing(Pupil::getEducationalInstitutionRating)
                        .thenComparing(Pupil::getAvgGrade))
                .toList();

        int index = Collections.binarySearch(sortedPupils, target, Comparator.comparing(Pupil::getSurname)
                .thenComparing(Pupil::getEducationalInstitution)
                .thenComparing(Pupil::getEducationalInstitutionRating)
                .thenComparing(Pupil::getAvgGrade));

        return index >= 0 ? Optional.of(sortedPupils.get(index)) : Optional.empty();
    }

    public static List<Pupil> sortByRatingRatio(List<Pupil> pupils) {
        return pupils.stream()
                .sorted(Comparator.comparingDouble(pupil -> pupil.getRating() / pupil.getEducationalInstitutionRating()))
                .collect(Collectors.toList());
    }

    public static List<String> getSurnamesStartingWithB(List<Pupil> pupils) {
        return pupils.stream()
                .map(Pupil::getSurname)
                .filter(surname -> surname.startsWith("B"))
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}