package src;

import java.awt.*;
import java.io.File;

import src.metier.*;
import src.vue.*;

import java.util.ArrayList;

public class Controleur
{
	private Metier metier;
	private FrameGen ihm;

	public Controleur()
	{
		this.metier = new Metier(this);
		this.ihm = new FrameGen(this);
	}

	public void CreerNoeud(String nom, int posX, int posY)
	{
		Noeud n = metier.CreerNoeud(nom, posX, posY);
		ihm.ajouterNoeudTableau(n);
	}

	public void CreerVoie(Noeud Noeud1, Noeud Noeud2, int nbWagon, Color color)
	{
		metier.CreerVoie(Noeud1, Noeud2, nbWagon, color);
		this.ihm.repaintApercu();
	}

	public void ajouterCarteObjectif(CarteObjectif co)
	{
		this.metier.ajouterCarteObjectif(co);
	}

	public ArrayList<Noeud> getNoeuds()
	{
		return metier.getNoeuds();
	}

	public ArrayList<CarteObjectif> getCarteObjectifs()
	{
		return metier.getCarteObjectifs();
	}


	public ArrayList<Voie> getVoies()
	{
		return metier.getVoies();
	}

	public ArrayList<Voie[]> getVoiesDoubles()
	{
		return metier.getVoiesDoubles();
	}

	public Regles getRegles()
	{
		return metier.getRegles();
	}

	public Metier getMetier()
	{
		return metier;
	}

	public FrameGen getIhm()
	{
		return ihm;
	}

	public File getVersoCarteObjectif()
	{
		return this.metier.getVersoCarteObjectif();
	}

	public File getVersoWagon()
	{
		return this.metier.getVersoWagon();
	}

	public boolean importer()
	{
		return metier.importer();
	}

	public void repaintApercu()
	{
		this.ihm.repaintApercu();
	}

	public void supprimerNoeud(Noeud n)
	{
		this.metier.supprimerNoeud(n);
		this.ihm.repaintApercu();
	}

	public static void main(String[] args)
	{
		new Controleur();
	}

	public void nouveau()
	{
		this.metier = new Metier(this);
		this.ihm = new FrameGen(this);
	}

	public void supprimerNoeud(int index)
	{
		this.metier.supprimerNoeud(index);
	}

	public Noeud getNoeudSelectionne()
	{
		return this.ihm.getNoeudSelectionne();
	}

	public void update()
	{
		this.ihm.repaintApercu();
		this.ihm.updateTable();
	}

	public Noeud getNoeud(String valueAt)
	{
		return this.metier.getNoeud(valueAt);

	}
}