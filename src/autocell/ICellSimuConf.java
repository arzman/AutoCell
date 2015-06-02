package autocell;

import java.awt.Color;

public interface ICellSimuConf {

	Cell createCell(String initMeth);
	
	Color getColorForEtat(int etat);
	
	void invokeSpecialInit();
	
	void invokePostInit();

}
