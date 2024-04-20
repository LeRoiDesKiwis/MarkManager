package fr.leroideskiwis.parser;

import fr.leroideskiwis.Mark;
import fr.leroideskiwis.Markable;
import fr.leroideskiwis.Parser;
import fr.leroideskiwis.util.Util;

public class MarkParser implements Parser {
    private final String line;

    public MarkParser(String line) {
        this.line = line;
    }

    @Override
    public Mark parse() {
        String[] args = line.split(":");
        return new Mark(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

    public boolean isCorrect() {
        return Util.isNumber(line.split(":")[0]);
    }
}
