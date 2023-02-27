package src.vue;

import src.metier.*;
import src.vue.*;
import src.Controleur;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import java.awt.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class FrameGen extends JFrame implements MouseListener
{
	private Controleur ctrl;
	private BarreMenu barreMenu;
	private PanelPoint panelPoint;
	private PanelVoie panelVoie;

	private int cptTableau = 0;

	private JScrollPane scrollPane;

	private JPanel panPrim;
	private PanelApercu panAperc;
	private JTable table1;
	private BarreMenu barreMenu1;
	private DefaultTableModel tabDonnees;
	private String[] tabEntetes = {"Ville", "X", "Y"};
	private FrameRegle frameRegle;
	private PopMenu popMenu;

	public FrameGen(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.panelPoint = new PanelPoint(ctrl, this);
		this.barreMenu = new BarreMenu(this, this.ctrl);
		this.panelVoie = new PanelVoie(ctrl, this);
		this.popMenu = new PopMenu(this, this.ctrl);
		//this.popMenu.add(new JMenuItem("Supprimer Voie"));
		//this.popMenu.add(new JMenuItem("Supprimer Noeud"));
		//this.popMenu.add(new JMenuItem("Supprimer Carte Objectif"));
		this.tabDonnees = new DefaultTableModel(tabEntetes, 0)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		this.setLocation(new Point());


		this.createUIComponents();

		//this.popMenu.show(this, 0, 0);
	}


	private void createUIComponents()
	{


		//creation composants		
		panPrim = new JPanel();
		panAperc = new PanelApercu(ctrl, this);
		JPanel panEast = new JPanel(new BorderLayout());

		this.table1 = new JTable(this.tabDonnees);
		scrollPane = new JScrollPane(table1);
		scrollPane.setMinimumSize(new Dimension(10, 200));

		panPrim.setLayout(new BorderLayout());

		//positionnement

		panEast.add(panelVoie, BorderLayout.NORTH);
		panEast.add(scrollPane, BorderLayout.CENTER);

		panPrim.add(panelPoint, BorderLayout.SOUTH);
		panPrim.add(panEast, BorderLayout.EAST);

		panPrim.add(panAperc, BorderLayout.CENTER);
		this.setTitle("Nouvelle carte");
		this.table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.setJMenuBar(barreMenu);

		this.add(panPrim);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1800, 1000);
		table1.addMouseListener(this);

		this.setVisible(true);
	}

	public JTable getTable()
	{
		return table1;
	}

	public void ajouterNoeudTableau(Noeud v)
	{
		this.tabDonnees.addRow(new Object[]{v.getNom(), v.getPosX(), v.getPosY()});
		panelVoie.ajouterVille(v.getNom());
		panAperc.repaint();
	}


	public void supprimerNoeud(Noeud n)
	{
		this.ctrl.supprimerNoeud(n);
	}

	public FrameRegle getFrameRegle()
	{
		return new FrameRegle(this);
	}

	public void setFrameRegle(FrameRegle frameRegle)
	{
		this.frameRegle = frameRegle;
	}

	public BarreMenu getBarreMenu()
	{
		return this.barreMenu;
	}


	public Controleur getControleur()
	{
		return this.ctrl;
	}

	public void repaintApercu()
	{
		this.panAperc.repaint();
	}

	public void setCalque(File f)
	{
		this.panAperc.setCalque(f);
	}

	public void setCalque(String path)
	{
		this.panAperc.setCalque(path);
	}

	public PanelPoint getPanelPoint()
	{
		return this.panelPoint;
	}

	public PanelVoie getPanelVoie()
	{
		return this.panelVoie;
	}

	public PanelApercu getPanelApercu()
	{
		return this.panAperc;
	}

	public Noeud getNoeudSelectionne()
	{
		return this.ctrl.getMetier().getNoeud(this.table1.getValueAt(this.table1.getSelectedRow(), 0).toString());
	}

	public void updateTable()
	{
		this.tabDonnees.setRowCount(0);
		ArrayList<Noeud> listeNoeud = this.ctrl.getNoeuds();
		for (int i = 0; i < listeNoeud.size(); i++)
		{
			this.tabDonnees.addRow(new Object[]{listeNoeud.get(i).getNom(), listeNoeud.get(i).getPosX(), listeNoeud.get(i).getPosY()});
			//listeNoeud.get(i).updatePos(listeNoeud.get(i).getPosX(), listeNoeud.get(i).getPosY());
		}
		this.panelVoie.update();
		this.panAperc.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3 && e.getSource() == this.table1)
		{
			this.table1.changeSelection(((JTable) e.getSource()).rowAtPoint(e.getPoint()),
					0, false, false);
			this.popMenu.show(this.table1, e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	

	/*public static void main(String[] args)
	{
		FrameGen frameGen = new FrameGen();
	}*/


}
