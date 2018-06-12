/**
 * Sottoclasse che rappresenta il qubit |->
 */
package qubit;

import org.apache.commons.math3.complex.Complex;

/**
 * @author Giorgio
 *
 */
public class QubitMinus extends Qubit{
	/**
	 * Il qubit |-> è rappresentato da (1/sqrt(2))*(1 -1)'
	 */
	public QubitMinus() {
		super(new Complex (1.0/Math.sqrt(2.0),0.0), new Complex(-1.0/Math.sqrt(2.0),0.0));
	}
}
