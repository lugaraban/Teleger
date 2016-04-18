import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

public class FriendPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	JPanel Contenedor;
	String name;
	
	public FriendPanel(String image, String name, JLabel imageBar, JLabel nameBar, JPanel contenedor) {
		this.Contenedor = contenedor;
		this.name=name;
		
		setBackground(new Color(204, 255, 204));
		setForeground(new Color(204, 255, 204));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//CardLayout cardLayout =  (CardLayout)(contenedor.getLayout());
			//	System.out.println("vou mostrar o " + name);
			((CardLayout) Contenedor.getLayout()).show(Contenedor, name);
			Contenedor.updateUI();
				nameBar.setText(name);
				System.out.println("Numero de componhentes:  "+Contenedor.getComponentCount());
			//	Contenedor.setVisible(false);
//				ImageIcon buildImage = new ImageIcon(getClass().getResource(image));
//				if(buildImage!=null){
//					imageBar.setIcon(buildImage);
//					imageBar.setText("");
//				}
			}
		});
		
		JLabel lblNombreAmigo = new JLabel("Nombre Amigo");
		lblNombreAmigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreAmigo.setBounds(83, 19, 62, 15);
		add(lblNombreAmigo);
		
		//Insertar la imagen
		/*ImageIcon buildImage = new ImageIcon(getClass().getResource(image));
		if(buildImage!=null){
			lblImagen.setIcon(buildImage);
			lblImagen.setText("");
		}*/
		
		//Insertar el nombre
		lblNombreAmigo.setText(name);
		this.setVisible(true);

	}

}
