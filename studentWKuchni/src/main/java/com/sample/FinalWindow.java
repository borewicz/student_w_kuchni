package com.sample;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;



public class FinalWindow extends JFrame implements ActionListener{
	
	private JButton potwierdzButton;
	public static Semaphore sem = new Semaphore(0);
	
	public FinalWindow (String decyzja){
		
		setLayout(new GridLayout(0,1));
		
		Border empty = new EmptyBorder(0, 20, 0, 0);
			
		JLabel label1 = new JLabel(decyzja);
		label1.setBorder(empty);
		add(label1);
		
		potwierdzButton = new JButton("Potwierdü");
        potwierdzButton.addActionListener(this);
        
        add(potwierdzButton);
		
		setSize(400, 100);
        setTitle("G≥odny student, smutny student.");
        setVisible(true);
        setLocationRelativeTo(null);
        try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
 
        if(source == potwierdzButton){
        	setVisible(false);
        	
        	sem.release();
        	
        	System.exit(0);
        }	
	}
	
	
}