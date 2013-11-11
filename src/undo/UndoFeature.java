package undo;

import javax.swing.JMenu;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import base.BoardManager;
import base.Gui;

@Aspect
public class UndoFeature {
	
	@Pointcut("execution(* base.Gui.hook_createUndoItem(..)) && this(cthis) && args(optionsMenu)")
	public void hook_createUndoItem(Gui cthis, JMenu optionsMenu) {}
	
	@Before("hook_createUndoItem(cthis, optionsMenu)")
	public void before1(Gui cthis, JMenu optionsMenu) {
		optionsMenu.add(cthis.createUndoMenuItem());
	}

	@Pointcut("execution(* base.BoardManager.hook_undo(..)) && this(cthis)")
	public void hook_undo(BoardManager cthis) {}
	
	@Before("hook_undo(cthis)")
	public void before2(BoardManager cthis) {
		cthis.undo();
	}

	@Pointcut("execution(* base.SudokuGenerator.hook_undo2(..)) && args(bm)")
	public void hook_undo2(BoardManager bm) {}
	
	@Before("hook_undo2(bm)")
	public void before3(BoardManager bm) {
		bm.undo();
	}
}
