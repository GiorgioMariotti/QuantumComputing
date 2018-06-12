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
public class CircuitoQEspento extends JFrame{
	String circuito="immagini\\qespento.jpg";
	Pannello schermata=new Pannello(circuito);
	public CircuitoQEspento(){
		this.setTitle("Quantum Eraser parte 2 dispositivo spento");
		this.setSize(400, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(schermata,"Center");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}