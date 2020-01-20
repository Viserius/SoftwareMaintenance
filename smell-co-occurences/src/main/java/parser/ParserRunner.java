package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public class ParserRunner {

    private final String SONAR_CODE_SMELLS_PATH = "C:\\Users\\soelm\\Desktop\\Repositories\\SoftwareMaintenance\\analysis\\Code Smells.csv";
    private final String DESIGN_SMELLS_PATH = "C:\\Users\\soelm\\Desktop\\Repositories\\SoftwareMaintenance\\designite-output\\DesignSmells.csv";
    private final String ARCH_SMELLS_PATH = "C:\\Users\\soelm\\Desktop\\Repositories\\SoftwareMaintenance\\designite-output\\ArchitectureSmells.csv";

    private SmellRegistry smellRegistry;

    public ParserRunner(SmellRegistry smellRegistry)
    {
        this.smellRegistry = smellRegistry;
    }

    public void run()
    {
        try {
            csvParser(SONAR_CODE_SMELLS_PATH, "sonarcodesmells", ";");
            csvParser(DESIGN_SMELLS_PATH, "designsmells", ",");
            csvParser(ARCH_SMELLS_PATH, "archsmells", ",");
        } catch (IOException e) {
            System.out.println("Something went wrong while parsing:");
            System.out.println(e.toString());
        }
    }

    private void csvParser(String path, String smellType, String delim) throws IOException {
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader(path));

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(delim);

                switch(smellType) {
                    case "sonarcodesmells":
                        parseSonarCodeSmells(data);
                        break;
                    case "designsmells":
                        parseDesignSmells(data);
                        break;
                    case "archsmells":
                        parseArchSmells(data);
                        break;
                }
            }
            csvReader.close();
    }

    private void parseArchSmells(String[] dataRow) {
        if(dataRow.length < 3 || !dataRow[3].contains("Independent sets of related classes within this component are")) return;

        String packageName = dataRow[1];
        String classNames = dataRow[3].substring(dataRow[3].indexOf('[')); // replace prefix
        classNames = classNames.substring(0, classNames.indexOf('.')) // replace suffix
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");

        String smellName = dataRow[2];
        for (String className : classNames.split(";")) {
            smellRegistry.add(packageName + "." + className, smellName);
        }
    }

    private void parseDesignSmells(String[] dataRow) {
        String className = dataRow[1] + "." + dataRow[2];
        String smellName = dataRow[3];

        smellRegistry.add(className, smellName);
    }

    private void parseSonarCodeSmells(String[] dataRow) {
        if(!dataRow[8].contains("org"))
            return;

        String className = dataRow[8].substring(dataRow[8].indexOf("org"))
                .replace("/", ".");
        String smellName = dataRow[1].replace("\"", "")
                .replace(",", " ");

        if(!className.contains(".java")) return;
        className = className.replace(".java", "");

        smellRegistry.add(className, smellName);
    }

}
