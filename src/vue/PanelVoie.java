package src.vue;

import src.metier.*;
import src.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PanelVoie extends JPanel implements ActionListener, MouseListener
{
	private Controleur ctrl;
	private JComboBox<String> cBBPoint1;
	private JComboBox<String> cBBPoint2;
	private JButton btnAddVoie;
	private JComboBox<String> cBBCoul;
	private JSpinner spinPoints;
	private JPopupMenu popMenu;
	private JMenuItem mItemSupp;

	public PanelVoie(Controleur ctrl, FrameGen frameMere)
	{
		this.setLayout(new GridLayout(5, 1));
		this.ctrl = ctrl;

		// creation composants
		this.setFont(new Font("Papyrus", Font.PLAIN, 12));
		this.cBBPoint1 = new JComboBox<String>();
		this.cBBPoint2 = new JComboBox<String>();
		this.cBBCoul = new JComboBox<String>();
		this.btnAddVoie = new JButton("Ajouter Voie");
		this.spinPoints = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		this.popMenu = new JPopupMenu();
		this.mItemSupp = new JMenuItem("Supprimer Voie");

		this.cBBCoul.addItem("Rouge");
		this.cBBCoul.addItem("Jaune");
		this.cBBCoul.addItem("Vert");
		this.cBBCoul.addItem("Bleu");
		this.cBBCoul.addItem("Rose");
		this.cBBCoul.addItem("Orange");
		this.cBBCoul.addItem("Blanc");
		this.cBBCoul.addItem("Noir");
		this.cBBCoul.addItem("Gris");

		this.popMenu.add(mItemSupp);

		// positionnement composant
		this.add(cBBPoint1);
		this.add(cBBPoint2);
		this.add(cBBCoul);
		this.add(spinPoints);
		this.add(btnAddVoie);

		// activation
		this.btnAddVoie.addActionListener(this);
		this.mItemSupp.addActionListener(this);
	}

	public void ajouterVille(String nom)
	{
		cBBPoint1.addItem(nom);
		cBBPoint2.addItem(nom);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAddVoie)
		{
			if (this.cBBPoint1.getSelectedItem() != null && this.cBBPoint2.getSelectedItem() != null
					&& this.spinPoints.getValue() != (Integer) 0 && this.cBBCoul != null)
			{
				if (this.cBBPoint1.getSelectedItem() != this.cBBPoint2.getSelectedItem())
				{
					Noeud n1 = null;
					Noeud n2 = null;
					for (Noeud n : this.ctrl.getNoeuds())
					{
						if (n.getNom() == this.cBBPoint1.getSelectedItem())
						{
							n1 = n;
						}
						if (n.getNom() == this.cBBPoint2.getSelectedItem())
						{
							n2 = n;
						}
					}


					Color c = null;

					switch ((String) this.cBBCoul.getSelectedItem())
					{
						case "Rouge":
							c = Color.RED;
							break;
						case "Jaune":
							c = Color.YELLOW;
							break;
						case "Vert":
							c = Color.GREEN;
							break;
						case "Bleu":
							c = Color.BLUE;
							break;
						case "Rose":
							c = Color.PINK;
							break;
						case "Blanc":
							c = new Color(240, 240, 240);
							break;
						case "Noir":
							c = Color.BLACK;
							break;
						case "Orange":
							c = Color.ORANGE;
							break;
						case "Gris":
							c = Color.LIGHT_GRAY;
							break;

						default:
							break;
					}

					this.ctrl.CreerVoie(n1, n2, (Integer) this.spinPoints.getValue(), c);
				}
				// this.ctrl.CreerVoie(this.cBBPoint1.getSelectedItem(),
				// this.cBBPoint2.getSelectedItem(), (Integer) this.spinPoints.getValue(),
				// (Color) this.cBBCoul);
			}
		}
		if (e.getSource() == this.mItemSupp)
		{
			//this.ctrl.SupprimerVoie();
		}
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON2)
		{
			if (e.getSource() instanceof Voie)
			{
				Voie v = (Voie) e.getSource();
				this.popMenu.show(mItemSupp, e.getX(), e.getY());
			}
		}

	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	/**
	 * Invoked when the mouse exits a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	public void update()
	{
		this.cBBPoint1.removeAllItems();
		this.cBBPoint2.removeAllItems();
		for (Noeud n : this.ctrl.getNoeuds())
		{
			this.cBBPoint1.addItem(n.getNom());
			this.cBBPoint2.addItem(n.getNom());
		}


	}


}
