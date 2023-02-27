package src.vue;

import src.metier.*;
import src.vue.*;
import src.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.filechooser.FileFilter;
import java.io.File;


import javax.swing.*;

public class PanelRegle extends JPanel implements ActionListener
{
	private JPanel panSaisi;
	private Controleur ctrl;
	private FrameRegle frameMere;
	//private boolean isDoubleVoie;
	private JButton btnValider;
	private Regles regles;
	private JSpinner spinNbWagon;
	private JSpinner spinNbWagonFin;
	private JSpinner spinNbJoueurMax;
	private JSpinner spinNbCarteObjectif;
	private JSpinner spinNbCartesWagonRouge;
	private JSpinner spinNbCartesWagonNoir;
	private JSpinner spinNbCartesWagonBlanc;
	private JSpinner spinNbCartesWagonJaune;
	private JSpinner spinNbCartesWagonVert;
	private JSpinner spinNbCartesWagonBleu;
	private JSpinner spinNbCartesWagonRose;
	private JSpinner spinNbCartesWagonGris;
	private JSpinner spinNbJVoiesDouble;
	private JSpinner spinJocker;
	private JButton btnVerso;
	private JLabel lblNomFichier;
	private JCheckBox CbLongChemin;
	private JSpinner spinNbPointsLongChemin;

	private boolean importer;

	private final FileFilter fileFilterVerso;

