package contestants.m1ngxu;

import dicer.Contestant;
import dicer.Rules;
import java.util.Base64;

public class M1ngXU implements Contestant {
    private static byte[] best = Base64.getDecoder().decode(
            "AAAAAAAAAAAAAAAAAAAAAAAAPM/zAADw//8DAMD//w8AAO7/PwAAuP//AADg/v8DAID7/w8AAO7/PwAAAAAAAAAAAAAAAAAAAAAAgO/7PgAA/v//AADw//8DAMD//w8AAO//PwAAvP//AADw/v8DAMD7/w8AAAAAAAAAAAAAAAAAAAAAAPD//w8AgP//PwAA/v//AAD4//8DAMD//w8AAP//PwAA/P//AADw//8DAAAAAAAAAAAAAACAIAiCAAD+//8DAOj//w8AoP//PwCA/v//AAD6//8DAOj//w8AoP//PwCA/v//AAAAAAAAAAAAAAAAMAzDMADA////AAD///8DAOz//w8AsP//PwDA/v//AAD7//8DAOz//w8AsP//PwAAAAAAAAAAAAAAAI7jOA4A+P//PwDg////AID///8DAP7//w8A+P//PwDg////AID///8DAP7//w8AAAAAAAAAAAAAAMDzPM8DAP///w8A/P//PwDw////AMD///8DAP///w8A/P//PwDw////AMD///8DAAAAAAAAAAAAAAD4vu/7AOD///8DgP///w8A/v//PwD4////AOD///8DgP///w8A/v//PwD4////AAAAAAAAAAAAAAAA////PwD8////APD///8DwP///w8A////PwD8////APD///8DwP///w8A////PwiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAiCIAhBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEARBEAQhCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgCIIgBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQBEEQhCAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIgiAIghAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAEQRAA");

    @Override
    public void setup(Rules r) {
        assert r.targetPoints == 15;
        assert r.diceSides == 6;
        assert r.maxThrows == 10;
        assert r.maxRetains == 6;
    }

    @Override
    public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
        if (currentPoints >= 15)
               return false;
        int idx = currentPoints * 11 * 7 * 6 + throwsLeft * 7 * 6 + retainsLeft * 6 + diceResult - 1;
        return (best[idx >> 3] & (1L << (idx & 7))) != 0;
    }
}
