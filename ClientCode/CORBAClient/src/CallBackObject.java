
import org.omg.CORBA.ORB;
import teleger.ClientInterfacePOA;
import teleger.SafeUser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rapnika
 */
public class CallBackObject extends  ClientInterfacePOA{
	public CallBackObject(){};
	private ORB orb;
	public void setORB(ORB orb_val) {
        orb = orb_val;
    }
	@Override
	public void notifyConnection(SafeUser connectedUser) {
		// TODO Auto-generated method stub
		System.out.println("Call back bitch!");
	}

	@Override
	public boolean receiveFriendRequest(SafeUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifyAnswerRequest(SafeUser connectedUser, boolean acceptance) {
		// TODO Auto-generated method stub
		
	}

}
