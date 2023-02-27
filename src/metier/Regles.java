package src.metier;

public class Regles
{
	private int nbWagon;
	private int nbWagonFin;
	private int nbJoueursMax;
	private int nbCartesObjectifs;

	private int nbCartesWagonRouge;
	private int nbCartesWagonNoir;
	private int nbCartesWagonBlanc;
	private int nbCartesWagonJaune;
	private int nbCartesWagonVert;
	private int nbCartesWagonBleu;
	private int nbCartesWagonRose;
	private int nbCartesWagonGris;
	private int nbJVoiesDoubles;
	private int nbJocker;
	private boolean doubleVoie;
	private boolean longChemin;
	private int longCheminVal;

	public Regles(
			int nbWagon,
			int nbWagonFin,
			int nbCartesWagonRouge,
			int nbCartesWagonNoir,
			int nbCartesWagonBlanc,
			int nbCartesWagonJaune,
			int nbCartesWagonVert,
			int nbCartesWagonBleu,
			int nbCartesWagonRose,
			int nbCartesWagonGris,
			int nbJoueursMax,
			int nbJVoiesDoubles,
			int nbJocker,
			boolean doubleVoie,
			boolean longChemin,
			int longCheminVal)
	{
		this.nbWagon = nbWagon;
		this.nbWagonFin = nbWagonFin;
		this.nbCartesWagonRouge = nbCartesWagonRouge;
		this.nbCartesWagonNoir = nbCartesWagonNoir;
		this.nbCartesWagonBlanc = nbCartesWagonBlanc;
		this.nbCartesWagonJaune = nbCartesWagonJaune;
		this.nbCartesWagonVert = nbCartesWagonVert;
		this.nbCartesWagonBleu = nbCartesWagonBleu;
		this.nbCartesWagonRose = nbCartesWagonRose;
		this.nbCartesWagonGris = nbCartesWagonGris;
		this.nbJoueursMax = nbJoueursMax;
		this.nbJVoiesDoubles = nbJVoiesDoubles;
		this.nbJocker = nbJocker;
		this.doubleVoie = doubleVoie;
		this.longChemin = longChemin;
		this.longCheminVal = longCheminVal;
	}

	public Regles()
	{
		this.nbWagonFin = 2;
		this.nbJoueursMax = 5;
		this.nbJVoiesDoubles = 2;
		this.nbJocker = 2;
		this.doubleVoie = true;
		this.longChemin = false;
		this.longCheminVal = 0;
	}

	public int getNbWagons()
	{
		return this.nbWagon;
	}

	public int getNbWagonFin()
	{
		return nbWagonFin;
	}

	public void setNbWagonFin(int nbWagonFin)
	{
		this.nbWagonFin = nbWagonFin;
	}


	public int getNbJoueursMax()
	{
		return nbJoueursMax;
	}

	public void setNbJoueursMax(int nbJoueursMax)
	{
		this.nbJoueursMax = nbJoueursMax;
	}

	public int getNbCartesObjectifs()
	{
		return nbCartesObjectifs;
	}

	public void setNbCartesObjectifs(int nbCartesObjectifs)
	{
		this.nbCartesObjectifs = nbCartesObjectifs;
	}

	public int getNbJVoiesDoubles()
	{
		return nbJVoiesDoubles;
	}

	public void setNbJVoiesDoubles(int nbJVoiesDoubles)
	{
		this.nbJVoiesDoubles = nbJVoiesDoubles;
	}

	public int getNbJocker()
	{
		return nbJocker;
	}

	public void setNbJocker(int nbJocker)
	{
		this.nbJocker = nbJocker;
	}

	public boolean isDoubleVoie()
	{
		return doubleVoie;
	}

	public int getNbCartesWagonRouge()
	{
		return nbCartesWagonRouge;
	}

	public void setNbCartesWagonRouge(int nbCartesWagonRouge)
	{
		this.nbCartesWagonRouge = nbCartesWagonRouge;
	}

	public int getNbCartesWagonNoir()
	{
		return nbCartesWagonNoir;
	}

	public void setNbCartesWagonNoir(int nbCartesWagonNoir)
	{
		this.nbCartesWagonNoir = nbCartesWagonNoir;
	}

	public int getNbCartesWagonBlanc()
	{
		return nbCartesWagonBlanc;
	}

	public void setNbCartesWagonBlanc(int nbCartesWagonBlanc)
	{
		this.nbCartesWagonBlanc = nbCartesWagonBlanc;
	}

	public int getNbCartesWagonJaune()
	{
		return nbCartesWagonJaune;
	}

	public void setNbCartesWagonJaune(int nbCartesWagonJaune)
	{
		this.nbCartesWagonJaune = nbCartesWagonJaune;
	}

	public int getNbCartesWagonVert()
	{
		return nbCartesWagonVert;
	}

	public void setNbCartesWagonVert(int nbCartesWagonVert)
	{
		this.nbCartesWagonVert = nbCartesWagonVert;
	}

	public int getNbCartesWagonBleu()
	{
		return nbCartesWagonBleu;
	}

	public void setNbCartesWagonBleu(int nbCartesWagonBleu)
	{
		this.nbCartesWagonBleu = nbCartesWagonBleu;
	}

	public int getNbCartesWagonRose()
	{
		return this.nbCartesWagonRose;
	}

	public void setNbCartesWagonRose(int nbCartesWagonRose)
	{
		this.nbCartesWagonRose = nbCartesWagonRose;
	}

	public int getNbCartesWagonGris()
	{
		return this.nbCartesWagonGris;
	}

	public void setNbCartesWagonGris(int nbCartesWagonGris)
	{
		this.nbCartesWagonGris = nbCartesWagonGris;
	}

	public void setDoubleVoie(boolean doubleVoie)
	{
		this.doubleVoie = doubleVoie;
	}

	public boolean isLongChemin()
	{
		return longChemin;
	}

	public void setLongChemin(boolean longChemin)
	{
		this.longChemin = longChemin;
	}

	public int getLongCheminVal()
	{
		return longCheminVal;
	}

	public void setLongCheminVal(int longCheminVal)
	{
		this.longCheminVal = longCheminVal;
	}


}