package gui.util;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * This classes' main should only be run manually, to quickly generate the additional person panels if CaretakerPanel has been changed.
 */
public class PersonPanelGenerator {
	public static void main(String[] args) {
        PersonPanelGenerator.generatePersonPanelClasses();
	}
	
	/**
	 * This utility method generates VetPanel and PatronPanel from CaretakerPanel.
	 * It loads the source code of CaretakerPanel, replaces occurrences of relevant keywords and the compiles the resulting strings back into classes.
	 * Note: After substantial changes to CaretakerPanel, this may no longer work and will have to be adjusted.
	 */
	public static void generatePersonPanelClasses() {
		String[] classNames = {"VetPanel", "PatronPanel"};
		
		String filePath = "src/gui/persons/CaretakerPanel.java";
		
		try {
            String sourceCode = new String(Files.readAllBytes(Paths.get(filePath)));;
            
            for(String className: classNames) {
            	FileWriter writer = new FileWriter("src/gui/persons/" + className + ".java");
                writer.write(
            		sourceCode
                		.replaceAll("CaretakerPanel", className)
                		.replaceAll("loadCaretakers", className.equals("VetPanel") ? "loadVets" : "loadPatrons")
            			.replaceAll("saveCaretaker", className.equals("VetPanel") ? "saveVet" : "savePatron")
            			.replaceAll("deleteCaretaker", className.equals("VetPanel") ? "deleteVet" : "deletePatron")
            			.replaceAll("CaretakerDTO", className.equals("VetPanel") ? "VetDTO" : "PatronDTO")
        		);
                writer.close();

                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

                Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects("src/gui/persons/" + className + ".java");
                Iterable<String> options = Arrays.asList("-d", "bin");

                compiler.getTask(null, fileManager, null, options, null, compilationUnits).call();
                fileManager.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
