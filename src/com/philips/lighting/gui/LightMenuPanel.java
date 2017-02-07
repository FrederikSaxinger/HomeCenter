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
import com.philips.lighting.data.StateUpdater;
import com.philips.lighting.data.Wohnung;

public class LightMenuPanel extends JPanel {
	private final Boolean raspberry = false;

	private final int FRAME_WIDTH = Constants.FRAME_WIDTH;
	private final int FRAME_HIGHT = Constants.FRAME_HIGHT;
	private final int MENU_WIDTH = Constants.MENU_WIDTH;
	private final int FIELD_SIZE = Constants.FIELD_SIZE;
	private final int ICON_SIZE = Constants.ICON_SIZE;
	private final int ICON_BORDER = Constants.ICON_BORDER;
	final private int FIELD_BORDER_VERTICAL = Constants.FIELD_BORDER_VERTICAL;
	final private int FRAME_BORDER_VERTICAL = Constants.FRAME_BORDER_VERTICAL;
	final private int FRAME_BORDER_HORIZONTAL = Constants.FRAME_BORDER_HORIZONTAL;
	final private int FIELD_BORDER_HORIZONTAL = Constants.FIELD_BORDER_HORIZONTAL;

	private ControllerCustom controller;
	private Wohnung wohnung;
	private StateUpdater stateUpdater;

	private Border emptyBorder;

	private Color passiv = new Color(0, 71, 152, 150);
	private Color aktiv = new Color(0, 100, 200, 150);
	private Color background = new Color(0, 25, 51, 255);
	private Color blaugrau = new Color(102, 143, 175, 100);
	private Color blaugrau2 = new Color(55, 90, 118, 255);

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	private int brightness;
	private Room focus = null;
	private JPanel reglerPanel;

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
		// reglerPanel.setBackground(passiv);
		reglerPanel.setBounds(FRAME_BORDER_HORIZONTAL,
				FRAME_BORDER_VERTICAL + 1 * FIELD_BORDER_VERTICAL + 1 * FIELD_SIZE,
				3 * FIELD_SIZE + 2 * FIELD_BORDER_HORIZONTAL, FIELD_SIZE);
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
				if (focus != null) {
					brightness = e.getX();
					repaint();
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

		if (focus != null) {
			g.setColor(aktiv);
			g.fillRect(FRAME_BORDER_HORIZONTAL, 2 * FRAME_BORDER_VERTICAL + 1 * FIELD_SIZE + 1 * FIELD_BORDER_VERTICAL,
					3 * FIELD_SIZE + 2 * FIELD_BORDER_HORIZONTAL, FIELD_SIZE - 2 * FRAME_BORDER_VERTICAL);
			g.fillRect(focus.fieldCoord.x, focus.fieldCoord.y + FIELD_SIZE, FIELD_SIZE,
					FIELD_BORDER_VERTICAL + FRAME_BORDER_VERTICAL);
			g.setColor(Color.WHITE);
			// g.fillRect(FRAME_BORDER_HORIZONTAL + ICON_BORDER,
			// 2 * FRAME_BORDER_VERTICAL + 1 * FIELD_SIZE + 1 *
			// FIELD_BORDER_VERTICAL + ICON_BORDER, (brightness),
			// FIELD_SIZE - 2 * ICON_BORDER - 2 * FRAME_BORDER_VERTICAL);
			g.fillRect(FRAME_BORDER_HORIZONTAL,
					2 * FRAME_BORDER_VERTICAL + 1 * FIELD_SIZE + 1 * FIELD_BORDER_VERTICAL + ICON_BORDER, (brightness),
					FIELD_SIZE - 2 * ICON_BORDER - 2 * FRAME_BORDER_VERTICAL);
		} else {
			g.setColor(passiv);
			g.fillRect(FRAME_BORDER_HORIZONTAL, 2 * FRAME_BORDER_VERTICAL + 1 * FIELD_SIZE + 1 * FIELD_BORDER_VERTICAL,
					3 * FIELD_SIZE + 2 * FIELD_BORDER_HORIZONTAL, FIELD_SIZE - 2 * FRAME_BORDER_VERTICAL);
		}
	}

	private void drawFeld(Graphics g, Room room) {
		if (room.light.lightOn) {
			g.setColor(aktiv);
			g.fillRect((int) room.fieldCoord.getX(), (int) room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE);
			room.getIcon_on().paintIcon(this, g, (int) room.fieldCoord.getX() + ICON_BORDER,
					(int) room.fieldCoord.getY() + ICON_BORDER);
		} else {
			g.setColor(passiv);
			g.fillRect((int) room.fieldCoord.getX(), (int) room.fieldCoord.getY(), FIELD_SIZE, FIELD_SIZE);
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
				if (raspberry) {
					if (room.light.lightOn == true) {
						focus = null;
					} else {
						focus = room;
					}
					controller.switchLight(room);
					repaint();

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!raspberry) {
					if (room.light.lightOn == true) {
						focus = null;
					} else {
						focus = room;
					}
					controller.switchLight(room);
					repaint();

				}
			}
		});
		add(button);
	}
}
