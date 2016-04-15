import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import teleger.ClientInterface;
import teleger.ClientInterfaceHelper;
import teleger.ServerInterface;
import teleger.ServerInterfaceHelper;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ServerInterface server = null;
				ClientInterface client = null;
				try {
					try {
						// Inicio el ORB
						ORB orb = ORB.init(args, null);
						
						// Consigo la referencia al 'PORTABLE OBJECT ADAPTER'
						POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
						// Inicio el POA manager
						rootpoa.the_POAManager().activate();
						
						// Obtengo la referencia del server
						Object objRef = orb.resolve_initial_references("NameService");
						NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
						
						//Obtengo la referencia del objeto servidor
						server = ServerInterfaceHelper.narrow(ncRef.resolve_str("TestServer"));
						
						Inicio frame = new Inicio();
						CallBackObject callBackClient = new CallBackObject(frame, server);
						callBackClient.setORB(orb);
						
						Object ref = rootpoa.servant_to_reference(callBackClient);
						client = ClientInterfaceHelper.narrow(ref);
						
						
						//Una vez establecida la conexión, inicio la interfaz gráfica
						
						frame.setVisible(true);
		                EnterRegister h=new EnterRegister(server, client, callBackClient);
		                h.setV(frame);
		                frame.setContentPane(h);
						
						} catch (InvalidName | AdapterInactive | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | ServantNotActive | WrongPolicy e) {
							e.printStackTrace();
						}
					
					
						

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
