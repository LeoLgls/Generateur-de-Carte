package src.vue;

import src.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class PanelPoint extends JPanel implements MouseListener, ActionListener, FocusListener
{
	private Controleur ctrl;
	private JFormattedTextField txtX;
	private JFormattedTextField txtY;
	private JTextField txtNom;
	private JButton btnValidPoint;
	private JToolTip erreur;
	private boolean clicX = false, clicY = false, clicNom = false;

	public PanelPoint(Controleur ctrl, FrameGen frameMere)
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(1, 4));
		//creation
		this.txtX = new JFormattedTextField("Position X");
		this.txtY = new JFormattedTextField("Position Y");
		this.txtNom = new JTextField("Nom ville");
		this.erreur = new JToolTip();
		this.erreur.setTipText("<html><p style=\"color:rgb(255, 0, 0);\">Les coordonnées doivent être des entiers</p></html>");

		this.txtX.setToolTipText("Valeur de X");
		this.txtY.setToolTipText("Valeur de Y"); // Tooltips (Genial)
		this.txtNom.setToolTipText("Ville");

		btnValidPoint = new JButton("Ajouter Point");

		//positionnement
		this.add(txtNom);
		this.add(txtX);
		this.add(txtY);
		this.add(btnValidPoint);
		this.btnValidPoint.add(erreur);
		this.erreur.setVisible(false);

		//activation
		this.txtX.addMouseListener(this);
		this.txtY.addMouseListener(this);
		this.txtX.addFocusListener(this);
		this.txtY.addFocusListener(this);
		this.txtNom.addFocusListener(this);
		this.txtNom.addMouseListener(this);

		this.btnValidPoint.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int x = 0;
		int y = 0;
		boolean coord = true;
		if (e.getSource() == this.btnValidPoint)
		{
			try
			{

				x = Integer.parseInt(this.txtX.getText());
				y = Integer.parseInt(this.txtY.getText());

				if (x < 0 || y < 0 || x > this.ctrl.getIhm().getPanelApercu().getMaxWidth() || y > this.ctrl.getIhm().getPanelApercu().getMaxHeight())
				{
					coord = false;
				}
			} catch (NumberFormatException a)
			{
				coord = false;
			}
			this.erreur.setVisible(!coord);
			this.erreur.grabFocus();

			if (coord == true && this.txtNom != null)
			{
				this.ctrl.CreerNoeud(this.txtNom.getText(), x, y);
				this.txtNom.setText("");
				this.txtX.setText("");
				this.txtY.setText("");
				this.erreur.setVisible(false);
			}
		}
	}

	public void mouseClicked(MouseEvent e)
	{

		if (e.getSource() == this.txtX && clicX == false)
		{
			this.txtX.setText("");
			this.clicX = true;
		}

		if (e.getSource() == this.txtY && clicY == false)
		{
			this.txtY.setText("");
			this.clicY = true;
		}

		if (e.getSource() == this.txtNom && clicNom == false)
		{
			this.txtNom.setText("");
			this.clicNom = true;
		}

	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{

	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if (e.getSource() == this.txtX)
		{
			this.txtX.setText("");
		}
		if (e.getSource() == this.txtY)
		{
			this.txtY.setText("");
		}
		if (e.getSource() == this.txtNom)
		{
			this.txtNom.setText("");
		}

	}

	@Override
	public void focusLost(FocusEvent e)
	{
		// TODO Auto-generated method stub

	}
}
