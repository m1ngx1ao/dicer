package dicer;

public interface Contestant {
	/**
	 * Guaranteed to be called exactly once before any decisions are to be made
	 */
	public void setup(Rules rules);
	
	/**
	 * @param currentPoints How many points do we have up to now?
	 * @param throwsLeft How many dice throws are left (including this throw for which the retain decision
	 * is to be made)?
	 * @param retainsLeft How many times are we still allowed to retain the dice result?
	 * @param diceResult What is the result of the next dice throw (for which the retain decision is
	 * to be made)?
	 * @return True if diceResult is to be retained
	 */
	public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult);
}
