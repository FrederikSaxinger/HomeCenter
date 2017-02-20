package com.philips.lighting.gui;

import java.awt.BorderLayout;
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

	@SuppressWarnings("unused")
	private StateUpdater stateUpdater;

	private JButton lightMenuButton;
	private JButton sensorMenuButton;
	private JButton grundrissMenuButton;
	private JButton sceneMenuButton;

	private LightMenuPanel lightPanel;
	private SensorMenuPanel sensorPanel;
	private GrundrissMenuPanel grundrissPanel;
	private SceneMenuPanel scenePanel;

	private Wohnung wohnung;

	public HomeCenterFrame(Wohnung wohnung) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (raspberry) {
			setUndecorated(true);
		}
		this.wohnung = wohnung;
		this.controller = wohnung.getController();

		setTitle("Home Center by Frederik Saxinger");
		menuPanel = new MenuPanel(wohnung);
		menuPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HIGHT));

		// Format stuff
		menuPanel.setBackground(Constants.COLOR_BACKGROUND);
		menuPanel.setLayout(null);
		getContentPane().add(menuPanel, BorderLayout.CENTER);

		// initializing light panel
		lightPanel = new LightMenuPanel(controller, wohnung);
		lightPanel
				.setPreferredSize(new Dimension((Constants.FRAME_WIDTH - Constants.MENU_WIDTH), Constants.FRAME_HIGHT));
		lightPanel.setLocation(Constants.MENU_WIDTH, 0);
		menuPanel.add(lightPanel);

		// initializing scene panel
		scenePanel = new SceneMenuPanel(controller, wohnung);
		scenePanel
				.setPreferredSize(new Dimension((Constants.FRAME_WIDTH - Constants.MENU_WIDTH), Constants.FRAME_HIGHT));
		scenePanel.setLocation(Constants.MENU_WIDTH, 0);

		// initializing sensor panel
		sensorPanel = new SensorMenuPanel(controller, wohnung);
		sensorPanel
				.setPreferredSize(new Dimension((Constants.FRAME_WIDTH - Constants.MENU_WIDTH), Constants.FRAME_HIGHT));
		sensorPanel.setLocation(Constants.MENU_WIDTH, 0);

		// initializing grundriss panel
		grundrissPanel = new GrundrissMenuPanel(controller, wohnung);
		grundrissPanel
				.setPreferredSize(new Dimension((Constants.FRAME_WIDTH - Constants.MENU_WIDTH), Constants.FRAME_HIGHT));
		grundrissPanel.setLocation(Constants.MENU_WIDTH, 0);

		if (controller != null) {
			controller.setLightPanel(lightPanel);
			controller.setGrundrissPanel(grundrissPanel);
		}

		lightMenuButton = new JButton();
		sceneMenuButton = new JButton();
		sensorMenuButton = new JButton();
		grundrissMenuButton = new JButton();

		setUpMenuButton(lightMenuButton);
		setUpLightsMenuButton();

		setUpMenuButton(sceneMenuButton);
		setUpSceneMenuButton();

		setUpMenuButton(sensorMenuButton);
		setUpSensorsMenuButton();

		setUpMenuButton(grundrissMenuButton);
		setUpGrundrissMenuButton();

		menuPanel.add(lightMenuButton);
		menuPanel.add(sensorMenuButton);
		menuPanel.add(grundrissMenuButton);
		menuPanel.add(sceneMenuButton);

		// 4. Size the frame.
		pack();
		if (raspberry) {
			setLocationRelativeTo(null); // Centre the window.
			setAlwaysOnTop(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			// setLocation(0, 0);
			//
			// set mouse cursor
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
					"blank cursor");
			getContentPane().setCursor(blankCursor);
			setAlwaysOnTop(true);
			setResizable(false);
		} else {
			// setLocationRelativeTo(null); // Centre the window.
			setLocation(2500, 50);
			setAlwaysOnTop(true);
		}

		setVisible(true);

		if (controller != null) {
			stateUpdater = new StateUpdater(controller, wohnung, menuPanel);
		}
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
		lightMenuButton.setBounds(Constants.MENU_BUTTON_BORDER, Constants.MENU_BUTTON_BORDER * 2,
				Constants.MENU_BUTTON_SIZE, Constants.MENU_BUTTON_SIZE);
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
					sceneMenuButton.setSelected(false);
					sensorMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(grundrissPanel);
					menuPanel.remove(sensorPanel);
					menuPanel.remove(scenePanel);
					menuPanel.add(lightPanel);

					menuPanel.selected = 1;
					menuPanel.repaint();
				}
			}
		});
	}

	private void setUpSceneMenuButton() {
		ImageIcon scene_off = new ImageIcon("resources/switch_off_50.png");
		ImageIcon scene_on = new ImageIcon("resources/switch_on_50.png");
		sceneMenuButton.setIcon(scene_off);
		sceneMenuButton.setSelectedIcon(scene_on);
		sceneMenuButton.setBounds(Constants.MENU_BUTTON_BORDER,
				(4 * Constants.MENU_BUTTON_BORDER + Constants.MENU_BUTTON_SIZE), Constants.MENU_BUTTON_SIZE,
				Constants.MENU_BUTTON_SIZE);
		sceneMenuButton.addMouseListener(new MouseListener() {

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
				System.out.println("button scene pressed");
				if (sceneMenuButton.isSelected() == false) {
					sceneMenuButton.setSelected(true);
					sensorMenuButton.setSelected(false);
					lightMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(lightPanel);
					menuPanel.remove(grundrissPanel);
					menuPanel.remove(sensorPanel);
					menuPanel.add(scenePanel);

					menuPanel.selected = 2;
					menuPanel.repaint();
				}
			}
		});
	}

	private void setUpSensorsMenuButton() {
		ImageIcon sensor_off = new ImageIcon("resources/motion_sensor_off_50.png");
		ImageIcon sensor_on = new ImageIcon("resources/motion_sensor_on_50.png");
		sensorMenuButton.setIcon(sensor_off);
		sensorMenuButton.setSelectedIcon(sensor_on);
		sensorMenuButton.setBounds(Constants.MENU_BUTTON_BORDER,
				(6 * Constants.MENU_BUTTON_BORDER + 2 * Constants.MENU_BUTTON_SIZE), Constants.MENU_BUTTON_SIZE,
				Constants.MENU_BUTTON_SIZE);
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
					sceneMenuButton.setSelected(false);
					lightMenuButton.setSelected(false);
					grundrissMenuButton.setSelected(false);

					menuPanel.remove(lightPanel);
					menuPanel.remove(scenePanel);
					menuPanel.remove(grundrissPanel);
					menuPanel.add(sensorPanel);

					menuPanel.selected = 3;
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
		grundrissMenuButton.setBounds(Constants.MENU_BUTTON_BORDER,
				(8 * Constants.MENU_BUTTON_BORDER + 3 * Constants.MENU_BUTTON_SIZE), Constants.MENU_BUTTON_SIZE,
				Constants.MENU_BUTTON_SIZE);
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
					sceneMenuButton.setSelected(false);
					lightMenuButton.setSelected(false);
					sensorMenuButton.setSelected(false);

					menuPanel.remove(sensorPanel);
					menuPanel.remove(scenePanel);
					menuPanel.remove(lightPanel);
					menuPanel.add(grundrissPanel);

					menuPanel.selected = 4;
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
