package com.philips.lighting;

import com.philips.lighting.data.Scene;
import com.philips.lighting.data.Scenes;

public class SceneController {

	private ControllerCustom controller;
	private Scenes scenes;

	public SceneController(ControllerCustom controller) {
		this.controller = controller;
		this.scenes = new Scenes();
	}

	public void setSleepMode() {
		for (Scene scene : scenes.scenesNacht) {
			controller.setSceneColor(scene.id, scene.lightId, scene.brightness, scene.color);
		}

		controller.setSceneColor(scenes.wcTag.id, scenes.wcTag.lightId, scenes.wcNacht.brightness,
				scenes.wcNacht.color);
		controller.setSceneColor(scenes.badezimmerTag.id, scenes.badezimmerTag.lightId,
				scenes.badezimmerNacht.brightness, scenes.badezimmerNacht.color);
		controller.setSceneColor(scenes.flurTag.id, scenes.flurTag.lightId, scenes.flurNacht.brightness,
				scenes.flurNacht.color);
	}

	public void setAwakeMode() {
		for (Scene scene : scenes.scenesTag) {
			controller.setSceneColor(scene.id, scene.lightId, scene.brightness, scene.color);
		}

		controller.setSceneColor(scenes.wcNacht.id, scenes.wcTag.lightId, scenes.wcTag.brightness, scenes.wcTag.color);
		controller.setSceneColor(scenes.badezimmerNacht.id, scenes.badezimmerTag.lightId,
				scenes.badezimmerTag.brightness, scenes.badezimmerTag.color);
		controller.setSceneColor(scenes.flurNacht.id, scenes.flurTag.lightId, scenes.flurTag.brightness,
				scenes.flurTag.color);
	}

	public void setNormalScenes() {
		for (Scene scene : scenes.scenesNacht) {
			controller.setSceneColor(scene.id, scene.lightId, scene.brightness, scene.color);
		}

		for (Scene scene : scenes.scenesTag) {
			controller.setSceneColor(scene.id, scene.lightId, scene.brightness, scene.color);
		}
	}
}
