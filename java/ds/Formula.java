
/** 

Usecase 1: Represent (P ∨ Q) ∧ (¬P ∨ R)  “either P or Q is true, and either P is false or R is true." as Object.


 */

import java.util.Set;

interface Formula {
	/**
	 * Values definition (as opposed to operational definition)
	 * Formula = Variable(name:String)
          + Not(formula:Formula)
          + And(left:Formula, right:Formula)
          + Or(left:Formula, right:Formula)

        How to define operations? 
        variables(Variable(x)) = setAdd(x, emptySet())
        variables(Not(f)) = variables(f)
        variables(And(f1,f2) = setUnion(variables(f1), variables(f2))
        variables(Or(left, right)) = setUnion(variables(f1), variables(f2))
	 */
	
	public Set<String> variables();
	public static boolean isSatisfiable(){
		Set<String> variables = variables();
		List<Map<String, Boolean>> envList;
		for (String s: variables ){
			Map<String, Boolean> environment =  new HashMap<String, Boolean;		}
		
		}

		



}

class Variable implements Formula {
	
	private final String letter


	public Variable(String letter){
		this.letter = letter;
	}

	public Set<String> variables(){
		return new HashSet<String>(letter);
	}
	
}

class Not implements Formula {
	
	private final Formula negatedFormula;


	public Not(Formula formula){
		this.negatedFormula = formula;
	}

	public Set<String> variables(){
		return negatedFormula.variables()
	}
	
}



class And implements Formula {
	
	Formula left;
	Formula  right;


	public And(Formula left, Formula right){
		this.left = left;
		this.right = right
	}

	public Set<String> variables(){
		Set<Integer> s = new HashSet<>();
		s.addAll(left.variables());
		s.addAll(right.variables());
		return s;
	}
}

class Or implements Formula {
	
	Formula left;
	Formula  right;


	public Or (Formula left, Formula right){
		this.left = left;
		this.right = right
	}


	public Set<String> variables(){
		Set<Integer> s = new HashSet<>();
		s.addAll(left.variables());
		s.addAll(right.variables());
		return s;
	}


	
}


public class FormulaClient{


	public static void main(String[] args){

		Formula test;
	}
}