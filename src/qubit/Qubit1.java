/**
 * Sottoclasse che rappresenta il qubit di base |1>
 */
package qubit;

import org.apache.commons.math3.complex.Complex;

/**
 * @author Giorgio
 *
 */
public class Qubit1 extends Qubit{
	/**
	 * Il qubit |1> è rappresentato da (0 1)'
	 */
	public Qubit1() {
		super(new Complex (0.0,0.0), new Complex(1.0,0.0));
	}
}
