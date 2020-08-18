import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AI {

    public int choose(tictac Game){
        return calculateBestMove(Game, 0, new HashMap<>());
    }

    public int calculateBestMove(tictac Game, int recursive, Map<Integer, Integer> potentialMoves){
        int TIE = 0;
        int WIN = -1;
        if (Game.isGameTied()) {
            return TIE;
        } else if (Game.isGameOVer()){
            return WIN;
        }
        else {
            checkPossibilities(Game, recursive, potentialMoves);
            if (recursive == 0) {
                return chooseBestMove(potentialMoves);
            } else {
                return bestScore(potentialMoves);
            }

        }

    }
    
    private void checkPossibilities(tictac Game, int recursive, Map<Integer, Integer> potentialMoves){
        for (Integer i : Game.getEmptyFields()){
            int[] position = Game.convertSpaceToRowCol(i);
            int row = position[0];
            int col = position[1];
            emulateTurn(Game, row, col);
            addPossibilityToOutcomes(Game, recursive, potentialMoves, row, col);
            resetBoard(Game, row, col);

        }
    }

    private void addPossibilityToOutcomes(tictac Game, int recursive, Map<Integer, Integer> potentialMoves, int row, int col){
        int space = Game.convertRowColToSpace(row, col);
        potentialMoves.put(space, -1 * (calculateBestMove(Game, recursive + 1, new HashMap<>())));
    }

    private void emulateTurn(tictac Game, int row, int col){
        Game.placeMark(row, col);
        Game.changePlayer();
    }

    private void resetBoard(tictac Game, int row, int col){
        Game.resetSpace(row, col);
        Game.changePlayer();
    }



    private int chooseBestMove(Map<Integer, Integer> potentialMoves){
        return potentialMoves.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    private int bestScore(Map<Integer, Integer> potentialMoves){
        return potentialMoves.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
    }

}
