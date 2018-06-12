/**
 * 
 */
package qubit;

import org.apache.commons.math3.complex.Complex;

/**
 * @author Giorgio
 *
 */
public class StatoMultiplo {
	
	public StatoMultiplo(Complex[] qubits){
		this.c=qubits;
	}
	
	public int getSize(){
		return c.length;
	}
	
	public Complex getElementAt(int index){
		return c[index];
	}
	
	public void setElementAt(Complex qubit, int index){
		c[index]=qubit;
	}
	
	public void setStatoMultiplo(Complex[] qubits){
		this.c=qubits;
	}
	
	private Complex[] c;
}
