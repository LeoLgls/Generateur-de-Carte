package src.vue;

import src.metier.*;
import src.vue.*;
import src.Controleur;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class FrameCarteObjectif extends JFrame
{
	private PanelCarteObjectif panCarteObjectif;
	private FrameGen frameGen;


	public FrameCarteObjectif(FrameGen frameGen)
	{
		this.setTitle("Carte Objectif");
		this.frameGen = frameGen;


		this.panCarteObjectif = new PanelCarteObjectif(frameGen.getControleur(), this);

		this.add(panCarteObjectif);


		this.setSize(1000, 200);
		this.setVisible(true);
	}

	public void setCalque(File f)
	{
		this.frameGen.setCalque(f);
	}

	public File getFileCarteObjectif()
	{
		return this.frameGen.getControleur().getMetier().getVersoCarteObjectif();
	}

	public void setFileCarteObjectif(File fileCarteObjectif)
	{
		this.frameGen.getControleur().getMetier().setVersoCarteObjectif(fileCarteObjectif);
	}


}