

import java.util.Scanner;

import org.omg.CORBA.ORB;

import teleger.ClientInterfacePOA;
import teleger.SafeUser;

public class CallBackObject extends  ClientInterfacePOA{
	public CallBackObject(){};
	private ORB orb;
	public void setORB(ORB orb_val) {
        orb = orb_val;
    }
	@Override
	public void notifyConnection(SafeUser connectedUser) {
		// Notificar que el usuario se ha conectado
		System.out.println("User "+connectedUser.name+" has been conected");
	}

	@Override
	public boolean receiveFriendRequest(SafeUser user) {
		// Indicar que se ha recibido una petición de amistad y aceptarla o no
        Scanner entrada=new Scanner(System.in);

        System.out.println("User "+user.name+" sent you a friend request\n ¿Do you want to accept it (y/n)?");
        String respuesta=entrada.next();
        
        return respuesta.equals("y");
	}

	@Override
	public void notifyAnswerRequest(SafeUser connectedUser, boolean acceptance) {
		if(acceptance==true){
            System.out.println("User "+connectedUser.name+" accepted your friend request");
        }
        else{
            System.out.println("El usuario "+connectedUser.name+" rejected your friend request");
        }
		
	}

}
