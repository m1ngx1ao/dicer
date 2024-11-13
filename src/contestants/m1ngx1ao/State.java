package contestants.m1ngx1ao;

import java.util.*; 

public class State {
	public int pointsLeft, throwsLeft, retainsLeft, diceSides;

	public State(int pointsLeft, int throwsLeft, int retainsLeft, int diceSides) {
		this.pointsLeft = pointsLeft;
		this.throwsLeft = throwsLeft;
		this.retainsLeft = retainsLeft;
		this.diceSides = diceSides;
	}
	
	private int[] getElems() {
		return new int[] {pointsLeft, throwsLeft, retainsLeft, diceSides};
	}

	public boolean hasWon() {
		return pointsLeft == 0 && retainsLeft == 0;
	}

	public boolean hasLost() {
		return pointsLeft < 0 || (pointsLeft > 0 && (throwsLeft == 0 || retainsLeft == 0)) || (retainsLeft != 0 && pointsLeft == 0);
	}
	
	@Override
	public String toString() {
		return "(" + pointsLeft + "/" + throwsLeft + "/" + retainsLeft + "/" + diceSides + ")";
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(getElems());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof State)) {
			return false;
		}
		int[] myElems = getElems();
		int[] oElems = ((State) o).getElems();
		return Arrays.equals(myElems, oElems);
	}
}
