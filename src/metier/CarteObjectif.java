package src.metier;

import java.io.File;

public class CarteObjectif
{
	private Noeud noeud1;
	private Noeud noeud2;
	private int nbPoints;

	public CarteObjectif(Noeud noeud1, Noeud noeud2, int nbPoints)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.nbPoints = nbPoints;
	}

	public Noeud getNoeud1()
	{
		return noeud1;
	}

	public Noeud getNoeud2()
	{
		return noeud2;
	}

	public int getNbPoints()
	{
		return nbPoints;
	}
}
