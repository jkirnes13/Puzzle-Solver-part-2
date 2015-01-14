import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Solver.java
 *
 * File:
 *	$Id: Solver.java,v 1.2 2014/11/25 04:48:02 jmk4806 Exp $
 *
 * Revisions:
 *	$Log: Solver.java,v $
 *	Revision 1.2  2014/11/25 04:48:02  jmk4806
 *	puts out errors, does not work
 *
 *	Revision 1.1  2014/11/25 04:19:57  jmk4806
 *	not perfect, but still working, but will only maybe find out how to do one
 *
 *	Revision 1.3  2014/11/06 01:13:03  jmk4806
 *	Modified: added comments!
 *
 *	Revision 1.2  2014/11/06 01:00:41  jmk4806
 *	Modified:It works!!!!!!!
 *
 *	Revision 1.1  2014/11/05 22:58:10  jmk4806
 *	Modified: begins program
 *
 */

/**
 * @author Julien Kirnes
 *
 */
public class Solver<T>{
	/**
	 * solve takes your Clock object, as a Puzzle interface, and uses it to solve the puzzle using the BFS algorithm 
	 * @param p
	 * @return the path of puzzle
	 */
	
	
	public ArrayList<T> solve (Puzzle<T> p){
		ArrayDeque<T> myQueue = new ArrayDeque<T>();
		T current = null;
		Map<T,T> predecessors = new HashMap<T,T>();
		boolean found = false;
		ArrayList<T> backPath = new ArrayList<T>();
		
		myQueue.add(p.getStart());
		
		while(!myQueue.isEmpty() && !found){
			current = myQueue.remove();
			
			for(T neighbor:p.getNeighbors(current)){
					if(!predecessors.containsKey(neighbor)){
						predecessors.put(neighbor, current);
						myQueue.add(neighbor);
					}
					if(p.isGoal(neighbor)){
						found = true;
					}
			}
			
		}
		if(found == true){
			T currNode = myQueue.getLast();
			while(currNode != p.getStart()){
				backPath.add(0,currNode);
				currNode = predecessors.get(currNode);
			}
			backPath.add(0,p.getStart());
			
		}
		return backPath;
	}
	
}
	
