package at.jku.isse.generator.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import at.jku.isse.generator.AntennaGenerator;
import at.jku.isse.generator.IGenerator;

public class MainMM {

	private static String[] mandatory;

	private static String[] optional;

	public static void main(String[] args) {

		int i = 1;

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Photo", "Exception" };
		optional = new String[] {};
		process(i++);

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Photo", "Exception" };
		optional = new String[] { "includeSorting" };
		process(i++);

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Photo", "Exception", "Label" };
		optional = new String[] { "includeSorting", "includeFavourites" };
		process(i++);

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Photo", "Exception", "Label" };
		optional = new String[] { "includeSorting", "includeFavourites",
				"includeCopyPhoto" };
		process(i++);

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Photo", "Exception", "Label" };
		optional = new String[] { "includeSorting", "includeFavourites",
				"includeCopyPhoto", "includeSmsFeature" };
		process(i++);

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Exception", "Label" };
		optional = new String[] { "includeSorting", "includeFavourites",
				"includeCopyPhoto", "includeSmsFeature", "includePhotoAlbum",
				"includeMusic" };
		process(i++);

		mandatory = new String[] { "MobileMedia", "MediaManagement",
				"CreateDelete", "ViewPlay", "Exception", "Label" };
		optional = new String[] { "includeSorting", "includeFavourites",
				"includeCopyPhoto", "includeSmsFeature", "includePhoto",
				"includeMusic", "includeVideo" };
		process(i++);

	}

	public static void process(int number) {

		int i = 0;

		long binarycounter = 0;
		long max = (int) Math.pow(2, optional.length);

		/* MM */

		IGenerator generator = new AntennaGenerator(new File("MMtemplates/"
				+ number + "/src/"), new File("MMtemplates/" + number
				+ "/features.txt"));

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
			generator.generateFiles(new File("ProductLines/_MMprograms"
					+ number + "/P" + number + "_" + (i++) + "/"));

			binarycounter++;
		}

	}

}
