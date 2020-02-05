package parser;

import java.util.Objects;

public class CommonSmellTuple {

    private String firstSmell;
    private String firstSmellType;
    private String secondSmell;
    private String secondSmellType;

    public CommonSmellTuple(){}

    public CommonSmellTuple(String firstSmell, String firstSmellType, String secondSmell, String secondSmellType) {
        this.firstSmell = firstSmell;
        this.firstSmellType = firstSmellType;
        this.secondSmell = secondSmell;
        this.secondSmellType = secondSmellType;
    }

    public String getFirstSmell() {
        return firstSmell;
    }

    public String getSecondSmell() {
        return secondSmell;
    }

    public String getFirstSmellType() {
        return firstSmellType;
    }

    public String getSecondSmellType() {
        return secondSmellType;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CommonSmellTuple))
            return false;
        return firstSmell.equals(((CommonSmellTuple) obj).firstSmell) &&
                secondSmell.equals(((CommonSmellTuple) obj).secondSmell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSmell, secondSmell);
    }
}
