import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import teleger.ClientInterface;
import teleger.ServerInterface;

import javax.swing.SwingConstants;

public class Popup extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Popup(String mensaje, JFrame panel) {
		setTitle("NOTIFICACIÓN");
		setResizable(false);
		setModal(false);
		
		//this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(panel);
		
		setBounds(100, 100, 281, 122);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblHol = new JLabel(mensaje);
			lblHol.setHorizontalAlignment(SwingConstants.CENTER);
			lblHol.setBounds(10, 10, 255, 64);
			contentPanel.add(lblHol);
		}
	}
	
//	public static void main(String[] args){
//		Inicio frame=new Inicio();
//		frame.setVisible(true);
//		Popup p = new Popup("User has been conected", new JFrame());
//		p.setVisible(true);
//	}

}
