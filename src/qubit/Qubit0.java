/**
 * Sottoclasse di Qubit che rappresenta il qubit di base |0>
 */
package qubit;

import org.apache.commons.math3.complex.Complex;

/**
 * @author Giorgio
 *
 */
public class Qubit0 extends Qubit{
	/**
	 * Il qubit |0> è identificato da (1 0)'
	 */
	public Qubit0() {
		super(new Complex (1.0,0.0), new Complex(0.0,0.0));
	}
}
