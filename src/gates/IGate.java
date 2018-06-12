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
public class IGate extends QG1Qubit{

	IGate(Qubit q1) {
		super(q1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Qubit operation(Qubit q1) {
		int[][] I=new int[2][2];
		I[0][0]=1;
		I[0][1]=0;
		I[1][0]=0;
		I[1][1]=1;
		Qubit res=new Qubit(new Complex(0.0,0.0), new Complex(0.0,0.0));
		for(int i=0; i<2; i++){
			res.getQubit()[i]=(q1.getQubit()[0].multiply(I[i][0])).add(q1.getQubit()[1].multiply(I[i][1]));
		}
		return res;
	}

}
