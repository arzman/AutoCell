package autocell.lifegame;

import java.awt.Color;

import javax.swing.JOptionPane;

import autocell.Cell;
import autocell.ICellSimuConf;

public class LifeGameSimuConf implements ICellSimuConf {

	private Color MORT = Color.WHITE;

	private Color VIVANTE = Color.BLACK;

	private double _pcVivante;

	@Override
	public Cell createCell(String initMeth) {

		int etat;
		if (Math.random() < _pcVivante) {
			etat = 1;
		} else {
			etat = 0;
		}

		return new LifeGameCell(etat);
	}

	@Override
	public Color getColorForEtat(int etat) {

		Color res = null;
		if (etat == 1) {
			res = VIVANTE;
		} else {
			res = MORT;
		}

		return res;
	}

	@Override
	public void invokeSpecialInit() {

		boolean isGood = false;

		while (!isGood) {

			String res = JOptionPane.showInputDialog(null, "Entrez le % de cellules vivantes", "Configuration", JOptionPane.QUESTION_MESSAGE);

			try {
				double p = Double.parseDouble(res.trim());
				isGood = true;
				_pcVivante = p / 100;
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "La valeur saisie doit être un décimal");
				isGood = false;
			}

		}

	}

	@Override
	public void invokePostInit() {
		// rien

	}

}
