/**
 * 
 */
package operations;

import org.apache.commons.math3.complex.Complex;

import quantumRegister.QuantumRegister;
import qubit.Qubit;
import qubit.StatoMultiplo;

/**
 * @author Giorgio
 *
 */
public class QRegisterOperations {
	public QRegisterOperations(){
	}
	
	
	public StatoMultiplo[] TP(QuantumRegister qr){
		if (qr.getSize()<2){
			return null;
		}
		QubitOperations op=new QubitOperations();
		StatoMultiplo[] res=new StatoMultiplo[qr.getSize()];
		for(int i=1;i<qr.getSize(); i++){
			res[i-1]=op.calcolaTP(qr.getQubit(i-1), qr.getQubit(i));
		}
		return res;
	}
}
