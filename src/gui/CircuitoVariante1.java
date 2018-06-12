/**
 * 
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author Giorgio
 *
 */
public class CircuitoVariante1 extends JFrame{
	String circuito="immagini\\variante1.jpg";
	Pannello schermata=new Pannello(circuito);
	public CircuitoVariante1(){
		this.setTitle("Variante 1");
		this.setSize(600, 90);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(schermata,"Center");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
