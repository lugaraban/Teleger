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
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchFriendPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SearchFriendPanel(SafeUser friend, ServerInterface server, SafeUser user) {
		setLocation(new Point(20, 45));
		setBackground(new Color(204, 255, 204));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//setLayout(null);
		
		JLabel lblNombreusuario = new JLabel("NombreUsuario");
		add(lblNombreusuario);
		lblNombreusuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Insertar el nombre
		lblNombreusuario.setText(friend.id);
		
		JButton btnFriend = new JButton("Request");
		btnFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnFriend);
		btnFriend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFriend.setBackground(new Color(60, 179, 113));
		btnFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Enviar una petición de amistad
				System.out.println(user.id+" envia peticion a "+friend.id);
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
