package dicer;

public class Rules {
	public int targetPoints;
	public int diceSides;
	public int maxThrows;
	public int maxRetains;
	public boolean mustUseUpAllRetains;
	public Goal goal;

	public enum Goal {
		/**
		 * Does a contestant either win (when finishing with exactly the target points) or lose (otherwise)?
		 */
		obtainTargetPoints,
		/**
		 * Is the final distance to the target to be minimized (instead of hard win criterion)?
		 */
		minimizeDistanceToTargetPoints
	}

	/**
	 * @param targetPoints How many points are needed to win?
	 * @param diceSides How many sides does the dice have?
	 * @param maxThrows How many times are we allowed to throw the dice?
	 * @param maxRetains How many times are we allowed to retain the dice result?
	 * @param mustUseUpAllRetains Do all retains need to be used up at the end in order to win?
	 */
	public Rules(int targetPoints, int diceSides, int maxThrows, int maxRetains, boolean mustUseUpAllRetains, Goal goal) {
		this.targetPoints = targetPoints;
		this.diceSides = diceSides;
		this.maxThrows = maxThrows;
		this.maxRetains = maxRetains;
		this.mustUseUpAllRetains = mustUseUpAllRetains;
		this.goal = goal;
	}
}
