/**
 * Classe che rappresenta un quantum register
 */
package quantumRegister;
import java.util.*;
import qubit.Qubit;

/**
 * @author Giorgio
 *
 */
public class QuantumRegister {
	public QuantumRegister(){
	}
	
	/**
	 * Metodo che consente di settare il Quantum Register, 
	 * passandogli in input la lista di qubit
	 * @param quantumreg
	 */
	public void setQuantumReg(ArrayList<Qubit> quantumreg){
		this.quantumreg=quantumreg;
	}
	
	/**
	 * Metodo che restituisce il numero di elementi del Quantum Register
	 * @return quantumreg.size()
	 */
	public int getSize(){
		return quantumreg.size();
	}
	
	/**
	 * Metodo che restituisce il qubit che si trova in posizione index
	 * @param index
	 * @return qubit
	 */
	public Qubit getQubit(int index){
		Qubit qubit=quantumreg.get(index);
		return qubit;
	}
	
	/**
	 * Metodo che consente di modificare il qubit che si trova
	 * nella posizione index
	 * @param q
	 * @param index
	 * @return quantumreg
	 */
	public QuantumRegister changeQubit(Qubit q, int index){
		quantumreg.set(index, q);
		return this;
	}
	
	/**
	 * quantumreg è la lista di qubit che costituiscono il Quantum Register
	 */
	private ArrayList<Qubit> quantumreg=new ArrayList<Qubit>();
}
