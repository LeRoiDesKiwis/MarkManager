package fr.leroideskiwis.parser;

import fr.leroideskiwis.markable.MarkableImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TotalParser implements Parser{

    private final String path;
    private final Map<String, List<File>> files = new HashMap<>();

    public TotalParser(String path){
        this.path = path;
    }

    public Set<File> getFiles(String dir) {
        return Stream.of(new File(path).listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().endsWith(".txt"))
                .collect(Collectors.toSet());
    }

    public MarkableImpl parse() {
        separate(getFiles("."));
        MarkableImpl.MarkableImplBuilder totalMark = new MarkableImpl.MarkableImplBuilder().name("TOTAL");
        files.forEach((truc, files) -> totalMark.mark(new YearParser(files).parse()));
        return totalMark.build();
    }

    private void separate(Set<File> fileList){
        for(File file : fileList){
            String[] args = file.getName().split(" ");
            if(files.containsKey(args[0])) files.get(args[0]).add(file);
            else files.put(args[0], new ArrayList<>(List.of(file)));
        }
    }
}
