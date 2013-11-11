package solver;

import java.util.List;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import base.Board;
import base.BoardManager;

@Aspect
public class SolverFeature {

	@Pointcut("execution(* base.BoardManager.hook_generateSolutions(..)) && this(cthis)")
	public void hook_generateSolutions(BoardManager cthis) {}
	
	@Around("hook_generateSolutions(cthis)")
	public List around1(BoardManager cthis) throws CloneNotSupportedException {
		return cthis.solve((Board) cthis.board.clone());
	}
	
	@Pointcut("execution(* base.BoardManager.hook_solveSudokuGenerator(..)) && args(board, bm)")
	public void hook_solveSudokuGenerator(Board board, BoardManager bm) {}

	@Around("hook_solveSudokuGenerator(board, bm)")
	public List around(Board board, BoardManager bm) throws CloneNotSupportedException {
		return bm.solve((Board) board.clone());
	}
}
