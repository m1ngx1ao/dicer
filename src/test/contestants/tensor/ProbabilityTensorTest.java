package test.contestants.tensor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contestants.tensor.ProbabilityTensor;
import dicer.Rules;

class ProbabilityTensorTest {

	static ProbabilityTensor pt;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pt = new ProbabilityTensor(new Rules(15, 6, 10, 6));
		System.out.println(pt.get(0, 10, 6));
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	private void assertProbability(double expected, int currentPoints, int throwsLeft, int retainsLeft) {
		final double precision = 1000000;
		assertEquals(Math.round(expected * precision),
				Math.round(pt.get(currentPoints, throwsLeft, retainsLeft) * precision));
	}

	@Test
	void onTargetOutOfThrowsSure() {
		assertProbability(1.0, 15, 0, 3);
	}

	@Test
	void offTargetOutOfThrowsImpossible() {
		assertProbability(0.0, 14, 0, 3);
	}

	@Test
	void onTargetOutOfRetainsSure() {
		assertProbability(1.0, 15, 3, 0);
	}

	@Test
	void offTargetOutOfRetainsImpossible() {
		assertProbability(0.0, 14, 3, 0);
	}

	@Test
	void nearTargetOneThrowLeftPossible() {
		assertProbability(1.0 / 6, 14, 1, 3);
	}

	@Test
	void offTargetOneThrowLeftPossible() {
		assertProbability(1.0 / 6, 9, 1, 3);
	}

	@Test
	void offTargetOneThrowLeftImpossible() {
		assertProbability(0.0, 8, 1, 3);
	}

	@Test
	void nearTargetTwoThrowsLeftPossible() {
		assertProbability(1.0 / 6 + 5.0 / 6 * 1 / 6, 12, 2, 3);
	}

	@Test
	void nearTargetTwoThrowsOneRetainLeftPossible() {
		assertProbability(1.0 / 6 + 5.0 / 6 * 1 / 6, 12, 2, 1);
	}

	@Test
	void offTargetTwoThrowsLeftPossible() {
		assertProbability(1.0 / 6, 8, 2, 3);
	}

	@Test
	void offTargetTwoThrowsOneRetainLeftImpossible() {
		assertProbability(0.0, 8, 2, 1);
	}

	@Test
	void offTargetTwoThrowsLeftImprobable() {
		assertProbability(5.0 / 6 * 1.0 / 6, 7, 2, 3);
	}

	@Test
	void wideOffTargetTwoThrowsLeftImpossible() {
		assertProbability(0.0, 2, 2, 3);
	}
}
