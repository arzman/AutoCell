package autocell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class AutoCellFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _initButton;
	private JPanel _graphPanel;

	private final int GRAPH_FACTOR = 5;
	private JButton _btnSuivant;
	private JComboBox<String> _cellSimuCombo;
	private JTextField _pasFiled;

	private JButton _runThreadButton;
	private JButton _stopThreadButton;
	private JTextField _delayField;

	public AutoCellFrame() {

		setSize(500, 500);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout(gridBagLayout);

		JPanel graphicGroup = new JPanel();
		graphicGroup.setBorder(new TitledBorder(null, "Graphique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_graphicGroup = new GridBagConstraints();
		gbc_graphicGroup.insets = new Insets(0, 0, 0, 5);
		gbc_graphicGroup.weightx = 5.0;
		gbc_graphicGroup.fill = GridBagConstraints.BOTH;
		gbc_graphicGroup.gridx = 0;
		gbc_graphicGroup.gridy = 0;
		getContentPane().add(graphicGroup, gbc_graphicGroup);
		GridBagLayout gbl_graphicGroup = new GridBagLayout();
		graphicGroup.setLayout(gbl_graphicGroup);

		_graphPanel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4123549056673725994L;


			@Override
			public void paint(Graphics g) {

				super.paint(g);
				if (CellManager.getInstance().isInitialized() && CellSimuConfManager.getInstance().isInitialized()) {
					int etatAt;
					// calcul de l'état suivant
					for (int i = 0; i < CellManager.getInstance().getRowSize(); i++) {
						for (int j = 0; j < CellManager.getInstance().getColSize(); j++) {

							etatAt = CellManager.getInstance().getEtatAt(i, j);
							g.setColor(CellSimuConfManager.getInstance().getColorForEtat(etatAt));
							g.fillRect(i * GRAPH_FACTOR, j * GRAPH_FACTOR, 1 * GRAPH_FACTOR, 1 * GRAPH_FACTOR);

						}
					}
				}
			}

		};

		_graphPanel.setSize(CellManager.getInstance().getColSize(), CellManager.getInstance().getRowSize());

		GridBagConstraints gbc__graphPanel = new GridBagConstraints();
		gbc__graphPanel.weighty = 1.0;
		gbc__graphPanel.weightx = 1.0;
		gbc__graphPanel.fill = GridBagConstraints.BOTH;
		gbc__graphPanel.gridx = 0;
		gbc__graphPanel.gridy = 0;
		graphicGroup.add(_graphPanel, gbc__graphPanel);

		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.anchor = GridBagConstraints.NORTH;
		gbc_controlPanel.weighty = 1.0;
		gbc_controlPanel.weightx = 1.0;
		gbc_controlPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_controlPanel.gridx = 1;
		gbc_controlPanel.gridy = 0;
		getContentPane().add(controlPanel, gbc_controlPanel);
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWeights = new double[] { 1.0 };
		controlPanel.setLayout(gbl_controlPanel);

		JPanel initPanel = new JPanel();
		initPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Initialisation", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_initPanel = new GridBagConstraints();
		gbc_initPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_initPanel.weightx = 1.0;
		gbc_initPanel.gridx = 0;
		gbc_initPanel.gridy = 0;
		controlPanel.add(initPanel, gbc_initPanel);

		_cellSimuCombo = new JComboBox<String>();
		initPanel.add(_cellSimuCombo);

		_initButton = new JButton("Initialise");
		initPanel.add(_initButton);

		JPanel runPanel = new JPanel();
		runPanel.setBorder(new TitledBorder(null, "Execution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_runPanel = new GridBagConstraints();
		gbc_runPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_runPanel.weightx = 1.0;
		gbc_runPanel.gridx = 0;
		gbc_runPanel.gridy = 1;
		controlPanel.add(runPanel, gbc_runPanel);
		GridBagLayout gbl_runPanel = new GridBagLayout();
		runPanel.setLayout(gbl_runPanel);

		JPanel stepPanel = new JPanel();
		GridBagConstraints gbc_stepPanel = new GridBagConstraints();
		gbc_stepPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_stepPanel.insets = new Insets(0, 0, 0, 5);
		gbc_stepPanel.gridx = 0;
		gbc_stepPanel.gridy = 0;
		runPanel.add(stepPanel, gbc_stepPanel);

		_btnSuivant = new JButton("Suivant");
		stepPanel.add(_btnSuivant);

		JLabel label = new JLabel("Pas :");
		stepPanel.add(label);
		_pasFiled = new JTextField();
		stepPanel.add(_pasFiled);
		_pasFiled.setText("1");
		_pasFiled.setColumns(5);

		JPanel threadPanel = new JPanel();
		GridBagConstraints gbc_threadPanel = new GridBagConstraints();
		gbc_threadPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_threadPanel.gridx = 0;
		gbc_threadPanel.gridy = 1;
		runPanel.add(threadPanel, gbc_threadPanel);

		_runThreadButton = new JButton("Go !");
		threadPanel.add(_runThreadButton);

		_stopThreadButton = new JButton("Stop");
		threadPanel.add(_stopThreadButton);
		
		JLabel lblNewLabel = new JLabel("delai (ms)");
		threadPanel.add(lblNewLabel);
		
		_delayField = new JTextField();
		_delayField.setText(Integer.toString(200));
		threadPanel.add(_delayField);
		_delayField.setColumns(5);

		initCellSimuConf();

		hookListeners();

		RunManager.getInstance().registerGraph(_graphPanel);
	}

	private void initCellSimuConf() {

		for (String key : CellSimuConfManager.getInstance().getAvailableConfId()) {

			_cellSimuCombo.addItem(key);
		}

	}

	private void hookListeners() {

		_initButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				RunManager.getInstance().stopRun();
				CellManager.getInstance().initialize((String) _cellSimuCombo.getSelectedItem(), "Random");
				_graphPanel.repaint();

			}
		});

		_btnSuivant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int numPas = 1;

				String numPasString = _pasFiled.getText().trim();

				try {
					numPas = Integer.parseInt(numPasString);
				} catch (Exception ex) {
					numPas = 1;
					_pasFiled.setText(String.valueOf(numPas));
				}

				RunManager.getInstance().makeStep(numPas);
			}
		});

		_stopThreadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RunManager.getInstance().stopRun();

			}
		});

		_runThreadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int ms = getMsToSleep();
				
				int numPas;
				try {
					numPas = Integer.parseInt(_pasFiled.getText().trim());
				} catch (Exception ex) {
					numPas = 1;
					_pasFiled.setText(String.valueOf(numPas));
				}
				
				RunManager.getInstance().startThreadRun("Normal",ms,numPas);

			}
		});

	}

	protected int getMsToSleep() {
		
		int ms=-1;
		boolean isOk = true;
		
		try{
			ms = Integer.parseInt(_delayField.getText().trim());
		}catch(NumberFormatException ex){
			isOk = false;
		}
		
		if(ms<0){
			isOk = false;
		}
		
		if(!isOk){
			ms = 200;
			_delayField.setText(Integer.toString(ms));
		}
		
		return ms;
	}

}
