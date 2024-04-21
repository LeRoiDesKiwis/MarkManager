package fr.leroideskiwis.parser;

import fr.leroideskiwis.markable.MarkableImpl;

import java.io.File;
import java.util.List;

public class YearParser implements Parser{

    private List<File> files;

    public YearParser(List<File> files){
        this.files = files;
    }

    @Override
    public MarkableImpl parse() {
        MarkableImpl.MarkableImplBuilder yearBuilder = new MarkableImpl.MarkableImplBuilder().coeff(1);
        for(File file : files){
            String[] names = file.getName().split(" ");
            yearBuilder.name(names[0]);
            yearBuilder.mark(new SemesterParser(file, names[1].replace(".txt", "")).parse());
        }
        return yearBuilder.build();
    }
}
