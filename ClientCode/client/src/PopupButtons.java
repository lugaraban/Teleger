import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import teleger.ServerInterface;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupButtons extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Boolean res;

	/**
	 * Create the dialog.
	 */
	public PopupButtons(String mensaje, JFrame panel, Boolean response) {
		setTitle("NOTIFICACIÓN");
		setResizable(false);
		setModal(false);
		this.res=response;
		
		//this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(panel);
		
		setBounds(100, 100, 333, 114);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRequest = new JLabel(mensaje);
			lblRequest.setBounds(322, 0, -320, 52);
			contentPanel.add(lblRequest);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Accept");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						res=true;
						dispose();
					}
				});
				okButton.setBackground(new Color(60, 179, 113));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Reject");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						res=false;
						dispose();
					}
				});
				cancelButton.setBackground(new Color(250, 128, 114));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
