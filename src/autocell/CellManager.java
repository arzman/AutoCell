package autocell;

public class CellManager {

	private static CellManager _instance;

	private Cell[][] _cells;

	private boolean _isInitialized;

	public CellManager() {

		int nbreCell = 100;
		_cells = new Cell[nbreCell][nbreCell];
		_isInitialized = false;
	}

	public void initialize(String modelId, String initMeth) {

		CellSimuConfManager.getInstance().selectConf(modelId);

		CellSimuConfManager.getInstance().invokeSpecialInit();

		for (int i = 0; i < _cells.length; i++) {
			for (int j = 0; j < _cells[i].length; j++) {
				_cells[i][j] = CellSimuConfManager.getInstance().createCell(initMeth);
			}
		}
		
		
		// 0 1 2
		// 3 C 4
		// 5 6 7

		for (int i = 0; i < _cells.length; i++) {
			for (int j = 0; j < _cells[i].length; j++) {

				Cell curCell = _cells[i][j];

				if (i - 1 > -1) {

					if (j - 1 > -1) {
						curCell.addCellVoisine(_cells[i - 1][j - 1],0);
					}
					curCell.addCellVoisine(_cells[i - 1][j],1);
					if (j + 1 < _cells[i].length) {
						curCell.addCellVoisine(_cells[i - 1][j + 1],2);
					}
				}

				if (j - 1 > -1) {
					curCell.addCellVoisine(_cells[i][j - 1],3);
				}

				if (j + 1 < _cells[i].length) {
					curCell.addCellVoisine(_cells[i][j + 1],4);
				}

				if (i + 1 < _cells.length) {

					if (j - 1 > -1) {
						curCell.addCellVoisine(_cells[i + 1][j - 1],5);
					}
					curCell.addCellVoisine(_cells[i + 1][j],6);
					if (j + 1 < _cells[i].length) {
						curCell.addCellVoisine(_cells[i + 1][j + 1],7);
					}
				}

			}
		}

		CellSimuConfManager.getInstance().invokePostInit();

		_isInitialized = true;

	}

	public static CellManager getInstance() {

		if (_instance == null) {
			_instance = new CellManager();
		}

		return _instance;
	}

	public void nextStep(int numStep) {

	}

	public int getEtatAt(int row, int col) {
		return _cells[row][col].getEtat();
	}

	public int getRowSize() {
		return _cells.length;
	}

	public int getColSize() {
		return _cells[0].length;
	}

	public boolean isInitialized() {
		return _isInitialized;

	}

	public void computeEvolve(int row, int col) {
		_cells[row][col].computeEvolve();

	}

	public void evolve(int row, int col) {
		_cells[row][col].evolve();

	}

	public void setEtatAt(int row, int col, int etat) {
		_cells[row][col].setEtat(etat);

	}

}
