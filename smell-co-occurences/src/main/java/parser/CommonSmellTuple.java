package parser;

import java.util.Objects;

public class CommonSmellTuple {

    private String firstSmell;
    private String secondSmell;

    public CommonSmellTuple(){}

    public CommonSmellTuple(String firstSmell, String secondSmell) {
        this.firstSmell = firstSmell;
        this.secondSmell = secondSmell;
    }

    public String getFirstSmell() {
        return firstSmell;
    }

    public String getSecondSmell() {
        return secondSmell;
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
