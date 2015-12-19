package com.sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.Semaphore;
import java.util.*;

import javax.swing.ButtonGroup;
//import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;
//import org.dyno.visual.swing.layouts.GroupLayout;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame implements ActionListener{
	 
    private JButton potwierdzButton;
    public static Semaphore dataAvailable = new Semaphore(0);
    
    private String nazwaCechy;
    private String trescPytania;
    private int liczbaOdpowiedzi;
    private String typFormularza;
    private JRadioButton[] tabRadio;
    private JCheckBox[] tabCheck;
    public Osoba o;
    
    private WindowListener windowListener;
    
    
    public Window(Osoba o, String trescPytania, String nazwaCechy, String typFormularza, String[] odpowiedziFormularza) {
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
    	
        this.liczbaOdpowiedzi = odpowiedziFormularza.length;
        this.o = o;
        this.trescPytania = trescPytania;
        this.typFormularza = typFormularza;
        this.nazwaCechy = nazwaCechy;
		
        Border empty = new EmptyBorder(0, 20, 0, 0);
        
    	JLabel label1 = new JLabel(trescPytania);
    	label1.setBorder(empty);
        add(label1);
         
        
        JPanel qArea = new JPanel(new GridLayout(liczbaOdpowiedzi,0));
        qArea.setBorder(empty);
         
        if (typFormularza == "radio"){
	         
        	ButtonGroup group = new ButtonGroup();
	         
	        final JRadioButton[] tab = new JRadioButton[liczbaOdpowiedzi];
	         
	        for (int i = 0; i < liczbaOdpowiedzi; i++){
	        	tab[i] = new JRadioButton(odpowiedziFormularza[i]);
	        	group.add(tab[i]);
	        	qArea.add(tab[i]);
	        }
	        tabRadio = tab;
         }
         if (typFormularza == "check"){
        	 ButtonGroup group = new ButtonGroup();
        	 
        	 final JCheckBox[] tab = new JCheckBox[liczbaOdpowiedzi];
        	 
        	 for (int i = 0;i < liczbaOdpowiedzi; i++){
        		 tab[i] = new JCheckBox(odpowiedziFormularza[i]);
        		 qArea.add(tab[i]);
        	 }
        	 tabCheck = tab;
         }
         
         add(qArea);
         
         add(new JPanel());
         
         JPanel buttonArea = new JPanel(new GridLayout(0,3));
         buttonArea.add(new JPanel());
         
         potwierdzButton = new JButton("PotwierdŸ");
         potwierdzButton.addActionListener(this);
         buttonArea.add(potwierdzButton);
         
         buttonArea.add(new JPanel());
         
         add(buttonArea);
         
         setVisible(true);
         
         setLayout(new GridLayout(4,1));
         setSize(450, 300);
         setTitle("Doradca zawodowy.");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
         try {
			dataAvailable.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
 
        if(source == potwierdzButton){

        	boolean czyCosWybrano = false;
        	
        	ArrayList <String> readyToSendList = new ArrayList<String>();
        	switch(this.typFormularza){
        	case "radio":
        		for (int i = 0; i < this.liczbaOdpowiedzi; i++){
        			if (tabRadio[i].isSelected()){
        				readyToSendList.add(tabRadio[i].getText());
        				czyCosWybrano = true;
        			}
        		}
        		break;
        	case "check":
        		for (int i = 0; i < this.liczbaOdpowiedzi; i++){
        			if (tabCheck[i].isSelected()){
        				readyToSendList.add(tabCheck[i].getText());
        				czyCosWybrano = true;
        			}
        		}
        		break;
        	}
        	
        	if(czyCosWybrano){
	        	o.cechy.put(this.getNazwaCechy(), readyToSendList); 
	        	
	        	setVisible(false);
	        	dataAvailable.release();
        	}
        }
    }

	public String getNazwaCechy() {
		return nazwaCechy;
	}

	public void setNazwaCechy(String nazwaCechy) {
		this.nazwaCechy = nazwaCechy;
	}
 
    
}