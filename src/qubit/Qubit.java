/**
 * Classe che rappresenta un qubit 
 */
package qubit;
import org.apache.commons.math3.complex.Complex;
/**
 * @author Giorgio
 *
 */
public class Qubit {
	/**
	 * Un qubit è definito da due numeri complessi
	 * @param c1
	 * @param c2
	 */
	public Qubit (Complex c1, Complex c2){
		//Definiamo un vettore composto da 2 numeri complessi
		Complex[] qubits= new Complex[2];
		qubits[0]= c1;
		qubits[1]= c2;
	}
	
	/**
	 * Un altro metodo per definirlo è passargli il vettore di numeri complessi
	 * @param qubits
	 */
	public Qubit(Complex[] qubits){
		this.qubits[0]=qubits[0];
		this.qubits[1]=qubits[1];
	}
	
	/**
	 * Qubits è il vettore che contiene i numeri complessi
	 */
	private Complex[] qubits=new Complex[2];
	
	/**
	 * get del qubit
	 * @return copy
	 */
	public Complex[] getQubit(){
		Complex[] copy=qubits;
		return copy;
	}
	/**
	 * set del qubit
	 * @param c1
	 * @param c2
	 */
	public void setQubit(Complex c1, Complex c2){
		qubits[0]=c1;
		qubits[1]=c2;
	}
}
