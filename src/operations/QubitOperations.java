/**
 * 
 */
package operations;

import org.apache.commons.math3.complex.Complex;

import operations.Exceptions.NotSameDimensionsException;
import qubit.Qubit;
import qubit.StatoMultiplo;

/**
 * @author Giorgio
 *
 */
public class QubitOperations {
	public QubitOperations(){		
	}
	
	public Complex[][] duale(Qubit q){
		return calcolaDuale(q.getQubit());
	}

	public Complex[][] calcolaDuale(Complex[] c) {
		Complex[][] duale=new Complex[1][c.length];
		for(int i=0;i<c.length;i++){
			duale[0][i]=c[i].conjugate();
		}
		return duale;
	}
	
	public Complex prodottointerno(Qubit q1, Qubit q2) throws NotSameDimensionsException{
		return calcolaProdottoInterno(q1.getQubit(),q2.getQubit());
	}

	public Complex calcolaProdottoInterno(Complex[] c1, Complex[] c2) throws NotSameDimensionsException {
		Complex result= new Complex(0.0,0.0);
		Complex mult=new Complex(0.0,0.0);
		QubitOperations qo=new QubitOperations();
		if(c1.length==c2.length) {
			Complex[][] dualc1=qo.calcolaDuale(c1);
			int numerorighe=c2.length;
			for(int i=0; i<numerorighe; i++){
				mult=dualc1[0][i].multiply(c2[i]);
				result=result.add(mult);
			}
		} else {
			throw new NotSameDimensionsException("i vettori non hanno la stessa lunghezza");
		}
		return result;
	}
	
	public Complex[][] prodottoEsterno(Qubit q1,Qubit q2) throws NotSameDimensionsException{
		return calcolaProdottoEsterno(q1.getQubit(),q2.getQubit());
	}

	public Complex[][] calcolaProdottoEsterno(Complex[] c1, Complex[] c2) throws NotSameDimensionsException{
		Complex[][] result=null;
		Complex mult=new Complex(0.0,0.0);
		QubitOperations qo=new QubitOperations();
		if(c1.length==c2.length) {
			Complex[][] dualc2=qo.calcolaDuale(c2);
			int numerorighe=c1.length;
			result=new Complex[numerorighe][dualc2[0].length];
			Complex somma=new Complex(0.0,0.0);
			for(int i=0; i<numerorighe; i++){
				for(int j=0; j<dualc2[0].length; j++){
					mult=c1[i].multiply(dualc2[0][j]);
					somma=somma.add(mult);
					result[i][j]=somma;
					somma=somma.multiply(new Complex(0.0, 0.0));
				}
			}
		} else {
			throw new NotSameDimensionsException("Devono avere la stessa dimensione");
		}
		return result;
	}
	
	public static StatoMultiplo TP(Qubit q1, Qubit q2 ){
		Complex[] listares= new Complex[q1.getQubit().length*q2.getQubit().length];
		int k=0;
		for(int i=0; i<q1.getQubit().length; i++){
			for(int j=0;j<q2.getQubit().length ; j++){
				listares[k]=q1.getQubit()[i].multiply(q2.getQubit()[j]);
				k=k+1;
			}
		}
		StatoMultiplo res=new StatoMultiplo(listares);
		res.setStatoMultiplo(listares);
		return res;
	}
	
	public static StatoMultiplo calcolaTP(Qubit q1, Qubit q2 ){
		return TP(q1,q2);
	}
	
	public StatoMultiplo[] TP(Qubit...qubits){
		StatoMultiplo[] res=new StatoMultiplo[qubits.length];
		for(int i=1; i<qubits.length; i++){
			res[i-1]=TP(qubits[i-1],qubits[i]);
		}
		return res;
	}

}
