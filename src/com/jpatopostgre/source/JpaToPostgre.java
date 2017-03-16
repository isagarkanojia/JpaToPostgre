package com.jpatopostgre.source;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JpaToPostgre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton BtnClear;
	JButton BtnConvert;
	JButton BtnCopy;
	JTextArea area;
	JTextArea area2;

	public JpaToPostgre() {
		super.setBounds(50, 10, 750, 400);
		
		this.setTitle("JpaToPostgre version 2.0");
		this.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/image.jpg"))));

	
		this.BtnCopy = new JButton("Copy");
		this.BtnCopy.setBounds(300, 300, 100, 30);
		this.BtnCopy.setForeground(Color.BLUE);
		this.BtnCopy.setBackground(Color.getHSBColor(365.0F, 367.0F, 85.0F));
		super.add(this.BtnCopy);
		this.BtnCopy.addActionListener(this);
		
		
		this.BtnConvert = new JButton("Convert");
		this.BtnConvert.setBounds(410, 300, 100, 30);
		this.BtnConvert.setForeground(Color.BLUE);
		this.BtnConvert.setBackground(Color.getHSBColor(365.0F, 367.0F, 85.0F));
		super.add(this.BtnConvert);
		this.BtnConvert.addActionListener(this);
				
		this.BtnClear = new JButton("Clear");
		this.BtnClear.setBounds(520, 300, 100, 30);
		this.BtnClear.setForeground(Color.BLUE);
		this.BtnClear.setBackground(Color.getHSBColor(365.0F, 367.0F, 85.0F));
		super.add(this.BtnClear);
		this.BtnClear.addActionListener(this);

		this.area = new JTextArea();
		JScrollPane pane = new JScrollPane(this.area);
		pane.setBounds(50, 10, 650, 100);
		super.add(pane);

		this.area2 = new JTextArea();
		JScrollPane pane2 = new JScrollPane(this.area2);
		pane2.setBounds(50, 160, 650, 100);
		super.add(pane2);

		super.setLayout(null);
		super.setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		JpaToPostgre obj = new JpaToPostgre();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.BtnCopy)
		{
			copyToClipBoard();
		}
		if (e.getSource() == this.BtnConvert) {
			convert();
		}
		if (e.getSource() == this.BtnClear) {
			clear();
		}
	}

	private void clear() {
		this.area.setText("");
		this.area2.setText("");
		JOptionPane.showMessageDialog(null, "Cleared!");
	}

	private void convert() {
		String input = this.area.getText();
		String med1 = input.replaceAll("[\"+\\t]", "");
		String mediator = med1.replaceAll("[:]", "####");
		StringBuffer output = new StringBuffer();
		char[] med = mediator.toCharArray();
		for(int x=0; x<med.length;x++)
		{
			
			int y=x;
			if( y-1==0 && med[x]>=65 && med[x]<=90 && med[0]>=91)
			{
				output.append("_"+(char)(med[x]+32));
			}
			else if(x>1 && med[x]>=65 && med[x]<=90  && med[y-1]>=91)
			{
				output.append("_"+(char)(med[x]+32));
			}
			else
			{
				output.append(med[x]);
			}
			
		}
		StringBuffer finalOutput = new StringBuffer();
		char[] arrayOfChar1;
		int j = (arrayOfChar1 = output.toString().toCharArray()).length;
		for (int i = 0; i < j; i++) {
			char c = arrayOfChar1[i];
			if ((c >= 'A') && (c <= 'Z')) {
				finalOutput.append((char) (c + ' '));
			} else {
				finalOutput.append(c);
			}
		}
		this.area2.setText(finalOutput.toString());
		JOptionPane.showMessageDialog(null, "Converted!");
	}

	private void copyToClipBoard() {
		String myString =this.area2.getText();
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		JOptionPane.showMessageDialog(null, "Copied!");
	}
}