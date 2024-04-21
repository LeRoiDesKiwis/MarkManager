package fr.leroideskiwis.markable;

import fr.leroideskiwis.Displayable;

import java.util.ArrayList;
import java.util.List;

public class MarkableImpl implements Markable, Displayable {

    private final String name;
    private final int coeff;
    private final List<Markable> marks;
    private final int tabNumber;

    public MarkableImpl(String name, int coeff, List<Markable> marks, int tabNumber){
        this.name = name;
        this.coeff = coeff;
        this.marks = marks;
        this.tabNumber = tabNumber;
    }

    @Override
    public Mark computeAverage() {
        return marks.stream()
                .map(Markable::computeAverage)
                .reduce(new Mark(0, 0), Mark::add).finalizeMark(coeff);
    }

    @Override
    public void display() {
        System.out.printf("%s%s (avg: %s) COEFF %d:\n", "\t".repeat(tabNumber), name, computeAverage(), coeff);

        marks.forEach(mark -> {
            if(mark instanceof Displayable) ((Displayable)mark).display();
        });
    }

    public static class MarkableImplBuilder{
        private String name;
        private int coeff = 1;
        private final List<Markable> marks = new ArrayList<>();
        private int tabNumber;

        public MarkableImplBuilder name(String name){
            this.name = name;
            return this;
        }

        public MarkableImplBuilder coeff(int coeff){
            this.coeff = coeff;
            return this;
        }

        public MarkableImplBuilder mark(Markable markable){
            marks.add(markable);
            return this;
        }

        public MarkableImplBuilder tabNumber(int tabNumber){
            this.tabNumber = tabNumber;
            return this;
        }

        public MarkableImpl build(){
            return new MarkableImpl(name, coeff, marks, tabNumber);
        }

    }
}
