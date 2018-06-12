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
public class CircuitoQEacceso extends JFrame{
	String circuito="immagini\\qeacceso.jpg";
	Pannello schermata=new Pannello(circuito);
	public CircuitoQEacceso(){
		this.setTitle("Quantum Eraser parte 2 dispositivo acceso");
		this.setSize(380, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(schermata,"Center");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}