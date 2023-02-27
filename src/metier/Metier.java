package src.metier;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.awt.Color;

import src.Controleur;
import src.metier.Noeud;
import src.metier.Voie;

import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.Element;

public class Metier
{
	private Controleur ctrl;
	private ArrayList<Noeud> ensNoeuds;
	private ArrayList<Voie> ensVoies;
	private ArrayList<Voie[]> ensVoiesDoubles;
	private ArrayList<CarteObjectif> ensCObjectif;
	private Regles regles;
	private File versoCarteObjectif;
	private File versoCarteWagon;

	private boolean importer;

	public Metier(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.ensNoeuds = new ArrayList<Noeud>();
		this.ensVoies = new ArrayList<Voie>();
		this.ensVoiesDoubles = new ArrayList<Voie[]>();
		this.ensCObjectif = new ArrayList<CarteObjectif>();
		this.regles = new Regles();
		this.versoCarteObjectif = null;
		this.versoCarteWagon = null;
	}

	public Controleur getCtrl()
	{
		return ctrl;
	}

	public Noeud CreerNoeud(String nom, int posX, int posY)
	{
		for (Noeud n : ensNoeuds)
		{
			if (n.getNom().equals(nom))
			{
				return null;
			}
		}
		Noeud n = new Noeud(nom, posX, posY);
		this.ensNoeuds.add(n);
		return n;
	}

	public void CreerVoie(Noeud v1, Noeud v2, int nbWagon, Color couleur)
	{
		int nbMmVoies = 0;
		Voie vDouble = null;

		for (Voie v : this.ensVoies)
		{
			if (v.equals(new Voie(v1, v2, nbWagon, couleur)))
			{
				nbMmVoies++;
				vDouble = v;
			}
		}
		if ((nbMmVoies == 1 && !this.regles.isDoubleVoie()) || nbMmVoies > 1)
		{
			System.out.println("Erreur : Voie déjà existante");
		} else if (nbMmVoies == 1 && this.regles.isDoubleVoie())
		{
			Voie[] v = new Voie[2];
			v[0] = new Voie(v1, v2, nbWagon, couleur);
			v[1] = vDouble;

			v1.ajouterVoie(new Voie(v1, v2, nbWagon, couleur));
			v2.ajouterVoie(new Voie(v1, v2, nbWagon, couleur));
			this.ensVoiesDoubles.add(v);
		} else
		{
			this.ensVoies.add(new Voie(v1, v2, nbWagon, couleur));
			v1.ajouterVoie(new Voie(v1, v2, nbWagon, couleur));
			v2.ajouterVoie(new Voie(v1, v2, nbWagon, couleur));
		}
	}

	public void ajouterCarteObjectif(CarteObjectif co)
	{
		this.ensCObjectif.add(co);
		for (CarteObjectif c : this.ensCObjectif)
		{
		}
	}

	public ArrayList<Noeud> getNoeuds()
	{
		return this.ensNoeuds;
	}

	public ArrayList<CarteObjectif> getCarteObjectifs()
	{
		return this.ensCObjectif;
	}

	public ArrayList<Voie> getVoies()
	{
		return this.ensVoies;
	}

	public ArrayList<Voie[]> getVoiesDoubles()
	{
		return this.ensVoiesDoubles;
	}

	public Regles getRegles()
	{
		return this.regles;
	}

	public void setRegles(Regles regles)
	{
		this.regles = regles;
	}

	public void setVersoCarteObjectif(File file)
	{
		this.versoCarteObjectif = file;
	}

	public File getVersoCarteObjectif()
	{
		return this.versoCarteObjectif;
	}

	public void setVersoCarteWagon(File file)
	{
		this.versoCarteWagon = file;
	}

	public File getVersoCarteWagon()
	{
		return this.versoCarteWagon;
	}

	public void supprimerNoeud(Noeud n)
	{
		this.ensNoeuds.remove(n);
	}

	public void supprimerNoeud(int i)
	{
		if (this.ensNoeuds.get(i).getVoies() == null)
		{
			this.ensNoeuds.remove(i);
		} else
		{
			System.out.println("Erreur : Noeud non vide");
		}
	}


