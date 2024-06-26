package fr.leroideskiwis.markable;

import fr.leroideskiwis.Displayable;

public class Mark implements Markable, Displayable {

    private final int mark;
    private final int coeff;

    public Mark(int mark,int coeff){
        this.mark = mark;
        this.coeff = coeff;
    }

    public Mark add(Mark a) {
        return new Mark(this.mark + a.mark*a.coeff, this.coeff + a.coeff);
    }

    public Mark finalizeMark(int newCoeff) {
        return new Mark(this.mark/coeff, newCoeff);
    }

    @Override
    public String toString() {
        return String.format("%.2f/20", mark/100f);
    }

    @Override
    public Mark computeAverage() {
        return this;
    }

    @Override
    public void display() {
        System.out.printf("\t\t\t %s coeff %d\n", this, coeff);
    }
}
