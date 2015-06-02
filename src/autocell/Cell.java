package autocell;

import java.util.Arrays;


/**
 * 
 * Emplacement des cellules voisines.
 * 
 *  0 1 2 
 *  3 C 4
 *  5 6 7
 */

public abstract class Cell {

	protected Cell[] _cellVoisines;

	protected int _etatSuivant;

	protected int _etatCourant;

	protected ICellListener _listener;

	public Cell(Cell[] cellVoisines, int etat) {

		if (cellVoisines != null) {
			_cellVoisines = Arrays.copyOf(cellVoisines, cellVoisines.length);
		} else {
			_cellVoisines = new Cell[8];
		}
		_etatCourant = etat;
		_etatSuivant = -1;

	}

	public Cell(int etat) {

		this(null, etat);

	}

	public Cell() {
		this(0);
	}

	public abstract void computeEvolve();

	public void evolve() {
		_etatCourant = _etatSuivant;
	}

	public void addCellVoisine(Cell voisine,int pos) {
		_cellVoisines[pos] = voisine;
	}

	public int getEtat() {
		return _etatCourant;
	}

	public void setEtat(int etat) {
		_etatCourant = etat;

	}

}
