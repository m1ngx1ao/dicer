package contestants.m1ngx1ao;

import java.util.*; 

public class State {
	public int pointsLeft, throwsLeft, retainsLeft;

	public State(int pointsLeft, int throwsLeft, int retainsLeft) {
		this.pointsLeft = pointsLeft;
		this.throwsLeft = throwsLeft;
		this.retainsLeft = retainsLeft;
	}	
	
	private int[] getElems() {
		return new int[] {pointsLeft, throwsLeft, retainsLeft};
	}

	@Override
	public String toString() {
		return "(" + pointsLeft + "/" + throwsLeft + "/" + retainsLeft + ")";
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
