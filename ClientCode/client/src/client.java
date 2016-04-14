

import org.omg.CORBA.ORB;
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
import org.omg.CORBA.Object;
public class client {
	static ServerInterface server;
	public static void main(String[] args) {
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
		
		
		CallBackObject callBackClient = new CallBackObject();
		callBackClient.setORB(orb);
		
		Object ref = rootpoa.servant_to_reference(callBackClient);
		ClientInterface client = ClientInterfaceHelper.narrow(ref);
		
		// Utilizar los m�todos que se deseen seg�n lo que se decida en la interfaz
		clientFunctionality functions = new clientFunctionality(server, client);
		functions.logIn();
		
		//Despu�s de esto, lo primero que hay que comprobar es si hay
		//solicitudes de amistad pendientes y avisar al usuario
		
		
		} catch (InvalidName | AdapterInactive | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | ServantNotActive | WrongPolicy e) {
			e.printStackTrace();
		}
		

	}

}
