import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
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

public class Message extends JPanel {
	
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
	public Message() {
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
		
		JLabel lblNombreuser = new JLabel("NombreUser");
		lblNombreuser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreuser.setBounds(92, 11, 115, 19);
		panel_1.add(lblNombreuser);
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(44, 15, 46, 14);
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
		lblNombreamigo.setBounds(107, 11, 133, 19);
		panel_2.add(lblNombreamigo);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(240, 41, 334, 285);
		add(textArea);
		
		JButton btnSend = new JButton("Send");
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

	}
}
