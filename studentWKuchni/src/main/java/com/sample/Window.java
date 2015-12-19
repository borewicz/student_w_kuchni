package com.sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.concurrent.Semaphore;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame implements ActionListener {

	private JButton submitButton;
	public static Semaphore dataAvailable = new Semaphore(0);

	private String attribute;
	private String question;
	private int numberOfAnswers;
	private String formType;
	private JRadioButton[] tabRadio;
	private JCheckBox[] tabCheck;
	public Dish d;

	private WindowListener windowListener;

	public Window(Dish d, String question, String attribute, String formType,
			String[] formAnswers) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.numberOfAnswers = formAnswers.length;
		this.d = d;
		this.question = question;
		this.formType = formType;
		this.attribute = attribute;

		int windowWidth = 600;
		int windowHeight = 400;
		Border empty = new EmptyBorder(10, 200, 20, 200);

		JLabel label1 = new JLabel(question);
		label1.setBorder(empty);
		add(label1);

		JPanel qArea = new JPanel(new GridLayout(10, 4));
		qArea.setBorder(empty);

		if (formType == "radio") {

			ButtonGroup group = new ButtonGroup();

			final JRadioButton[] tab = new JRadioButton[numberOfAnswers];

			for (int i = 0; i < numberOfAnswers; i++) {
				tab[i] = new JRadioButton(formAnswers[i]);
				group.add(tab[i]);
				qArea.add(tab[i]);
			}
			tabRadio = tab;
		}
		if (formType == "check") {
			ButtonGroup group = new ButtonGroup();

			final JCheckBox[] tab = new JCheckBox[numberOfAnswers];

			for (int i = 0; i < numberOfAnswers; i++) {
				tab[i] = new JCheckBox(formAnswers[i]);
				qArea.add(tab[i]);
			}
			tabCheck = tab;
		}

		add(qArea);

		add(new JPanel());

		JPanel buttonArea = new JPanel(new FlowLayout());
		buttonArea.add(new JPanel());

		submitButton = new JButton("PotwierdŸ");
		submitButton.addActionListener(this);
		buttonArea.add(submitButton);

		buttonArea.add(new JPanel());

		add(buttonArea);

		setVisible(true);
		setLayout(new FlowLayout());
		setSize(windowWidth, windowHeight);
		setTitle("Student w Kuchni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		try {
			dataAvailable.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == submitButton) {

			boolean czyCosWybrano = false;

			ArrayList<String> readyToSendList = new ArrayList<String>();
			switch (this.formType) {
			case "radio":
				for (int i = 0; i < this.numberOfAnswers; i++) {
					if (tabRadio[i].isSelected()) {
						readyToSendList.add(tabRadio[i].getText());
						czyCosWybrano = true;
					}
				}
				break;
			case "check":
				for (int i = 0; i < this.numberOfAnswers; i++) {
					if (tabCheck[i].isSelected()) {
						readyToSendList.add(tabCheck[i].getText());
						czyCosWybrano = true;
					}
				}
				break;
			}

			if (czyCosWybrano) {
				d.attributes.put(this.getattribute(), readyToSendList);

				setVisible(false);
				dataAvailable.release();
			}
		}
	}

	public String getattribute() {
		return attribute;
	}

	public void setattribute(String attribute) {
		this.attribute = attribute;
	}

}