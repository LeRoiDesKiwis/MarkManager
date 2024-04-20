package fr.leroideskiwis.markable;

public class Mark implements Markable{

    private final int mark;
    private final int coeff;

    public Mark(int mark, int coeff) {
        this.mark = mark;
        this.coeff = coeff;
    }

    public Mark add(Mark a) {
        Mark mark = new Mark(this.mark + a.mark*a.coeff, this.coeff + a.coeff);
        System.out.printf("%s+%s = %s \n", this, a, mark);
        return mark;
    }

    public Mark finalizeMark(int newCoeff) {
        Mark mark = new Mark(this.mark/coeff, newCoeff);
        System.out.printf("new mark: %s\n", mark);
        return mark;
    }

    @Override
    public String toString() {
        return String.format("[%d coeff %d]", mark, coeff);
    }

    @Override
    public Mark computeAverage() {
        return this;
    }
}