	public void genererXML(File file, File fileImage)
	{
		try
		{
			PrintWriter pw = new PrintWriter(file.getAbsolutePath(), "UTF-8");
			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			pw.println("<Graphe>");

			String encodedfile = null;
			FileInputStream fileInputStreamReader = null;
			byte[] bytes = null;

			if (this.versoCarteObjectif != null)
			{
				fileInputStreamReader = new FileInputStream(versoCarteObjectif);
				bytes = new byte[(int) versoCarteObjectif.length()];
				fileInputStreamReader.read(bytes);
				encodedfile = Base64.getEncoder().encodeToString(bytes);

				fileInputStreamReader.close();

				pw.println("    <carteObjectifVerso>" + encodedfile + "</carteObjectifVerso>");
			} else
			{
				pw.println("    <carteObjectifVerso>null</carteObjectifVerso>");
			}

			if (this.versoCarteWagon != null)
			{
				fileInputStreamReader = new FileInputStream(this.versoCarteWagon);
				bytes = new byte[(int) versoCarteWagon.length()];
				fileInputStreamReader.read(bytes);
				encodedfile = Base64.getEncoder().encodeToString(bytes);

				fileInputStreamReader.close();

				pw.println("    <carteWagonVerso>" + encodedfile + "</carteWagonVerso>");
			} else
			{
				pw.println("    <carteWagonVerso>null</carteWagonVerso>");
			}


			for (Noeud n : this.ensNoeuds)
			{
				pw.println("    <noeud Nom =\"" + n.getNom() + "\">");
				pw.println("        <x>" + n.getPosX() + "</x>");
				pw.println("        <y>" + n.getPosY() + "</y>");
				pw.println("        <nomX>" + n.getStringPosX() + "</nomX>");
				pw.println("        <nomY>" + n.getStringPosY() + "</nomY>");
				pw.println("    </noeud>");
			}

			for (Voie v : this.ensVoies)
			{
				pw.println("    <voie Noeud1 =\"" + v.getNoeud1().getNom() + "\">");
				pw.println("        <Noeud2>" + v.getNoeud2().getNom() + "</Noeud2>");
				pw.println("        <nbWagon>" + v.getNbWagons() + "</nbWagon>");
				pw.println("        <couleur r =\"" + v.getCouleur().getRed() + "\" g = \"" + v.getCouleur().getGreen() + "\" b = \"" + v.getCouleur().getBlue() + "\"/>");
				pw.println("    </voie>");
			}

			for (Voie[] v : this.ensVoiesDoubles)
			{
				pw.println("    <voieDouble Noeud1 =\"" + v[0].getNoeud1().getNom() + "\">");
				pw.println("        <Noeud2>" + v[0].getNoeud2().getNom() + "</Noeud2>");
				pw.println("        <Noeud3>" + v[1].getNoeud1().getNom() + "</Noeud3>");
				pw.println("        <Noeud4>" + v[1].getNoeud2().getNom() + "</Noeud4>");
				pw.println("        <nbWagon1>" + v[0].getNbWagons() + "</nbWagon1>");
				pw.println("        <nbWagon2>" + v[1].getNbWagons() + "</nbWagon2>");
				pw.println("        <couleur1 r =\"" + v[0].getCouleur().getRed() + "\" g = \"" + v[0].getCouleur().getGreen() + "\" b = \"" + v[0].getCouleur().getBlue() + "\"/>");
				pw.println("        <couleur2 r =\"" + v[1].getCouleur().getRed() + "\" g = \"" + v[1].getCouleur().getGreen() + "\" b = \"" + v[1].getCouleur().getBlue() + "\"/>");
				pw.println("    </voieDouble>");
			}

			for (CarteObjectif c : this.ensCObjectif)
			{
				pw.println("    <carteObjectif Noeud1 =\"" + c.getNoeud1().getNom() + "\">");
				pw.println("        <Noeud2>" + c.getNoeud2().getNom() + "</Noeud2>");
				pw.println("        <nbPoints>" + c.getNbPoints() + "</nbPoints>");
				pw.println("    </carteObjectif>");
			}

			pw.println("    <regles nbWagons =\"" + this.regles.getNbWagons() + "\">");
			pw.println("        <nbWagonsFin>" + this.regles.getNbWagonFin() + "</nbWagonsFin>");
			pw.println("        <nbCartesWagonRouge>" + this.regles.getNbCartesWagonRouge() + "</nbCartesWagonRouge>");
			pw.println("        <nbCartesWagonNoir>" + this.regles.getNbCartesWagonNoir() + "</nbCartesWagonNoir>");
			pw.println("        <nbCartesWagonBlanc>" + this.regles.getNbCartesWagonBlanc() + "</nbCartesWagonBlanc>");
			pw.println("        <nbCartesWagonVert>" + this.regles.getNbCartesWagonVert() + "</nbCartesWagonVert>");
			pw.println("        <nbCartesWagonBleu>" + this.regles.getNbCartesWagonBleu() + "</nbCartesWagonBleu>");
			pw.println("        <nbCartesWagonJaune>" + this.regles.getNbCartesWagonJaune() + "</nbCartesWagonJaune>");
			pw.println("        <nbCartesWagonRose>" + this.regles.getNbCartesWagonRose() + "</nbCartesWagonRose>");
			pw.println("        <nbCartesWagonGris>" + this.regles.getNbCartesWagonGris() + "</nbCartesWagonGris>");
			pw.println("        <nbJVoiesDoubles>" + this.regles.getNbJVoiesDoubles() + "</nbJVoiesDoubles>");
			pw.println("        <nbJoueursMax>" + this.regles.getNbJoueursMax() + "</nbJoueursMax>");
			pw.println("        <nbCartesObjectif>" + this.regles.getNbCartesObjectifs() + "</nbCartesObjectif>");
			pw.println("        <nbJVoiesDoubles>" + this.regles.getNbJVoiesDoubles() + "</nbJVoiesDoubles>");
			pw.println("        <nbJockers>" + this.regles.getNbJocker() + "</nbJockers>");
			pw.println("        <doubleVoie>" + this.regles.isDoubleVoie() + "</doubleVoie>");
			pw.println("        <longChemin>" + this.regles.isLongChemin() + "</longChemin>");
			pw.println("        <longCheminVal>" + this.regles.getLongCheminVal() + "</longCheminVal>");

			pw.println("    </regles>");

			String encodedfile2 = null;

			if (fileImage != null)
			{
				fileInputStreamReader = new FileInputStream(fileImage);
				bytes = new byte[(int) fileImage.length()];
				fileInputStreamReader.read(bytes);
				encodedfile2 = Base64.getEncoder().encodeToString(bytes);

				pw.println("<Image>" + encodedfile2 + "</Image>");
			} else
			{
				pw.println("<Image>null</Image>");
			}


			pw.println("</Graphe>");

			pw.close();
			fileInputStreamReader.close();
			System.out.println("Fichier XML généré avec succès");

			fileInputStreamReader.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void importerXML(File file) throws JDOMException, IOException
	{
		this.importer = true;
		Document document;
		Element racine;

		SAXBuilder builder = new SAXBuilder();

		File xmFile = file;

		try
		{
			document = builder.build(xmFile.getAbsolutePath());
		} catch (JDOMException | IOException e)
		{
			throw e;
		}

		racine = document.getRootElement();

		List<Element> listNoeuds = racine.getChildren("noeud");
		List<Element> listVoies = racine.getChildren("voie");
		List<Element> listCartesObjectif = racine.getChildren("carteObjectif");
		List<Element> listVersoCarteObjectif = racine.getChildren("carteObjectifVerso");
		List<Element> listVersoCarteWagon = racine.getChildren("carteWagonVerso");
		List<Element> listRegles = racine.getChildren("regles");
		List<Element> listVoiesDoubles = racine.getChildren("voieDouble");
		List<Element> listImages = racine.getChildren("Image");


		byte[] decodedBytes = null;
		FileOutputStream fos = null;

		if (listImages.get(0).getText().equals("null"))
		{
			System.out.println("Pas d'image de fond");
		} else
		{
			decodedBytes = Base64.getDecoder().decode(listImages.get(0).getText());
			fos = new FileOutputStream("carte.png");
			fos.write(decodedBytes);
			fos.close();

			File image = new File("carte.png");

			ctrl.getIhm().getBarreMenu().setImage(image);
			ctrl.getIhm().setCalque(image);
		}

		if (listVersoCarteObjectif.get(0).getText().equals("null"))
		{
			System.out.println("Pas d'image de carte objectif");
		} else
		{
			decodedBytes = Base64.getDecoder().decode(listVersoCarteObjectif.get(0).getText());
			fos = new FileOutputStream("Verso Carte objectif.png");
			fos.write(decodedBytes);
			fos.close();

			this.versoCarteObjectif = new File("Verso Carte objectif.png");
		}

		if (listVersoCarteWagon.get(0).getText().equals("null"))
		{
			System.out.println("Pas d'image de carte Wagon");
		} else
		{
			decodedBytes = Base64.getDecoder().decode(listVersoCarteWagon.get(0).getText());
			fos = new FileOutputStream("Verso Carte Wagon.png");
			fos.write(decodedBytes);
			fos.close();


			this.versoCarteWagon = new File("Verso Carte Wagon.png");

		}


		for (Element noeud : listNoeuds)
		{
			String nom = noeud.getAttributeValue("Nom");
			int posX = Integer.parseInt(noeud.getChildText("x"));
			int posY = Integer.parseInt(noeud.getChildText("y"));
			int nomX = Integer.parseInt(noeud.getChildText("nomX"));
			int nomY = Integer.parseInt(noeud.getChildText("nomY"));

			this.ensNoeuds.add(new Noeud(nom, posX, posY, nomX, nomY));
		}

		for (Element voie : listVoiesDoubles)
		{
			String nomNoeud1 = voie.getAttributeValue("Noeud1");
			String nomNoeud2 = voie.getChildText("Noeud2");
			String nomNoeud3 = voie.getChildText("Noeud3");
			String nomNoeud4 = voie.getChildText("Noeud4");

			int nbWagons1 = Integer.parseInt(voie.getChildText("nbWagon1"));
			int nbWagons2 = Integer.parseInt(voie.getChildText("nbWagon2"));
			String r1 = voie.getChild("couleur1").getAttributeValue("r");
			String g1 = voie.getChild("couleur1").getAttributeValue("g");
			String b1 = voie.getChild("couleur1").getAttributeValue("b");
			String r2 = voie.getChild("couleur2").getAttributeValue("r");
			String g2 = voie.getChild("couleur2").getAttributeValue("g");
			String b2 = voie.getChild("couleur2").getAttributeValue("b");

			Noeud noeud1 = null;
			Noeud noeud2 = null;
			Noeud noeud3 = null;
			Noeud noeud4 = null;

			for (Noeud n : this.ensNoeuds)
			{
				if (n.getNom().equals(nomNoeud1))
					noeud1 = n;
				if (n.getNom().equals(nomNoeud2))
					noeud2 = n;
				if (n.getNom().equals(nomNoeud3))
					noeud3 = n;
				if (n.getNom().equals(nomNoeud4))
					noeud4 = n;
			}

			Voie vTemp1 = new Voie(noeud1, noeud2, nbWagons1, new Color(Integer.parseInt(r1), Integer.parseInt(g1), Integer.parseInt(b1)));
			Voie vTemp2 = new Voie(noeud3, noeud4, nbWagons2, new Color(Integer.parseInt(r2), Integer.parseInt(g2), Integer.parseInt(b2)));

			this.CreerVoie(vTemp1.getNoeud1(), vTemp1.getNoeud2(), vTemp1.getNbWagons(), vTemp1.getCouleur());
			this.CreerVoie(vTemp2.getNoeud1(), vTemp2.getNoeud2(), vTemp2.getNbWagons(), vTemp2.getCouleur());
		}

		for (Element voie : listVoies)
		{
			String nomNoeud1 = voie.getAttributeValue("Noeud1");
			String nomNoeud2 = voie.getChildText("Noeud2");
			int nbWagons = Integer.parseInt(voie.getChildText("nbWagon"));
			String r = voie.getChild("couleur").getAttributeValue("r");
			String g = voie.getChild("couleur").getAttributeValue("g");
			String b = voie.getChild("couleur").getAttributeValue("b");

			Noeud noeud1 = null;
			Noeud noeud2 = null;

			for (Noeud n : this.ensNoeuds)
			{
				if (n.getNom().equals(nomNoeud1))
					noeud1 = n;
				if (n.getNom().equals(nomNoeud2))
					noeud2 = n;
			}

			Voie vTemp = new Voie(noeud1, noeud2, nbWagons, new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
			this.CreerVoie(vTemp.getNoeud1(), vTemp.getNoeud2(), vTemp.getNbWagons(), vTemp.getCouleur());
		}

		for (Element carteObjectif : listCartesObjectif)
		{
			String nomNoeud1 = carteObjectif.getAttributeValue("Noeud1");
			String nomNoeud2 = carteObjectif.getChildText("Noeud2");
			int nbPoints = Integer.parseInt(carteObjectif.getChildText("nbPoints"));

			Noeud noeud1 = null;
			Noeud noeud2 = null;

			for (Noeud n : this.ensNoeuds)
			{
				if (n.getNom().equals(nomNoeud1))
					noeud1 = n;
				if (n.getNom().equals(nomNoeud2))
					noeud2 = n;
			}

			this.ensCObjectif.add(new CarteObjectif(noeud1, noeud2, nbPoints));
		}

		for (Element regle : listRegles)
		{
			int nbWagons = Integer.parseInt(regle.getAttributeValue("nbWagons"));
			int nbCartesObjectif = Integer.parseInt(regle.getChildText("nbCartesObjectif"));
			int nbWagonFin = Integer.parseInt(regle.getChildText("nbWagonsFin"));
			int nbCartesWagonVert = Integer.parseInt(regle.getChildText("nbCartesWagonVert"));
			int nbCartesWagonBleu = Integer.parseInt(regle.getChildText("nbCartesWagonBleu"));
			int nbCartesWagonJaune = Integer.parseInt(regle.getChildText("nbCartesWagonJaune"));
			int nbCartesWagonRose = Integer.parseInt(regle.getChildText("nbCartesWagonRose"));
			int nbCartesWagonGris = Integer.parseInt(regle.getChildText("nbCartesWagonGris"));
			int nbCartesWagonNoir = Integer.parseInt(regle.getChildText("nbCartesWagonNoir"));
			int nbCartesWagonBlanc = Integer.parseInt(regle.getChildText("nbCartesWagonBlanc"));
			int nbCartesWagonRouge = Integer.parseInt(regle.getChildText("nbCartesWagonRouge"));
			int nbJoueursMax = Integer.parseInt(regle.getChildText("nbJoueursMax"));
			//int nbCartesObjectif = Integer.parseInt(regle.getChildText("nbCartesObjectif"));
			int nbJVoiesDoubles = Integer.parseInt(regle.getChildText("nbJVoiesDoubles"));
			int nbJockers = Integer.parseInt(regle.getChildText("nbJockers"));
			boolean doubleVoie = Boolean.parseBoolean(regle.getChildText("doubleVoie"));
			boolean longChemin = Boolean.parseBoolean(regle.getChildText("longChemin"));
			int longCheminVal = Integer.parseInt(regle.getChildText("longCheminVal"));

			this.regles = new Regles(
					nbWagons,
					nbWagonFin,
					nbCartesWagonRouge,
					nbCartesWagonNoir,
					nbCartesWagonBlanc,
					nbCartesWagonJaune,
					nbCartesWagonVert,
					nbCartesWagonBleu,
					nbCartesWagonRose,
					nbCartesWagonGris,
					nbJoueursMax,
					nbJVoiesDoubles,
					nbJockers,
					longChemin,
					doubleVoie,
					longCheminVal);
		}

		ctrl.getIhm().repaintApercu();
		System.out.println("Fichier XML importé avec succès");
	}

	public Noeud getNoeud(String valueAt)
	{
		for (Noeud n : this.ensNoeuds)
		{
			if (n.getNom().equals(valueAt))
				return n;
		}
		return null;
	}

	public File getVersoWagon()
	{
		return this.versoCarteWagon;
	}

	public boolean importer()
	{
		return importer;
	}
}
