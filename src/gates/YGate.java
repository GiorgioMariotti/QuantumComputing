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
public class YGate extends QG1Qubit{

	YGate(Qubit q1) {
		super(q1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Qubit operation(Qubit q1) {
		Complex[][] Y= new Complex[2][2];
		Complex I=new Complex(0.0, 1.0);
		int c=-1;
		Y[0][0]=new Complex(0.0,0.0);
		Y[0][1]=I.multiply(c);
		Y[1][0]=I;
		Y[1][1]=new Complex(0.0,0.0);
		Qubit res=new Qubit(new Complex(0.0,0.0), new Complex(0.0,0.0));
		for(int i=0; i<2; i++){
			res.getQubit()[i]=(q1.getQubit()[0].multiply(Y[i][0])).add(q1.getQubit()[1].multiply(Y[i][1]));
		}
		return res;
	}

}
