import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import teleger.ClientInterface;
import teleger.SafeUser;
import teleger.ServerInterface;
import teleger.User;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterRegister extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField_1;

	CallBackObject callBack;
	
	ClientInterface client;
	ServerInterface server;
	Inicio v=new Inicio();

	public void setServer(ServerInterface server){
		this.server=server;
	}
	
	public ServerInterface getServer(){
		return server;
	}
	
    public Inicio getV() {
        return v;
    }

    public void setV(Inicio v) {
        this.v = v;
    }
	
	/**
	 * Create the panel.
	 */
	public EnterRegister(ServerInterface s, ClientInterface c, CallBackObject callBackClient) {
		callBack=callBackClient;
		client=c;
		server=s;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(62, 78, 198, 192);
		add(panel);
		
		textField = new JTextField();
		textField.setToolTipText("Name");
		textField.setName("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setCaretColor(new Color(60, 179, 113));
		textField.setAutoscrolls(false);
		textField.setBounds(20, 64, 150, 20);
		panel.add(textField);
		
		JLabel label = new JLabel("Password:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(20, 92, 103, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("User name:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(20, 49, 103, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Log in");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setFocusable(false);
		label_2.setBounds(52, 0, 85, 27);
		panel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField.setBounds(20, 107, 150, 20);
		panel.add(passwordField);
		
		JButton button = new JButton("Enter");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Comprobar si est� registrado y si lo est� iniciar sesi�n
				String name = textField.getText();
				char[] pass = passwordField.getPassword();
				String password = String.valueOf(pass);
				
				SafeUser[] friends;
				friends=server.logIn(name, password, "ip", client);
				if(friends.length>0 && friends[0].id.equals("NULL")){
					System.out.println("Error en el loggeo");
					Popup error = new Popup("Can't log in, maybe your credentials are incorrect", v);
					error.setVisible(true);
				}
				else{
					callBack.userId=friends[0].id;
					callBack.userPassword=password;
					int i;
					for(i=0;i<friends.length;i++){
						System.out.println(friends[i].id);
					}
					//Mandamos la vista al panel de los mensajes
					v.getContentPane().setVisible(false);
			        Message msg=new Message(friends, server, client, password, callBack);
			        msg.setVisible(true);
			        msg.setV(v);
			        v.setContentPane(msg);
				}

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBackground(new Color(60, 179, 113));
		button.setBounds(20, 145, 150, 23);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(299, 77, 198, 270);
		add(panel_1);
		
		JLabel label_3 = new JLabel("Register");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(52, 0, 90, 31);
		panel_1.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(25, 53, 146, 20);
		panel_1.add(textField_1);
		
		JLabel label_4 = new JLabel("User name:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(25, 38, 86, 14);
		panel_1.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(25, 98, 146, 20);
		panel_1.add(textField_2);
		
		JLabel label_5 = new JLabel("Name:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(25, 84, 46, 14);
		panel_1.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(25, 144, 146, 20);
		panel_1.add(textField_3);
		
		JLabel label_6 = new JLabel("Image URL:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(25, 123, 100, 20);
		panel_1.add(label_6);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField_1.setBounds(25, 190, 146, 20);
		panel_1.add(passwordField_1);
		
		JLabel label_7 = new JLabel("Password:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(25, 175, 86, 14);
		panel_1.add(label_7);
		
		JButton button_1 = new JButton("Register");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = textField_1.getText();
				String completeName = textField_2.getText();
				String image = textField_3.getText();
				char [] pass = passwordField_1.getPassword();
				String password = String.valueOf(pass);
				
				//System.out.println(password);
				User user = new User(name, password, completeName, image);
				if(server.register(user)){
					SafeUser[] friends;
					friends=server.logIn(name, password, "ip", client);
					int i;
					for(i=0;i<friends.length;i++){
						System.out.println(friends[i].id);
					}
					
					
					//Mandamos la vista al panel de los mensajes
					v.getContentPane().setVisible(false);
			        Message msg=new Message(friends, server, client, password, callBack);
			        msg.setVisible(true);
			        msg.setV(v);
			        v.setContentPane(msg);
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBackground(new Color(60, 179, 113));
		button_1.setBounds(25, 227, 146, 23);
		panel_1.add(button_1);
		
		JLabel label_8 = new JLabel("WELCOME TO TELEGER!");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_8.setFocusable(false);
		label_8.setBackground(Color.WHITE);
		label_8.setBounds(172, 27, 298, 40);
		add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("C:\\Users\\Rapnika\\Documents\\3_curso\\Distribuida\\TerceraEntrega\\madera.jpg"));
		label_9.setBounds(0, 0, 574, 401);
		add(label_9);

	}

}