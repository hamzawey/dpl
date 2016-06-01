package at.jku.isse.generator.main;

import java.io.File;

import at.jku.isse.generator.VelocityGenerator;

public class MainDPL {

	public static void main(String[] args) {

		File baseDir = new File("DPL/");
		
		int version = 0;

		VelocityGenerator generator = new VelocityGenerator(new File(baseDir, "templates" + version + "/src/"), new File(baseDir, "templates" + version + "/features.vm"));

		generator.setFeatures(new String[] { "DPL", "LINE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P1/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "RECT" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P2/"));

		generator.setFeatures(new String[] { "DPL", "RECT" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P3/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "RECT", "COLOR", "RED", "BLACK", "BLUE", "GREEN" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P4/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "COLOR", "RED", "BLACK", "BLUE", "GREEN" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P5/"));

		generator.setFeatures(new String[] { "DPL", "RECT", "COLOR", "RED", "BLACK", "BLUE", "GREEN" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P6/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "WIPE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P7/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "RECT", "WIPE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P8/"));

		generator.setFeatures(new String[] { "DPL", "RECT", "WIPE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P9/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "RECT", "COLOR", "RED", "BLACK", "BLUE", "GREEN", "WIPE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P10/"));

		generator.setFeatures(new String[] { "DPL", "LINE", "COLOR", "RED", "BLACK", "BLUE", "GREEN", "WIPE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P11/"));

		generator.setFeatures(new String[] { "DPL", "RECT", "COLOR", "RED", "BLACK", "BLUE", "GREEN", "WIPE" });
		generator.generateFiles(new File(baseDir, "variants" + version + "/P12/"));
	}

}
