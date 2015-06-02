package autocell;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RunCellSimulationThread extends Thread {

	private boolean _canRun;

	private final JPanel _graph;

	private int _msToSleep;

	private int _numPas;

	public RunCellSimulationThread(JPanel graph, int msToSleep, int numPas) {
		_canRun = true;
		_graph = graph;
		_numPas = numPas;
		_msToSleep = msToSleep;
	}

	public void stopRun() {
		_canRun = false;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (_canRun) {

			try {

				if (_msToSleep > 0) {
					sleep(_msToSleep);
				}

				RunManager.getInstance().makeStep(_numPas);

				SwingUtilities.invokeAndWait(new Runnable() {

					@Override
					public void run() {
						_graph.repaint();

					}
				});

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void startRun() {

		start();

	}

}
