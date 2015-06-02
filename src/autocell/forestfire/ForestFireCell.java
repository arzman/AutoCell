package autocell.forestfire;

import autocell.Cell;

/***
 * 
 * 
 * MORT = 0 CENDRES = 1 FEU = 2 ARBRES = 3
 * 
 */
public class ForestFireCell extends Cell {

	public ForestFireCell(int etat) {
		super(etat);
	}

	@Override
	public void computeEvolve() {

		switch (_etatCourant) {
		case 0:
			_etatSuivant = 0;
			break;
		case 1:
			_etatSuivant = 0;
			break;
		case 2:
			_etatSuivant = 1;
			break;
		case 3:
			checkArbres();
			break;
		default:
			_etatSuivant = 0;
			break;

		}

	}

	private void checkArbres() {

		_etatSuivant = 3;

		for (Cell cell : _cellVoisines) {

			if (cell != null) {
				if (cell.getEtat() == 2) {
					_etatSuivant = 2;
				}
			}

		}

	}

}
