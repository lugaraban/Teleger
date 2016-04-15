

import javax.swing.JFrame;
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
		
		Popup p = new Popup("User "+connectedUser.id+" has been conected", inicio);
		p.setVisible(true);
	}

	@Override
	public boolean receiveFriendRequest(SafeUser friend) {
		// Indicar que se ha recibido una petición de amistad y aceptarla o no
//        Scanner entrada=new Scanner(System.in);
//
//        System.out.println("User "+user.id+" sent you a friend request\n ¿Do you want to accept it (y/n)?");
//        String respuesta=entrada.next();
        
        Boolean response = false;
        PopupButtons p = new PopupButtons("User "+friend.id+" sent you a friend request", inicio, response);
		p.setVisible(true);
        
		server.notifyAnswerRequest(userId, userPassword, friend.id, response);
		
        return response;
	}
	
	public boolean sendMessage(String message, String type){
		if(type.equals("text")){
			textArea.append(userId+" says:\n");
			textArea.append(message+"\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
			return true;
		}
		return false;
	}

}
