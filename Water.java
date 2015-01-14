import java.util.ArrayList;

/**
 * Water.java
 *
 * File:
 *	$Id: Water.java,v 1.4 2014/11/25 04:48:01 jmk4806 Exp $
 *
 * Revisions:
 *	$Log: Water.java,v $
 *	Revision 1.4  2014/11/25 04:48:01  jmk4806
 *	puts out errors, does not work
 *
 *	Revision 1.3  2014/11/25 04:19:56  jmk4806
 *	not perfect, but still working, but will only maybe find out how to do one
 *
 *	Revision 1.2  2014/11/11 00:14:27  jmk4806
 *	Begin Program
 *
 */

/**
 * @author Julien Kirnes
 *
 */
public class Water implements Puzzle<ArrayList<Integer>>{

	private static int amount;
	private static ArrayList<Integer> jugs;
	public static ArrayList<Integer> jugsLevel;
	static ArrayList<ArrayList<Integer>> visited = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) {
	try{
		/**
		 * program does as clock and checks if numbers are within range then
		 *outputs the steps
		 */
		Solver solve = new Solver();
		int amount = Integer.parseInt(args[0]);
		ArrayList<Integer> jugs = new ArrayList<Integer>();
		for(int i = 1;i<args.length;i++){
				jugs.add(Integer.parseInt(args[i]));
		}
		ArrayList<ArrayList<Integer>> hold = new ArrayList<ArrayList<Integer>>();
		if(amount < 1){
			System.out.println("water amount is out of Range!");
			System.exit(1);
		}
		else if(jugs.get(0) < 1){
			System.out.println("jug 1 is out of Range!");
			System.exit(1);
		}
		for(int x = 1;x<jugs.size();x++){
			if(jugs.get(x) <= 0){
				System.out.println("jug is out of Range!");
				System.exit(1);
			}
		}
		
		Water myWater = new Water(amount,jugs);
		if(!solve.solve(myWater).isEmpty()){
			int j;
			for(int i = 0;i<solve.solve(myWater).size();i++){
				System.out.print("Step " + i +": ");
				for(j = 0;j<((ArrayList<Integer>)solve.solve(myWater).get(i)).size() - 1;j++){
					 System.out.print(((ArrayList<Integer>)solve.solve(myWater).get(i)).get(j) + " " ); 
				}
				System.out.println(((ArrayList<Integer>)solve.solve(myWater).get(i)).get(j));
			}
		}
		else{
			System.out.println("No solution.");
		}
		
	
	} catch(NumberFormatException e){
		System.out.println("All input should be an int!");
	/**
	 * catches to see if the the object thrown into argument is proper
	 */
	} catch(ArrayIndexOutOfBoundsException e){
		System.out.println(" Usage: java Water amount jug1 jug2 ...");
	}

	}
	/**
	 * constructor initializes member fields of an instance.
	 * @param amount initial value for this.amount
	 * @param jugs initial value for this.JugLevel
	 * @param jugLevel initial value for this.jugLevel
	 */
	public Water(int amount,ArrayList<Integer> jugs){
		this.amount = amount;
		this.jugs = jugs;
		this.jugsLevel = new ArrayList<Integer>();
		for(int i = 0;i<jugs.size();i++){
			jugsLevel.add(0);
	}
	}
	/**
	 *  getStart gets the starting config for this puzzle.
	 * @return clock's start config
	 */
	@Override
	public ArrayList<Integer> getStart() {
		return jugsLevel;
	}
	/**
	 * getGoal gets gets the goal configuration for this puzzle.
	 * @return clock's end configuration
	 */
	@Override
	public boolean isGoal(ArrayList<Integer> config) {
		if(config.get(0)== amount){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * getNeighbors takes an incoming config, generate and return all direct neighbors to this config.
	 * @param config
	 * @return all direct neighbors to this config.
	 */
	@Override
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config) {
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		int current;
		for(int i = 0;i<jugs.size();i++){
			current = config.get(i);
			ArrayList<Integer> clone = new ArrayList<Integer>();
			for(int a = 0;a<config.size();a++){
				clone.add(new Integer(config.get(a).intValue()));
			}
			if(current == 0){
				current = jugs.get(i);
				clone.set(i, current);
				neighbors.add(clone);
			}
			else{
				if (current<jugs.get(i)){
					current = jugs.get(i);
					clone.set(i, current);
					neighbors.add(clone);
				}
				else if (current == jugs.get(i)){
					current = 0;
					clone.set(i,current);
					neighbors.add(clone);
				}
			}
		}
		for(int j=0;j<jugs.size();j++) {
			current = config.get(j);
			if (current == 0){
				continue;
			}
			ArrayList<Integer> clone = new ArrayList<Integer>();
			for(int a = 0;a<config.size();a++){
				clone.add(new Integer(config.get(a).intValue()));
			}
			for(int k=0;k<jugs.size();k++){
				if (j == k){
					continue;
				}
				int second = config.get(k);
				if(second<jugs.get(k)){
					current = (jugs.get(k) - config.get(k));
					if (current > config.get(j)) current = config.get(j); 
						clone.set(j, config.get(j)-current);
						clone.set(k, config.get(k)+current);
						neighbors.add(clone);
					}
				}
			}
		return neighbors;
	}
	@Override
	public ArrayList<Integer> getGoal() {
		ArrayList<Integer> newb = new ArrayList<Integer>();
		newb.add(amount);
		return newb;
	}
}
