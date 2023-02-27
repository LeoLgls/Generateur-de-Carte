package src.metier;

import java.awt.Color;

public class Voie
{
	private Noeud noeud1;
	private Noeud noeud2;
	private int nbWagons;
	private Color couleur;

	public Voie(Noeud noeud1, Noeud noeud2, int nbWagons, Color couleur)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.nbWagons = nbWagons;
		this.couleur = couleur;
		this.noeud1.ajouterVoie(this);
		this.noeud2.ajouterVoie(this);
	}

	public Noeud getNoeud1()
	{
		return noeud1;
	}

	public Noeud getNoeud2()
	{
		return noeud2;
	}

	public int getNbWagons()
	{
		return nbWagons;
	}

	public Color getCouleur()
	{
		return couleur;
	}

	public String toString()
	{
		return "Voie{" +
				"noeud1=" + noeud1 +
				", noeud2=" + noeud2 +
				", nbWagons=" + nbWagons +
				", couleur=" + couleur.toString() +
				'}';
	}

	public float getLongueur()
	{
		return (float) Math.sqrt(Math.pow(noeud1.getPosX() - noeud2.getPosX(), 2) + Math.pow(noeud1.getPosY() - noeud2.getPosY(), 2));
	}

	// coefficient directeur de la droite
	public float getSlopeCoef()
	{
		return (float) (noeud2.getPosY() - noeud1.getPosY()) / (float) (noeud2.getPosX() - noeud1.getPosX());
	}

	// angle de la droite par rapport à l'axe des abscisses
	public float getAngle()
	{
		return (float) Math.atan(getSlopeCoef());
	}

	public float getAngleDeg()
	{
		return (float) Math.toDegrees(getAngle());
	}

	// cosinus de l'angle de la droite par rapport à l'axe des abscisses
	public float getSlopeCoefX()
	{
		return (float) Math.cos(getAngle());
	}

	// sinus de l'angle de la droite par rapport à l'axe des abscisses
	public float getSlopeCoefY()
	{
		return (float) Math.sin(getAngle());
	}

	public float getPosX()
	{
		return (float) noeud1.getPosX();
	}

	public int getWagons()
	{
		return nbWagons;
	}


	public boolean equals(Voie aut)
	{
		if (aut == null)
			return false;
		return (this.getNoeud1() == aut.getNoeud1() && this.getNoeud2() == aut.getNoeud2()) ||
				(this.getNoeud1() == aut.getNoeud2() && this.getNoeud2() == aut.getNoeud1());
	}
}
