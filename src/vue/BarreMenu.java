package src.vue;

import javax.swing.*;

import src.Controleur;
import src.metier.*;

import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;


public class BarreMenu extends JMenuBar implements ActionListener
{
	private Controleur ctrl;
	private FrameGen frameGen;
	private JMenu menuFichier;
	private JMenuItem itemNouveau;
	private JMenuItem itemOuvrir;
	private JMenuItem itemOuvrirImage;
	private JMenuItem itemEnregistrer;
	private JMenuItem itemEnregistrerSous;
	private JMenuItem itemQuitter;
	private JMenu menuJeu;
	private JMenuItem itemCreerCarteobjectif;
	private JMenuItem itemRegles;
	private final FileFilter fileXml;
	private final FileFilter fileImage;
	private File fileImageFond;


	private File fichierCourant;

	public BarreMenu(FrameGen frameGen, Controleur ctrl)
	{
		this.ctrl = frameGen.getControleur();
		this.frameGen = frameGen;


		menuFichier = new JMenu("Fichier");
		itemNouveau = new JMenuItem("Nouveau");
		itemOuvrir = new JMenuItem("Ouvrir");
		itemOuvrirImage = new JMenuItem("Ouvrir image");
		itemEnregistrer = new JMenuItem("Enregistrer");
		itemEnregistrerSous = new JMenuItem("Enregistrer sous");
		itemQuitter = new JMenuItem("Quitter");

		menuJeu = new JMenu("Jeu");
		itemCreerCarteobjectif = new JMenuItem("Créer une carte objectif");
		itemRegles = new JMenuItem("Editer les règles");

		menuFichier.add(itemNouveau);
		menuFichier.add(itemOuvrir);
		menuFichier.add(itemOuvrirImage);
		menuFichier.add(new JSeparator());
		menuFichier.add(itemEnregistrer);
		menuFichier.add(itemEnregistrerSous);
		menuFichier.add(new JSeparator());
		menuFichier.add(itemQuitter);
		menuJeu.add(itemCreerCarteobjectif);
		menuJeu.add(itemRegles);

		menuFichier.setMnemonic(KeyEvent.VK_F);

		itemNouveau.setMnemonic(KeyEvent.VK_N);
		itemOuvrir.setMnemonic(KeyEvent.VK_O);
		itemOuvrirImage.setMnemonic(KeyEvent.VK_I);
		itemEnregistrer.setMnemonic(KeyEvent.VK_S);
		itemEnregistrerSous.setMnemonic(KeyEvent.VK_E);
		itemQuitter.setMnemonic(KeyEvent.VK_Q);


		menuJeu.setMnemonic(KeyEvent.VK_J);

		itemCreerCarteobjectif.setMnemonic(KeyEvent.VK_C);
		itemRegles.setMnemonic(KeyEvent.VK_R);

		itemNouveau.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		itemOuvrir.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
		itemOuvrirImage.setAccelerator(KeyStroke.getKeyStroke('I', InputEvent.CTRL_DOWN_MASK));
		itemEnregistrer.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		itemEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		itemQuitter.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));


		itemCreerCarteobjectif.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
		itemRegles.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK));


		fileXml = new FileFilter()
		{
			@Override
			public boolean accept(File f)
			{
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription()
			{
				return "Fichier XML";
			}
		};

		fileImage = new FileFilter()
		{
			@Override
			public boolean accept(File f)
			{
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".png");
			}

			@Override
			public String getDescription()
			{
				return "Fichier image (*.jpg, *.png)";
			}
		};

		itemNouveau.addActionListener(this);
		itemOuvrir.addActionListener(this);
		itemOuvrirImage.addActionListener(this);
		itemEnregistrer.addActionListener(this);
		itemEnregistrerSous.addActionListener(this);
		itemQuitter.addActionListener(this);

		itemCreerCarteobjectif.addActionListener(this);
		itemRegles.addActionListener(this);

		this.add(menuFichier);
		this.add(menuJeu);
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == itemEnregistrerSous)
		{
			this.enregistrerSous();
		} else if (e.getSource() == itemEnregistrer)
		{
			this.enregistrer();
		} else if (e.getSource() == itemOuvrir)
		{
			this.ouvrir();
		} else if (e.getSource() == itemOuvrirImage)
		{
			this.ouvrirImage();
		} else if (e.getSource() == itemNouveau)
		{
			this.nouveau();
		} else if (e.getSource() == itemQuitter)
		{
			this.quitter();
		} else if (e.getSource() == itemCreerCarteobjectif)
		{
			this.creerCarteObjectif();
		} else if (e.getSource() == itemRegles)
		{
			this.editerRegles();
		}
	}

	private void nouveau()
	{
		frameGen.getControleur().nouveau();
		frameGen.dispose();
		this.fichierCourant = null;
	}

	private void editerRegles()
	{
		this.ctrl.getIhm().setFrameRegle(new FrameRegle(this.frameGen));
	}

	private void creerCarteObjectif()
	{
		new FrameCarteObjectif(this.frameGen);
	}

	private void quitter()
	{
		System.exit(0);
	}

	private void ouvrirImage()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(fileImage);
		int returnVal = fileChooser.showOpenDialog(frameGen);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			this.fileImageFond = fileChooser.getSelectedFile();
			this.frameGen.setCalque(this.fileImageFond);
		}
	}

	public void setImage(File fileImageFond)
	{
		this.fileImageFond = fileImageFond;
	}

	private void ouvrir()
	{
		this.nouveau(); // On vide le plateau
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(fileXml);
		fileChooser.setDialogTitle("Specify a file to open");
		fileChooser.setApproveButtonText("Open");

		int returnVal = fileChooser.showOpenDialog(frameGen);
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try
			{
				this.frameGen.getControleur().getMetier().importerXML(file);
				this.fichierCourant = new File(fileChooser.getSelectedFile().getAbsolutePath());
				this.frameGen.setTitle(this.fichierCourant.getName());
			} catch (Exception e)
			{
				System.out.println("Erreur lors de l'ouverture du fichier");
				e.printStackTrace();
			}
			this.ctrl.getIhm().updateTable();
		}
	}

	private void enregistrerSous()
	{
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		fileChooser.setApproveButtonText("Save");

		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.addChoosableFileFilter(fileXml);

		int userSelection = fileChooser.showSaveDialog(parentFrame);


		if (userSelection == JFileChooser.APPROVE_OPTION)
		{
			if (fileChooser.getSelectedFile().getName().endsWith(".xml"))
			{
				fichierCourant = fileChooser.getSelectedFile();
			} else
			{
				fichierCourant = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".xml");
			}
			this.frameGen.getControleur().getMetier().genererXML(fichierCourant, this.fileImageFond);
			this.frameGen.setTitle(fichierCourant.getName());
		}
	}

	public String getFichierCourant()
	{
		return fichierCourant.getAbsolutePath();
	}

	private void enregistrer()
	{
		if (fichierCourant == null || !fichierCourant.exists() || fichierCourant.equals(""))
		{
			this.enregistrerSous();
		} else
		{
			this.frameGen.getControleur().getMetier().genererXML(fichierCourant, this.fileImageFond);
		}
	}
}
