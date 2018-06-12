/**
 * 
 */
package algorithms;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.math3.complex.Complex;

import gates.CNOTGate;
import gates.HGate;
import gates.LambdaGate;
import gui.CircuitoVariante1;
import gui.CircuitoVariante2;
import measurement.Measure;
import measurement.Exceptions.NotValidResult;
import operations.QRegisterOperations;
import operations.QubitOperations;
import quantumRegister.QuantumRegister;
import qubit.Qubit;
import qubit.Qubit0;
import qubit.Qubit1;
import qubit.StatoMultiplo;

/**
 * @author Giorgio
 *
 */
public class DoppiaFendituraV2 {

	/**
	 * @param args+
	 */
	public static void main(String[] args) throws NotValidResult{
		CircuitoVariante2 cv2=new CircuitoVariante2();
		QuantumRegister QR=new QuantumRegister();
		ArrayList<Qubit> elements=new ArrayList<Qubit>();
		Qubit q1=new Qubit1();
		Qubit q2=new Qubit0();
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		q1.setQubit(c0,c1);
		q2.setQubit(c1, c0);
		elements.add(q1);
		elements.add(q2);
		QR.setQuantumReg(elements);
		JOptionPane.showMessageDialog(null, "Il primo qubit del QR iniziale è "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1], "Stato iniziale primo qubit", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Il secondo qubit del QR iniziale è "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1], "Stato iniziale secondo qubit", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Il primo qubit del QR iniziale è "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1]);
		//System.out.println("Il secondo qubit del QR iniziale è "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1]);
		//Applichiamo la prima porta Hadamard sul qubit q1
		HGate H1=new HGate(QR.getQubit(0));
		ArrayList<Qubit> primaH=new ArrayList<Qubit>();
		primaH.add(QR.getQubit(0));
		primaH.addAll(H1.operationalt(QR.getQubit(0)));
		primaH.remove(0);
		JOptionPane.showMessageDialog(null, "Dopo la prima porta H abbiamo "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1], "Stato dopo la prima porta H", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la prima porta H abbiamo "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1]);
		//introduciamo una lista di supporto senza coefficienti, che ci servirà dopo
		ArrayList<Qubit> copy=new ArrayList<Qubit>();
		copy.add(q2);
		copy.add(q1);
		//System.out.println("In copy abbiamo "+copy.get(0).getQubit()[0]+" "+copy.get(0).getQubit()[1]+" e "+copy.get(1).getQubit()[0]+" "+copy.get(1).getQubit()[1]);
		///Applichiamo la porta CNOT
		CNOTGate CNOT=new CNOTGate(primaH, QR.getQubit(1));
		ArrayList<Qubit> primaC=new ArrayList<Qubit>();
		primaC.add(QR.getQubit(0));
		primaC.addAll(CNOT.operation(primaH, QR.getQubit(1)));
		primaC.remove(0);
		JOptionPane.showMessageDialog(null, "Dopo la Porta CNOT il target è diventato "+primaC.get(0).getQubit()[0]+" "+primaC.get(0).getQubit()[1]+" e "+primaC.get(1).getQubit()[0]+" "+primaC.get(1).getQubit()[1], "Target dopo la porta CNOT", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la Porta CNOT abbiamo "+primaC.get(0).getQubit()[0]+" "+primaC.get(0).getQubit()[1]+" e "+primaC.get(1).getQubit()[0]+" "+primaC.get(1).getQubit()[1]);
		//Applichiamo la porta Lambda
		double fi=Double.parseDouble(JOptionPane.showInputDialog("Inserisci il valore della fi"));
		fi=fi*Math.PI;
		JOptionPane.showMessageDialog(null, "Il valore di fi è "+fi, "Valore della fi", JOptionPane.INFORMATION_MESSAGE);
		LambdaGate lambda=new LambdaGate(primaH,fi);
		primaH=lambda.operation(primaH, fi);
		JOptionPane.showMessageDialog(null, "Dopo la Porta Lambda il primo stato è diventato \n"+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" \n"+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1], "Primo stato dopo la porta Lambda", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("primaH ora è "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1]);
		Complex I =new Complex(0.0,1.0);
		Complex coefficiente1= (I.multiply(fi/2)).exp();
		Complex coefficiente2=(I.multiply(-1).multiply(fi/2)).exp();
		double coefficiente3=1.0/2.0;
		//Applichiamo la seconda porta Hadamard
		ArrayList<Qubit> secondaH=new ArrayList<Qubit>();
		secondaH.add(QR.getQubit(0));
		//System.out.println("In secondaH abbiamo "+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]);
		HGate H2=new HGate(copy);
		//System.out.println("In copy abbiamo "+copy.get(0).getQubit()[0]+" "+copy.get(0).getQubit()[1]+" e "+copy.get(1).getQubit()[0]+" "+copy.get(1).getQubit()[1]);
		secondaH.addAll(H2.operation(copy));
		secondaH.remove(0);
		JOptionPane.showMessageDialog(null, "La seconda porta H fa avere \n"+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]+" \n"+secondaH.get(1).getQubit()[0]+" "+secondaH.get(1).getQubit()[1]+" \n"+secondaH.get(2).getQubit()[0]+" "+secondaH.get(2).getQubit()[1]+" \n"+secondaH.get(3).getQubit()[0]+" "+secondaH.get(3).getQubit()[1], "La seconda porta H porta ad avere", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la seconda porta H secondaH è "+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]+" "+secondaH.get(1).getQubit()[0]+" "+secondaH.get(1).getQubit()[1]+" "+secondaH.get(2).getQubit()[0]+" "+secondaH.get(2).getQubit()[1]+" "+secondaH.get(3).getQubit()[0]+" "+secondaH.get(3).getQubit()[1]);
		//Sistemiamo gli stati per poi effettuare la misurazione
		ArrayList<Qubit> qprem=new ArrayList<Qubit>();
		Qubit q3=new Qubit1();
		Qubit q4=new Qubit0();
		Qubit q5=new Qubit1();
		Qubit q6=new Qubit0();
		q3.setQubit(c0,c1);
		q4.setQubit(c1, c0);
		q5.setQubit(c0, c1);
		q6.setQubit(c1, c0);
		qprem.add(q4);
		qprem.add(q3);
		qprem.add(q6);
		qprem.add(q5);
		qprem.get(0).getQubit()[0]=qprem.get(0).getQubit()[0].multiply(coefficiente3);
		qprem.get(0).getQubit()[1]=qprem.get(0).getQubit()[1].multiply(coefficiente3);
		qprem.get(1).getQubit()[0]=qprem.get(1).getQubit()[0].multiply(coefficiente3);
		qprem.get(1).getQubit()[1]=qprem.get(1).getQubit()[1].multiply(coefficiente3);
		qprem.get(2).getQubit()[0]=qprem.get(2).getQubit()[0].multiply(coefficiente3);
		qprem.get(2).getQubit()[1]=qprem.get(2).getQubit()[1].multiply(coefficiente3);
		qprem.get(3).getQubit()[0]=qprem.get(3).getQubit()[0].multiply(coefficiente3);
		qprem.get(3).getQubit()[1]=qprem.get(3).getQubit()[1].multiply(coefficiente3);
		qprem.get(0).getQubit()[0]=qprem.get(0).getQubit()[0].multiply(coefficiente1);
		qprem.get(0).getQubit()[1]=qprem.get(0).getQubit()[1].multiply(coefficiente1);
		qprem.get(1).getQubit()[0]=qprem.get(1).getQubit()[0].multiply(coefficiente1);
		qprem.get(1).getQubit()[1]=qprem.get(1).getQubit()[1].multiply(coefficiente1);
		qprem.get(2).getQubit()[0]=qprem.get(2).getQubit()[0].multiply(coefficiente2);
		qprem.get(2).getQubit()[1]=qprem.get(2).getQubit()[1].multiply(coefficiente2);
		qprem.get(3).getQubit()[0]=qprem.get(3).getQubit()[0].multiply(coefficiente2);
		qprem.get(3).getQubit()[1]=qprem.get(3).getQubit()[1].multiply(coefficiente2);
		//System.out.println("Prima della misura abbiamo "+qprem.get(0).getQubit()[0]+" "+qprem.get(0).getQubit()[1]+" "+qprem.get(1).getQubit()[0]+" "+qprem.get(1).getQubit()[1]+" "+qprem.get(2).getQubit()[0]+" "+qprem.get(2).getQubit()[1]+" "+qprem.get(3).getQubit()[0]+" "+qprem.get(3).getQubit()[1]);
		//entrano in gioco i prodotti tensore
		QubitOperations op=new QubitOperations();
		Complex[] v1=new Complex[qprem.get(0).getQubit().length*primaC.get(0).getQubit().length];
		StatoMultiplo sd1=new StatoMultiplo(v1);
		sd1=op.TP(qprem.get(0), primaC.get(0));
		//System.out.println("La dimensione di sd1 è "+sd1.getSize());
		//System.out.println("In sd1 abbiamo "+sd1.getElementAt(0)+" "+sd1.getElementAt(1)+" "+sd1.getElementAt(2)+" "+sd1.getElementAt(3));
		Complex[] v2=new Complex[qprem.get(1).getQubit().length*primaC.get(0).getQubit().length];
		StatoMultiplo sd2=new StatoMultiplo(v2);
		sd2=op.TP(qprem.get(1), primaC.get(0));
		//System.out.println("La dimensione di sd2 è "+sd2.getSize());
		//System.out.println("In sd2 abbiamo "+sd2.getElementAt(0)+" "+sd2.getElementAt(1)+" "+sd2.getElementAt(2)+" "+sd2.getElementAt(3));
		Complex[] v3=new Complex[qprem.get(2).getQubit().length*primaC.get(1).getQubit().length];
		StatoMultiplo sd3=new StatoMultiplo(v3);
		sd3=op.TP(qprem.get(2), primaC.get(1));
		//System.out.println("La dimensione di sd3 è "+sd3.getSize());
		//System.out.println("In sd3 abbiamo "+sd3.getElementAt(0)+" "+sd3.getElementAt(1)+" "+sd3.getElementAt(2)+" "+sd3.getElementAt(3));
		Complex[] v4=new Complex[qprem.get(3).getQubit().length*primaC.get(1).getQubit().length];
		StatoMultiplo sd4=new StatoMultiplo(v4);
		sd4=op.TP(qprem.get(3), primaC.get(1));
		//System.out.println("La dimensione di sd4 è "+sd4.getSize());
		//System.out.println("In sd4 abbiamo "+sd4.getElementAt(0)+" "+sd4.getElementAt(1)+" "+sd4.getElementAt(2)+" "+sd4.getElementAt(3));
		//Adesso vanno messi in un unico stato doppio su cui poi fare la misura
		sd1.setElementAt(sd1.getElementAt(0).add(sd2.getElementAt(0)),0);
		sd1.setElementAt(sd1.getElementAt(1).add(sd2.getElementAt(1)),1);
		sd1.setElementAt(sd1.getElementAt(2).add(sd2.getElementAt(2)),2);
		sd1.setElementAt(sd1.getElementAt(3).add(sd2.getElementAt(3)),3);
		sd1.setElementAt(sd1.getElementAt(0).add(sd3.getElementAt(0).multiply(-1.0)),0);
		sd1.setElementAt(sd1.getElementAt(1).add(sd3.getElementAt(1).multiply(-1.0)),1);
		sd1.setElementAt(sd1.getElementAt(2).add(sd3.getElementAt(2).multiply(-1.0)),2);
		sd1.setElementAt(sd1.getElementAt(3).add(sd3.getElementAt(3).multiply(-1.0)),3);
		sd1.setElementAt(sd1.getElementAt(0).add(sd4.getElementAt(0)),0);
		sd1.setElementAt(sd1.getElementAt(1).add(sd4.getElementAt(1)),1);
		sd1.setElementAt(sd1.getElementAt(2).add(sd4.getElementAt(2)),2);
		sd1.setElementAt(sd1.getElementAt(3).add(sd4.getElementAt(3)),3);
		JOptionPane.showMessageDialog(null, "Stato finale prima della misura \n"+sd1.getElementAt(0)+" \n"+sd1.getElementAt(1)+" \n"+sd1.getElementAt(2)+" \n"+sd1.getElementAt(3), "Stato finale prima della misura", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("In sd1 ora abbiamo "+sd1.getElementAt(0)+" "+sd1.getElementAt(1)+" "+sd1.getElementAt(2)+" "+sd1.getElementAt(3));
		//Ora sd1 è lo stato doppio su cui effettuare la misura
		//boolean scelta=true;
		boolean scelta=Boolean.parseBoolean(JOptionPane.showInputDialog("Inserisci la scelta (true->base computazionale, false->simulazione)"));
		JOptionPane.showMessageDialog(null, "Scelta è "+scelta, "Scelta", JOptionPane.INFORMATION_MESSAGE);
		Measure misura= new Measure(scelta);
		misura.setScelta(scelta);
		misura=misura.inizializzazioneestesa(sd1);
		StatoMultiplo res=misura.misuraestesa(scelta);
		//System.out.println("La dimensione di res è "+res.getSize());
		JOptionPane.showMessageDialog(null, "Il risultato finale è "+res.getElementAt(0)+" "+res.getElementAt(1)+" "+res.getElementAt(2)+" "+res.getElementAt(3), "Risultato finale", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Il risultato è "+res.getElementAt(0)+" "+res.getElementAt(1)+" "+res.getElementAt(2)+" "+res.getElementAt(3));
		

	}

}
