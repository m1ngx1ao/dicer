package dicer;

import java.util.List;

import dicer.GameMaster.ContestantLoss;
import dicer.Rules.Goal;

public class OneGameVisualizer {

	public static void main(String[] args) {
		GameMaster gm = GameMasterFactory.get(new Rules(
			15, 6, 10, 6, true,
			//Goal.obtainTargetPoints
			Goal.minimizeDistanceToTargetPoints
		));
		GameMaster.GameResult result = gm.play();
		System.out.println(result);
		List<Contestant> winners = result.getWinners();
		System.out.println("\nWinners:");
		for (Contestant w : winners) {
			System.out.println(w.getClass().getSimpleName());
		}
		System.out.println("\nLosses:");
		for (ContestantLoss cl : result.getSortedContestantLosses()) {
			System.out.println("" + cl.loss + " <-- " + cl.contestant.getClass().getSimpleName());
		}
	}

}
