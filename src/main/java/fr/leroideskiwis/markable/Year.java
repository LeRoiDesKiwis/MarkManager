package fr.leroideskiwis.markable;

import java.util.ArrayList;
import java.util.List;

public class Year implements Markable {

    private final List<SchoolUnity> schoolUnities = new ArrayList<>();
    private final int coeff;
    private final String name;

    public Year(String name, int coeff) {
        this.name = name;
        this.coeff = coeff;
    }

    @Override
    public Mark computeAverage() {
        return schoolUnities.stream()
                .map(SchoolUnity::computeAverage)
                .reduce(new Mark(0, 0), Mark::add).finalizeMark(coeff);
    }

    public static class YearBuilder {

        private final List<SchoolUnity> schoolUnities = new ArrayList<>();
        private int coeff = 1;
        private String name;

        public YearBuilder schoolUnity(SchoolUnity schoolUnity) {
            schoolUnities.add(schoolUnity);
            return this;
        }

        public Year build() {
            Year year = new Year(name, coeff);
            year.schoolUnities.addAll(schoolUnities);
            return year;
        }

        public YearBuilder name(String name){
            this.name = name;
            return this;
        }

        public YearBuilder coeff(int coeff) {
            this.coeff = coeff;
            return this;
        }
    }
}