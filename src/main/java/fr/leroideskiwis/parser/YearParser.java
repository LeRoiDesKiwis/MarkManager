package fr.leroideskiwis.parser;

import fr.leroideskiwis.Mark;
import fr.leroideskiwis.Markable;
import fr.leroideskiwis.Parser;
import fr.leroideskiwis.Year;
import fr.leroideskiwis.util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class YearParser implements Parser {
    private final File file;

    public YearParser(File file) {
        this.file = file;
    }

    public List<String> getLines(File file) throws IOException {
        return Files.lines(file.toPath()).collect(Collectors.toList());
    }

    @Override
    public Year parse() {
        List<String> lines;
        try {
            lines = getLines(file).stream().filter(line -> !line.isBlank()).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Year.YearBuilder year = new Year.YearBuilder().name(file.getName()).coeff(Integer.parseInt(lines.get(0)));

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            MarkParser markParser = new MarkParser(line);
            if(!markParser.isCorrect()) {
                SchoolUnityParser schoolUnityParser = new SchoolUnityParser(lines.subList(i, lines.size()));
                year.schoolUnity(schoolUnityParser.parse());
                i += schoolUnityParser.getStop();
            }

        }
        return year.build();
    }
}
