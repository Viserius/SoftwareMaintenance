package parser;

import java.util.HashMap;
import java.util.HashSet;

public class SmellRegistry {
    private HashMap<String, HashSet<Smell>> smellRegistry;

    public SmellRegistry() {
        this.smellRegistry = new HashMap<>();
    }

    public void add(String className, Smell smell) {
        // Check if hashset already exists
        if(this.smellRegistry.containsKey(className)) {
            this.smellRegistry.get(className).add(smell);
        } else {
            this.smellRegistry.put(className, new HashSet<Smell>());
            this.smellRegistry.get(className).add(smell);
        }
    }

    public HashMap<String, HashSet<Smell>> getSmellRegistry() {
        return smellRegistry;
    }
}
