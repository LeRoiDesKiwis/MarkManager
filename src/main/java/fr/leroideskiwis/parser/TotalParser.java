package fr.leroideskiwis.parser;

import fr.leroideskiwis.markable.TotalMark;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TotalParser implements Parser{

    private String path;

    public TotalParser(String path){
        this.path = path;
    }

    public Set<File> getFiles(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().endsWith(".txt"))
                .collect(Collectors.toSet());
    }

    public TotalMark parse() {
        TotalMark.TotalBuilder totalMark = new TotalMark.TotalBuilder();
        for(File file : getFiles(path)){
            totalMark.year(new YearParser(file).parse());
        }
        return totalMark.build();
    }
}
