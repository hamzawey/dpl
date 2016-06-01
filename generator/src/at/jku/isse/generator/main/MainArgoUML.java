package at.jku.isse.generator.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import at.jku.isse.generator.IGenerator;
import at.jku.isse.generator.JavaPpGenerator;

/**
 * Creates ALL ArgoUML products. Warning: this will take a very long time!
 */
public class MainArgoUML {

	/*
	 * Optional: COGNITIVE=on LOGGING=on ACTIVITYDIAGRAM=on STATEDIAGRAM=on
	 * SEQUENCEDIAGRAM=on USECASEDIAGRAM=on COLLABORATIONDIAGRAM=on
	 * DEPLOYMENTDIAGRAM=on
	 * 
	 * Mandatory: Diagrams, Class, ArgoUML
	 */

	private static String COGNITIVE = "COGNITIVE";
	private static String LOGGING = "LOGGING";
	private static String ACTIVITYDIAGRAM = "ACTIVITYDIAGRAM";
	private static String STATEDIAGRAM = "STATEDIAGRAM";
	private static String SEQUENCEDIAGRAM = "SEQUENCEDIAGRAM";
	private static String USECASEDIAGRAM = "USECASEDIAGRAM";
	private static String COLLABORATIONDIAGRAM = "COLLABORATIONDIAGRAM";
	private static String DEPLOYMENTDIAGRAM = "DEPLOYMENTDIAGRAM";
	private static String Diagrams = "Diagrams";
	private static String Class = "Class";
	private static String ArgoUML = "ArgoUML";

	private static String[] mandatory = new String[] { "java.home",
			"user.home", "user.dir", Diagrams, Class, ArgoUML };

	private static String[] optional = new String[] { COGNITIVE, LOGGING,
			ACTIVITYDIAGRAM, STATEDIAGRAM, SEQUENCEDIAGRAM, USECASEDIAGRAM,
			COLLABORATIONDIAGRAM, DEPLOYMENTDIAGRAM };

	public static void main(String[] args) {

		int i = 0;

		long binarycounter = 0;
		long max = (int) Math.pow(2, optional.length);

		/* ArgoUML-SPL */

		IGenerator generator = new JavaPpGenerator(new File(
				"ArgoUMLtemplates/src/"), new File(
				"ArgoUMLtemplates/features.txt"));

		while (binarycounter < max) {
			ArrayList<String> current = new ArrayList<String>(
					Arrays.asList(mandatory));
			for (int j = 0; j < optional.length; j++) {
				// if bit on position j is 1
				if (((binarycounter >> j) & 1) == 1) {
					current.add(optional[j]);
				}
			}

			generator.setFeatures(current.toArray(new String[current.size()]));
			generator.generateFiles(new File(
					"ProductLines/_ArgoUMLprogramsALL/P" + (i++) + "/"));

			binarycounter++;
		}

	}

}
