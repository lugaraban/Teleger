

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
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
	JTextArea textArea;
	ArrayList<SafeUser> friends;
	Message messagePanel;
	
	
	public CallBackObject(JFrame inicio, ServerInterface server){
		this.inicio=inicio;
		this.server=server;
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
		
		if(friends.contains(connectedUser)){
			System.out.println("El usuario ya estaba conectado");
		}else{
			friends.add(connectedUser);
		}
		
		for(SafeUser f: friends){
			System.out.println(f.id);
		}
		
		Popup p = new Popup("User "+connectedUser.id+" has been conected", inicio);
		p.setVisible(true);
	}

	@Override
	public void receiveFriendRequest(String friend) {
		// Indicar que se ha recibido una petición de amistad y aceptarla o no
		
        System.out.println(userId+" "+userPassword);
        PopupButtons p = new PopupButtons("User "+friend+" sent you a friend request", inicio, userId, userPassword, friend, server);
		p.setVisible(true);
		
	}
	
	public boolean sendMessage(String message, String type){
		if(type.equals("text")){
			System.out.println(userId+" says:\n"+message);
			textArea.append(userId+" says:\n");
			textArea.append(message+"\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
			textArea.updateUI();
			return true;
		}
		return false;
	}

}
