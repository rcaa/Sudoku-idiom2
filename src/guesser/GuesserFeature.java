package guesser;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import base.Board;
import base.BoardManager;

@Aspect
public class GuesserFeature {
	
	@Pointcut("execution(* base.BoardManager.id_pattern(..)) && this(cthis) && args(board, solutions)")
	public void hook_guesser(BoardManager cthis, Board board, List solutions) {}
	
	@Before("hook_guesser(cthis, board, solutions)")
	public void before1(BoardManager cthis, Board board, List solutions) {
		Guesser guesser = new Guesser();
		List guessed = guesser.guess(board);
		for (int i = 0; i < guessed.size(); i++)
			solutions.addAll(cthis.solve(((Board) guessed.get(i))));
	}
}