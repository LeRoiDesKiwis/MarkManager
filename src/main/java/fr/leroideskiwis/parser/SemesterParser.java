package fr.leroideskiwis.parser;

import fr.leroideskiwis.markable.MarkableImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SemesterParser implements Parser {
    private final File file;
    private final String name;

    public SemesterParser(File file, String name) {
        this.name = name;
        this.file = file;
    }

    public List<String> getLines(File file) throws IOException {
        try(Stream<String> lines = Files.lines(file.toPath())){
            return lines.collect(Collectors.toList());
        }
    }

    @Override
    public MarkableImpl parse() {
        List<String> lines;
        try {
            lines = getLines(file).stream().filter(line -> !line.isBlank()).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MarkableImpl.MarkableImplBuilder semester = new MarkableImpl.MarkableImplBuilder()
                .name(name)
                .coeff(Integer.parseInt(lines.getFirst()))
                .tabNumber(1);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            MarkParser markParser = new MarkParser(line);
            if(!markParser.isCorrect()) {
                SchoolUnityParser schoolUnityParser = new SchoolUnityParser(lines.subList(i, lines.size()));
                semester.mark(schoolUnityParser.parse());
                i += schoolUnityParser.getStop();
            }

        }
        return semester.build();
    }
}
