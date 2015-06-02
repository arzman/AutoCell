package autocell.fourmi;

import java.awt.Color;

import autocell.Cell;
import autocell.CellManager;
import autocell.ICellSimuConf;

public class LangtonAntSimuConf implements ICellSimuConf {

	private static LangtonAntSimuConf _instance;

	// # 0 #
	// 1 F 3
	// # 2 #
	private int directionFourmi;

	private LangtonAntSimuConf() {

		directionFourmi = 2;

	}

	public static LangtonAntSimuConf getInstance() {

		if (_instance == null) {
			_instance = new LangtonAntSimuConf();
		}

		return _instance;
	}

	// 0
	private Color BLANC = Color.WHITE;

	// 1
	private Color NOIR = Color.BLACK;

	// 2
	private Color FOURMI = Color.RED;

	@Override
	public Cell createCell(String initMeth) {

		return new LangtonAntCell(0);
	}

	@Override
	public Color getColorForEtat(int etat) {

		Color res = null;

		switch (etat) {
		case 0:
			res = BLANC;
			break;
		case 1:
			res = NOIR;
			break;
		case 2:
			res = FOURMI;
			break;

		default:
			res = BLANC;
			break;
		}

		return res;
	}

	@Override
	public void invokeSpecialInit() {

	}

	@Override
	public void invokePostInit() {

		int i = CellManager.getInstance().getRowSize() / 2;
		int j = CellManager.getInstance().getColSize() / 2;

		CellManager.getInstance().setEtatAt(i, j, 2);

		directionFourmi = 2;

	}

	/**
	 * Fait tourner la fourmi suivant l'état de la case
	 * 
	 * @param etat
	 */
	public void turn(int etat) {

		// blanc -> gauche
		if (etat == 0) {

			directionFourmi = directionFourmi - 1;

		} else {
			// noir -> droite

			directionFourmi = directionFourmi + 1;
		}

		if (directionFourmi < 0) {
			directionFourmi = directionFourmi + 4;
		}

		if (directionFourmi > 3) {
			directionFourmi = directionFourmi - 4;
		}

	}

	/**
	 * Rappel des cases :
	 * 
	 * 0 1 2 3 C 4 5 6 7
	 * 
	 */
	public int numCellToLook() {

		int res;

		switch (directionFourmi) {
		case 0:
			res = 6;
			break;
		case 1:
			res = 4;
			break;
		case 2:
			res = 1;
			break;
		case 3:
			res = 3;
			break;

		default:
			res = -1;
			break;

		}

		return res;

	}

}
