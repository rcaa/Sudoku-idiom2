package undo;

import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import base.Board;
import base.BoardManager;
import base.Gui;
import base.ListenerFactory;
import base.SudokuGenerator;

public privileged aspect UndoFeatureInter {
	
	public ActionListener ListenerFactory.getUndoListener() {
		return new UndoListener(bm);
	}
	
	public void BoardManager.undo() {
		if (!history.empty()) {
			board = (Board) history.pop();
			updateSudokuViews();
		}
	}	
	public JMenuItem Gui.createUndoMenuItem() {
		JMenuItem loadMenuItem = new JMenuItem();
		loadMenuItem.setText("Undo");
		loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				Event.CTRL_MASK, true));
		loadMenuItem.addActionListener(listenerFactory.getUndoListener());
		return loadMenuItem;
	}
}