package com.philips.lighting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import com.philips.lighting.ControllerCustom;
import com.philips.lighting.data.Constants;
import com.philips.lighting.data.StateUpdater;
import com.philips.lighting.data.Wohnung;

public class HomeCenterFrame extends JFrame {
	private final Boolean raspberry = false;

	private static final long serialVersionUID = -7469471678945429320L;
	private MenuPanel menuPanel;
	private ControllerCustom controller;

	private StateUpdater stateUpdater;

	private final int FRAME_WIDTH = Constants.FRAME_WIDTH;
	private final int FRAME_HIGHT = Constants.FRAME_HIGHT;
	private final int MENU_WIDTH = Constants.MENU_WIDTH;
	private final int MENU_BUTTON_SIZE = Constants.MENU_BUTTON_SIZE;
	private final int MENU_BUTTON_BORDER = Constants.MENU_BUTTON_BORDER;

	JButton lightMenuButton;
	JButton sensorMenuButton;
	JButton grundrissMenuButton;

	LightMenuPanel lightPanel;
	SensorMenuPanel sensorPanel;
	GrundrissMenuPanel grundrissPanel;

	private Wohnung wohnung;

	public HomeCenterFrame(Wohnung wohnung) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.wohnung = wohnung;
		this.controller = wohnung.getController();

		setTitle("Home Center by Frederik Saxinger");
		menuPanel = new MenuPanel(wohnung);
		menuPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HIGHT));

		// Format stuff
		Color background = new Color(0, 25, 51, 255);
		menuPanel.setBackground(background);
		menuPanel.setLayout(null);
		getContentPane().add(menuPanel, BorderLayout.CENTER);

		// initializing light panel
		lightPanel = new LightMenuPanel(controller, wohnung);
		lightPanel.setPreferredSize(new Dimension((FRAME_WIDTH - MENU_WIDTH), FRAME_HIGHT));
		lightPanel.setLocation(MENU_WIDTH, 0);
		controller.setLightPanel(lightPanel);
		menuPanel.add(lightPanel);

		// initializing sensor panel
		sensorPanel = new SensorMenuPanel();
		sensorPanel.setPreferredSize(new Dimension((FRAME_WIDTH - MENU_WIDTH), FRAME_HIGHT));
		sensorPanel.setLocation(MENU_WIDTH, 0);

		// initializing grundriss panel
		grundrissPanel = new GrundrissMenuPanel();
		grundrissPanel.setPreferredSize(new Dimension((FRAME_WIDTH - MENU_WIDTH), FRAME_HIGHT));
		grundrissPanel.setLocation(MENU_WIDTH, 0);

		lightMenuButton = new JButton();
		sensorMenuButton = new JButton();
		grundrissMenuButton = new JButton();

		setUpMenuButton(lightMenuButton);
		setUpLightsMenuButton();

		setUpMenuButton(sensorMenuButton);
		setUpSensorsMenuButton();

		setUpMenuButton(grundrissMenuButton);
		setUpGrundrissMenuButton();

		menuPanel.add(lightMenuButton);
		menuPanel.add(sensorMenuButton);
		menuPanel.add(grundrissMenuButton);
		// grundrissPanel.add(wohnung.getFlur().sensorButton);
		// grundrissPanel.add(wohnung.getBadezimmer().sensorButton);
		// grundrissPanel.add(wohnung.getWc().sensorButton);
		// grundrissPanel.add(wohnung.getEingang().sensorButton);

		// 4. Size the frame.
		pack();
		if (raspberry) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setLocation(0, -30);

			// set mouse cursor
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
					"blank cursor");
			getContentPane().setCursor(blankCursor);
			setAlwaysOnTop(true);
			setUndecorated(true);
			setResizable(false);
		} else {
			setLocationRelativeTo(null); // Centre the window.
			setAlwaysOnTop(true);
		}

		setVisible(true);

		stateUpdater = new StateUpdater(controller, wohnung, menuPanel);
	}

	private void setUpMenuButton(JButton button) {
		button.setSelected(false);
		button.setBackground(null);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		button.setBorder(emptyBorder);
		button.setFocusable(false);
		button.removeMouseListener(button.getMouseListeners()[0]);
	}

	private void setUpLightsMenuButton() {
		lightMenuButton.setSelected(true);
		ImageIcon light_on = new ImageIcon("resources/light_on_50.png");
		ImageIcon light_off = new ImageIcon("resources/light_off_50.png");
		lightMenuButton.setIcon(light_off);
		lightMenuButton.setSelectedIcon(light_on);
		lightMenuButton.setBounds(MENU_BUTTON_BORDER, MENU_BUTTON_BORDER * 2, MENU_BUTTON_SIZE, MENU_BUTTON_SIZE);
		lightMenuButton.addMouseListener(new MouseListener() {

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
				if (lightMenuButton.isSelected() == false) {
					lightMenuButton.setSelected(true);
					sensorMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(grundrissPanel);
					menuPanel.remove(sensorPanel);
					menuPanel.add(lightPanel);

					menuPanel.selected = 1;
					menuPanel.repaint();
				}
			}
		});
	}

	private void setUpSensorsMenuButton() {
		// TODO on/off name selected...
		ImageIcon sensor_off = new ImageIcon("resources/motion_sensor_off_50.png");
		ImageIcon sensor_on = new ImageIcon("resources/motion_sensor_on_50.png");
		sensorMenuButton.setIcon(sensor_off);
		sensorMenuButton.setSelectedIcon(sensor_on);
		sensorMenuButton.setBounds(MENU_BUTTON_BORDER, (4 * MENU_BUTTON_BORDER + MENU_BUTTON_SIZE), MENU_BUTTON_SIZE,
				MENU_BUTTON_SIZE);
		sensorMenuButton.addMouseListener(new MouseListener() {

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
				if (sensorMenuButton.isSelected() == false) {
					sensorMenuButton.setSelected(true);
					lightMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(lightPanel);
					menuPanel.remove(grundrissPanel);
					menuPanel.add(sensorPanel);

					menuPanel.selected = 2;
					menuPanel.repaint();
				}
			}
		});
	}

	private void setUpGrundrissMenuButton() {
		ImageIcon grundriss_on = new ImageIcon("resources/grundriss_on_50.png");
		ImageIcon grundriss_off = new ImageIcon("resources/grundriss_off_50.png");
		grundrissMenuButton.setIcon(grundriss_off);
		grundrissMenuButton.setSelectedIcon(grundriss_on);
		grundrissMenuButton.setBounds(MENU_BUTTON_BORDER, (6 * MENU_BUTTON_BORDER + 2 * MENU_BUTTON_SIZE),
				MENU_BUTTON_SIZE, MENU_BUTTON_SIZE);
		grundrissMenuButton.addMouseListener(new MouseListener() {

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
				if (grundrissMenuButton.isSelected() == false) {
					grundrissMenuButton.setSelected(true);
					lightMenuButton.setSelected(false);
					sensorMenuButton.setSelected(false);

					menuPanel.remove(sensorPanel);
					menuPanel.remove(lightPanel);
					menuPanel.add(grundrissPanel);

					menuPanel.selected = 3;
					menuPanel.repaint();
				}
			}
		});
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public Wohnung getWohnung() {
		return wohnung;
	}

	public void setWohnung(Wohnung wohnung) {
		this.wohnung = wohnung;
	}

}
