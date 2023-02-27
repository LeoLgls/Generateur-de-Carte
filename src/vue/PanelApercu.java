package src.vue;

import src.metier.*;
import src.Controleur;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class PanelApercu extends JPanel implements MouseListener, MouseMotionListener
{
	private Controleur ctrl;
	private ImageIcon img;
	private double imgScale;

	private FrameGen frameGen;

	private int imgWidth;
	private int imgHeight;
	private Noeud noeudClique = null;
	private Noeud noeudCliqueString = null;
	private PopMenu popMenu;

	public PanelApercu(Controleur ctrl, FrameGen frameGen)
	{
		JToolTip ttp = new JToolTip();

		this.noeudClique = null;
		this.ctrl = ctrl;
		this.frameGen = frameGen;
		this.setLayout(null);
		this.setMaximumSize(new Dimension(1325, 900));
		this.setMinimumSize(new Dimension(600, 1000));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setToolTipText("<html><body><h4>Cliquez pour ajouter un point</h4></body></html>");
		this.popMenu = new PopMenu(this.frameGen, this.ctrl);
	}

	public void setCalque(File img)
	{
		this.img = new ImageIcon(img.getAbsolutePath());
		imgScale = (double) this.img.getIconWidth() / (double) this.img.getIconHeight();
		this.repaint();
	}

	public void setCalque(String path)
	{
		this.img = new ImageIcon(path);
		imgScale = (double) this.img.getIconWidth() / (double) this.img.getIconHeight();
		this.repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		int space = 10;
		//this.ctrl.getIhm().updateTable();


		if (this.img != null)
		{
			imgWidth = this.img.getIconWidth();
			imgHeight = this.img.getIconHeight();
			if (imgWidth > this.getMaximumSize().width)
			{
				imgWidth = this.getMaximumSize().width;
				imgHeight = (int) (imgWidth / imgScale);
			}
			if (imgHeight > this.getMaximumSize().height)
			{
				imgHeight = this.getMaximumSize().height;
				imgWidth = (int) (imgHeight * imgScale);
			}

			g2D.drawImage(this.img.getImage(), 0, 0, imgWidth, imgHeight, null);
			this.setSize(imgWidth, imgHeight);
		}

		for (Voie v : this.ctrl.getVoies())
		{
			g.setColor(Color.WHITE);
			g2D.setStroke(new BasicStroke(12));
			g2D.drawLine(v.getNoeud1().getPosX() + 3, v.getNoeud1().getPosY() + 3, v.getNoeud2().getPosX() + 3,
					v.getNoeud2().getPosY() + 3);
			g.setColor(v.getCouleur());
			g2D.setStroke(new BasicStroke(2.0f,
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER,
					1f, new float[]{v.getLongueur() / (v.getNbWagons()), 5.0f}, 2.0f));
			g2D.drawLine(v.getNoeud1().getPosX() + 3, v.getNoeud1().getPosY() + 3, v.getNoeud2().getPosX() + 3,
					v.getNoeud2().getPosY() + 3);
		}

		for (Voie[] v : this.ctrl.getVoiesDoubles())
		{
			float offset = 4;


			int x1 = (int) (v[0].getNoeud1().getPosX() - offset * v[0].getSlopeCoefY());
			int y1 = (int) (v[0].getNoeud1().getPosY() + offset * v[0].getSlopeCoefX());

			int x2 = (int) (v[0].getNoeud2().getPosX() - offset * v[0].getSlopeCoefY());
			int y2 = (int) (v[0].getNoeud2().getPosY() + offset * v[0].getSlopeCoefX());


			int x3 = (int) (v[1].getNoeud1().getPosX() + offset * v[1].getSlopeCoefY());
			int y3 = (int) (v[1].getNoeud1().getPosY() - offset * v[1].getSlopeCoefX());

			int x4 = (int) (v[1].getNoeud2().getPosX() + offset * v[1].getSlopeCoefY());
			int y4 = (int) (v[1].getNoeud2().getPosY() - offset * v[1].getSlopeCoefX());


			g.setColor(Color.WHITE);
			g2D.setStroke(new BasicStroke(16));
			g2D.drawLine(v[0].getNoeud1().getPosX() + 3, v[0].getNoeud1().getPosY() + 3, v[0].getNoeud2().getPosX() + 3,
					v[0].getNoeud2().getPosY() + 3);

			g.setColor(v[0].getCouleur());
			g2D.setStroke(new BasicStroke(2.0f,
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER,
					1f, new float[]{v[0].getLongueur() / (v[0].getNbWagons()), 5.0f}, 2.0f));
			g2D.drawLine(x1 + 3, y1 + 3,
					x2 + 3, y2 + 3);

			g.setColor(v[1].getCouleur());
			g2D.setStroke(new BasicStroke(2.0f,
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER,
					1f, new float[]{v[1].getLongueur() / (v[1].getNbWagons()), 5.0f}, 2.0f));
			g2D.drawLine(x3 + 3, y3 + 3,
					x4 + 3, y4 + 3);
		}

		for (Noeud n : this.ctrl.getNoeuds())
		{
			g.setColor(Color.BLACK);
			g2D.setStroke(new BasicStroke(5));
			g2D.fillOval(n.getPosX() - 5, n.getPosY() - 5, 10, 10);
			g2D.setColor(Color.WHITE);
			JLabel lblNom = new JLabel(n.getNom());

			g2D.fillRect((int) (n.getStringPosX() - 1 - lblNom.getPreferredSize().getWidth() / 2), n.getStringPosY() + 3 - lblNom.getPreferredSize().height, lblNom.getPreferredSize().width, lblNom.getPreferredSize().height + 1);

			g2D.setColor(Color.BLACK);
			g2D.drawString(lblNom.getText(), (int) (n.getStringPosX() - lblNom.getPreferredSize().getWidth() / 2), n.getStringPosY());
			lblNom.addMouseMotionListener(this);
		}
	}


	public int getImgHeight()
	{
		return imgHeight;
	}

	public int getMaxHeight()
	{
		return this.getMaximumSize().height;
	}

	public int getImgWidth()
	{
		return imgWidth;
	}

	public int getMaxWidth()
	{
		return this.getMaximumSize().width;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX() - 5;
		int y = e.getY() - 5;

		JFrame frame = new JFrame("Nom Ville");


		class PanelNom extends JPanel implements ActionListener
		{
			private JButton btnValider;
			private JTextField txtNom;
			private String nom;
			private JFrame frame;
			private int x;
			private int y;
			private Controleur ctrl;

			public PanelNom(JFrame frame, int x, int y, Controleur ctrl)
			{
				this.frame = frame;
				this.x = x;
				this.y = y;
				this.ctrl = ctrl;
				this.setLayout(new GridLayout(1, 3));
				JLabel lblNom = new JLabel("Entrez nom");
				this.txtNom = new JTextField();
				btnValider = new JButton("Valider");
				this.add(lblNom);
				this.add(txtNom);
				this.add(btnValider);

				btnValider.addActionListener(this);

			}

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == btnValider)
				{
					if (this.txtNom.getText() != "")
					{
						nom = this.txtNom.getText();
						ctrl.CreerNoeud(nom, this.x, this.y);
						this.close(frame);
					}
				}

			}

			public void close(JFrame frame)
			{
				frame.dispose();
			}
		}


		PanelNom panelNom = new PanelNom(frame, x, y, this.ctrl);
		frame.add(panelNom);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 75);
		frame.setLocation(e.getX() - frame.getWidth() / 2, e.getY() + frame.getHeight() / 2);

		frame.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();

		for (Noeud n : this.ctrl.getNoeuds())
		{
			if (n.getPosX() <= x + 7 && n.getPosX() >= x - 7 && n.getPosY() <= y + 7 && n.getPosY() >= y - 7)
			{
				this.noeudClique = n;

			}

			if (n.getStringPosX() <= x + 7 && n.getStringPosX() >= x - 7 && n.getStringPosY() <= y + 7 && n.getStringPosY() >= y - 7)
			{
				this.noeudCliqueString = n;
			}
		}
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			for (Noeud n : this.ctrl.getNoeuds())
			{
				if (n.getPosX() <= x + 10 && n.getPosX() >= x - 10 && n.getPosY() <= y + 10 && n.getPosY() >= y - 10)
				{
					this.popMenu.setNoeudSelectionne(n);
					this.popMenu.show(this, e.getX(), e.getY());
				}

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.noeudCliqueString = null;
		this.noeudClique = null;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (this.noeudClique != null)
		{
			this.noeudClique.setPosX(e.getX());
			this.noeudClique.setPosY(e.getY());
			this.noeudClique.setStringPosX(this.noeudClique.getPosX());
			this.noeudClique.setStringPosY(this.noeudClique.getPosY() - 12);
		}

		if (this.noeudCliqueString != null)
		{
			this.noeudCliqueString.setStringPosX(e.getX());
			this.noeudCliqueString.setStringPosY(e.getY());
		}
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{

	}
}
