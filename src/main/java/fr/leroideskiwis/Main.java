package fr.leroideskiwis;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SchoolUnity schoolUnity = new SchoolUnity.SchoolUnityBuilder()
                .name("Maths")
                .coeff(4)
                .mark(new Mark(20, 1))
                .mark(new Mark(20, 1))
                .mark(new Mark(20, 3))
                .build();

        SchoolUnity schoolUnity2 = new SchoolUnity.SchoolUnityBuilder()
                .name("French")
                .coeff(2)
                .mark(new Mark(20, 1))
                .mark(new Mark(20, 1))
                .mark(new Mark(20, 4))
                .build();

        Year year = new Year.YearBuilder()
                .schoolUnity(schoolUnity)
                .schoolUnity(schoolUnity2)
                .build();

        Year year1 = new Year.YearBuilder()
                .schoolUnity(new SchoolUnity("Maths", 4, List.of(new Mark(19, 1), new Mark(20, 1), new Mark(20, 3)))).build();

        System.out.printf("Maths: %s\n", schoolUnity.computeAverage());
        System.out.printf("\nFrench: %s\n", schoolUnity2.computeAverage());
        System.out.printf("\nYear: %s\n", year.computeAverage());

        TotalMark totalMark = new TotalMark.TotalBuilder()
                .year(year)
                .year(year1)
                .build();
        System.out.printf("\nTotal: %s\n", totalMark.computeAverage());


    }
}