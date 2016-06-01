package at.jku.isse.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * This generator uses the Apache Velocity template engine.
 */
public class VelocityGenerator implements IGenerator {

	private static final int MIN_FILESIZE = 5;

	private VelocityContext context;

	private File templatesFolder;
	private File featuresFile;
	private File outputFolder;

	private String[] features;

	public VelocityGenerator(File templatesFolder, File featuresFile) {
		this.templatesFolder = templatesFolder;
		this.featuresFile = featuresFile;
		this.features = null;
	}

	@Override
	public void setFeatures(String[] args) {
		this.features = args;
	}

	@Override
	public void generateFiles(File dir) {
		if (this.features == null)
			return;

		// Creates merging context
		createMergeContext();

		outputFolder = new File(dir, "src");
		if (!outputFolder.exists())
			outputFolder.mkdirs();

		// Generate features file
		generateClass(new File(dir, "features.txt"),
				this.featuresFile.toString());

		// Generate Java files
		this.generateFilesRecursively(this.templatesFolder);
	}

	/* Helper Functions */

	private void generateFilesRecursively(File dir) {
		File[] filelist = dir.listFiles();

		for (File f : filelist) {
			if (f.isDirectory() && !f.getName().contains(".svn")) {
				this.generateFilesRecursively(f);
			} else if (f.isFile()) {
				if (f.getName().endsWith(".vm")) {
					String pathname = f.getPath().substring(
							f.getPath().indexOf(this.templatesFolder.getPath())
									+ this.templatesFolder.getPath().length());
					pathname = pathname.substring(0,
							pathname.lastIndexOf(".vm"))
							+ ".java";

					File srcfile = new File(this.outputFolder, pathname);

					if (!srcfile.exists())
						srcfile.getParentFile().mkdirs();

					this.generateClass(srcfile, f.getPath());
				}
			}
		}
	}

	/**
	 * Creates a Velocity context that contains all the boolean feature
	 * variables.
	 */
	private void createMergeContext() {
		// Initializes the template engine
		try {
			Properties p = new Properties();
			Velocity.init(p);
		} catch (Exception e) {
			System.out.println("Problem initializing Velocity : " + e);
			return;
		}

		// Creates the context and adds values to it
		this.context = new VelocityContext();
		for (String feature : this.features) {
			this.context.put(feature, true);
		}
	}

	/** Generates the file for a class. */
	private void generateClass(File file, String templateName) {
		FileWriter outWriter;

		// Creates the output file
		try {
			outWriter = new FileWriter(file);
		} catch (IOException e) {
			System.out.println("Error while creating output file");
			e.printStackTrace();
			return;
		}

		// Merges the context with the template
		try {
			Template template = Velocity.getTemplate(templateName);
			template.merge(this.context, outWriter);
			outWriter.flush();
			outWriter.close();

			if (file.length() <= MIN_FILESIZE)
				file.delete();
		} catch (Exception e) {
			System.out.println("Error while creating output file");
			e.printStackTrace();
			return;
		}

	}

}
