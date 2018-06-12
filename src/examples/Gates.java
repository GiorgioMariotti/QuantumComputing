/**
 * Classe che include alcuni esempi
 * di utilizzo delle porte logiche
 */
package examples;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

import gates.*;
import quantumRegister.QuantumRegister;
import qubit.Qubit;
import qubit.Qubit0;
import qubit.Qubit1;
import qubit.QubitMinus;
import qubit.QubitPlus;

/**
 * @author Giorgio
 *
 */
public class Gates {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Qubit q1=new Qubit1();
		Qubit q2=new Qubit0();
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		q1.setQubit(c0,c1);
		q2.setQubit(c1, c0);
		//Porta H
		
		//Passandogli come input |1>, ci aspettiamo come risultato
		//(|0>-|1>)/sqrt(2)
		//ovvero nella nostra rappresentazione
		//(1/sqrt(2),0.0)(-1/sqrt(2),0.0)
		HGate H1=new HGate(q1);
		Qubit qfin=H1.operation(q1);
		System.out.println("H con input |1> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |0>, ci aspettiamo come risultato
		//(|0>+|1>)/sqrt(2) ovvero
		//(1/sqrt(2),0.0)(1/sqrt(2),0.0)
		H1=new HGate(q2);
		qfin=H1.operation(q2);
		System.out.println("H con input |0> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |+>, ci aspettiamo come risultato
		//|0> al netto di approssimazioni
		Complex cplus=new Complex(1.0/Math.sqrt(2.0),0.0);
		Qubit qplus=new QubitPlus();
		qplus.setQubit(cplus, cplus);
		H1=new HGate(qplus);
		qfin=H1.operation(qplus);
		System.out.println("H con input |+> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
				
		//Passandogli come input |->, ci aspettiamo come risultato
		//|1> al netto di approssimazioni
		Complex cminus=new Complex(-1.0/Math.sqrt(2.0),0.0);
		Qubit qminus=new QubitMinus();
		qminus.setQubit(cplus, cminus);
		H1=new HGate(qminus);
		qfin=H1.operation(qminus);
		System.out.println("H con input |-> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);	
		
		//Volendo invece tenere gli stati separati, passandogli |1>
		//ci aspettiamo (|0>/sqrt(2))(|1>/sqrt(2)) ovvero
		//(1/sqrt(2),0.0)(0.0,0.0)(0.0,0.0)(1/sqrt(2),0.0)
		//ricordiamo che il segno di sottrazione tra |0> e |1>
		//va reintrodotto nell'algoritmo in cui si applica la porta
		H1=new HGate(q1);
		ArrayList<Qubit> res=new ArrayList<Qubit>();
		res.add(q1);
		res.addAll(H1.operationalt(q1));
		res.remove(0);
		System.out.println("H con input |1> stati separati "+res.get(0).getQubit()[0]+" "+res.get(0).getQubit()[1]+" "+res.get(1).getQubit()[0]+" "+res.get(1).getQubit()[1]);
		
		//Passandogli |0> ci aspettiamo un risultato analogo
		H1=new HGate(q2);
		res.removeAll(res);
		res.add(q2);
		res.addAll(H1.operationalt(q2));
		res.remove(0);
		System.out.println("H con input |0> stati separati "+res.get(0).getQubit()[0]+" "+res.get(0).getQubit()[1]+" "+res.get(1).getQubit()[0]+" "+res.get(1).getQubit()[1]);
		
		//Adesso vediamo un uso su stati separati
		//passando una lista
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		H1=new HGate(res);
		ArrayList<Qubit> res2=new ArrayList<Qubit>();
		res2.add(q1);
		res2.addAll(H1.operation(res));
		res2.remove(0);
		System.out.println("H con input lista "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]+" "+res2.get(2).getQubit()[0]+" "+res2.get(2).getQubit()[1]+" "+res2.get(3).getQubit()[0]+" "+res2.get(3).getQubit()[1]);
		res.removeAll(res);
		res2.removeAll(res2);
		
		//Porta X
		
		//Passandogli come input |1>, ci aspettiamo come risultato |0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		XGate X=new XGate(q1);
		qfin=X.operation(q1);
		System.out.println("X con input |1> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |0>, ci aspettiamo come risultato |1>
		X=new XGate(q2);
		qfin=X.operation(q2);
		System.out.println("X con input |0> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |+>, ci aspettiamo come risultato
		//(|1>+|0>)/sqrt(2) che equivale di nuovo a |+>
		qplus.setQubit(cplus, cplus);
		X=new XGate(qplus);
		qfin=X.operation(qplus);
		System.out.println("X con input |+> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |->, ci aspettiamo come risultato
		//(|1>-|0>)/sqrt(2)
		//Ovvero (-1/sqrt(2),0.0)(1/sqrt(2),0.0)
		qminus.setQubit(cplus, cminus);
		X=new XGate(qminus);
		qfin=X.operation(qminus);
		System.out.println("X con input |-> "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Porta Lambda
		
		//i valori numerici cambiano a seconda del valore di fi
		//utilizziamo a titolo d'esempio fi=PI
		double fi=Math.PI;
		
		//Passandogli come input |0>
		//ci aspettiamo come risultato e^(i*(fi/2))|0>
		//Ovvero (e^(fi/2),1.0)(0.0,0.0)
		q2.setQubit(c1, c0);
		LambdaGate Lambda=new LambdaGate(q2, fi);
		qfin=Lambda.operation(q2, fi);
		System.out.println("Lambda con input |0> e PI "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |1>
		//ci aspettiamo come risultato e^(-i*(fi/2))|1>
		//Ovvero (0.0,0,0)(e^(fi/2),-1.0)
		q1.setQubit(c0, c1);
		Lambda=new LambdaGate(q1, fi);
		qfin=Lambda.operation(q1, fi);
		System.out.println("Lambda con input |1> e PI "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		//Passandogli come input |+>
		//che ricordiamo corrispondere a (|0>+|1>)/sqrt(2)
		//ci aspettiamo come risultato (e^(i*(fi/2))|0>)/sqrt(2)+(e^(-i*(fi/2))|1>)/sqrt(2)
		//Ovvero (e^(fi/2)/sqrt(2), 1.0/sqrt(2))(e^(fi/2)/sqrt(2), -1.0/sqrt(2))
		qplus.setQubit(cplus, cplus);
		Lambda=new LambdaGate(qplus, fi);
		qfin=Lambda.operation(qplus, fi);
		System.out.println("Lambda con input |+> e PI "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);

		//Passandogli come input |->
		//che ricordiamo corrispondere a (|0>-|1>)/sqrt(2)
		//ci aspettiamo come risultato (e^(i*(fi/2))|0>)/sqrt(2)-(e^(-i*(fi/2))|1>)/sqrt(2)
		//Ovvero (e^(fi/2)/sqrt(2), 1.0/sqrt(2))(-e^(fi/2)/sqrt(2), 1.0/sqrt(2))
		qminus.setQubit(cplus, cminus);
		Lambda=new LambdaGate(qminus, fi);
		qfin=Lambda.operation(qminus, fi);
		System.out.println("Lambda con input |-> e PI "+qfin.getQubit()[0]+" "+qfin.getQubit()[1]);
		
		
		//Passandogli come input una lista |0>,|1>
		//ci aspettiamo come risultato
		//una lista e^(i*(fi/2))|0>, e^(-i*(fi/2))|1> 
		//Ovvero (e^(fi/2),1.0)(0.0,0.0)(0.0,0.0)(e^(fi/2),-1.0)
		res.removeAll(res);
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		Lambda=new LambdaGate(res,fi);
		res2.addAll(Lambda.operation(res, fi));
		System.out.println("Lambda con input |0>,|1> e PI "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input una lista |1>,|0>
		//ci aspettiamo come risultato
		//una lista e^(-i*(fi/2))|1>, e^(i*(fi/2))|0> 
		//Ovvero (0.0,0.0)(e^(fi/2),-1.0)(e^(fi/2),1.0)(0.0,0.0)
		res.removeAll(res);
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		Lambda=new LambdaGate(res,fi);
		res2.addAll(Lambda.operation(res, fi));
		System.out.println("Lambda con input |1>,|0> e PI "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input una lista |0>/sqrt(2),|1>/sqrt(2)
		//ci aspettiamo come risultato
		//una lista (e^(i*(fi/2))|0>)/sqrt(2), (e^(-i*(fi/2))|1>)/sqrt(2) 
		//Ovvero ((e^(fi/2))/sqrt(2),1.0/sqrt(2))(0.0,0.0)(0.0,0.0)((e^(fi/2))/sqrt(2),-1.0/sqrt(2))
		res.removeAll(res);
		q1.setQubit(cplus, c0);
		q2.setQubit(c0, cplus);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		Lambda=new LambdaGate(res,fi);
		res2.addAll(Lambda.operation(res, fi));
		System.out.println("Lambda con input |0>/sqrt(2),|1>/sqrt(2) e PI "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input una lista |1>/sqrt(2),|0>/sqrt(2)
		//ci aspettiamo come risultato
		//una lista (e^(-i*(fi/2))|1>)/sqrt(2), (e^(i*(fi/2))|0>)/sqrt(2) 
		//Ovvero (0.0,0.0)((e^(fi/2))/sqrt(2),-1.0/sqrt(2))((e^(fi/2))/sqrt(2),1.0/sqrt(2))(0.0,0.0)
		res.removeAll(res);
		q1.setQubit(cplus, c0);
		q2.setQubit(c0, cplus);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		Lambda=new LambdaGate(res,fi);
		res2.addAll(Lambda.operation(res, fi));
		System.out.println("Lambda con input |1>/sqrt(2),|0>/sqrt(2) e PI "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Porta CNOT
		
		//Passandogli come input 2 qubit
		//con |0> come controllo e |1> come target
		//ci aspettiamo come risultato un QR
		// con |0> e |1>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		CNOTGate CNOT=new CNOTGate(q2,q1);
		QuantumRegister QR=new QuantumRegister();
		QR.setQuantumReg(res);
		QR=CNOT.operation(q2, q1);
		System.out.println("Elementi QR "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1]+" "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1]);
		
		//Passandogli come input 2 qubit
		//con |0> come controllo e |0> come target
		//ci aspettiamo come risultato un QR
		// con |0> e |0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		CNOT=new CNOTGate(q2,q2);
		QR=new QuantumRegister();
		QR.setQuantumReg(res);
		QR=CNOT.operation(q2, q2);
		System.out.println("Elementi QR "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1]+" "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1]);

		
		//Passandogli come input 2 qubit
		//con |1> come controllo e |0> come target
		//ci aspettiamo come risultato un QR
		// con |1> e |1>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		CNOT=new CNOTGate(q1,q2);
		QR=new QuantumRegister();
		QR.setQuantumReg(res);
		QR=CNOT.operation(q1, q2);
		System.out.println("Elementi QR "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1]+" "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1]);
		
		//Passandogli come input 2 qubit
		//con |1> come controllo e |1> come target
		//ci aspettiamo come risultato un QR
		// con |1> e |0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		CNOT=new CNOTGate(q1,q1);
		QR=new QuantumRegister();
		QR.setQuantumReg(res);
		QR=CNOT.operation(q1, q1);
		System.out.println("Elementi QR "+QR.getQubit(0).getQubit()[0]+" "+QR.getQubit(0).getQubit()[1]+" "+QR.getQubit(1).getQubit()[0]+" "+QR.getQubit(1).getQubit()[1]);
		
		//Passandogli come input
		//come controllo una lista |0>,|1>
		//e target |0>
		//ci aspettiamo in output
		//una lista target |0>, |1>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q2);
		res2.addAll(CNOT.operation(res, q2));
		System.out.println("CNOT target |0> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);

		//Passandogli come input
		//come controllo una lista |0>,|1>
		//e target |1>
		//ci aspettiamo in output
		//una lista target |1>, |0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q1);
		res2.addAll(CNOT.operation(res, q1));
		System.out.println("CNOT target |1> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);

		//Passandogli come input
		//come controllo una lista |1>,|0>
		//e target |0>
		//ci aspettiamo in output
		//una lista target |1>, |0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q2);
		res2.addAll(CNOT.operation(res, q2));
		System.out.println("CNOT target |0> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input
		//come controllo una lista |1>,|0>
		//e target |1>
		//ci aspettiamo in output
		//una lista target |0>, |1>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q1);
		res2.addAll(CNOT.operation(res, q1));
		System.out.println("CNOT target |1> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input
		//come controllo una lista |0>/sqrt(2),|1>/sqrt(2)
		//e target |0>
		//ci aspettiamo in output
		//una lista target |0>/sqrt(2), |1>/sqrt(2)
		qplus.setQubit(c0, cplus);
		q2.setQubit(cplus, c0);
		q1.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q2);
		res.add(qplus);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q1);
		res2.addAll(CNOT.operation(res, q1));
		System.out.println("CNOT target |0> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input
		//come controllo una lista |0>/sqrt(2),|1>/sqrt(2)
		//e target |1>
		//ci aspettiamo in output
		//una lista target |1>/sqrt(2), |0>/sqrt(2)
		qplus.setQubit(c0, cplus);
		q2.setQubit(cplus, c0);
		q1.setQubit(c0, c1);
		res.removeAll(res);
		res.add(q2);
		res.add(qplus);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q1);
		res2.addAll(CNOT.operation(res, q1));
		System.out.println("CNOT target |1> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input
		//come controllo una lista |1>/sqrt(2),|0>/sqrt(2)
		//e target |0>
		//ci aspettiamo in output
		//una lista target |1>/sqrt(2), |0>/sqrt(2)
		qplus.setQubit(c0, cplus);
		q2.setQubit(cplus, c0);
		q1.setQubit(c1, c0);
		res.removeAll(res);
		res.add(qplus);
		res.add(q2);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q1);
		res2.addAll(CNOT.operation(res, q1));
		System.out.println("CNOT target |0> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input
		//come controllo una lista |1>/sqrt(2),|0>/sqrt(2)
		//e target |1>
		//ci aspettiamo in output
		//una lista target |0>/sqrt(2), |1>/sqrt(2)
		qplus.setQubit(c0, cplus);
		q2.setQubit(cplus, c0);
		q1.setQubit(c0, c1);
		res.removeAll(res);
		res.add(qplus);
		res.add(q2);
		res2.removeAll(res2);
		CNOT=new CNOTGate(res,q1);
		res2.addAll(CNOT.operation(res, q1));
		System.out.println("CNOT target |1> "+res2.get(0).getQubit()[0]+" "+res2.get(0).getQubit()[1]+" "+res2.get(1).getQubit()[0]+" "+res2.get(1).getQubit()[1]);
		
		//Passandogli come input
		//due liste di stati separati
		//come controllo |0>, |1>
		//e come target |0>, |1>
		//ci aspettiamo come output
		//Il target aggiornato
	    //|0>,|0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		res2.add(q2);
		res2.add(q1);
		CNOT=new CNOTGate(res,res2);
		ArrayList<Qubit> res3=new ArrayList<Qubit>();
		res3.add(q2);
		res3.addAll(CNOT.operation(res, res2));
		res3.remove(0);
		System.out.println("CNOT target |0>,|1> "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);

		//Passandogli come input
		//due liste di stati separati
		//come controllo |0>, |1>
		//e come target |1>, |0>
		//ci aspettiamo come output
		//Il target aggiornato
	    //|1>,|1>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		res2.add(q1);
		res2.add(q2);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		res3.add(q2);
		res3.addAll(CNOT.operation(res, res2));
		res3.remove(0);
		System.out.println("CNOT target |1>,|0> "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);
	
		//Passandogli come input
		//due liste di stati separati
		//come controllo |1>, |0>
		//e come target |0>, |1>
		//ci aspettiamo come output
		//Il target aggiornato
	    //|1>,|1>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		Qubit q3=new Qubit0();
		Qubit q4=new Qubit1();
		q3.setQubit(c1, c0);
		q4.setQubit(c0, c1);
		res2.add(q3);
		res2.add(q4);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		//res3.add(q2);
		res3.addAll(CNOT.operation(res, res2));
		//res3.remove(0);
		System.out.println("CNOT target |0>,|1> "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);
	
		//Passandogli come input
		//due liste di stati separati
		//come controllo |1>, |0>
		//e come target |1>, |0>
		//ci aspettiamo come output
		//Il target aggiornato
		//|0>,|0>
		q1.setQubit(c0, c1);
		q2.setQubit(c1, c0);
		res.removeAll(res);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		q3.setQubit(c1, c0);
		q4.setQubit(c0, c1);
		res2.add(q4);
		res2.add(q3);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		res3.addAll(CNOT.operation(res, res2));
		System.out.println("CNOT target |1>,|0> "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);

		//Passandogli come input
		//due liste di stati separati
		//come controllo |0>/sqrt(2), |1>/sqrt(2)
		//e come target |0>/sqrt(2), |1>/sqrt(2)
		//ci aspettiamo come output
		//Il target aggiornato
		//|0>/sqrt(2),|0>/sqrt(2)
		q1.setQubit(cplus, c0);
		q2.setQubit(c0, cplus);
		res.removeAll(res);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		q3.setQubit(cplus, c0);
		q4.setQubit(c0, cplus);
		res2.add(q3);
		res2.add(q4);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		res3.addAll(CNOT.operation(res, res2));
		System.out.println("CNOT target |0>/sqrt(2),|1>/sqrt(2) "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);
	
		//Passandogli come input
		//due liste di stati separati
		//come controllo |0>/sqrt(2), |1>/sqrt(2)
		//e come target |1>/sqrt(2), |0>/sqrt(2)
		//ci aspettiamo come output
		//Il target aggiornato
		//|1>/sqrt(2),|1>/sqrt(2)
		q1.setQubit(cplus, c0);
		q2.setQubit(c0, cplus);
		res.removeAll(res);
		res.add(q1);
		res.add(q2);
		res2.removeAll(res2);
		q3.setQubit(cplus, c0);
		q4.setQubit(c0, cplus);
		res2.add(q4);
		res2.add(q3);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		res3.addAll(CNOT.operation(res, res2));
		System.out.println("CNOT target |1>/sqrt(2),|0>/sqrt(2) "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);

		//Passandogli come input
		//due liste di stati separati
		//come controllo |1>/sqrt(2), |0>/sqrt(2)
		//e come target |0>/sqrt(2), |1>/sqrt(2)
		//ci aspettiamo come output
		//Il target aggiornato
		//|1>/sqrt(2),|1>/sqrt(2)
		q1.setQubit(cplus, c0);
		q2.setQubit(c0, cplus);
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		q3.setQubit(cplus, c0);
		q4.setQubit(c0, cplus);
		res2.add(q3);
		res2.add(q4);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		res3.addAll(CNOT.operation(res, res2));
		System.out.println("CNOT target |0>/sqrt(2),|1>/sqrt(2) "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);

		//Passandogli come input
		//due liste di stati separati
		//come controllo |1>/sqrt(2), |0>/sqrt(2)
		//e come target |1>/sqrt(2), |0>/sqrt(2)
		//ci aspettiamo come output
		//Il target aggiornato
		//|0>/sqrt(2),|0>/sqrt(2)
		q1.setQubit(cplus, c0);
		q2.setQubit(c0, cplus);
		res.removeAll(res);
		res.add(q2);
		res.add(q1);
		res2.removeAll(res2);
		q3.setQubit(cplus, c0);
		q4.setQubit(c0, cplus);
		res2.add(q4);
		res2.add(q3);
		CNOT=new CNOTGate(res,res2);
		res3.removeAll(res3);
		res3.addAll(CNOT.operation(res, res2));
		System.out.println("CNOT target |1>/sqrt(2),|0>/sqrt(2) "+res3.get(0).getQubit()[0]+" "+res3.get(0).getQubit()[1]+" "+res3.get(1).getQubit()[0]+" "+res3.get(1).getQubit()[1]);

	}

}
