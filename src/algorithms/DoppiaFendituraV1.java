/**
 * 
 */
package algorithms;


import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.math3.complex.Complex;

import gates.HGate;
import gates.LambdaGate;
import gui.*;
import measurement.Measure;
import measurement.Exceptions.NotValidResult;
import qubit.Qubit;
import qubit.Qubit0;
import qubit.Qubit1;

/**
 * @author Giorgio
 *
 */
public class DoppiaFendituraV1 extends JFrame{

	/**
	 * @param args
	 * @throws NotValidResult 
	 */
	public static void main(String[] args) throws NotValidResult {
		CircuitoVariante1 cv1=new CircuitoVariante1();
		Complex c1=new Complex(1.0,0.0);
		Complex c2=new Complex(0.0,0.0);
		Qubit qin=new Qubit1();
		qin.setQubit(c2, c1);
		JOptionPane.showMessageDialog(null, "Lo stato iniziale è "+qin.getQubit()[0]+" "+qin.getQubit()[1], "Stato iniziale", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Lo stato iniziale è "+qin.getQubit()[0]+" "+qin.getQubit()[1]);
		HGate H1=new HGate(qin);
		Qubit q1=H1.operation(qin);
		JOptionPane.showMessageDialog(null, "Lo stato dopo la prima porta Hadamard è "+q1.getQubit()[0]+" "+q1.getQubit()[1], "Stato dopo la prima H", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Lo stato dopo la prima porta Hadamard è "+q1.getQubit()[0]+" "+q1.getQubit()[1]);
		//Il valore di fi per la porta Lambda lo facciamo scegliere all'utente
		//Per farlo si usa la classe Scanner
		//Scanner input1=new Scanner(System.in);
		//System.out.println("Inserisci il valore della fi");
		//double fi=input1.nextDouble();
		double fi=Double.parseDouble(JOptionPane.showInputDialog("Inserisci il valore della fi"));
		fi=fi*Math.PI;
		JOptionPane.showMessageDialog(null, "Il valore di fi è "+fi, "Valore della fi", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Il valore di fi è "+fi);
		LambdaGate lambda=new LambdaGate(q1,fi);
		Qubit q2=lambda.operation(q1, fi);
		JOptionPane.showMessageDialog(null, "Lo stato dopo la porta Lambda è "+q2.getQubit()[0]+" "+q2.getQubit()[1], "Stato dopo la porta Lambda", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Lo stato dopo la porta Lambda è "+q2.getQubit()[0]+" "+q2.getQubit()[1]);
		HGate H2=new HGate(q2);
		Qubit qfin=H2.operation(q2);
		JOptionPane.showMessageDialog(null, "Lo stato dopo la seconda porta Hadamard è "+qfin.getQubit()[0]+" "+qfin.getQubit()[1], "Stato dopo la seconda porta H", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Lo stato dopo la seconda porta Hadamard è "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		//anche la scelta si fa inserire all'utente
		//System.out.println("Inserisci la scelta");
		//Scanner input2=new Scanner(System.in);
		//boolean scelta=input2.nextBoolean();
		boolean scelta=Boolean.parseBoolean(JOptionPane.showInputDialog("Inserisci la scelta (true->base computazionale, false->simulazione)"));
		JOptionPane.showMessageDialog(null, "Scelta è "+scelta, "Scelta", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Scelta è "+scelta);
		//boolean scelta=true;
		Measure misura= new Measure(scelta);
		misura.setScelta(scelta);
		misura=misura.inizializzazione(qfin);
		Qubit res=misura.misura(scelta);
		JOptionPane.showMessageDialog(null, "Il risultato finale è "+res.getQubit()[0]+" "+res.getQubit()[1], "Risultato finale", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Il risultato finale è "+res.getQubit()[0]+" "+res.getQubit()[1]);

	}
}
