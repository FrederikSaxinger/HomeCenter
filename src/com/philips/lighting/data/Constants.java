package com.philips.lighting.data;

import java.awt.Color;

public interface Constants {

	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HIGHT = 480;
	public static final int MENU_WIDTH = 100;
	public static final int MENU_BUTTON_SIZE = 50;
	public static final int MENU_BUTTON_BORDER = 25;
	public static final int FIELD_SIZE = 140;
	public static final int ICON_SIZE = 70;
	public static final int ICON_BORDER = 35;
	public static final int FIELD_BORDER_VERTICAL = 10;
	public static final int FRAME_BORDER_VERTICAL = 20;
	public static final int FIELD_BORDER_HORIZONTAL = 40;
	public static final int FRAME_BORDER_HORIZONTAL = 100;
	public static final int REGLER_BREITE_INNEN = 2 * FIELD_BORDER_HORIZONTAL + 3 * FIELD_SIZE - 2 * ICON_BORDER;
	public static final int FIELD_CORNERS = 30;

	// public static final Color COLOR_SIDEBAR = new Color(0, 51, 102, 255);
	// public static final Color COLOR_BACKGROUND = new Color(0, 25, 51, 255);
	// public static final Color COLOR_PASSIV_BUTTON = new Color(0, 52, 110,
	// 255);
	// public static final Color COLOR_AKTIV_BUTTON = new Color(0, 69, 139,
	// 255);
	public static final Color COLOR_SIDEBAR = new Color(0, 70, 90, 255);
	public static final Color COLOR_BACKGROUND = new Color(0, 44, 55, 255);
	public static final Color COLOR_PASSIV_BUTTON = new Color(0, 70, 90, 255);
	public static final Color COLOR_AKTIV_BUTTON = new Color(0, 100, 125, 255);
	public static final Color COLOR_GRUNDRISS_LIGHT = new Color(0, 125, 150, 255);

	public static final int SECONDS_HEARTBEAT = 29;
	public static final int SECONDS_STATEUPDATER = 7;
}
