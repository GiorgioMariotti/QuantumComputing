/**
 * 
 */
package gates;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

import qubit.Qubit;
import qubit.Qubit0;
import qubit.Qubit1;

/**
 * @author Giorgio
 *
 */
public class HGate extends QG1Qubit{

	public HGate(Qubit q1) {
		super(q1);
		// TODO Auto-generated constructor stub
	}
	public HGate(ArrayList<Qubit> list){
		super(list);
	}


	@Override
	public Qubit operation(Qubit q1) {
		int[][] Hadamard=new int[2][2];
		Hadamard[0][0]=1;
		Hadamard[0][1]=1;
		Hadamard[1][0]=1;
		Hadamard[1][1]=-1;
		double coefficiente=1/Math.sqrt(2);
		Complex c1=new Complex(0.0,0.0);
		Complex[] c=new Complex[2];
		c[0]=c1;
		c[1]=c1;
		Qubit res=new Qubit(c);
		res.setQubit(c1, c1);
		for (int i=0; i<2; i++){
			res.getQubit()[i]=(q1.getQubit()[0].multiply(Hadamard[i][0])).add(q1.getQubit()[1].multiply(Hadamard[i][1]));
			res.getQubit()[i]=res.getQubit()[i].multiply(coefficiente);
		}
		return res;
	}
	
	/*
	 * Metodo di operation alternativo 
	 * da usare in necessità di avere a disposizione
	 * i singoli stati di sovrapposizionamento separati,
	 * ovvero senza che siano già sommati o sottratti.
	 */
	public ArrayList<Qubit> operationalt(Qubit q1){
		ArrayList<Qubit> states=new ArrayList<Qubit>();
		Complex c1=new Complex(1.0,0.0);
		Complex c2=new Complex(0.0,0.0);
		Qubit qzero=new Qubit0();
		Qubit quno=new Qubit1();
		qzero.setQubit(c1, c2);
		quno.setQubit(c2, c1);
		if((q1.getQubit()[0].equals(q1.getQubit()[0], qzero.getQubit()[0])&&(q1.getQubit()[1]).equals(q1.getQubit()[1], qzero.getQubit()[1]))){
			Complex ch=new Complex(1.0/Math.sqrt(2), 0.0);
			Qubit qh1=new Qubit(ch, c2);
			Qubit qh2=new Qubit(c2,ch);
			qh1.setQubit(ch, c2);
			qh2.setQubit(c2, ch);
			states.add(qh1);
			states.add(qh2);
			return states;
		} else{
			if((q1.getQubit()[0].equals(q1.getQubit()[0], quno.getQubit()[0]))&&(q1.getQubit()[1].equals(q1.getQubit()[1], quno.getQubit()[1]))){
				Complex ch=new Complex(1.0/Math.sqrt(2), 0.0);
				Qubit qh1=new Qubit(ch, c2);
				Qubit qh2=new Qubit(c2,ch);
				qh1.setQubit(ch, c2);
				qh2.setQubit(c2, ch);
				states.add(qh1);
				states.add(qh2);
				return states;
			}
		}
		return states;
	}
	
	public ArrayList<Qubit> operation(ArrayList<Qubit> list){
		Complex c1=new Complex(1.0,0.0);
		Complex c2=new Complex(0.0,0.0);
		Complex cplus=new Complex(1.0/Math.sqrt(2),0.0);
		Qubit qzero=new Qubit0();
		Qubit quno=new Qubit1();
		qzero.setQubit(c1, c2);
		quno.setQubit(c2, c1);
		ArrayList<Qubit> res=new ArrayList<Qubit>();
		double coefficiente= 1.0/Math.sqrt(2);
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getQubit()[0].equals(list.get(i).getQubit()[0],c2)){
				res.addAll(list);
			} else{
				res.addAll(list);
			}
		}
		int j=0;
		while(j<res.size()){
			if(res.get(j).getQubit()[0].equals(res.get(j).getQubit()[0],cplus)){
				j=j+1;
				continue;
			} else {
				if(res.get(j).getQubit()[1].equals(res.get(j).getQubit()[1],cplus)){
					j=j+1;
					continue;
				} else {
					res.get(j).getQubit()[0]=res.get(j).getQubit()[0].multiply(coefficiente);
					res.get(j).getQubit()[1]=res.get(j).getQubit()[1].multiply(coefficiente);
				}
			}
			j=j+1;
		}
		return res;
	}

}
