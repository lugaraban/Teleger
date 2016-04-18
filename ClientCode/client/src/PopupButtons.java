import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;

public class PopupButtons extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Boolean res;

	/**
	 * Create the dialog.
	 */
	public PopupButtons(String mensaje, JFrame panel, String userId, String userPassword, String friend, ServerInterface server) {
		setTitle("NOTIFICACIÓN");
		setResizable(false);
		setModal(false);
		
		//this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(panel);
		
		setBounds(100, 100, 333, 114);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JButton okButton = new JButton("Accept");
		okButton.setBounds(0, 62, 178, 23);
		contentPanel.add(okButton);
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				res=true;
				server.notifyAnswerRequest(userId, userPassword, friend, res);
				dispose();
			}
		});
		okButton.setBackground(new Color(60, 179, 113));
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		{
			JButton cancelButton = new JButton("Reject");
			cancelButton.setBounds(178, 62, 149, 23);
			contentPanel.add(cancelButton);
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					res=false;
					server.notifyAnswerRequest(userId, userPassword, friend, res);
					dispose();
				}
			});
			cancelButton.setBackground(new Color(250, 128, 114));
			cancelButton.setActionCommand("Cancel");
		}
		{
			JLabel lblRequest = new JLabel(mensaje);
			lblRequest.setHorizontalAlignment(SwingConstants.CENTER);
			lblRequest.setBounds(0, 0, 327, 59);
			contentPanel.add(lblRequest);
		}
	}

}
