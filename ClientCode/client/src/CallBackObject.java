

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.omg.CORBA.ORB;

import teleger.ClientInterfacePOA;
import teleger.SafeUser;
import teleger.ServerInterface;

public class CallBackObject extends  ClientInterfacePOA{
	JFrame inicio;
	ServerInterface server;
	private ORB orb;
	String userPassword;
	String userId;
	ArrayList<SafeUser> friends;
	TestPane panelAmigos;
	JPanel contenedor;
	JLabel lblName;
	JLabel lblImage;
	
	public CallBackObject(JFrame inicio, ServerInterface server,TestPane friendsPanel,JPanel contenedor,ArrayList<SafeUser> friends,JLabel lblName,JLabel lblImage){
		this.inicio=inicio;
		this.server=server;
		this.contenedor=contenedor;
		this.contenedor.setBounds(240, 40, 334, 298);
		this.contenedor.setLayout(null);
		this.contenedor.setBackground(new Color(204, 255, 204));
		
		CardLayout cardLayout = new CardLayout();
		this.contenedor.setLayout(cardLayout);
		this.panelAmigos=friendsPanel;
		this.friends=friends;
		this.lblName=lblName;
		this.lblImage=lblImage;
	}
	
	public void setORB(ORB orb_val) {
        orb = orb_val;
    }
	
	public void setUserPassword(String pass){
		userPassword=pass;
	}
	
	public void setUserId(String id){
		userId=id;
	}
	
	@Override
	public void notifyConnection(SafeUser connectedUser) {
		// Notificar que el usuario se ha conectado
		System.out.println("User "+connectedUser.id+" has been conected");
		
		//Actualizar mi lista de amigos conectados
		int i, contador=0;
		
		System.out.println("La referencia del cliente que se conecto: "+connectedUser.reference);
		System.out.println("añado a "+connectedUser.id);
		for(i=0;i<friends.size();i++){
			System.out.println("Usuario "+friends.get(i).id);
			if(friends.get(i).id.equals(connectedUser.id)){
				System.out.println("actualizo la referencia"+friends.get(i).id);
				friends.remove(i);
				friends.add(connectedUser);
				contador=1;
			}
		}
		if(contador==0){
			friends.add(connectedUser);
			System.out.println("añado normal a "+connectedUser.id);
		}
		
		
		for(SafeUser f: friends){
			System.out.println(f.id);
		}
		System.out.println("Numero de amigos conectados: "+friends.size());
		
		Popup p = new Popup("User "+connectedUser.id+" has been conected", inicio);
		p.setVisible(true);
		
		//Actualizar el panel de amigos conectados
		panelAmigos.addFriend(connectedUser);
		//Actualizar el cardPannel
		contenedor.add(connectedUser.id, new JTextArea());
		contenedor.getComponent(contenedor.getComponentCount()-1).setName(connectedUser.id);

        System.out.println("contenedooooooooor---->funcionando"+contenedor.getComponentCount());
	}

	@Override
	public void receiveFriendRequest(String friend) {
		// Indicar que se ha recibido una petición de amistad y aceptarla o no
		
        System.out.println(userId+" "+userPassword);
        PopupButtons p = new PopupButtons("User "+friend+" sent you a friend request", inicio, userId, userPassword, friend, server);
		p.setVisible(true);
		
	}
	
	public boolean sendMessage(String message, String type){
		JPanel card;
		JTextArea textArea = null;
		
		System.out.println("Mensaje recibido: "+message);
		System.out.println("La referencia del cliente que recibe: "+friends.get(0).reference);
		
		for(Component comp: contenedor.getComponents()){
			System.out.println("nome   "+comp.getName());
			//((CardLayout) contenedor.getLayout()).show(contenedor, type);
			if(comp.getClass().equals(JTextArea.class)){
				System.out.println("Primer if");
			textArea=(JTextArea)comp;
				if(type.equals(textArea.getName())){
					System.out.println("Nombre textarea"+textArea.getName());
					System.out.println("Nombre componente"+comp.getName());
					textArea.append(type+" says:\n");
					textArea.append(message+"\n");
					textArea.setCaretPosition(textArea.getDocument().getLength());
					textArea.updateUI();
					
					if(message.equals("I'm disconnected")){
						panelAmigos.removeFriend(type);
						panelAmigos.updateUI();
						contenedor.remove(comp);
						
					}
					
					return true;
				}
			}
		}
		return false;
	}

}
