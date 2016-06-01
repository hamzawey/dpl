package at.jku.isse.generator;

import java.io.File;

public interface IGenerator {

	public void setFeatures(String[] args);

	public void generateFiles(File dir);

}
