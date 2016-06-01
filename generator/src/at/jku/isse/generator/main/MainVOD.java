package at.jku.isse.generator.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import at.jku.isse.generator.IGenerator;
import at.jku.isse.generator.VelocityGenerator;

/**
 * Generates ALL VOD programs.
 */
public class MainVOD {

	private static String vod = "VOD";
	private static String pause = "Pause";
	private static String playimm = "PlayImm";
	private static String startmovie = "StartMovie";
	private static String stopmovie = "StopMovie";
	private static String startplayer = "StartPlayer";
	private static String vrcinterface = "VRCInterface";
	private static String quitplayer = "QuitPlayer";
	private static String selectmovie = "SelectMovie";
	private static String changeserver = "ChangeServer";
	private static String detail = "Detail";

	private static String[] mandatory = new String[] { vod, playimm,
			startmovie, startplayer, vrcinterface, selectmovie };

	private static String[] optional = new String[] { pause, stopmovie,
			quitplayer, changeserver, detail };

	public static void main(String[] args) {

		int i = 0;

		long binarycounter = 0;
		long max = (int) Math.pow(2, optional.length);

		/* VOD */

		IGenerator generator = new VelocityGenerator(new File("VODtemplates/src/"),
				new File("VODtemplates/features.vm"));

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
			generator.generateFiles(new File("ProductLines/_VODprogramsALL/P"
					+ (i++) + "/"));

			binarycounter++;
		}

	}

}
