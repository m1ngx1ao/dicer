package dicer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameMaster {
	private Rules rules;
	private List<Contestant> contestants;

	public class ContestantLoss implements Comparable<ContestantLoss> {
		public Contestant contestant;
		public int loss;

		public ContestantLoss(Contestant contestant, int loss) {
			this.contestant = contestant;
			this.loss = loss;
		}

		@Override
		public int compareTo(ContestantLoss other) {
			return loss - other.loss;
		}
	}

	public class GameResult {
		private List<ContestantLoss> contestantLosses;
		private List<Contestant> winners;
		private List<Integer> diceResults;
		private Map<Contestant, List<Boolean>> retainDecisionsByContestant;
		
		private GameResult() {
			winners = new LinkedList<Contestant>();
			diceResults = new LinkedList<Integer>();
			retainDecisionsByContestant = new HashMap<Contestant,List<Boolean>>(); 
		}
		
		public List<Contestant> getWinners() {
			return winners;
		}

		public List<ContestantLoss> getSortedContestantLosses() {
			Collections.sort(contestantLosses);
			return contestantLosses;
		}
		
		@Override
		public String toString() {
			String r = diceResults.stream().map(dr -> "" + dr).reduce((a, b) -> a + "_" + b).get();
			for (Contestant c : contestants) {
				r += "\n" + retainDecisionsByContestant.get(c).stream().map(d -> d ? "X" : " ")
						.reduce((a, b) -> a + "_" + b).get()
						+ " <-- " + c.getClass().getSimpleName();
			}
			return r;
		}
	}
	
	public GameMaster(Rules rules,
			List<Contestant> contestants) {
		this.rules = rules;
		this.contestants = contestants;
		for (Contestant c : contestants) {
			c.setup(rules);
		}
	}
	
	/**
	 * Guaranteed to be free of side effects
	 */
	public GameResult play() {
		GameResult result = new GameResult();
		for (int i = 0; i < rules.maxThrows; i++) {
			result.diceResults.add((int) Math.floor(Math.random() * rules.diceSides) + 1);
		}
		result.contestantLosses = new LinkedList<ContestantLoss>();
		for (Contestant c : contestants) {
			int currentPoints = 0;
			int retainsLeft = rules.maxRetains;
			Iterator<Integer> dri = result.diceResults.iterator();
			List<Boolean> retainDecisions = new LinkedList<Boolean>();
			for (int throwsLeft = rules.maxThrows; throwsLeft > 0; throwsLeft--) {
				int diceResult = dri.next();
				boolean toBeRetained = c.toBeRetained(currentPoints, throwsLeft, retainsLeft, diceResult);
				if (throwsLeft == retainsLeft && rules.mustUseUpAllRetains) {
					// overrule contestant to ensure that retain count becomes zero at the end (if required)
					toBeRetained = true;
				}
				if (toBeRetained && retainsLeft > 0) {
					currentPoints += diceResult;
					retainsLeft--;
				}
				retainDecisions.add(toBeRetained);
			}
			result.retainDecisionsByContestant.put(c, retainDecisions);
			result.contestantLosses.add(new ContestantLoss(c, Math.abs(rules.targetPoints - currentPoints)));
			if (currentPoints == rules.targetPoints) {
				result.winners.add(c);
			}
		}
		return result;
	}
}
