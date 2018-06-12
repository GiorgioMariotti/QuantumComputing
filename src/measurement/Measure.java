/**
 * Classe che rappresenta il processo di misura
 */
package measurement;

import java.util.Random;

import org.apache.commons.math3.complex.Complex;

import measurement.Exceptions.NotValidResult;
import operations.Exceptions.NotSameDimensionsException;
import qubit.Qubit;
import qubit.Qubit0;
import qubit.Qubit1;
import qubit.StatoMultiplo;

/**
 * @author Giorgio
 *
 */
public class Measure {
	public Measure(boolean scelta){
		this.scelta=scelta;
	}
	
	public boolean getScelta(){
		return scelta;
	}
	public void setScelta(boolean scelta){
		this.scelta=scelta;
	}
	
	private boolean scelta;
	private Qubit risultatomisura=null;
	private StatoMultiplo risultatomisuraestesa=null;
	
	public Measure inizializzazione(Qubit risultatomisura) {
		this.risultatomisura = risultatomisura;
		return this;
	}
	
	public Measure inizializzazioneestesa(StatoMultiplo risultatomisuraestesa){
		this.risultatomisuraestesa=risultatomisuraestesa;
		return this;
	}

	public Qubit misura(boolean scelta) throws NotValidResult{
		// collasso
		/*
		 * Posizione conterrà l'indice
		 * del risultato della misura
		 */
		int posizione = processo();
		/*
		 * alla fine il risultato viene messo
		 * in risultatomisura
		 */
		collasso(posizione);
		/*
		 * scelta=true->computazione quantistica
		 * e nel caso se il risultato è diverso da 0 
		 * o da 1, ritorna un errore
		 * scelta=false->"simulazione" quantistica e
		 * ritorna il risultato in ogni caso 
		 */
		Complex c1=new Complex(0.0,0.0);
		Complex c2=new Complex(1.0,0.0);
		Qubit qzero=new Qubit0();
		Qubit quno=new Qubit1();
		qzero.setQubit(c2, c1);
		quno.setQubit(c1, c2);
		if(scelta==true){
			if(risultatomisura.getQubit()[0].equals(risultatomisura.getQubit()[0], qzero.getQubit()[0])==true){
				if(risultatomisura.getQubit()[1].equals(risultatomisura.getQubit()[1], qzero.getQubit()[1])==true){
					return risultatomisura;
				} 
				else{
					throw new NotValidResult("Il risultato deve essere o |0> o |1>");
				}
			}
			else {
				if(risultatomisura.getQubit()[0].equals(risultatomisura.getQubit()[0], quno.getQubit()[0])==true){
					if(risultatomisura.getQubit()[1].equals(risultatomisura.getQubit()[1], risultatomisura.getQubit()[1])==true){
						return risultatomisura;
					} 
					else{
						throw new NotValidResult("Il risultato deve essere o |0> o |1>");
					}
				} else {
					throw new NotValidResult("Il risultato deve essere o |0> o |1>");
				}
			}
			}
			else{
				return risultatomisura;
			}
	}
	
	public StatoMultiplo misuraestesa(boolean scelta)throws NotValidResult{
		// collasso
		/*
		 * Posizione conterrà l'indice
		 * del risultato della misura
		 */
		int posizione = processoesteso();
		/*
		 * alla fine il risultato viene messo
		 * in risultatomisura
		 */
		collassoesteso(posizione);
		/*
		 * scelta=true->computazione quantistica
		 * e nel caso se il risultato è diverso da 0 
		 * o da 1, ritorna un errore
		 * scelta=false->"simulazione" quantistica e
		 * ritorna il risultato in ogni caso 
		 */
		Complex c1=new Complex(0.0,0.0);
		Complex c2=new Complex(1.0,0.0);
		if(scelta==true){
			int i=0;
			while(i<risultatomisuraestesa.getSize()){
				if((risultatomisuraestesa.getElementAt(i).equals(risultatomisuraestesa.getElementAt(i), c1)==false)&&(risultatomisuraestesa.getElementAt(i).equals(risultatomisuraestesa.getElementAt(i), c2)==false)){
					throw new NotValidResult("Il risultato deve essere uno stato della base computazionale");
				} else {
					i=i+1;
				}
			}
			return risultatomisuraestesa;
		}else{
		return risultatomisuraestesa;
		}
	}

