package fr.leroideskiwis.markable;

import fr.leroideskiwis.Displayable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Year implements Markable, Displayable {

    private List<Semester> semesters = new ArrayList<>();
    private String name;
    private int coeff;

    public Year(String name, List<Semester> semesters, int coeff){
        this.name = name;
        this.semesters = semesters;
        this.coeff = coeff;
    }

    public Mark computeAverage() {
        return semesters.stream()
                .map(Semester::computeAverage)
                .reduce(new Mark(0, 0), Mark::add).finalizeMark(coeff);
    }

    @Override
    public void display() {
        System.out.printf("Year %s (avg: %s) COEFF %d:\n", name, computeAverage(), coeff);
        for (SchoolUnity schoolUnity : schoolUnities) {
            schoolUnity.display();
            System.out.println();
        }
        System.out.printf("Year %s (avg: %s) COEFF %d\n", name, computeAverage(), coeff);
        semesters.forEach(Semester::display);
    }

    }

    public static class YearBuilder {
        private String name;
        private List<Semester> semesters = new ArrayList<>();
        private int coeff;

        public YearBuilder name(String name){
            this.name = name;
            return this;
        }

        public YearBuilder semester(Semester semester){
            semesters.add(semester);
            return this;
        }

        public YearBuilder coeff(int coeff){
            this.coeff = coeff;
            return this;
        }

        public Year build(){
            return new Year(name, semesters, coeff);
        }
    }
}
