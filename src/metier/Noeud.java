package src.metier;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;

public class Noeud
{
	private String nom;
	private int posX;
	private int posY;
	private int stringPosX;
	private int stringPosY;
	private ArrayList<Voie> voies;
	private ArrayList<Noeud> voisins;

	public Noeud(String nom, int posX, int posY)
	{
		this.nom = nom;
		this.posX = posX;
		this.posY = posY;
		this.stringPosX = posX;
		this.stringPosY = posY - 12;
		this.voies = new ArrayList<Voie>();
		this.voisins = new ArrayList<Noeud>();
	}

	public Noeud(String nom, int posX, int posY, int stringPosX, int stringPosY)
	{
		this.nom = nom;
		this.posX = posX;
		this.posY = posY;
		this.stringPosX = stringPosX;
		this.stringPosY = stringPosY;
		this.voies = new ArrayList<Voie>();
		this.voisins = new ArrayList<Noeud>();
	}

	public String getNom()
	{
		return this.nom;
	}

	public int getPosX()
	{
		return this.posX;
	}

	public int getPosY()
	{
		return this.posY;
	}

	public void setPosX(int x)
	{
		this.posX = x;
	}

	public void setPosY(int y)
	{
		this.posY = y;
	}


	public int getStringPosX()
	{
		return stringPosX;
	}

	public int getStringPosY()
	{
		return stringPosY;
	}

	public void setStringPosX(int x)
	{
		this.stringPosX = x;
	}

	public void setStringPosY(int y)
	{
		this.stringPosY = y;
	}

	private void addVoisins(Noeud n)
	{
		this.voisins.add(n);
	}

	public void ajouterVoie(Voie v)
	{
		this.voies.add(v);
		if (v.getNoeud1() == this)
		{
			if (!this.voisins.contains(v.getNoeud2()))
			{
				this.addVoisins(v.getNoeud2());
			}

		} else
		{
			if (!this.voisins.contains(v.getNoeud1()))
			{
				this.addVoisins(v.getNoeud1());
			}
		}
	}

	public float XString()
	{
		float x = this.posX;
		int cptNoeud = 1;

		for (Noeud t : this.getVoisins())
		{
			x += t.getPosX();
			cptNoeud++;
		}

		return (this.getPosX() /* - (x / cptNoeud)*/);
	}

	public float YString()
	{
		float y = this.posY;
		int cptNoeud = 1;

		for (Noeud t : this.getVoisins())
		{
			y += t.getPosY();
			cptNoeud++;
		}

		return (this.getPosY() - 12 /* - (y / cptNoeud)*/);
	}


	public ArrayList<Noeud> getVoisins()
	{
		return this.voisins;
	}

	public ArrayList<Voie> getVoies()
	{
		return this.voies;
	}

	public String toString()
	{
		return nom + " (" + posX + ", " + posY + ")";
	}

	/*public void updatePos(int x, int y)
	{
		this.posX = x;
		this.posY = y;
		this.stringPosX = x;
		this.stringPosY = y - 12;
	}*/
}
