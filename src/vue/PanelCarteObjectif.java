package src.vue;

import src.metier.*;
import src.vue.*;
import src.Controleur;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.table.DefaultTableModel;

public class PanelCarteObjectif extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JPanel panSaisi;
	private JPanel panListe;
	private JPanel panImage;

	private FrameCarteObjectif frameCarteObjectif;

	private JLabel lblNombrePoint;
	private JLabel lblNomVille1;
	private JLabel lblNomVille2;
	private JLabel lblNomFichier;

	private JSpinner spinPoints;

	private JComboBox cBBNoeud1;
	private JComboBox cBBNoeud2;

	private JScrollPane scrollPane;
	private JTable tblCObjectif;
	private DefaultTableModel tabDonnees;
	private String[] tabEntetes = {"Noeud 1", "Noeud 2", "Points"};

	private boolean importer;

	//private JButton btnRecto;
	private JButton btnVerso;
	private static File fileVerso = null;

	private JButton btnValider;
	private JButton btnFinir;

	private final FileFilter fileFilterVerso;

	public PanelCarteObjectif(Controleur ctrl, FrameCarteObjectif frameCarteObjectif)
	{
		//creation composants
		this.setLayout(new GridLayout(1, 3));
		this.ctrl = ctrl;
		this.frameCarteObjectif = frameCarteObjectif;

		this.panSaisi = new JPanel(new GridLayout(4, 2));
		this.panListe = new JPanel(new BorderLayout());
		this.panImage = new JPanel();

		this.lblNombrePoint = new JLabel("Nombre de point de la carte :");
		this.lblNomVille1 = new JLabel("Noeud 1 :");
		this.lblNomVille2 = new JLabel("Noeud 2 :");
		if (this.frameCarteObjectif.getFileCarteObjectif() != null)
		{
			this.lblNomFichier = new JLabel(this.frameCarteObjectif.getFileCarteObjectif().getName());
		} else
		{
			this.lblNomFichier = new JLabel("");
		}

		importer = this.ctrl.importer();


		this.spinPoints = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		this.cBBNoeud1 = new JComboBox<String>();
		this.cBBNoeud2 = new JComboBox<String>();

		this.tabDonnees = new DefaultTableModel(tabEntetes, 0);
		this.tblCObjectif = new JTable(this.tabDonnees);

		this.scrollPane = new JScrollPane(tblCObjectif);
		this.scrollPane.setSize(10, 200);

		this.btnVerso = new JButton("Image Verso");

		this.btnFinir = new JButton("Finir");
		this.btnValider = new JButton("Valider");

		//activation composants
		this.btnVerso.addActionListener(this);

		this.btnFinir.addActionListener(this);
		this.btnValider.addActionListener(this);

		for (Noeud n : this.ctrl.getNoeuds())
		{
			this.cBBNoeud1.addItem(n.getNom());
			this.cBBNoeud2.addItem(n.getNom());
		}

		for (CarteObjectif carte : this.ctrl.getCarteObjectifs())
		{
			this.tabDonnees.addRow(new Object[]{carte.getNoeud1().getNom(), carte.getNoeud2().getNom(), carte.getNbPoints()});
			this.repaint();
		}

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


		//positionnement composants
		this.panSaisi.add(this.lblNomVille1);
		this.panSaisi.add(this.cBBNoeud1);

		this.panSaisi.add(this.lblNomVille2);
		this.panSaisi.add(this.cBBNoeud2);

		this.panSaisi.add(this.lblNombrePoint);
		this.panSaisi.add(this.spinPoints);

		this.panSaisi.add(this.btnFinir);
		this.panSaisi.add(this.btnValider);

		this.panListe.add(this.scrollPane, BorderLayout.CENTER);

		//this.panImage.add(this.btnRecto);
		this.panImage.add(this.btnVerso);
		this.panImage.add(this.lblNomFichier);

		this.add(this.panImage);
		this.add(this.panSaisi);
		this.add(this.panListe);

		if (importer == true)
		{
			if (this.ctrl.getVersoCarteObjectif() != null)
			{
				this.lblNomFichier.setText(this.ctrl.getVersoCarteObjectif().getName());
			}
		}
	}

	public void ajouterCObjectifTableau(CarteObjectif co)
	{
		this.tabDonnees.addRow(new Object[]{co.getNoeud1().getNom(), co.getNoeud2().getNom(), (Integer) this.spinPoints.getValue()});
		this.repaint();
	}


	private void ouvrirVerso()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(fileFilterVerso);
		int returnVal = fileChooser.showOpenDialog(frameCarteObjectif);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			this.frameCarteObjectif.setFileCarteObjectif(file);

			this.lblNomFichier.setText(this.frameCarteObjectif.getFileCarteObjectif().getName());
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
		if (e.getSource() == this.btnFinir)
		{
			this.frameCarteObjectif.dispose();
		}

		if (e.getSource() == this.btnValider)
		{
			for (CarteObjectif co : this.ctrl.getCarteObjectifs())
			{
				if ((co.getNoeud1() == this.cBBNoeud1.getSelectedItem() && co.getNoeud2() == this.cBBNoeud2.getSelectedItem())
						|| (co.getNoeud1() == this.cBBNoeud2.getSelectedItem() && co.getNoeud2() == this.cBBNoeud1.getSelectedItem()))
				{
					this.lblNomFichier.setText("Cette carte objectif existe déjà");
					break;
				}
			}

			if (this.cBBNoeud1.getSelectedItem() != null && this.cBBNoeud2.getSelectedItem() != null && this.spinPoints.getValue() != (Integer) 0)
			{
				if (this.cBBNoeud1.getSelectedItem() != this.cBBNoeud2.getSelectedItem())
				{
					Noeud n1 = null;
					Noeud n2 = null;
					for (Noeud n : this.ctrl.getNoeuds())
					{

						if (n.getNom() == this.cBBNoeud1.getSelectedItem())
						{
							n1 = n;
						}
						if (n.getNom() == this.cBBNoeud2.getSelectedItem())
						{
							n2 = n;
						}
					}

					if (this.frameCarteObjectif.getFileCarteObjectif() != null)
					{
						CarteObjectif co = new CarteObjectif(n1, n2, (Integer) this.spinPoints.getValue());
						this.ctrl.ajouterCarteObjectif(co);
						ajouterCObjectifTableau(co);
					} else
					{
						this.lblNomFichier.setText("Veuillez choisir un fichier");
					}
				}
			}
		}

		if (e.getSource() == this.btnVerso)
		{
			this.ouvrirVerso();
		}
	}
}