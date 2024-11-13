package dicer;

import java.util.HashMap;
import java.util.Map;

import dicer.GameMaster.ContestantLoss;
import dicer.Rules.Goal;

public class Simulator {

	public static void main(String[] args) {
		final int NUMBER_OF_GAMES = 100000;
		Map<Contestant, Integer> winsByContestant = new HashMap<Contestant, Integer>();
		Map<Contestant, Integer> totalLossByContestant = new HashMap<Contestant, Integer>();
		GameMaster gm = GameMasterFactory.get(new Rules(
			15, 6, 10, 6, false,
			//Goal.obtainTargetPoints
			Goal.minimizeDistanceToTargetPoints
		));
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			GameMaster.GameResult result = gm.play();
			for (Contestant c : result.getWinners()) {
				Integer formerWins = winsByContestant.get(c);
				winsByContestant.put(c, 1 + (formerWins == null ? 0 : formerWins));
			}
			for (ContestantLoss cl : result.getSortedContestantLosses()) {
				Integer formerTotalLoss = totalLossByContestant.get(cl.contestant);
				totalLossByContestant.put(cl.contestant, cl.loss + (formerTotalLoss == null ? 0 : formerTotalLoss));
			}
		}
		
		System.out.println("Wins:");
		for (Contestant c : winsByContestant.keySet()) {
			System.out.println(c.getClass().getSimpleName() + ": "
					+ Math.round(100000.0 * winsByContestant.get(c) / NUMBER_OF_GAMES) / 1000.0 + "%");
		}
		
		System.out.println("\nAverage Loss:");
		for (Contestant c : totalLossByContestant.keySet()) {
			System.out.println(c.getClass().getSimpleName() + ": "
					+ Math.round(1000.0 * totalLossByContestant.get(c) / NUMBER_OF_GAMES) / 1000.0);
		}
	}

}
