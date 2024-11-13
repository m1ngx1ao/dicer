package contestants.tensor;

class State {
	int currentPoints;
	int throwsLeft;
	int retainsLeft;

	public State(int currentPoints, int throwsLeft, int retainsLeft) {
		this.currentPoints = currentPoints;
		this.throwsLeft = throwsLeft;
		this.retainsLeft = retainsLeft;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof State)) {
			return false;
		}
		State otherState = (State) other;
		return currentPoints == otherState.currentPoints && throwsLeft == otherState.throwsLeft
				&& retainsLeft == otherState.retainsLeft;
	}

	@Override
	public String toString() {
		return "(" + currentPoints + ", " + throwsLeft + ", " + retainsLeft + ")";
	}
}

