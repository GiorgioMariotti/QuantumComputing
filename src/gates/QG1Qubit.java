/**
 * 
 */
package gates;

import java.util.ArrayList;

import qubit.Qubit;

/**
 * @author Giorgio
 *
 */
public abstract class QG1Qubit{
	QG1Qubit(Qubit q1){
	}
	QG1Qubit(ArrayList<Qubit> list){
	}
	abstract public Qubit operation(Qubit q1);
}
