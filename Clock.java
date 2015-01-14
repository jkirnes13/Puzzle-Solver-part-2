import java.util.ArrayList;

/**
 * Clock.java
 *
 * File:
 *	$Id: Clock.java,v 1.3 2014/11/25 04:48:02 jmk4806 Exp $
 *
 * Revisions:
 *	$Log: Clock.java,v $
 *	Revision 1.3  2014/11/25 04:48:02  jmk4806
 *	puts out errors, does not work
 *
 *	Revision 1.2  2014/11/25 04:19:57  jmk4806
 *	not perfect, but still working, but will only maybe find out how to do one
 *
 *	Revision 1.1  2014/11/11 00:13:34  jmk4806
 *	Modified: begin program
 *
 *	Revision 1.6  2014/11/06 01:24:33  jmk4806
 *	Modified: redo on try-catch statment
 *
 *	Revision 1.5  2014/11/06 01:22:17  jmk4806
 *	Modified: updated try-catch
 *
 *	Revision 1.4  2014/11/06 01:20:04  jmk4806
 *	Modified: updated prevention statement
 *
 *	Revision 1.3  2014/11/06 01:13:03  jmk4806
 *	Modified: added comments!
 *
 *	Revision 1.2  2014/11/06 01:00:40  jmk4806
 *	Modified:It works!!!!!!!
 *
 *	Revision 1.1  2014/11/05 22:58:09  jmk4806
 *	Modified: begins program
 *
 */

/**
 * @author Julien Kirnes
 *
 */
public class Clock implements Puzzle<Integer> {
	/**
	 * uninstanced variables for the constructor
	 */
	private static int hours;
	private static int start;
	private static int end;
	
	public static void main(String[] args) {
	try{
		/**
		 * program accepts three numbers from the user: hours, start, end
		 */
			Solver solve = new Solver();
			int hours = Integer.parseInt(args[0]);
			int start = Integer.parseInt(args[1]);
			int end = Integer.parseInt(args[2]);
			/**
			 * checks to if start and finish are between 1..N
			 */
			if(start < 1 || start > hours){
				System.out.println("start time is out of Range!");
				System.exit(1);
			}
			else if(end < 1 || end > hours){
				System.out.println("end time is out of Range!");
				System.exit(1);
			}
			/**
			 * creates object, throws it into the puzzle solver, and prints out the formatted path
			 */
			Clock myClock = new Clock(hours,start,end);
			for(int i = 0;i<solve.solve(myClock).size();i++){
				System.out.println("Step " + i +": " + solve.solve(myClock).get(i));
			}
	/**
	 * catches if all the numbers are integers
	 */
	} catch(NumberFormatException e){
		System.out.println("All input should be an int!");
	/**
	 * catches to see if the the object thrown into argument is proper
	 */
	} catch(ArrayIndexOutOfBoundsException e){
		System.out.println("Usage: java Clock hours start goal");
	}

	}
	/**
	 * constructor initializes member fields of an instance.
	 * @param hours initial value for this.hours
	 * @param start initial value for this.start
	 * @param end initial value for this.end
	 */
	public Clock(int hours,int start, int end){
		this.hours = hours;
		this.start = start;
		this.end = end;
	}
	/**
	 *  getStart gets the starting config for this puzzle.
	 * @return clock's start config
	 */
	@Override
	public Integer getStart() {
		return start;
	}
	/**
	 * getGoal gets gets the goal configuration for this puzzle.
	 * @return clock's end configuration
	 */
	@Override
	public boolean isGoal(Integer config) {
		if(config == end){
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
	public ArrayList<Integer> getNeighbors(Integer config) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		if(config == 1){
			neighbors.add(hours);
			neighbors.add(config+1);
			return neighbors;
		}
		else if((int)config == hours){
			neighbors.add(config-1);
			neighbors.add(1);
			return neighbors;
		}
		else{
			neighbors.add(config-1);
			neighbors.add(config+1);
			return neighbors;
		}
	}
	@Override
	public Integer getGoal() {
		return end;
	}

}
