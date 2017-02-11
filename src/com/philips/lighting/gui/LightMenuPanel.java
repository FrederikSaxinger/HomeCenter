package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.Room;
import com.philips.lighting.data.Wohnung;

public class LightMenuPanel extends JPanel {
	private static final long serialVersionUID = 4910078256877368167L;

	private final int FRAME_WIDTH = Constants.FRAME_WIDTH;
	private final int FRAME_HIGHT = Constants.FRAME_HIGHT;
	private final int MENU_WIDTH = Constants.MENU_WIDTH;
	private final int FIELD_SIZE = Constants.FIELD_SIZE;
	private final int ICON_BORDER = Constants.ICON_BORDER;
	final private int FIELD_BORDER_VERTICAL = Constants.FIELD_BORDER_VERTICAL;
	final private int FRAME_BORDER_VERTICAL = Constants.FRAME_BORDER_VERTICAL;
	final private int FRAME_BORDER_HORIZONTAL = Constants.FRAME_BORDER_HORIZONTAL;
	final private int FIELD_BORDER_HORIZONTAL = Constants.FIELD_BORDER_HORIZONTAL;

	private ControllerCustom controller;
	private Wohnung wohnung;

	private Border emptyBorder;

	private Color passiv = new Color(0, 52, 110, 255);
	private Color aktiv = new Color(0, 69, 139, 255);
	private Color background = new Color(0, 25, 51, 255);

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	private int brightness;
	private Room focus = null;
	private JPanel reglerPanel;
	boolean reglerclicked = false;
	private Double reglerProzent;

	public LightMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;

