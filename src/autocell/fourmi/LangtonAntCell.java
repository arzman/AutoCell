package autocell.fourmi;

import autocell.Cell;

/***
 * 
 * 
 * BLANC = 0 NOIR = 1 FOURMI = 2
 * 
 */
public class LangtonAntCell extends Cell {

	int etatSauv = 0;

	public LangtonAntCell(int etat) {
		super(etat);
	}

	@Override
	public void computeEvolve() {

		// la fourmi est du bon coté
		Cell cellToLook = _cellVoisines[LangtonAntSimuConf.getInstance().numCellToLook()];
		if (cellToLook == null) {
			_etatSuivant = _etatCourant;
		} else {

			if (cellToLook.getEtat() == 2) {
				// devient la fourmi
				_etatSuivant = 2;
				// sauve de son état
				etatSauv = _etatCourant;

			} else {
				if (_etatCourant != 2) {
					// ne change pas d'état
					_etatSuivant = _etatCourant;
				} else {
					// ancienne fourmi, swith de l'état

					if (etatSauv == 0) {
						_etatSuivant = 1;

					} else {
						_etatSuivant = 0;
					}

				}

			}
		}

	}

	@Override
	public void evolve() {

		if (_etatSuivant == 2) {
			LangtonAntSimuConf.getInstance().turn(etatSauv);

		}

		super.evolve();
	}

}
