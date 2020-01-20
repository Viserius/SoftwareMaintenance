package parser;

import java.util.HashMap;
import java.util.HashSet;

public class SmellRegistry {
    private HashMap<String, HashSet<String>> smellRegistry;

    public SmellRegistry() {
        this.smellRegistry = new HashMap<>();
    }

    public void add(String className, String smellName) {
        // Check if hashset already exists
        if(this.smellRegistry.containsKey(className)) {
            this.smellRegistry.get(className).add(smellName);
        } else {
            this.smellRegistry.put(className, new HashSet<String>());
            this.smellRegistry.get(className).add(smellName);
        }
    }

    public HashMap<String, HashSet<String>> getSmellRegistry() {
        return smellRegistry;
    }
}
