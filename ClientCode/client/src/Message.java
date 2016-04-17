import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.border.LineBorder;

import teleger.ClientInterface;
import teleger.SafeUser;
import teleger.ServerInterface;
import teleger.User;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Message extends JPanel {
	
	ArrayList<SafeUser> friendsResult;
	ArrayList<SafeUser> friends;
	SafeUser newFriend;
	Inicio v;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	ClientInterface client;
	ServerInterface server;
	JPanel friendsSearched;
	JLabel lblNombreAmigo;
	JPanel panel_4;
	//CallBackObject callBack;

    public Inicio getV() {
        return v;
    }

    public void setV(Inicio v) {
        this.v = v;
    }
    
	/**
	 * Create the panel.
	 */
	public Message(ArrayList<SafeUser> f, ServerInterface server, ClientInterface client, String password, CallBackObject callBackClient) {
		friends=f;
		this.server=server;
		this.client=client;
		//callBack=callBackClient;
		friendsResult = new ArrayList<>();
		setBackground(new Color(204, 255, 204));
		v=new Inicio();
		setMinimumSize(new Dimension(434, 340));
		setMaximumSize(new Dimension(434, 340));
		setLayout(null);
		
		//JPanel contenedor = callBackClient.contenedor;
		callBackClient.contenedor.setBounds(240, 40, 334, 298);
		callBackClient.contenedor.setOpaque(false);
		add(callBackClient.contenedor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(240, 0, 334, 41);
		add(panel_2);
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setLayout(null);
		
		lblNombreAmigo = callBackClient.lblName;
		lblNombreAmigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreAmigo.setBounds(69, 9, 133, 19);
		panel_2.add(lblNombreAmigo);
		
	//	JLabel label = new JLabel("Imagen");
		JLabel label = callBackClient.lblImage;
		label.setBounds(10, 0, 48, 41);
		panel_2.add(label);
		
//		JPanel contenedor = new JPanel();
//		contenedor.setBounds(240, 40, 334, 298);
//		add(contenedor);
//		contenedor.setLayout(null);
//		contenedor.setBackground(new Color(204, 255, 204));
//		callBack.contenedor = contenedor;
//		
//		CardLayout cardLayout = new CardLayout();
//		contenedor.setLayout(cardLayout);
		

		panel_4 = callBackClient.panelAmigos;
				//new TestPane(friends,label,lblNombreAmigo, callBackClient.contenedor);
		//panel_4 = callBackClient.contenedor;
		friendsSearched = new TestPane(friendsResult, label, lblNombreAmigo, callBackClient.contenedor);
		
		for(int i=0;i<friends.size();i++){
			//JTextArea txtArea = new JTextArea();
			//txtArea.setName(friends.get(i).id);
			//txtArea.setOpaque(false);
			callBackClient.contenedor.add(friends.get(i).id, new JTextArea(friends.get(i).id));
			callBackClient.contenedor.getComponent(i).setName(friends.get(i).id);
			//(JTextArea)(contenedor.getComponent(i)).setOpaque(false);
		}
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 242, 41);
		add(panel_1);
		panel_1.setBackground(new Color(60, 179, 113));
		panel_1.setLayout(null);
		
		/*ImageIcon buildImage = new ImageIcon(getClass().getResource(friends[0].image));
		if(buildImage!=null){
			lblImagen.setIcon(buildImage);
			lblImagen.setText("");
		}*/
		//JLabel lblImagen = new JLabel(buildImage);
		
		JLabel lblImagen = callBackClient.lblImage;
		lblImagen.setBounds(0, 0, 46, 41);
		panel_1.add(lblImagen);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(240, 337, 265, 64);
		add(textArea_1);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Mandar mensaje al amigo de la conversación actual
				String name = lblNombreAmigo.getText();
				String message = textArea_1.getText();
				System.out.println("Le mando a "+name+" el siguiente mensaje:"+message+"");
				
				//Meter mi mensaje en mi textarea
				//textArea.append(">"+message+"\n");
				//textArea.setCaretPosition(textArea.getDocument().getLength());
				//textArea.updateUI();
				
				int i;
				for(i=0;i<friends.size();i++){
					System.out.println("Amigos->"+friends.get(i).id);
					if(friends.get(i).id.equals(name)){
						//Mandar el mensaje al otro cliente
						
						//Comprobar si se puede enviar el mensaje
						//Si no se puede es que el usuario está desconectado,
						//y se eliminaría de la lista de amigos conectados.
						if(friends.get(i).reference.sendMessage(message, friends.get(0).id)){
							System.out.println("Se ha enviado el mensaje correctamente");
						}else{
							System.out.println("El usuario está desconectado");
							Popup p = new Popup("The user is not connected",v);
							//Eliminar al usuario de la lista
							friends.remove(friends.get(i));
							
							//ACTUALIZAR EL SCROLLPANE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						}
						
//						//Meter mi mensaje en mi textarea
//						textArea.append(friends[0].id+" says:\n");
//						textArea.append(message+"\n");
//						textArea.setCaretPosition(textArea.getDocument().getLength());
						
						//break;
					}
				}
				textArea_1.setText("");
			}
		});
		
		
		btnSend.setBackground(new Color(60, 179, 113));
		btnSend.setBounds(503, 337, 71, 64);
		add(btnSend);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(204, 255, 204));
		tabbedPane.setBounds(0, 40, 242, 361);
		add(tabbedPane);
		//panel_4.setBackground(new Color(204, 255, 204));
		tabbedPane.addTab("Friends", null, panel_4, null);
		//callBackClient.panelAmigos=(TestPane)panel_4;
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("My profile", null, panel, null);
		panel.setLayout(null);
		
		//JLabel lblImage = new JLabel("");
		JLabel lblImage = new JLabel("Image");
		lblImage.setBounds(28, 44, 177, 131);
		panel.add(lblImage);
		
		
		/*ImageIcon buildImage = new ImageIcon(getClass().getResource(friends[0].image));
		if(buildImage!=null){
			lblImage.setIcon(buildImage);
			lblImage.setText("");
		}*/
		
		
		JLabel lblNombreusuario = new JLabel(friends.get(0).id);
		lblNombreusuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreusuario.setBounds(115, 176, 106, 28);
		panel.add(lblNombreusuario);
		
		JLabel lblPassword = new JLabel("Actual password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(52, 212, 95, 20);
		panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(45, 233, 125, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setBackground(new Color(60, 179, 113));
		btnCambiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Llamar a la función de cambiar contraseña
				String actual = textField.getText();
				String newPass = textField_2.getText();
				
				System.out.println(friends.get(0).id+" quiere cambiar de "+actual+" a "+newPass);
				
				if(server.changePassword(actual, newPass, friends.get(0).id)){
					Popup p = new Popup("Your password has been changed", v);
					p.setVisible(true);
				}
			}
		});
		btnCambiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCambiar.setBounds(45, 310, 125, 23);
		panel.add(btnCambiar);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(server.logOut(friends.get(0).id, password)){
					System.out.println("User has sucessfully logged out");
					
					//Volver a la pantalla de inicio
					v.getContentPane().setVisible(false);
			        EnterRegister er = new EnterRegister(server, client, callBackClient);
			        er.setVisible(true);
			        er.setV(v);
			        v.setContentPane(er);
				}
			}
		});
		btnLogOut.setBackground(new Color(60, 179, 113));
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogOut.setBounds(45, 11, 118, 28);
		panel.add(btnLogOut);
		
		JLabel lblNewPassword = new JLabel("New password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewPassword.setBounds(64, 258, 95, 14);
		panel.add(lblNewPassword);
		
		textField_2 = new JTextField();
		textField_2.setBounds(45, 279, 125, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name:");
		//JLabel lblUserName = callBackClient.lblName;
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUserName.setBounds(23, 180, 82, 21);
		panel.add(lblUserName);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Search", null, panel_5, null);
		panel_5.setLayout(null);
		friendsSearched.setBackground(new Color(255, 255, 255));
		friendsSearched.setBounds(0, 72, 237, 261);
		panel_5.add(friendsSearched);
		GridBagLayout gbl_friendsSearched = new GridBagLayout();
		gbl_friendsSearched.columnWidths = new int[]{0};
		gbl_friendsSearched.rowHeights = new int[]{0};
		gbl_friendsSearched.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_friendsSearched.rowWeights = new double[]{Double.MIN_VALUE};
		friendsSearched.setLayout(gbl_friendsSearched);
		
		textField_1 = new JTextField();
		textField_1.setBounds(20, 11, 188, 20);
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(131, 38, 77, 23);
		btnSearch.setBackground(new Color(60, 179, 113));
		
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Vaciar el arraylist
				friendsResult.clear();
				
				//friendsSearched = new TestPane(friendsResult, label, lblNombreamigo);
				
				
				//Llamar a la función de buscar nuevos amigos
				String patron = textField_1.getText();
				
				SafeUser[] newFriends;
				newFriends=server.searchNewFriends(patron);
				
				//newFriend=newFriends[0];
				
				//Se ofrecen los resultados coincidentes
				//y se pasan todos los datos a un arraylist
				System.out.println("El tamaño del array de vuelta es: "+newFriends.length);
				System.out.println("Los usuarios coincidentes son: ");
				int i;
				for(i=0;i<newFriends.length;i++){
					friendsResult.add(newFriends[i]);
					System.out.println(">"+friendsResult.get(i).id);
				}
				System.out.println("Frends get 0 "+ friends.get(0).id);
				//friendsSearched = new TestPane(friendsResult, label, lblNombreamigo);
				((TestPane) friendsSearched).addSearchFriends(friendsResult, server, friends.get(0));
				panel_5.add(friendsSearched);
			}
		});
		panel_5.add(btnSearch);
		
	
	}
}