		this.setPreferredSize(new Dimension(FRAME_WIDTH - MENU_WIDTH, FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(MENU_WIDTH, 0, FRAME_WIDTH - MENU_WIDTH, FRAME_HIGHT);
		this.setBackground(background);

		initializeButton(button1, wohnung.getFlur());
		initializeButton(button2, wohnung.getBadezimmer());
		initializeButton(button3, wohnung.getSchlafzimmer());
		initializeButton(button4, wohnung.getEingang());
		initializeButton(button5, wohnung.getWc());
		initializeButton(button6, wohnung.getAbstellkammerl());

		reglerPanel = new JPanel();
		reglerPanel.setBounds(FRAME_BORDER_HORIZONTAL + ICON_BORDER,
				FRAME_BORDER_VERTICAL + 1 * FIELD_BORDER_VERTICAL + 1 * FIELD_SIZE, Constants.REGLER_BREITE_INNEN,
				FIELD_SIZE);
		reglerPanel.setOpaque(false);
		reglerPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked on reglerPanel");
				if (focus != null) {
					reglerProzent = (double) e.getX() / (Constants.REGLER_BREITE_INNEN);
					Double tempBrightness = reglerProzent * 254.;
					brightness = tempBrightness.intValue();
					repaint();
					System.out.println("repaint Regler");
					reglerclicked = true;
				}
			}
		});
		add(reglerPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Arial", Font.BOLD, 15));

		drawFeld(g, wohnung.getBadezimmer());
		drawFeld(g, wohnung.getWc());
		drawFeld(g, wohnung.getSchlafzimmer());
		drawFeld(g, wohnung.getEingang());
		drawFeld(g, wohnung.getAbstellkammerl());
		drawFeld(g, wohnung.getFlur());

		drawRegler(g, focus);

	}

	private void drawFeld(Graphics g, Room room) {
		if (room.light.isOn) {
			g.setColor(aktiv);
			// g.fillRect((int) room.fieldCoord.getX(), (int)
			// room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE);
			g.fillRoundRect((int) room.fieldCoord.getX(), (int) room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE, 20, 20);
			room.getIcon_on().paintIcon(this, g, (int) room.fieldCoord.getX() + ICON_BORDER,
					(int) room.fieldCoord.getY() + ICON_BORDER);
		} else {
			g.setColor(passiv);
			g.fillRoundRect((int) room.fieldCoord.getX(), (int) room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE, 20, 20);
			room.getIcon_off().paintIcon(this, g, (int) room.fieldCoord.getX() + ICON_BORDER,
					(int) room.fieldCoord.getY() + ICON_BORDER);
		}

	}

	private void initializeButton(JButton button, Room room) {
		button = new JButton();
		button.setBounds(room.fieldCoord.x, room.fieldCoord.y, FIELD_SIZE, FIELD_SIZE);
		button.setContentAreaFilled(false);
		button.setBorder(emptyBorder);
		button.setFocusable(false);
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (room.light.isOn == false) {
					focus = room;
					if (controller != null) {
						controller.switchLight(room);
						// TODO änder to switchon
						// TODO sensor aus
					} else {
						room.light.isOn = !room.light.isOn;
					}
				} else if (focus == room) { // && licht an (logik)
					focus = null;
					if (controller != null) {
						controller.switchLight(room);
						// switchoff
						// sensor ein
					} else {
						room.light.isOn = !room.light.isOn;
					}

				} else { // fall licht ein && focus aus
					focus = room;
				}
				repaint();
				System.out.println("Repaint wegen licht in " + room.name);
			}
		});
		add(button);
	}

	private void drawVerbindungen(Graphics g, Room focus) {
		if (focus.fieldCoord.y == FRAME_BORDER_VERTICAL) {
			g.fillRect(focus.fieldCoord.x, focus.fieldCoord.y + FIELD_SIZE - Constants.FIELD_CORNERS, FIELD_SIZE,
					FIELD_BORDER_VERTICAL + FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_CORNERS);
		} else {
			g.fillRect(focus.fieldCoord.x,
					focus.fieldCoord.y - FIELD_BORDER_VERTICAL - FRAME_BORDER_VERTICAL - Constants.FIELD_CORNERS,
					FIELD_SIZE, FIELD_BORDER_VERTICAL + FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_CORNERS);
		}
	}

	private void drawRegler(Graphics g, Room focus) {
		int reglerx = FRAME_BORDER_HORIZONTAL;
		int reglery = 2 * FRAME_BORDER_VERTICAL + 1 * FIELD_SIZE + 1 * FIELD_BORDER_VERTICAL;
		int reglerw = 3 * FIELD_SIZE + 2 * FIELD_BORDER_HORIZONTAL;
		int reglerh = FIELD_SIZE - 2 * FRAME_BORDER_VERTICAL;
		if (focus != null) {
			g.setColor(aktiv);
			drawVerbindungen(g, focus);

			g.fillRoundRect(reglerx, reglery, reglerw, reglerh, Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);

			if (reglerclicked == true) {
				reglerclicked = false;
				Double reglerbreiteTemp = 0.;
				g.setColor(Color.WHITE);
				if (reglerProzent > 0.75) {
					reglerbreiteTemp = 1. * Constants.REGLER_BREITE_INNEN;
					focus.light.reglerbreite = reglerbreiteTemp.intValue();
					focus.light.brightness = 254;
				} else {
					reglerbreiteTemp = reglerProzent * Constants.REGLER_BREITE_INNEN;
					focus.light.reglerbreite = reglerbreiteTemp.intValue();
					focus.light.brightness = brightness;
				}
				g.fillRoundRect(reglerx + ICON_BORDER, reglery + ICON_BORDER, focus.light.reglerbreite,
						reglerh - 2 * ICON_BORDER, Constants.FIELD_CORNERS >> 1, Constants.FIELD_CORNERS >> 1);

				if (controller != null) {
					controller.setLightBrightness(focus, brightness);
				}
			} else {
				g.setColor(Color.WHITE);
				// regler weiß lichtstate
				g.fillRoundRect(reglerx + ICON_BORDER, reglery + ICON_BORDER, focus.light.reglerbreite,
						reglerh - 2 * ICON_BORDER, Constants.FIELD_CORNERS >> 1, Constants.FIELD_CORNERS >> 1);
			}
		} else {
			g.setColor(passiv);
			g.fillRoundRect(reglerx, reglery, reglerw, reglerh, Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
		}
	}

}
