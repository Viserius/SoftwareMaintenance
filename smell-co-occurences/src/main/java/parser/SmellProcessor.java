package parser;

import jdk.nashorn.internal.ir.CallNode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SmellProcessor {

    private final SmellRegistry smellRegistry;
    private final HashMap<CommonSmellTuple, Integer> smellTuples;

    public SmellProcessor(SmellRegistry smellRegistry) {
        this.smellRegistry = smellRegistry;
        this.smellTuples = new HashMap<>();
    }

    public void process() {
        // For every file, aggregate the smells
        for(Map.Entry<String, HashSet<String>> entry : this.smellRegistry.getSmellRegistry().entrySet()) {
            String className = entry.getKey();
            HashSet Smells = entry.getValue();

            aggregateSmells(Smells);
        }
    }

    private void aggregateSmells(HashSet<String> smells) {
        // Ordered list of smells
        String[] orderedSmells = smells.toArray(new String[0]);
        Arrays.sort(orderedSmells);

        // For every possible combination, increment the count with 1
        for(int i = 0; i < orderedSmells.length; i++) {
            for(int j = i+1; j < orderedSmells.length; j++) {
                CommonSmellTuple tuple = new CommonSmellTuple(orderedSmells[i], orderedSmells[j]);
                incrementSmells(tuple);
            }
        }
    }

    private void incrementSmells(CommonSmellTuple tuple) {
        if(smellTuples.containsKey(tuple)) {
            smellTuples.put(tuple, smellTuples.get(tuple) + 1);
        } else {
            smellTuples.put(tuple, 1);
        }
    }

    public void export() throws IOException {
        FileWriter csvWriter = new FileWriter("C:\\Users\\soelm\\Desktop\\Repositories\\SoftwareMaintenance\\SmellCoOccurrences.csv");
        for(Map.Entry<CommonSmellTuple,Integer> entry : smellTuples.entrySet()) {
            csvWriter.append(entry.getKey().getFirstSmell());
            csvWriter.append(',');
            csvWriter.append(entry.getKey().getSecondSmell());
            csvWriter.append(',');
            csvWriter.append(entry.getValue().toString());
            csvWriter.append('\n');
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
