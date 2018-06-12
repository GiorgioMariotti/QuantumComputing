/**
 * 
 */
package gates;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

import quantumRegister.QuantumRegister;
import qubit.Qubit;
import qubit.Qubit0;

/**
 * @author Giorgio
 *
 */
public class CNOTGate extends QG2Qubit{

	public CNOTGate(Qubit q1, Qubit q2) {
		super(q1,q2);
		ArrayList<Qubit> gate=new ArrayList<Qubit>();
		gate.add(q1);
		gate.add(q2);
		QuantumRegister qubits=new QuantumRegister();
		qubits.setQuantumReg(gate);
		
	}
	public CNOTGate(ArrayList<Qubit> list, Qubit q2){
		super(list, q2);
	}
	public CNOTGate(ArrayList<Qubit> controllo, ArrayList<Qubit> target){
		super(controllo,target);
	}

	@Override
	public QuantumRegister operation(Qubit q1, Qubit q2) {
		ArrayList<Qubit> qin=new ArrayList<Qubit>();
		qin.add(q1);
		qin.add(q2);
		QuantumRegister in=new QuantumRegister();
		in.setQuantumReg(qin);
		QuantumRegister out=new QuantumRegister();
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		Qubit zero=new Qubit0();
		zero.setQubit(c1, c0);
		if( in.getQubit(0).getQubit()[0].equals(in.getQubit(0).getQubit()[0],zero.getQubit()[0])){
			ArrayList<Qubit> qout=new ArrayList<Qubit>();
			qout.add(q1);
			qout.add(q2);
			out.setQuantumReg(qout);
		} else {
			Qubit x=new Qubit(c0,c0);
			x.setQubit(c0, c0);
			x.getQubit()[0]=q2.getQubit()[1];
			x.getQubit()[1]=q2.getQubit()[0];
			ArrayList<Qubit> qout=new ArrayList<Qubit>();
			qout.add(q1);
			qout.add(x);
			out.setQuantumReg(qout);
		}
		return out;
	}
	
	@Override
	public ArrayList<Qubit> operation(ArrayList<Qubit> list, Qubit q2){
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		Complex cplus=new Complex(1.0/Math.sqrt(2),0.0);
		ArrayList<Qubit> res=new ArrayList<Qubit>();
		double coefficiente= 1.0/Math.sqrt(2);
		Qubit x=new Qubit(c0,c0);
		Qubit y=new Qubit(c0,c0);
		x.setQubit(c0, c0);
		y.setQubit(c0, c0);
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],c1)){
				if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],c0)){
					y.getQubit()[0]=q2.getQubit()[0];
					y.getQubit()[1]=q2.getQubit()[1];
					res.add(y);
				}
			} else {
				if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],cplus)){
					if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],c0)){
						y.getQubit()[0]=q2.getQubit()[0].multiply(coefficiente);
						y.getQubit()[1]=q2.getQubit()[1].multiply(coefficiente);
						res.add(y);
					}
				} else {
					if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],c0)){
						if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],c1)){
							x.getQubit()[0]=q2.getQubit()[1];
							x.getQubit()[1]=q2.getQubit()[0];
							res.add(x);
						} else {
							if(list.get(i).getQubit()[1].equals(list.get(i).getQubit()[1],cplus)){
								x.getQubit()[0]=q2.getQubit()[1].multiply(coefficiente);
								x.getQubit()[1]=q2.getQubit()[0].multiply(coefficiente);
								res.add(x);
							}
						}
					}
				}
			}
		}
		return res;
	}
	
	public ArrayList<Qubit> operation(ArrayList<Qubit> controllo, ArrayList<Qubit> target){
		Complex c0=new Complex(0.0,0.0);
		Complex c1=new Complex(1.0,0.0);
		Complex cplus=new Complex(1.0/Math.sqrt(2),0.0);
		//double coefficiente= 1.0/Math.sqrt(2);
		//Qubit y=new Qubit(c0,c0);
		//y.setQubit(c0, c0);
		for(int i=0; i<controllo.size(); i++){
			if(controllo.get(i).getQubit()[0].equals(controllo.get(i).getQubit()[0],c0)){
				if(controllo.get(i).getQubit()[1].equals(controllo.get(i).getQubit()[1],c1)){
						Qubit x=new Qubit(c0,c0);
						x.setQubit(c0, c0);
						x.getQubit()[0]=target.get(i).getQubit()[1];
						x.getQubit()[1]=target.get(i).getQubit()[0];
						target.get(i).getQubit()[0]=x.getQubit()[0];
						target.get(i).getQubit()[1]=x.getQubit()[1];
						
					
				} else {
					if(controllo.get(i).getQubit()[1].equals(controllo.get(i).getQubit()[1],cplus)){
							Qubit x=new Qubit(c0,c0);
							x.setQubit(c0, c0);
							x.getQubit()[0]=target.get(i).getQubit()[1];
							x.getQubit()[1]=target.get(i).getQubit()[0];
							target.get(i).getQubit()[0]=x.getQubit()[0];
							target.get(i).getQubit()[1]=x.getQubit()[1];
							
						
					}
				}
			}
		}
		
		return target;
	}

}
