package autocell.forestfire;

import java.awt.Color;

import javax.swing.JOptionPane;

import autocell.Cell;
import autocell.CellManager;
import autocell.ICellSimuConf;

public class ForestFireConf implements ICellSimuConf {

	private Color MORT = Color.WHITE;

	private Color CENDRES = Color.GRAY;

	private Color FEU = Color.RED;

	private Color ARBRES = Color.GREEN;

	private double _portionArbre = 0.5;

	@Override
	public Cell createCell(String initMeth) {

		int etat;

		if (Math.random() < _portionArbre) {
			etat = 3;
		} else {
			etat = 0;
		}

		return new ForestFireCell(etat);
	}

	@Override
	public Color getColorForEtat(int etat) {

		Color res = null;

		switch (etat) {
		case 1:
			res = CENDRES;
			break;
		case 2:
			res = FEU;
			break;
		case 3:
			res = ARBRES;
			break;
		default:
			res = MORT;
			break;
		}

		return res;
	}

	@Override
	public void invokeSpecialInit() {

		boolean isGood = false;

		while (!isGood) {

			String res = JOptionPane.showInputDialog(null, "Entrez le % d'arbres", "Configuration", JOptionPane.QUESTION_MESSAGE);

			try {
				double p = Double.parseDouble(res.trim());
				isGood = true;
				_portionArbre = p/100;
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "La valeur saisie doit être un décimal");
				isGood = false;
			}

		}

	}

	@Override
	public void invokePostInit() {

		int i = CellManager.getInstance().getRowSize() / 2;
		int j = CellManager.getInstance().getColSize() / 2;

		CellManager.getInstance().setEtatAt(i, j, 2);

	}

}
