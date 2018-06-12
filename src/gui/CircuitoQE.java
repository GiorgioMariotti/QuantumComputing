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
public class CircuitoQE extends JFrame{
	String circuito="immagini\\qe.jpg";
	Pannello schermata=new Pannello(circuito);
	public CircuitoQE(){
		this.setTitle("Quantum Eraser parte 1");
		this.setSize(500, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(schermata,"Center");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}