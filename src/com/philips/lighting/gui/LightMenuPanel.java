package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Dimension;
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

	private ControllerCustom controller;
	private Wohnung wohnung;

	private Border emptyBorder;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	private int brightness;
	private Room focus = null;
	private boolean justFocused = false;
	private JPanel reglerPanel;
	boolean reglerclicked = false;
	private Double reglerProzent;

	public LightMenuPanel(ControllerCustom controller, Wohnung wohnung) {
		this.controller = controller;
		this.wohnung = wohnung;

		this.setPreferredSize(new Dimension(Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT));
		this.setLayout(null);
		this.setBounds(Constants.MENU_WIDTH, 0, Constants.FRAME_WIDTH - Constants.MENU_WIDTH, Constants.FRAME_HIGHT);
		this.setBackground(Constants.COLOR_BACKGROUND);

		initializeButton(button1, wohnung.getFlur());
		initializeButton(button2, wohnung.getBadezimmer());
		initializeButton(button3, wohnung.getSchlafzimmer());
		initializeButton(button4, wohnung.getEingang());
		initializeButton(button5, wohnung.getWc());
		initializeButton(button6, wohnung.getAbstellkammerl());

		reglerPanel = new JPanel();
		reglerPanel.setBounds(Constants.FRAME_BORDER_HORIZONTAL + Constants.ICON_BORDER,
				Constants.FRAME_BORDER_VERTICAL + 1 * Constants.FIELD_BORDER_VERTICAL + 1 * Constants.FIELD_SIZE,
				Constants.REGLER_BREITE_INNEN, Constants.FIELD_SIZE);
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

		// g.setFont(new Font("Arial", Font.BOLD, 15));

		drawFeld(g, wohnung.getBadezimmer());
		drawFeld(g, wohnung.getWc());
		drawFeld(g, wohnung.getSchlafzimmer());
		drawFeld(g, wohnung.getEingang());
		drawFeld(g, wohnung.getAbstellkammerl());
		drawFeld(g, wohnung.getFlur());

		// focus aktualisieren
		if (focus != null && !justFocused && !focus.light.isOn) {
			focus = null;
		}
		justFocused = false;
		drawRegler(g, focus);

	}

	private void drawFeld(Graphics g, Room room) {
		if (room.light.isOn) {
			g.setColor(Constants.COLOR_AKTIV_BUTTON);
			// g.fillRect((int) room.fieldCoord.getX(), (int)
			// room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE);
			g.fillRoundRect(room.fieldCoord.x, room.fieldCoord.y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
					Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
			room.getIcon_on().paintIcon(this, g, room.fieldCoord.x + Constants.ICON_BORDER,
					room.fieldCoord.y + Constants.ICON_BORDER);
		} else {
			g.setColor(Constants.COLOR_PASSIV_BUTTON);
			g.fillRoundRect(room.fieldCoord.x, room.fieldCoord.y, Constants.FIELD_SIZE, Constants.FIELD_SIZE,
					Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
			room.getIcon_off().paintIcon(this, g, room.fieldCoord.x + Constants.ICON_BORDER,
					room.fieldCoord.y + Constants.ICON_BORDER);
		}

	}

	private void initializeButton(JButton button, Room room) {
		button = new JButton();
		button.setBounds(room.fieldCoord.x, room.fieldCoord.y, Constants.FIELD_SIZE, Constants.FIELD_SIZE);
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
					justFocused = true;
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
		if (focus.fieldCoord.y == Constants.FRAME_BORDER_VERTICAL) {
			g.fillRect(focus.fieldCoord.x, focus.fieldCoord.y + Constants.FIELD_SIZE - Constants.FIELD_CORNERS,
					Constants.FIELD_SIZE,
					Constants.FIELD_BORDER_VERTICAL + Constants.FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_CORNERS);
		} else {
			g.fillRect(focus.fieldCoord.x,
					focus.fieldCoord.y - Constants.FIELD_BORDER_VERTICAL - Constants.FRAME_BORDER_VERTICAL
							- Constants.FIELD_CORNERS,
					Constants.FIELD_SIZE,
					Constants.FIELD_BORDER_VERTICAL + Constants.FRAME_BORDER_VERTICAL + 2 * Constants.FIELD_CORNERS);
		}
	}

	private void drawRegler(Graphics g, Room focus) {
		int reglerx = Constants.FRAME_BORDER_HORIZONTAL;
		int reglery = 2 * Constants.FRAME_BORDER_VERTICAL + 1 * Constants.FIELD_SIZE
				+ 1 * Constants.FIELD_BORDER_VERTICAL;
		int reglerw = 3 * Constants.FIELD_SIZE + 2 * Constants.FIELD_BORDER_HORIZONTAL;
		int reglerh = Constants.FIELD_SIZE - 2 * Constants.FRAME_BORDER_VERTICAL;
		if (focus != null) {
			g.setColor(Constants.COLOR_AKTIV_BUTTON);
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
				g.fillRoundRect(reglerx + Constants.ICON_BORDER, reglery + Constants.ICON_BORDER,
						focus.light.reglerbreite, reglerh - 2 * Constants.ICON_BORDER, Constants.FIELD_CORNERS >> 1,
						Constants.FIELD_CORNERS >> 1);

				if (controller != null) {
					controller.setLightBrightness(focus, brightness);
				}
			} else {
				g.setColor(Color.WHITE);
				// regler weiß lichtstate
				g.fillRoundRect(reglerx + Constants.ICON_BORDER, reglery + Constants.ICON_BORDER,
						focus.light.reglerbreite, reglerh - 2 * Constants.ICON_BORDER, Constants.FIELD_CORNERS >> 1,
						Constants.FIELD_CORNERS >> 1);
			}
		} else {
			g.setColor(Constants.COLOR_PASSIV_BUTTON);
			g.fillRoundRect(reglerx, reglery, reglerw, reglerh, Constants.FIELD_CORNERS, Constants.FIELD_CORNERS);
		}
	}

}
