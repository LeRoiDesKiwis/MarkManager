package fr.leroideskiwis.parser;

import fr.leroideskiwis.markable.Mark;

public class MarkParser implements Parser {
    private final String line;

    public MarkParser(String line) {
        this.line = line;
    }

    @Override
    public Mark parse() {
        String[] args = line.split(":");
        return new Mark(Integer.parseInt(fixZeroes(args[0])), Integer.parseInt(args[1]));
    }

    private String fixZeroes(String string){
        if(string.contains(".")){
            String decimal = string.split("\\.")[1];
            return (string+"0".repeat(2-decimal.length())).replace(".", "");
        }
        return string+"00";
    }

    public boolean isCorrect(){
        try{
            parse();
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
