import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FriendPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	public FriendPanel(String image, String name, JLabel imageBar, JLabel nameBar) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nameBar.setText(name);
				ImageIcon buildImage = new ImageIcon(getClass().getResource(image));
				if(buildImage!=null){
					imageBar.setIcon(buildImage);
					imageBar.setText("");
				}
			}
		});
		setLayout(null);
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(0, 0, 63, 53);
		add(lblImagen);
		
		JLabel lblNombreAmigo = new JLabel("Nombre Amigo");
		lblNombreAmigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreAmigo.setBounds(89, 18, 103, 14);
		add(lblNombreAmigo);
		
		//Insertar la imagen
		ImageIcon buildImage = new ImageIcon(getClass().getResource(image));
		if(buildImage!=null){
			lblImagen.setIcon(buildImage);
			lblImagen.setText("");
		}
		
		//Insertar el nombre
		lblNombreAmigo.setText(name);

	}

}
