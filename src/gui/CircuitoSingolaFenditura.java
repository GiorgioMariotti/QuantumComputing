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
public class CircuitoSingolaFenditura extends JFrame{
	String circuito="immagini\\singolafenditura.jpg";
	Pannello schermata=new Pannello(circuito);
	public CircuitoSingolaFenditura(){
		this.setTitle("Singola fenditura");
		this.setSize(600, 90);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(schermata,"Center");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
