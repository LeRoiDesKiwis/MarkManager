package fr.leroideskiwis.markable;

import fr.leroideskiwis.Displayable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Semester implements Markable, Displayable {

    private final List<SchoolUnity> schoolUnities = new ArrayList<>();
    private final int coeff;
    private final String name;

    public Semester(String name, int coeff) {
        this.name = name;
        this.coeff = coeff;
    }

    @Override
    public Mark computeAverage() {
        return schoolUnities.stream()
                .map(SchoolUnity::computeAverage)
                .reduce(new Mark(0, 0), Mark::add).finalizeMark(coeff);
    }

    @Override
    public void display() {
        System.out.printf("\tSemester %s (avg: %s) COEFF %d:\n", name, computeAverage(), coeff);
        for (SchoolUnity schoolUnity : schoolUnities) {
            schoolUnity.display();
            System.out.println();
        }
    }

    public static class SemesterBuilder {

        private final List<SchoolUnity> schoolUnities = new ArrayList<>();
        private int coeff = 1;
        private String name;

        public SemesterBuilder schoolUnity(SchoolUnity schoolUnity) {
            schoolUnities.add(schoolUnity);
            return this;
        }

        public Semester build() {
            Semester semester = new Semester(name, coeff);
            semester.schoolUnities.addAll(schoolUnities);
            return semester;
        }

        public SemesterBuilder name(String name){
            this.name = name;
            return this;
        }

        public SemesterBuilder coeff(int coeff) {
            this.coeff = coeff;
            return this;
        }
    }
}
