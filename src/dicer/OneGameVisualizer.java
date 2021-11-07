package dicer;

import java.util.List;

public class OneGameVisualizer {

	public static void main(String[] args) {
		GameMaster gm = GameMasterFactory.get(new Rules(15, 6, 10, 6));
		GameMaster.GameResult result = gm.play();
		System.out.println(result);
		List<Contestant> winners = result.getWinners();
		System.out.println("\nWinners:");
		for (Contestant w : winners) {
			System.out.println(w.getClass().getSimpleName());
		}
	}

}
