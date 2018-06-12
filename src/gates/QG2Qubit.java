/**
 * 
 */
package gates;

import java.util.ArrayList;

import quantumRegister.QuantumRegister;
import qubit.Qubit;

/**
 * @author Giorgio
 *
 */
public abstract class QG2Qubit{
	QG2Qubit (Qubit q1 , Qubit q2 ) {
	}
	QG2Qubit(ArrayList<Qubit> controllo, Qubit q2){
	}
	QG2Qubit(ArrayList<Qubit> controllo, ArrayList<Qubit> target){
	}
	abstract public QuantumRegister operation(Qubit q1, Qubit q2);
	abstract public ArrayList<Qubit> operation(ArrayList<Qubit> controllo, Qubit q2);
	abstract public ArrayList<Qubit> operation(ArrayList<Qubit> controllo, ArrayList<Qubit> target);
}
