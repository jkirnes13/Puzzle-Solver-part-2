import java.util.ArrayList;

/**
 * Puzzle.java
 *
 * File:
 *	$Id: Puzzle.java,v 1.2 2014/11/25 04:19:57 jmk4806 Exp $
 *
 * Revisions:
 *	$Log: Puzzle.java,v $
 *	Revision 1.2  2014/11/25 04:19:57  jmk4806
 *	not perfect, but still working, but will only maybe find out how to do one
 *
 *	Revision 1.1  2014/11/11 00:13:35  jmk4806
 *	Modified: begin program
 *
 *	Revision 1.1  2014/11/05 22:58:09  jmk4806
 *	Modified: begins program
 *
 */

/**
 * @author Julien Kirnes
 *
 */
public interface Puzzle<C>{
	/**
	 *  getStart gets the starting config for this puzzle.
	 * @return
	 */
	C getStart();
	/**
	 * isGoal confirms if the config is equal to goal config
	 * @param config
	 * @return
	 */
	boolean isGoal(C config);
	/**
	 * getNeighbors takes an incoming config, generate and return all direct neighbors to this config.
	 * @param config
	 * @return all direct neighbors to this config.
	 */
	ArrayList<C> getNeighbors(C config);
	/**
	 * getGoal gets gets the goal config for this puzzle.
	 * @return
	 */
	C getGoal();
}
