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
import gui.CircuitoQE;
import gui.CircuitoQEacceso;
import gui.CircuitoQEspento;
import gui.CircuitoVariante2;
import measurement.Measure;
import measurement.Exceptions.NotValidResult;
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
public class QuantumEraser {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NotValidResult{
		CircuitoQE qe=new CircuitoQE();
		//Inizializziamo il QR iniziale
		QuantumRegister QR=new QuantumRegister();
		ArrayList<Qubit> elements=new ArrayList<Qubit>();
		Qubit q1=new Qubit1();
		Qubit q2=new Qubit0();
		Qubit q3=new Qubit0();
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		q1.setQubit(c0,c1);
		q2.setQubit(c1, c0);
		q3.setQubit(c1, c0);
		elements.add(q1);
		elements.add(q2);
		elements.add(q3);
		QR.setQuantumReg(elements);
		JOptionPane.showMessageDialog(null, "Il primo qubit del QR iniziale è "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1], "Stato iniziale primo qubit", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Il secondo qubit del QR iniziale è "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1], "Stato iniziale secondo qubit", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Il terzo qubit del QR iniziale è "+QR.getQubit(2).getQubit()[0]+" "+QR.getQubit(2).getQubit()[1], "Stato iniziale terzo qubit", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Il primo qubit del QR iniziale è "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1]);
		//System.out.println("Il secondo qubit del QR iniziale è "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1]);
		//System.out.println("Il terzo qubit del QR iniziale è "+QR.getQubit(2).getQubit()[0]+" "+QR.getQubit(2).getQubit()[1]);
		//Applichiamo la prima porta Hadamard sul qubit q1
		HGate H1=new HGate(QR.getQubit(0));
		ArrayList<Qubit> primaH=new ArrayList<Qubit>();
		primaH.add(QR.getQubit(0));
		primaH.addAll(H1.operationalt(QR.getQubit(0)));
		primaH.remove(0);
		JOptionPane.showMessageDialog(null, "Dopo la prima porta H abbiamo "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1], "Stato dopo la prima porta H", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la prima porta H abbiamo "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1]);
		//introduciamo due liste di supporto senza coefficienti, che ci serviranno dopo
		ArrayList<Qubit> copy=new ArrayList<Qubit>();
		copy.add(q2);
		copy.add(q1);
		ArrayList<Qubit> copy2=new ArrayList<Qubit>();
		copy2.add(q2);
		copy2.add(q1);
		///Applichiamo la prima porta CNOT controllo primo qubit, target il secondo
		CNOTGate CNOT1=new CNOTGate(primaH, QR.getQubit(1));
		ArrayList<Qubit> primaC1=new ArrayList<Qubit>();
		primaC1.add(QR.getQubit(0));
		primaC1.addAll(CNOT1.operation(primaH, QR.getQubit(1)));
		primaC1.remove(0);
		JOptionPane.showMessageDialog(null, "Dopo la prima porta CNOT il target è diventato "+primaC1.get(0).getQubit()[0]+" "+primaC1.get(0).getQubit()[1]+" e "+primaC1.get(1).getQubit()[0]+" "+primaC1.get(1).getQubit()[1], "Target dopo la prima porta CNOT", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la prima porta CNOT il target è diventato "+primaC1.get(0).getQubit()[0]+" "+primaC1.get(0).getQubit()[1]+" e "+primaC1.get(1).getQubit()[0]+" "+primaC1.get(1).getQubit()[1]);
		///Applichiamo la seconda porta CNOT controllo secondo qubit, target il terzo
		CNOTGate CNOT2=new CNOTGate(primaC1, QR.getQubit(2));
		ArrayList<Qubit> primaC2=new ArrayList<Qubit>();
		primaC2.add(QR.getQubit(0));
		primaC2.addAll(CNOT2.operation(primaC1, QR.getQubit(2)));
		primaC2.remove(0);
		JOptionPane.showMessageDialog(null, "Dopo la seconda porta CNOT il target è diventato "+primaC2.get(0).getQubit()[0]+" "+primaC2.get(0).getQubit()[1]+" e "+primaC2.get(1).getQubit()[0]+" "+primaC2.get(1).getQubit()[1], "Target dopo la seconda porta CNOT", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la seconda porta CNOT il target è diventato "+primaC2.get(0).getQubit()[0]+" "+primaC2.get(0).getQubit()[1]+" e "+primaC2.get(1).getQubit()[0]+" "+primaC2.get(1).getQubit()[1]);
		///Applichiamo la terza porta CNOT controllo terzo qubit, target il secondo
		CNOTGate CNOT3=new CNOTGate(primaC2, primaC1);
		ArrayList<Qubit> primaC3=new ArrayList<Qubit>();
		primaC3.add(QR.getQubit(0));
		primaC3.addAll(CNOT3.operation(primaC2, primaC1));
		primaC3.remove(0);
		JOptionPane.showMessageDialog(null, "Dopo la terza porta CNOT il target è diventato "+primaC3.get(0).getQubit()[0]+" "+primaC3.get(0).getQubit()[1]+" e "+primaC3.get(1).getQubit()[0]+" "+primaC3.get(1).getQubit()[1], "Target dopo la terza porta CNOT", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Dopo la terza porta CNOT il target è diventato "+primaC3.get(0).getQubit()[0]+" "+primaC3.get(0).getQubit()[1]+" e "+primaC3.get(1).getQubit()[0]+" "+primaC3.get(1).getQubit()[1]);
		//A questo punto il resto dell'algoritmo cambia
		//in base a che il dispositivo sia acceso o spento
		//Lo facciamo con un booleano
		//true->acceso
		//false->spento
		boolean dispositivo=Boolean.parseBoolean(JOptionPane.showInputDialog("Il dispositivo è acceso(true) o spento (false)?"));
		JOptionPane.showMessageDialog(null, "Il dispositivo è "+dispositivo, "Dispositivo", JOptionPane.INFORMATION_MESSAGE);
		//boolean dispositivo=false;
		if(dispositivo==true){
			CircuitoQEacceso qeacceso=new CircuitoQEacceso();
			//caso in cui il dispositivo è acceso
			//Applichiamo la porta Lambda sul primo qubit
			double fi=Double.parseDouble(JOptionPane.showInputDialog("Inserisci il valore della fi"));
			fi=fi*Math.PI;
			JOptionPane.showMessageDialog(null, "Il valore di fi è "+fi, "Valore della fi", JOptionPane.INFORMATION_MESSAGE);
			LambdaGate lambda=new LambdaGate(primaH,fi);
			primaH=lambda.operation(primaH, fi);
			JOptionPane.showMessageDialog(null, "Dopo la Porta Lambda il primo stato è diventato \n"+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" \n"+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1], "Primo stato dopo la porta Lambda", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("Dopo la porta Lambda il primo stato è "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1]);
			Complex I =new Complex(0.0,1.0);
			Complex coefficiente1= (I.multiply(fi/2)).exp();
			Complex coefficiente2=(I.multiply(-1).multiply(fi/2)).exp();
			double coefficiente3=1.0/2.0;
			//Applichiamo la seconda porta Hadamard
			ArrayList<Qubit> secondaH=new ArrayList<Qubit>();
			secondaH.add(QR.getQubit(0));
			HGate H2=new HGate(copy);
			secondaH.addAll(H2.operation(copy));
			secondaH.remove(0);
			JOptionPane.showMessageDialog(null, "La seconda porta H fa avere \n"+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]+" \n"+secondaH.get(1).getQubit()[0]+" "+secondaH.get(1).getQubit()[1]+" \n"+secondaH.get(2).getQubit()[0]+" "+secondaH.get(2).getQubit()[1]+" \n"+secondaH.get(3).getQubit()[0]+" "+secondaH.get(3).getQubit()[1], "La seconda porta H porta ad avere", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("La seconda porta H fa avere "+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]+" "+secondaH.get(1).getQubit()[0]+" "+secondaH.get(1).getQubit()[1]+" "+secondaH.get(2).getQubit()[0]+" "+secondaH.get(2).getQubit()[1]+" "+secondaH.get(3).getQubit()[0]+" "+secondaH.get(3).getQubit()[1]);
			//Sistemiamo gli stati per poi effettuare la misurazione
			ArrayList<Qubit> qprem=new ArrayList<Qubit>();
			Qubit q4=new Qubit1();
			Qubit q5=new Qubit0();
			Qubit q6=new Qubit1();
			Qubit q7=new Qubit0();
			q4.setQubit(c0,c1);
			q5.setQubit(c1, c0);
			q6.setQubit(c0, c1);
			q7.setQubit(c1, c0);
			qprem.add(q5);
			qprem.add(q4);
			qprem.add(q7);
			qprem.add(q6);
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
			//entrano in gioco i prodotti tensore
			QubitOperations op=new QubitOperations();
			Complex[] v1=new Complex[qprem.get(0).getQubit().length*primaC2.get(0).getQubit().length];
			StatoMultiplo sd1=new StatoMultiplo(v1);
			sd1=op.TP(qprem.get(0), primaC2.get(0));
			Complex[] v2=new Complex[qprem.get(1).getQubit().length*primaC2.get(0).getQubit().length];
			StatoMultiplo sd2=new StatoMultiplo(v2);
			sd2=op.TP(qprem.get(1), primaC2.get(0));
			Complex[] v3=new Complex[qprem.get(2).getQubit().length*primaC2.get(1).getQubit().length];
			StatoMultiplo sd3=new StatoMultiplo(v3);
			sd3=op.TP(qprem.get(2), primaC2.get(1));
			Complex[] v4=new Complex[qprem.get(3).getQubit().length*primaC2.get(1).getQubit().length];
			StatoMultiplo sd4=new StatoMultiplo(v4);
			sd4=op.TP(qprem.get(3), primaC2.get(1));
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
			//Ora sd1 è lo stato doppio su cui effettuare la misura
			JOptionPane.showMessageDialog(null, "Stato finale prima della misura \n"+sd1.getElementAt(0)+" \n"+sd1.getElementAt(1)+" \n"+sd1.getElementAt(2)+" \n"+sd1.getElementAt(3), "Stato finale prima della misura", JOptionPane.INFORMATION_MESSAGE);
			//boolean scelta=true;
			boolean scelta=Boolean.parseBoolean(JOptionPane.showInputDialog("Inserisci la scelta (true->base computazione, false->simulazione)"));
			JOptionPane.showMessageDialog(null, "Scelta è "+scelta, "Scelta", JOptionPane.INFORMATION_MESSAGE);
			Measure misura= new Measure(scelta);
			misura.setScelta(scelta);
			misura=misura.inizializzazioneestesa(sd1);
			StatoMultiplo res=misura.misuraestesa(scelta);
			JOptionPane.showMessageDialog(null, "Il risultato finale è "+res.getElementAt(0)+" "+res.getElementAt(1)+" "+res.getElementAt(2)+" "+res.getElementAt(3), "Risultato finale", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("Il risultato finale è "+res.getElementAt(0)+" "+res.getElementAt(1)+" "+res.getElementAt(2)+" "+res.getElementAt(3));
			
		}else{
			CircuitoQEspento qespento=new CircuitoQEspento();
			//caso in cui il dispositivo è spento
			//Applichiamo la porta Lambda sul primo qubit
			double fi=Double.parseDouble(JOptionPane.showInputDialog("Inserisci il valore della fi"));
			fi=fi*Math.PI;
			JOptionPane.showMessageDialog(null, "Il valore di fi è "+fi, "Valore della fi", JOptionPane.INFORMATION_MESSAGE);
			LambdaGate lambda=new LambdaGate(primaH,fi);
			primaH=lambda.operation(primaH, fi);
			JOptionPane.showMessageDialog(null, "Dopo la Porta Lambda il primo stato è diventato \n"+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" \n"+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1], "Primo stato dopo la porta Lambda", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("Dopo la porta Lambda il primo stato è "+primaH.get(0).getQubit()[0]+" "+primaH.get(0).getQubit()[1]+" "+primaH.get(1).getQubit()[0]+" "+primaH.get(1).getQubit()[1]);
			Complex I =new Complex(0.0,1.0);
			Complex coefficiente1= (I.multiply(fi/2)).exp();
			Complex coefficiente2=(I.multiply(-1).multiply(fi/2)).exp();
			double coefficiente3=1.0/Math.sqrt(2);
			//Applichiamo la seconda porta Hadamard sul primo qubit
			ArrayList<Qubit> secondaH=new ArrayList<Qubit>();
			secondaH.add(QR.getQubit(0));
			HGate H2=new HGate(copy);
			secondaH.addAll(H2.operation(copy));
			secondaH.remove(0);
			JOptionPane.showMessageDialog(null, "La seconda porta H fa avere \n"+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]+" \n"+secondaH.get(1).getQubit()[0]+" "+secondaH.get(1).getQubit()[1]+" \n"+secondaH.get(2).getQubit()[0]+" "+secondaH.get(2).getQubit()[1]+" \n"+secondaH.get(3).getQubit()[0]+" "+secondaH.get(3).getQubit()[1], "La seconda porta H porta ad avere", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("La seconda porta H fa avere "+secondaH.get(0).getQubit()[0]+" "+secondaH.get(0).getQubit()[1]+" "+secondaH.get(1).getQubit()[0]+" "+secondaH.get(1).getQubit()[1]+" "+secondaH.get(2).getQubit()[0]+" "+secondaH.get(2).getQubit()[1]+" "+secondaH.get(3).getQubit()[0]+" "+secondaH.get(3).getQubit()[1]);
			//Applichiamo la terza porta Hadamart sul secondo qubit
			ArrayList<Qubit> terzaH=new ArrayList<Qubit>();
			terzaH.add(QR.getQubit(0));
			HGate H3=new HGate(copy2);
			terzaH.addAll(H3.operation(copy2));
			terzaH.remove(0);
			JOptionPane.showMessageDialog(null, "La terza porta H fa avere \n"+terzaH.get(0).getQubit()[0]+" "+terzaH.get(0).getQubit()[1]+" \n"+terzaH.get(1).getQubit()[0]+" "+terzaH.get(1).getQubit()[1]+" \n"+terzaH.get(2).getQubit()[0]+" "+terzaH.get(2).getQubit()[1]+" \n"+terzaH.get(3).getQubit()[0]+" "+terzaH.get(3).getQubit()[1], "La terza porta H porta ad avere", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("La terza porta H fa avere "+terzaH.get(0).getQubit()[0]+" "+terzaH.get(0).getQubit()[1]+" "+terzaH.get(1).getQubit()[0]+" "+terzaH.get(1).getQubit()[1]+" "+terzaH.get(2).getQubit()[0]+" "+terzaH.get(2).getQubit()[1]+" "+terzaH.get(3).getQubit()[0]+" "+terzaH.get(3).getQubit()[1]);
			//Adesso vanno sistemati gli stati per fare la misura
			ArrayList<Qubit> qprem=new ArrayList<Qubit>();
			Qubit q4=new Qubit1();
			Qubit q5=new Qubit0();
			Qubit q6=new Qubit1();
			Qubit q7=new Qubit0();
			q4.setQubit(c0,c1);
			q5.setQubit(c1, c0);
			q6.setQubit(c0, c1);
			q7.setQubit(c1, c0);
			qprem.add(q5);
			qprem.add(q4);
			qprem.add(q7);
			qprem.add(q6);
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
			ArrayList<Qubit> qprem2=new ArrayList<Qubit>();
			qprem2.add(q5);
			qprem2.add(q4);
			qprem2.add(q7);
			qprem2.add(q6);
			//entrano in gioco i prodotti tensore
			QubitOperations op=new QubitOperations();
			Complex[] v1=new Complex[qprem.get(0).getQubit().length*qprem2.get(0).getQubit().length];
			StatoMultiplo sd1=new StatoMultiplo(v1);
			sd1=op.TP(qprem.get(0), qprem2.get(0));
			Complex[] v2=new Complex[qprem.get(0).getQubit().length*qprem2.get(1).getQubit().length];
			StatoMultiplo sd2=new StatoMultiplo(v2);
			sd2=op.TP(qprem.get(0), qprem2.get(1));
			Complex[] v3=new Complex[qprem.get(1).getQubit().length*qprem2.get(0).getQubit().length];
			StatoMultiplo sd3=new StatoMultiplo(v3);
			sd3=op.TP(qprem.get(1), qprem2.get(0));
			Complex[] v4=new Complex[qprem.get(1).getQubit().length*qprem2.get(1).getQubit().length];
			StatoMultiplo sd4=new StatoMultiplo(v4);
			sd4=op.TP(qprem.get(1), qprem2.get(1));
			Complex[] v5=new Complex[qprem.get(2).getQubit().length*qprem2.get(2).getQubit().length];
			StatoMultiplo sd5=new StatoMultiplo(v5);
			sd5=op.TP(qprem.get(2), qprem2.get(2));
			Complex[] v6=new Complex[qprem.get(2).getQubit().length*qprem2.get(3).getQubit().length];
			StatoMultiplo sd6=new StatoMultiplo(v6);
			sd6=op.TP(qprem.get(2), qprem2.get(3));
			Complex[] v7=new Complex[qprem.get(3).getQubit().length*qprem2.get(2).getQubit().length];
			StatoMultiplo sd7=new StatoMultiplo(v7);
			sd7=op.TP(qprem.get(3), qprem2.get(2));
			Complex[] v8=new Complex[qprem.get(3).getQubit().length*qprem2.get(3).getQubit().length];
			StatoMultiplo sd8=new StatoMultiplo(v8);
			sd8=op.TP(qprem.get(3), qprem2.get(3));
			sd1.setElementAt(sd1.getElementAt(0).add(sd2.getElementAt(0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd2.getElementAt(1)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd2.getElementAt(2)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd2.getElementAt(3)),3);
			sd1.setElementAt(sd1.getElementAt(0).add(sd3.getElementAt(0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd3.getElementAt(1)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd3.getElementAt(2)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd3.getElementAt(3)),3);
			sd1.setElementAt(sd1.getElementAt(0).add(sd4.getElementAt(0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd4.getElementAt(1)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd4.getElementAt(2)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd4.getElementAt(3)),3);
			sd1.setElementAt(sd1.getElementAt(0).add(sd5.getElementAt(0).multiply(-1.0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd5.getElementAt(1).multiply(-1.0)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd5.getElementAt(2).multiply(-1.0)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd5.getElementAt(3).multiply(-1.0)),3);
			sd1.setElementAt(sd1.getElementAt(0).add(sd6.getElementAt(0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd6.getElementAt(1)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd6.getElementAt(2)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd6.getElementAt(3)),3);
			sd1.setElementAt(sd1.getElementAt(0).add(sd7.getElementAt(0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd7.getElementAt(1)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd7.getElementAt(2)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd7.getElementAt(3)),3);
			sd1.setElementAt(sd1.getElementAt(0).add(sd8.getElementAt(0).multiply(-1.0)),0);
			sd1.setElementAt(sd1.getElementAt(1).add(sd8.getElementAt(1).multiply(-1.0)),1);
			sd1.setElementAt(sd1.getElementAt(2).add(sd8.getElementAt(2).multiply(-1.0)),2);
			sd1.setElementAt(sd1.getElementAt(3).add(sd8.getElementAt(3).multiply(-1.0)),3);
			//Ora sd1 è lo stato doppio su cui effettuare la misura
			JOptionPane.showMessageDialog(null, "Stato finale prima della misura \n"+sd1.getElementAt(0)+" \n"+sd1.getElementAt(1)+" \n"+sd1.getElementAt(2)+" \n"+sd1.getElementAt(3), "Stato finale prima della misura", JOptionPane.INFORMATION_MESSAGE);
			//boolean scelta=true;
			boolean scelta=Boolean.parseBoolean(JOptionPane.showInputDialog("Inserisci la scelta (true->base computazionale, false->simulazione)"));
			JOptionPane.showMessageDialog(null, "Scelta è "+scelta, "Scelta", JOptionPane.INFORMATION_MESSAGE);
			Measure misura= new Measure(scelta);
			misura.setScelta(scelta);
			misura=misura.inizializzazioneestesa(sd1);
			StatoMultiplo res=misura.misuraestesa(scelta);
			JOptionPane.showMessageDialog(null, "Il risultato finale è "+res.getElementAt(0)+" "+res.getElementAt(1)+" "+res.getElementAt(2)+" "+res.getElementAt(3), "Risultato finale", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("Il risultato finale è "+res.getElementAt(0)+" "+res.getElementAt(1)+" "+res.getElementAt(2)+" "+res.getElementAt(3));
		}

	}

}
