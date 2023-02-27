package src.vue;

import src.Controleur;
import src.metier.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopMenu extends JPopupMenu implements ActionListener
{
	private JMenuItem mItemSupp;
	private FrameGen ihm;
	private Controleur ctrl;
	private Voie voie;
	private Voie[] voieDouble;
	private CarteObjectif carteObjectif;
	private Noeud noeudSelectionne = null;


	public PopMenu()
	{
		this.mItemSupp = new JMenuItem("Supprimer Noeud");
		this.add(mItemSupp);
	}

	public PopMenu(FrameGen ihm, Controleur ctrl)
	{
		this.ihm = ihm;
		this.ctrl = ctrl;
		this.mItemSupp = new JMenuItem("Supprimer Noeud");
		this.add(mItemSupp);
		this.mItemSupp.addActionListener(this);
	}

	public void setNoeudSelectionne(Noeud n)
	{
		this.noeudSelectionne = n;
	}


	public JMenuItem getmItemSupp()
	{
		return mItemSupp;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		boolean aVoie = false;
		if (e.getSource() == mItemSupp && this.noeudSelectionne == null)
		{
			for (Voie v : this.ctrl.getVoies())
			{
				if (v.getNoeud1() == this.ctrl.getNoeudSelectionne() || v.getNoeud2() == this.ctrl.getNoeudSelectionne())
				{
					aVoie = true;
				}
			}

			if (aVoie != true)
			{
				this.ctrl.supprimerNoeud(this.ctrl.getNoeudSelectionne());
				this.ctrl.update();
				this.ihm.updateTable();
				this.ihm.repaintApercu();
				this.ihm.repaint();
			}
		}

		//suppression noeud sur panelApercu (car il a pas acces a this.Ctrl.getNoeudSelectionne)
		if (e.getSource() == mItemSupp && this.noeudSelectionne != null)
		{
			for (Voie v : this.ctrl.getVoies())
			{
				if (v.getNoeud1() == this.noeudSelectionne || v.getNoeud2() == this.noeudSelectionne)
				{
					aVoie = true;
				}
			}

			if (aVoie != true)
			{
				this.ctrl.supprimerNoeud(this.noeudSelectionne);
				this.ctrl.update();
				this.ihm.updateTable();
				this.ihm.repaintApercu();
				this.ihm.repaint();
			}
		}
	}
}
