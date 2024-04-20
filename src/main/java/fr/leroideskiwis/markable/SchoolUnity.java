package fr.leroideskiwis.markable;

import fr.leroideskiwis.Displayable;

import java.util.ArrayList;
import java.util.List;

public class SchoolUnity implements Markable, Displayable {

    private String name;
    private int coeff;
    private final List<Mark> marks;

    public SchoolUnity(String name, int coeff, List<Mark> marks) {
        this.name = name;
        this.coeff = coeff;
        this.marks = marks;
    }

    @Override
    public Mark computeAverage() {
        return marks.stream().reduce(new Mark(0, 0), (mark1, mark) -> mark1.add(mark)).finalizeMark(coeff);
    }

    @Override
    public void display() {
        System.out.printf("\tSchool unity %s (avg: %d/20)\n", name.toUpperCase(), computeAverage().toInt());
        marks.forEach(Mark::display);
    }

    public static class SchoolUnityBuilder {

        private String name;
        private int coeff;
        private List<Mark> marks = new ArrayList<>();

        public SchoolUnityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SchoolUnityBuilder coeff(int coeff) {
            this.coeff = coeff;
            return this;
        }

        public SchoolUnityBuilder mark(Mark mark) {
            marks.add(mark);
            return this;
        }

        public SchoolUnity build() {
            return new SchoolUnity(name, coeff, marks);
        }
    }
}
