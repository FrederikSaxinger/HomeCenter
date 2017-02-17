package com.philips.lighting.data;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Scenes {

	public Scene wcNacht;
	public Scene wcTag;

	public Scene flurNacht;
	public Scene flurTag;

	public Scene badezimmerNacht;
	public Scene badezimmerTag;

	public List<Scene> scenesNacht;
	public List<Scene> scenesTag;

	public Scenes() {
		flurNacht = new Scene("CnPES2YucgOYke7", 2, 35, new Point2D.Double(0.6463, 0.3319));
		flurTag = new Scene("UEIVfRR8oGhMAID", 2, 254, new Point2D.Double(0.3885, 0.3772));

		wcNacht = new Scene("b6pXT3PpFWsgRi7", 1, 35, new Point2D.Double(0.6463, 0.3319));
		wcTag = new Scene("MdGD4DnzD5YdiSN", 1, 254, new Point2D.Double(0.3885, 0.3772));

		badezimmerNacht = new Scene("l4RXEFURkOSntwh", 4, 35, new Point2D.Double(0.6463, 0.3319));
		badezimmerTag = new Scene("4NNvEjiQbg9cKYm", 4, 254, new Point2D.Double(0.3885, 0.3772));

		scenesNacht = new LinkedList<>();
		scenesNacht.add(wcNacht);
		scenesNacht.add(badezimmerNacht);
		scenesNacht.add(flurNacht);

		scenesTag = new LinkedList<>();
		scenesTag.add(wcTag);
		scenesTag.add(badezimmerTag);
		scenesTag.add(flurTag);
	}

}
