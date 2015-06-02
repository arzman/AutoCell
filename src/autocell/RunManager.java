package autocell;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RunManager {

	private static RunManager _instance;
	private JPanel _graphPanel;

	private RunCellSimulationThread _thread;

	private RunManager() {

	}

	public static RunManager getInstance() {

		if (_instance == null) {
			_instance = new RunManager();
		}

		return _instance;
	}

	public void registerGraph(JPanel graphPanel) {

		_graphPanel = graphPanel;

	}

	public void makeStep(int numStep) {

		for (int step = 0; step < numStep; step++) {

			// calcul de l'état suivant
			for (int i = 0; i < CellManager.getInstance().getRowSize(); i++) {
				for (int j = 0; j < CellManager.getInstance().getColSize(); j++) {

					CellManager.getInstance().computeEvolve(i, j);

				}
			}

			// maj de l'état
			for (int i = 0; i < CellManager.getInstance().getRowSize(); i++) {
				for (int j = 0; j < CellManager.getInstance().getColSize(); j++) {

					CellManager.getInstance().evolve(i, j);

				}
			}

		}

		if (SwingUtilities.isEventDispatchThread()) {
			_graphPanel.repaint();
		}

	}

	public void stopRun() {
		if (_thread != null) {
			_thread.stopRun();
			_thread = null;
		}

	}

	public void startThreadRun(String string,int msToSleep,int numPas) {

		if (_thread == null) {
			_thread = new RunCellSimulationThread(_graphPanel,msToSleep,numPas);
			_thread.startRun();
		}

	}

}
