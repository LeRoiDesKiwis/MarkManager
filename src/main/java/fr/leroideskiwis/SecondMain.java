package fr.leroideskiwis;

import fr.leroideskiwis.markable.MarkableImpl;
import fr.leroideskiwis.parser.TotalParser;

public class SecondMain {

    public static void main(String... args){
        TotalParser parser = new TotalParser(".");
        MarkableImpl totalMark = parser.parse();
        totalMark.display();
    }
}
