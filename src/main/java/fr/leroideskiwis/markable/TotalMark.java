package fr.leroideskiwis.markable;

import fr.leroideskiwis.Displayable;

import java.util.ArrayList;
import java.util.List;

public class TotalMark implements Markable, Displayable {
    private final List<Year> years = new ArrayList<>();

    @Override
    public Mark computeAverage() {
        return years.stream()
                .map(Year::computeAverage)
                .reduce(new Mark(0, 0), Mark::add).finalizeMark(1);
    }

    @Override
    public void display() {
        years.forEach(Year::display);
        System.out.printf("TOTAL AVERAGE: %s\n", computeAverage());
    }

    public static class TotalBuilder {

        private final List<Year> schoolUnities = new ArrayList<>();

        public TotalBuilder year(Year year) {
            schoolUnities.add(year);
            return this;
        }

        public TotalMark build() {
            TotalMark year = new TotalMark();
            year.years.addAll(schoolUnities);
            return year;
        }
    }
}