	private void collasso(int posizione) {
		Complex[] qubit = risultatomisura.getQubit();
		for(int i=0; i<qubit.length; i++) {
			qubit[i] = new Complex(0.0, 0.0);
		}
		qubit[posizione] = new Complex(1.0, 0.0);
		risultatomisura = new Qubit(qubit);
	}
	
	private void collassoesteso(int posizione) {
		//Complex[] qubit = risultatomisura.getQubit();
		Complex[] qubit = new Complex[risultatomisuraestesa.getSize()];
		for(int j=0; j<qubit.length; j++){
			qubit[j]=risultatomisuraestesa.getElementAt(j);
		}
		for(int i=0; i<qubit.length; i++) {
			qubit[i] = new Complex(0.0, 0.0);
		}
		qubit[posizione] = new Complex(1.0,0.0);
		risultatomisuraestesa = new StatoMultiplo(qubit);
	}

	private int processo() {
		int nel = risultatomisura.getQubit().length;
		double[] probabilities = new double[nel];
		double valore = 0.0;
		double probabilitatotale = 0.0;
		double choice;
		int posizione = 0;
		int index = 0;
		//pos rappresenta l'ultima posizione non nulla
		int pos = 0;
		/*
		 * Calcoliamo il vettore probabilities
		 * e probabilitatotale
		 */
		for(Complex c : risultatomisura.getQubit()) {
			/*
			 * valore è dato dalla parte reale della
			 * moltiplicazione tra c e il suo 
			 * complesso coniugato 
			 */
			valore = c.multiply(c.conjugate()).getReal();
			probabilitatotale += valore;
			probabilities[posizione++] = valore;
		}
		/*
		 * alla fine quindi in probabilitatotale si trova la
		 * sommatoria tra tutte le probabilità
		 * ed in probabilities[posizione]
		 * la probabilità di ogni singolo risultato
		 */
		//normalizzazione
		for(posizione=0; posizione<nel; posizione++) {
			probabilities[posizione] /= probabilitatotale;
		}
		/*
		 * Facciamo la misura, casuale, basata sul tempo
		 * del sistema in millisecondi
		 */
		choice = new Random(System.currentTimeMillis()).nextDouble();

		for(index=0, posizione=0; index< nel; ++index, ++posizione) {
			valore = probabilities[index];
			if(Double.compare(valore,0.0)!=0) {
				pos = index;
			}
			if(Double.compare((choice-valore),0.0)==0||Double.compare((choice-valore),0.0)<0) {
				return posizione;
			}
			choice -= valore;
		}
		return 0;
	}
	
	private int processoesteso() {
		int nel = risultatomisuraestesa.getSize();
		double[] probabilities = new double[nel];
		double valore = 0.0;
		double probabilitatotale = 0.0;
		double choice;
		int posizione = 0;
		int index = 0;
		//pos rappresenta l'ultima posizione non nulla
		int pos = 0;
		/*
		 * Calcoliamo il vettore probabilities
		 * e probabilitatotale
		 */
		for(int i=0; i<risultatomisuraestesa.getSize(); i++) {
			/*
			 * valore è dato dalla parte reale della
			 * moltiplicazione tra l'elemento e il suo 
			 * complesso coniugato 
			 */
			valore = risultatomisuraestesa.getElementAt(i).multiply(risultatomisuraestesa.getElementAt(i).conjugate()).getReal();
			probabilitatotale += valore;
			probabilities[posizione++] = valore;
		}
		/*
		 * alla fine quindi in probabilitatotale si trova la
		 * sommatoria tra tutte le probabilità
		 * ed in probabilities[posizione]
		 * la probabilità di ogni singolo risultato
		 */
		//normalizzazione
		for(posizione=0; posizione<nel; posizione++) {
			probabilities[posizione] /= probabilitatotale;
		}
		/*
		 * Facciamo la misura, casuale, basata sul tempo
		 * del sistema in millisecondi
		 */
		choice = new Random(System.currentTimeMillis()).nextDouble();

		for(index=0, posizione=0; index< nel; ++index, ++posizione) {
			valore = probabilities[index];
			if(Double.compare(valore,0.0)!=0) {
				pos = index;
			}
			if(Double.compare((choice-valore),0.0)==0||Double.compare((choice-valore),0.0)<0) {
				return posizione;
			}
			choice -= valore;
		}
		return 0;
	}
	
}
