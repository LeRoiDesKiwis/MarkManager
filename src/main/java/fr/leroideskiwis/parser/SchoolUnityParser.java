package fr.leroideskiwis.parser;

import fr.leroideskiwis.markable.MarkableImpl;

import java.util.List;

public class SchoolUnityParser implements Parser {
    private final List<String> lines;
    private int stop = 1;

    public SchoolUnityParser(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public MarkableImpl parse() {
        MarkableImpl.MarkableImplBuilder schoolUnityBuilder = new MarkableImpl.MarkableImplBuilder()
                .name(lines.get(0))
                .coeff(Integer.parseInt(lines.get(1)))
                .tabNumber(2);
        for(String line : lines.subList(2, lines.size())){
            MarkParser markParser = new MarkParser(line);
            if(!markParser.isCorrect()) return schoolUnityBuilder.build();
            schoolUnityBuilder.mark(markParser.parse());
            stop++;
        }
        return schoolUnityBuilder.build();
    }

    public int getStop(){
        return stop;
    }
}
