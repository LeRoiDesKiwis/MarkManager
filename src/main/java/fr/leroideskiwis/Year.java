package fr.leroideskiwis;

import java.util.ArrayList;
import java.util.List;

public class Year implements AverageMark{

    private final List<SchoolUnity> schoolUnities = new ArrayList<>();

    @Override
    public Mark computeAverage() {
        return schoolUnities.stream()
                .map(SchoolUnity::computeAverage)
                .reduce(new Mark(0, 0), Mark::add).finalizeMark(1);
    }

    public static class YearBuilder {

        private final List<SchoolUnity> schoolUnities = new ArrayList<>();

        public YearBuilder schoolUnity(SchoolUnity schoolUnity) {
            schoolUnities.add(schoolUnity);
            return this;
        }

        public Year build() {
            Year year = new Year();
            year.schoolUnities.addAll(schoolUnities);
            return year;
        }
    }
}
