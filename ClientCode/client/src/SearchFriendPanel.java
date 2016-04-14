import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchFriendPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SearchFriendPanel(String image, String name) {
		setBackground(new Color(204, 255, 204));
		setLayout(null);
		
		JLabel label = new JLabel("Imagen");
		label.setBounds(0, 0, 63, 53);
		add(label);
		
		JLabel lblNombreusuario = new JLabel("NombreUsuario");
		lblNombreusuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreusuario.setBounds(69, 14, 105, 25);
		add(lblNombreusuario);
		
		JButton btnFriend = new JButton("Request");
		btnFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Enviar una petición de amistad
				
			}
		});
		btnFriend.setBounds(172, 0, 73, 55);
		add(btnFriend);
		
		//Insertar la imagen
				/*ImageIcon buildImage = new ImageIcon(getClass().getResource(image));
				if(buildImage!=null){
					label.setIcon(buildImage);
					label.setText("");
				}*/
				
		//Insertar el nombre
		lblNombreusuario.setText(name);

	}

}
