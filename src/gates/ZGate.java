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
public class ZGate extends QG1Qubit{

	ZGate(Qubit q1) {
		super(q1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Qubit operation(Qubit q1) {
		int[][] Z=new int[2][2];
		Z[0][0]=1;
		Z[0][1]=0;
		Z[1][0]=0;
		Z[1][1]=-1;
		Qubit res=new Qubit(new Complex(0.0,0.0), new Complex(0.0,0.0));
		for(int i=0; i<2; i++){
			res.getQubit()[i]=(q1.getQubit()[0].multiply(Z[i][0])).add(q1.getQubit()[1].multiply(Z[i][1]));
		}
		return res;
	}

}
