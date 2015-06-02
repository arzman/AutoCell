package autocell;

import java.awt.Color;
import java.util.HashMap;
import java.util.Set;

import autocell.forestfire.ForestFireConf;
import autocell.fourmi.LangtonAntSimuConf;
import autocell.lifegame.LifeGameSimuConf;
import autocell.quadlife.QuadLifeSimuConf;

public class CellSimuConfManager {

	private static CellSimuConfManager _instance;

	private HashMap<String, ICellSimuConf> _confs;

	private ICellSimuConf _selectedConf;

	private CellSimuConfManager() {

		_confs = new HashMap<String, ICellSimuConf>();
		_confs.put("Jeu de la vie", new LifeGameSimuConf());
		_confs.put("QuadLife", new QuadLifeSimuConf());
		_confs.put("Feu de forêt", new ForestFireConf());
		_confs.put("Fourmi",  LangtonAntSimuConf.getInstance());

	}

	public static CellSimuConfManager getInstance() {

		if (_instance == null) {
			_instance = new CellSimuConfManager();
		}

		return _instance;
	}

	public Set<String> getAvailableConfId() {
		return _confs.keySet();
	}

	public ICellSimuConf getSimuConf(String key) {
		return _confs.get(key);
	}

	public Color getColorForEtat(int etatAt) {
		return _selectedConf.getColorForEtat(etatAt);
	}

	public boolean isInitialized() {
		return _selectedConf != null;

	}

	public Cell createCell(String initMeth) {
		return _selectedConf.createCell(initMeth);
	}

	public void selectConf(String modelId) {

		_selectedConf = _confs.get(modelId);

	}

	public void invokeSpecialInit() {

		if (_selectedConf != null) {

			_selectedConf.invokeSpecialInit();
		}

	}

	public void invokePostInit() {

		if (_selectedConf != null) {

			_selectedConf.invokePostInit();
		}

	}

}
