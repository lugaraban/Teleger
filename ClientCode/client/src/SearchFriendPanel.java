import javax.swing.JPanel;

import teleger.SafeUser;
import teleger.ServerInterface;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class SearchFriendPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SearchFriendPanel(SafeUser friend, ServerInterface server, SafeUser user) {
		setLocation(new Point(20, 45));
		setBackground(new Color(204, 255, 204));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(60, 179, 113));
		panel.setBounds(0, 0, 245, 55);
		add(panel);
		
		JLabel label = new JLabel("Imagen");
		panel.add(label);
		
		JLabel lblNombreusuario = new JLabel("NombreUsuario");
		panel.add(lblNombreusuario);
		lblNombreusuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Insertar el nombre
		lblNombreusuario.setText(friend.id);
		
		JButton btnFriend = new JButton("Request");
		panel.add(btnFriend);
		btnFriend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFriend.setBackground(new Color(60, 179, 113));
		btnFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Enviar una petición de amistad
				server.sendRequestForFriend(user, friend.id);
			}
		});
		
		//Insertar la imagen
				/*ImageIcon buildImage = new ImageIcon(getClass().getResource(friend.image));
				if(buildImage!=null){
					label.setIcon(buildImage);
					label.setText("");
				}*/

	}

}