	public PanelRegle(Controleur ctrl, FrameRegle frameMere)
	{
		this.ctrl = ctrl;
		this.frameMere = frameMere;
		this.setLayout(new BorderLayout());
		importer = this.ctrl.importer();

		//creation composant
		JPanel panelForm = new JPanel(new GridLayout(4, 2));
		//this.regles = ctrl.getRegles();
		this.setLayout(new BorderLayout());
		this.panSaisi = new JPanel(new GridLayout(17, 4));

		//creation labels
		this.lblNomFichier = new JLabel("");

		//creation composants

		this.spinNbJoueurMax = new JSpinner(new SpinnerNumberModel(2, 2, 20, 1));

		this.spinNbWagon = new JSpinner(new SpinnerNumberModel(0, 0, 30, 1));
		this.spinNbCarteObjectif = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonRouge = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonNoir = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonBlanc = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonJaune = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonVert = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonBleu = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonRose = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbCartesWagonGris = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.spinNbJVoiesDouble = new JSpinner(new SpinnerNumberModel(0, 0, (int) this.spinNbJoueurMax.getValue(), 1));
		this.spinJocker = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.btnVerso = new JButton("Image verso");
		this.CbLongChemin = new JCheckBox("Long chemin");
		this.spinNbPointsLongChemin = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));

		this.spinNbWagonFin = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		if (this.ctrl.getRegles() != null)
		{
			this.spinNbJoueurMax.setValue(this.ctrl.getRegles().getNbJoueursMax());
			this.spinNbWagon.setValue(this.ctrl.getRegles().getNbWagons());
			this.spinNbCarteObjectif.setValue(this.ctrl.getRegles().getNbCartesObjectifs());
			this.spinNbCartesWagonRouge.setValue(this.ctrl.getRegles().getNbCartesWagonRouge());
			this.spinNbCartesWagonNoir.setValue(this.ctrl.getRegles().getNbCartesWagonNoir());
			this.spinNbCartesWagonBlanc.setValue(this.ctrl.getRegles().getNbCartesWagonBlanc());
			this.spinNbCartesWagonJaune.setValue(this.ctrl.getRegles().getNbCartesWagonJaune());
			this.spinNbCartesWagonVert.setValue(this.ctrl.getRegles().getNbCartesWagonVert());
			this.spinNbCartesWagonBleu.setValue(this.ctrl.getRegles().getNbCartesWagonBleu());
			this.spinNbCartesWagonRose.setValue(this.ctrl.getRegles().getNbCartesWagonRose());
			this.spinNbCartesWagonGris.setValue(this.ctrl.getRegles().getNbCartesWagonGris());
			this.spinNbJVoiesDouble.setValue(this.ctrl.getRegles().getNbJVoiesDoubles());
			this.spinJocker.setValue(this.ctrl.getRegles().getNbJocker());
			this.spinNbWagonFin.setValue(this.ctrl.getRegles().getNbWagonFin());
			this.CbLongChemin.setSelected(this.ctrl.getRegles().isLongChemin());
			this.spinNbPointsLongChemin.setValue(this.ctrl.getRegles().getLongCheminVal());
		}


		if (this.ctrl.getMetier().getVersoWagon() != null) 
		{
			this.lblNomFichier.setText(this.ctrl.getMetier().getVersoWagon().getName());
		}




		if (this.ctrl.getMetier().getVersoWagon() != null) 
		{
			this.lblNomFichier.setText(this.ctrl.getMetier().getVersoWagon().getName());
		}


		if (importer == true) 
		{
			if (this.ctrl.getMetier().getVersoWagon() != null) 
			{
				this.lblNomFichier.setText(this.ctrl.getMetier().getVersoWagon().getName());
			}
		}


		this.CbLongChemin.addActionListener(this);
		this.btnValider = new JButton("Valider");
		this.btnValider.addActionListener(this);
		this.btnVerso.addActionListener(this);

		fileFilterVerso = new FileFilter()
		{
			@Override
			public boolean accept(File f)
			{
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".png");
			}


			public String getDescription()
			{
				return "Fichier image (*.jpg, *.png)";
			}
		};

		this.panSaisi.add(new JLabel("Nombre de wagon de chaque couleur en début de partie"));
		this.panSaisi.add(this.spinNbWagon);
		this.panSaisi.add(new JLabel("Nombre de wagons pour finir la partie"));
		this.panSaisi.add(this.spinNbWagonFin);
		this.panSaisi.add(new JLabel("Nombre de joueurs maximum"));
		this.panSaisi.add(this.spinNbJoueurMax);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon rouge"));
		this.panSaisi.add(this.spinNbCartesWagonRouge);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon noir"));
		this.panSaisi.add(this.spinNbCartesWagonNoir);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon blanc"));
		this.panSaisi.add(this.spinNbCartesWagonBlanc);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon jaune"));
		this.panSaisi.add(this.spinNbCartesWagonJaune);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon vert"));
		this.panSaisi.add(this.spinNbCartesWagonVert);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon bleu"));
		this.panSaisi.add(this.spinNbCartesWagonBleu);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon rose"));
		this.panSaisi.add(this.spinNbCartesWagonRose);
		this.panSaisi.add(new JLabel("Nombre de cartes wagon gris"));
		this.panSaisi.add(this.spinNbCartesWagonGris);
		this.panSaisi.add(new JLabel("Nombre joueurs pour voies doubles"));
		this.panSaisi.add(this.spinNbJVoiesDouble);
		this.panSaisi.add(new JLabel("Nombre de joker"));
		this.panSaisi.add(this.spinJocker);
		this.panSaisi.add(this.btnVerso);
		this.panSaisi.add(this.lblNomFichier);
		this.panSaisi.add(this.CbLongChemin);
		this.panSaisi.add(new JLabel());
		this.panSaisi.add(new JLabel("Nombre de points pour le long chemin"));
		this.panSaisi.add(this.spinNbPointsLongChemin);

		this.spinNbPointsLongChemin.setEnabled(CbLongChemin.isSelected());

		this.add(this.panSaisi, BorderLayout.CENTER);
		this.add(this.btnValider, BorderLayout.SOUTH);
	}


	private void ouvrirVerso()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(fileFilterVerso);
		int returnVal = fileChooser.showOpenDialog(frameMere);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			this.frameMere.setFileCarteWagon(file);

			this.lblNomFichier.setText(this.frameMere.getFileCarteWagon().getName());
		}
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnVerso)
		{
			this.ouvrirVerso();
		}

		if (e.getSource() == btnValider)
		{
			this.ctrl.getMetier().setRegles(new Regles(
					(int) this.spinNbWagon.getValue(),
					(int) this.spinNbWagonFin.getValue(),
					(int) this.spinNbCartesWagonRouge.getValue(),
					(int) this.spinNbCartesWagonNoir.getValue(),
					(int) this.spinNbCartesWagonBlanc.getValue(),
					(int) this.spinNbCartesWagonJaune.getValue(),
					(int) this.spinNbCartesWagonVert.getValue(),
					(int) this.spinNbCartesWagonBleu.getValue(),
					(int) this.spinNbCartesWagonRose.getValue(),
					(int) this.spinNbCartesWagonGris.getValue(),
					(int) this.spinNbJoueurMax.getValue(),
					(int) this.spinNbJVoiesDouble.getValue(),
					(int) this.spinJocker.getValue(),
					true,
					this.CbLongChemin.isSelected(),
					(int) this.spinNbPointsLongChemin.getValue()));
			this.frameMere.dispose();
		} else if (e.getSource() == CbLongChemin)
		{
			this.spinNbPointsLongChemin.setEnabled(CbLongChemin.isSelected());
		}
	}

	public void createRegle()
	{
		this.ctrl.getMetier().setRegles(new Regles());
	}
}