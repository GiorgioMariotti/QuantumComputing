/**
 * 
 */
package gates;

import org.apache.commons.math3.complex.Complex;

import qubit.Qubit;

/**
 * @author Giorgio
 *
 */
public class XGate extends QG1Qubit{

	public XGate(Qubit q1) {
		super(q1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Qubit operation(Qubit q1) {
		int[][] X=new int[2][2];
		X[0][0]=0;
		X[0][1]=1;
		X[1][0]=1;
		X[1][1]=0;
		Qubit res=new Qubit(new Complex(0.0,0.0), new Complex(0.0,0.0));
		for(int i=0; i<2; i++){
			res.getQubit()[i]=(q1.getQubit()[0].multiply(X[i][0])).add(q1.getQubit()[1].multiply(X[i][1]));
		}
		return res;
	}

}
