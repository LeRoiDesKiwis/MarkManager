package fr.leroideskiwis;

import fr.leroideskiwis.markable.TotalMark;
import fr.leroideskiwis.parser.TotalParser;

public class SecondMain {

    public static void main(String... args){
        TotalParser parser = new TotalParser(".");
        TotalMark totalMark = parser.parse();
        totalMark.computeAverage();
    }
}
