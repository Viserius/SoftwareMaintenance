import parser.CommonSmellTuple;
import parser.ParserRunner;
import parser.SmellProcessor;
import parser.SmellRegistry;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Parsing
        SmellRegistry smellRegistry = new SmellRegistry();
        ParserRunner parser = new ParserRunner(smellRegistry);
        parser.run();

        // Processing
        SmellProcessor smellProcessor = new SmellProcessor(smellRegistry);
        smellProcessor.process();

        // Export
        try {
            smellProcessor.export();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
