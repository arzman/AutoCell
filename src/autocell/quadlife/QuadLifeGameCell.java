package autocell.quadlife;

import autocell.Cell;

/***
 * 
 * 
 * MORT = 0 VIVANTE_1 = 1 VIVANTE_2 = 2 VIVANTE_3 = 3 VIVANTE_4 = 4
 * 
 */
public class QuadLifeGameCell extends Cell {

	public QuadLifeGameCell(int etat) {
		super(etat);
	}

	@Override
	public void computeEvolve() {

		if (_etatCourant == 0) {
			// si la cellule est morte on regarde si elle peut naitre

			int[] etatCellule = new int[] { 0, 0, 0, 0, 0 };

			for (Cell cell : _cellVoisines) {

				if (cell != null) {
					etatCellule[cell.getEtat()] = etatCellule[cell.getEtat()] + 1;
				}

			}

			// 3 voisines -> naissances
			if (etatCellule[1] + etatCellule[2] + etatCellule[3] + etatCellule[4] == 3) {

				// détermination de la couleur
				int indexVide = -1;
				int indexMax = -1;
				int max = -1;

				for (int i = 1; i < etatCellule.length; i++) {

					if (etatCellule[i] == 0) {
						indexVide = i;
					}

					if (etatCellule[i] > max) {
						max = etatCellule[i];
						indexMax = i;
					}

				}

				if (max == 1) {
					// les 3 couleurs sont présente, on prend celle qui manque
					_etatSuivant = indexVide;
				} else {
					// on prend la majorité
					_etatSuivant = indexMax;
				}

			} else {
				// pas de naissance
				_etatSuivant = 0;
			}

		} else {

			// la cellule est vivante, on regarde si elle le reste
			int cmpt = 0;

			for (Cell cell : _cellVoisines) {

				if (cell != null) {
					if (cell.getEtat() != 0) {
						cmpt = cmpt + 1;
					}
				}
			}

			if (cmpt == 3 || cmpt == 2) {
				_etatSuivant = _etatCourant;
			} else {
				_etatSuivant = 0;
			}

		}

	}

}
