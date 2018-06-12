/**
 * 
 */
package gates;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

import qubit.Qubit;

/**
 * @author Giorgio
 *
 */
public class LambdaGate {
	public LambdaGate(Qubit q1, double fi){
	}
	public LambdaGate(ArrayList<Qubit> list, double fi){
	}
	
	public Qubit operation(Qubit q1,double fi){
		Complex[][] Lambda=new Complex[2][2];
		Complex I =new Complex(0.0,1.0);
		Lambda[0][0]=(I.multiply(fi/2)).exp();
		Lambda[0][1]=new Complex(0.0,0.0);
		Lambda[1][0]=new Complex(0.0,0.0);
		Lambda[1][1]=(I.multiply(-1).multiply(fi/2)).exp();
		Complex c1=new Complex(0.0,0.0);
		Complex[] c= new Complex[2];
		c[0]=c1;
		c[1]=c1;
		Qubit res=new Qubit(c);
		for ( int i=0; i<2; i++){
			res.getQubit()[i]=(q1.getQubit()[0].multiply(Lambda[i][0])).add(q1.getQubit()[1].multiply(Lambda[i][1]));
		}
		return res;
	}
	
	public ArrayList<Qubit> operation(ArrayList<Qubit> list, double fi){
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		Complex cplus=new Complex(1.0/Math.sqrt(2),0.0);
		Complex I =new Complex(0.0,1.0);
		Complex coefficiente1= (I.multiply(fi/2)).exp();
		Complex coefficiente2=(I.multiply(-1).multiply(fi/2)).exp();
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],c1)){
				if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],c0)){
					list.get(i).getQubit()[0]=list.get(i).getQubit()[0].multiply(coefficiente1);
					list.get(i).getQubit()[1]=list.get(i).getQubit()[1].multiply(coefficiente1);
				}
			} else {
				if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],cplus)){
					if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],c0)){
						list.get(i).getQubit()[0]=list.get(i).getQubit()[0].multiply(coefficiente1);
						list.get(i).getQubit()[1]=list.get(i).getQubit()[1].multiply(coefficiente1);
					}
				} else {
					if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],c0)){
						if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],c1)){
							list.get(i).getQubit()[0]=list.get(i).getQubit()[0].multiply(coefficiente2);
							list.get(i).getQubit()[1]=list.get(i).getQubit()[1].multiply(coefficiente2);
						} else {
							if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],cplus)){
								list.get(i).getQubit()[0]=list.get(i).getQubit()[0].multiply(coefficiente2);
								list.get(i).getQubit()[1]=list.get(i).getQubit()[1].multiply(coefficiente2);
							}
						}
					}
				}
			}
		}
		return list;

	}

}
