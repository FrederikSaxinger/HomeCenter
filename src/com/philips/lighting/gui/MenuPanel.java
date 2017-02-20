package com.philips.lighting.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Wohnung;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = -8072760331082729434L;

	@SuppressWarnings("unused")
	private ControllerCustom controller;

	@SuppressWarnings("unused")
	private Wohnung wohnung;

	public int selected = 1;

	public MenuPanel(Wohnung wohnung) {
		this.wohnung = wohnung;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// g.setColor(Constants.COLOR_SIDEBAR);
		// g.fillRect(0, 0, Constants.MENU_WIDTH, Constants.FRAME_HIGHT);
		//
		// g.setColor(Constants.COLOR_BACKGROUND);
		// g.fillRect(0, Constants.MENU_BUTTON_BORDER + Constants.MENU_WIDTH *
		// (selected - 1), Constants.MENU_WIDTH,
		// Constants.MENU_WIDTH);

		g.setColor(Constants.COLOR_SIDEBAR);
		g.fillRoundRect(-Constants.FIELD_CORNERS, -Constants.FIELD_CORNERS,
				Constants.MENU_WIDTH + Constants.FIELD_CORNERS,
				Constants.MENU_BUTTON_BORDER + Constants.MENU_WIDTH * (selected - 1) + Constants.FIELD_CORNERS,
				Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);

		// g.setColor(Constants.COLOR_BACKGROUND);
		// g.fillRoundRect(0, Constants.MENU_BUTTON_BORDER +
		// Constants.MENU_WIDTH * (selected - 1), Constants.MENU_WIDTH,
		// Constants.MENU_WIDTH, Constants.FIELD_CORNERS,
		// Constants.FIELD_CORNERS);

		g.setColor(Constants.COLOR_SIDEBAR);
		g.fillRoundRect(-Constants.FIELD_CORNERS,
				Constants.MENU_BUTTON_BORDER + Constants.MENU_WIDTH * (selected - 1) + Constants.MENU_WIDTH,
				Constants.MENU_WIDTH + Constants.FIELD_CORNERS,
				Constants.FRAME_HIGHT
						- (Constants.MENU_BUTTON_BORDER + Constants.MENU_WIDTH * (selected - 1) + Constants.MENU_WIDTH)
						+ Constants.FIELD_CORNERS,
				Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
	}

	public void setController(ControllerCustom controller) {
		this.controller = controller;
	}
}
