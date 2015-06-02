package autocell.lifegame;

import autocell.Cell;

/***
 * 
 * 
 * MORT = 0 VIVANTE = 1
 * 
 */
public class LifeGameCell extends Cell {

	public LifeGameCell(int etat) {
		super(etat);
	}

	@Override
	public void computeEvolve() {

		if (_etatCourant == 0) {

			int cmpt = 0;

			for (Cell cel : _cellVoisines) {
				if (cel != null) {
					if (cel.getEtat() == 1) {
						cmpt = cmpt + 1;
					}
				}
			}

			if (cmpt == 3) {
				_etatSuivant = 1;
			} else {
				_etatSuivant = 0;
			}

		}

		if (_etatCourant == 1) {

			int cmpt = 0;

			for (Cell cel : _cellVoisines) {
				if (cel != null) {
					if (cel.getEtat() == 1) {
						cmpt = cmpt + 1;
					}
				}
			}

			if (cmpt == 3 || cmpt == 2) {
				_etatSuivant = 1;
			} else {
				_etatSuivant = 0;
			}
		}

	}

}
