package src.vue;

import src.metier.*;
import src.vue.*;
import src.Controleur;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class FrameRegle extends JFrame
{
	private PanelRegle panRegle;
	private FrameGen frameGen;

	public FrameRegle(FrameGen frameGen)
	{
		this.frameGen = frameGen;
		PanelRegle panRegle = new PanelRegle(frameGen.getControleur(), this);
		this.add(panRegle);
		this.setTitle("Regles");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.pack();
		this.setVisible(true);
		this.setLocation(this.frameGen.getX(), this.frameGen.getY());
	}


	public File getFileCarteWagon()
	{
		return this.frameGen.getControleur().getMetier().getVersoCarteWagon();
	}

	public void setFileCarteWagon(File fileCarteObjectif)
	{
		this.frameGen.getControleur().getMetier().setVersoCarteWagon(fileCarteObjectif);
	}


}