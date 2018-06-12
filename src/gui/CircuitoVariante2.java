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
public class CircuitoVariante2 extends JFrame{
	String circuito="immagini\\variante2.jpg";
	Pannello schermata=new Pannello(circuito);
	public CircuitoVariante2(){
		this.setTitle("Variante 2");
		this.setSize(600, 150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(schermata,"Center");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}