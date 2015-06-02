package autocell.quadlife;

import java.awt.Color;

import autocell.Cell;
import autocell.ICellSimuConf;

public class QuadLifeSimuConf implements ICellSimuConf {

	private Color MORT = Color.WHITE;

	private Color VIVANTE_1 = Color.BLUE;

	private Color VIVANTE_2 = Color.RED;

	private Color VIVANTE_3 = Color.GREEN;

	private Color VIVANTE_4 = Color.BLACK;

	@Override
	public Cell createCell(String initMeth) {

		int etat = (int) (Math.random() *4+1);
		
		if(Math.random()<0.5){
			etat = 0;
		}

		return new QuadLifeGameCell(etat);
	}

	@Override
	public Color getColorForEtat(int etat) {

		Color res = null;

		switch (etat) {
		case 1:
			res = VIVANTE_1;
			break;
		case 2:
			res = VIVANTE_2;
			break;
		case 3:
			res = VIVANTE_3;
			break;
		case 4:
			res = VIVANTE_4;
			break;
		default:
			res = MORT;
			break;
		}

		return res;
	}

	@Override
	public void invokeSpecialInit() {
		// rien

	}

	@Override
	public void invokePostInit() {

		// rien

	}


}
