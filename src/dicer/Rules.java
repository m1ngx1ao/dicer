package dicer;

public class Rules {
	public int targetPoints;
	public int diceSides;
	public int maxThrows;
	public int maxRetains;

	/**
	 * @param targetPoints How many points are needed to win?
	 * @param diceSides How many sides does the dice have?
	 * @param maxThrows How many times are we allowed to throw the dice?
	 * @param maxRetains How many times are we allowed to retain the dice result?
	 */
	public Rules(int targetPoints, int diceSides, int maxThrows, int maxRetains) {
		this.targetPoints = targetPoints;
		this.diceSides = diceSides;
		this.maxThrows = maxThrows;
		this.maxRetains = maxRetains;
	}
}
