import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;

import teleger.SafeUser;

import javax.swing.JTextField;
import java.awt.Font;
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

public class Message extends JPanel {
	
	SafeUser[] friends;
	Inicio v;

    public Inicio getV() {
        return v;
    }

    public void setV(Inicio v) {
        this.v = v;
    }
	/**
	 * Create the panel.
	 */
	public Message(SafeUser[] f) {
		friends=f;
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
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(44, 0, 46, 41);
		panel_1.add(lblImagen);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBounds(10, 11, 24, 24);
		panel_1.add(btnMenu);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(240, 0, 334, 41);
		add(panel_2);
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setLayout(null);
		
		JLabel lblNombreamigo = new JLabel("NombreAmigo");
		lblNombreamigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreamigo.setBounds(69, 9, 133, 19);
		panel_2.add(lblNombreamigo);
		
		JLabel label = new JLabel("Imagen");
		label.setBounds(10, 0, 48, 41);
		panel_2.add(label);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(240, 41, 334, 285);
		add(textArea);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Mandar mensaje al amigo de la conversación actual
				String name = label.getText();
				String message = textArea.getText();
				System.out.println("Le mando a "+name+" el siguiente mensaje:\n"+message+"");
			}
		});
		btnSend.setBackground(new Color(60, 179, 113));
		btnSend.setBounds(503, 337, 71, 64);
		add(btnSend);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(240, 337, 265, 64);
		add(textArea_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(0, 41, 236, 360);
		add(scrollPane);
		
		int i;
		for(i=0; i<friends.length;i++){
			scrollPane.setViewportView(new FriendPanel(friends[i].image, friends[i].name, label, lblNombreamigo));
		}

	}
}
