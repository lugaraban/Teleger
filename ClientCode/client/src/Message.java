import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.LineBorder;

import teleger.ClientInterface;
import teleger.SafeUser;
import teleger.ServerInterface;
import teleger.User;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class Message extends JPanel {
	
	SafeUser[] friends;
	SafeUser newFriend;
	Inicio v;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	CallBackObject callBack;

    public Inicio getV() {
        return v;
    }

    public void setV(Inicio v) {
        this.v = v;
    }
	/**
	 * Create the panel.
	 */
	public Message(SafeUser[] f, ServerInterface server, ClientInterface client, String password, CallBackObject callBackClient) {
		friends=f;
		
		callBack=callBackClient;
		
		setBackground(new Color(204, 255, 204));
		v=new Inicio();
		setMinimumSize(new Dimension(434, 340));
		setMaximumSize(new Dimension(434, 340));
		setLayout(null);
		
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
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(0, 0, 46, 41);
		panel_1.add(lblImagen);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(240, 0, 334, 41);
		add(panel_2);
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setLayout(null);
		
		JLabel lblNombreamigo = new JLabel("Marcos");
		lblNombreamigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreamigo.setBounds(69, 9, 133, 19);
		panel_2.add(lblNombreamigo);
		
		JLabel label = new JLabel("Imagen");
		label.setBounds(10, 0, 48, 41);
		panel_2.add(label);
		
		JTextArea textArea = new JTextArea();
		//textArea.setBorder(BorderFactory.createEmptyBorder());
		//textArea.setBackground(new Color(0, 0, 0, 0));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(250, 48, 314, 278);
		add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(240, 337, 265, 64);
		add(textArea_1);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Mandar mensaje al amigo de la conversación actual
				String name = lblNombreamigo.getText();
				String message = textArea_1.getText();
				System.out.println("Le mando a "+name+" el siguiente mensaje:"+message+"");
				
				//Meter mi mensaje en mi textarea
				textArea.append(friends[0].id+" says:\n");
				textArea.append(message+"\n");
				textArea.setCaretPosition(textArea.getDocument().getLength());
				//textArea.updateUI();
				
				int i;
				System.out.println(friends.length);
				for(i=0;i<friends.length;i++){
					if(friends[i].id.equals(name)){
						//Mandar el mensaje al otro cliente
						System.out.println(friends[i].reference);
						friends[i].reference.sendMessage(message, "text");
						
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
		tabbedPane.setBackground(new Color(144, 238, 144));
		tabbedPane.setBounds(0, 40, 242, 361);
		add(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Friends", null, panel_4, null);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(new Color(255, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setMinimumSize(new Dimension(10, 1000000));
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);
		
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
		
		
		JLabel lblNombreusuario = new JLabel(friends[0].id);
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
				
				System.out.println(friends[0].id+" quiere cambiar de "+actual+" a "+newPass);
				
				if(server.changePassword(actual, newPass, friends[0].id)){
					Popup p = new Popup("Your password has been changed", v);
					p.setVisible(true);
				}
			}
		});
		btnCambiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCambiar.setBounds(45, 310, 125, 23);
		panel.add(btnCambiar);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBackground(new Color(60, 179, 113));
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(server.logOut(friends[0].id, password)){
					System.out.println("User has sucessfully logged out");
					
					//Volver a la pantalla de inicio
					v.getContentPane().setVisible(false);
			        EnterRegister er = new EnterRegister(server, client, callBack);
			        er.setVisible(true);
			        er.setV(v);
			        v.setContentPane(er);
				}
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
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUserName.setBounds(23, 180, 82, 21);
		panel.add(lblUserName);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Search", null, panel_5, null);
		panel_5.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(20, 11, 188, 20);
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(60, 179, 113));
		btnSearch.setBounds(131, 38, 77, 23);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Llamar a la función de buscar nuevos amigos
				String patron = textField_1.getText();
				
				SafeUser[] newFriends;
				newFriends=server.searchNewFriends(patron);
				
				newFriend=newFriends[0];
				
				//Se ofrecen los resultados coincidentes
				int i;
				for(i=0;i<newFriends.length;i++){
					System.out.println(newFriends[i].id);
				}
				
				panel_5.add(new SearchFriendPanel(newFriend, server, friends[0]));
				panel_5.updateUI();
			}
		});
		panel_5.add(btnSearch);
		
		int i;
		System.out.println(friends.length);
		
		//Disponer todos los amigos conectados unos debajo de otros en el scroll pane
		//setHorizontalGroup
//		GroupLayout panelLayout = new GroupLayout(panel_3);
//		panel_3.setLayout(panelLayout);
//		
//		ParallelGroup p = panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
//		SequentialGroup seqH = panelLayout.createSequentialGroup();
//		ParallelGroup p2 = panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
//		
////		panelLayout.setHorizontalGroup(p);
////		p.addGroup(seqH);
////		seqH.addGroup(p2);
//		
//		FriendPanel prueba = new FriendPanel("image", "nombre", label, lblNombreamigo);
//		p2.addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
////		ArrayList<FriendPanel> panels = new ArrayList<>();
////		for(i=0; i<friends.length;i++){
////			panels.add(new FriendPanel(friends[i].image, friends[i].name, label, lblNombreamigo));
////        	p2.addComponent(panels.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
////		}
//		seqH.addGroup(p2);
//		p.addGroup(seqH);
//		panelLayout.setHorizontalGroup(p);
//            
//        
//		//setVerticalGroup
//		ParallelGroup v = panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
//		SequentialGroup seqG = panelLayout.createSequentialGroup();
//		
//		seqG.addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
//    	seqG.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
////		for(i=0; i<panels.size();i++){
////        	seqG.addComponent(panels.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
////        	seqG.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
////		}
//		seqG.addGap(0, 2012, Short.MAX_VALUE);
//		v.addGroup(seqG);
//		
//		panelLayout.setVerticalGroup(v);
//        
//		scrollPane.setViewportView(panel_3);
//		
//		
//		GroupLayout layout = new GroupLayout(panel_4);
//        panel_4.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
//        );
		
		//PRUEBA CON EL CÓDIGO DE IPO
//		javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panel_3);
//        panel_3.setLayout(panelContenedorLayout);
//        FriendPanel prueba = new FriendPanel("image", "nombre", label, lblNombreamigo);
//        panelContenedorLayout.setHorizontalGroup(
//            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(panelContenedorLayout.createSequentialGroup()
//                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                )
//                .addGap(0, 24, Short.MAX_VALUE))
//        );
//        panelContenedorLayout.setVerticalGroup(
//            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(panelContenedorLayout.createSequentialGroup()
//                .addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//               .addGap(0, 2012, Short.MAX_VALUE))
//        );
//
//        scrollPane.setViewportView(panel_3);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel_4);
//        panel_4.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
//        );
//		scrollPane.updateUI();
	}
}
