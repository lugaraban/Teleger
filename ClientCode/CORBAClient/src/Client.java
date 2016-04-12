
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rapnika
 */
public class Client {
    static ServerInterface server;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Properties props = System.getProperties();
	    props.put("org.omg.CORBA.ORBInitialPort", "6666");
	    //Replace MyHost with the name of the host on which you are running the server
	    props.put("org.omg.CORBA.ORBInitialHost", "192.168.0.157");
            
            //Iniciar el ORB
            ORB orb = ORB.init(args,props);
            
            //Conseguir la referencia al POA(PORTABLE OBJECT ADAPTER)
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            
            //Iniciar el POA manager
            rootpoa.the_POAManager().activate();
            
            //Obtener la referencia al servidor
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            //Obtener la referencia del objeto servidor
            server = ServerInterfaceHelper.narrow(ncRef.resolve_str("TestServer"));
            
            CallBackObject callBackClient = new CallBackObject();
            callBackClient.setORB(orb);
            
            Object ref = rootpoa.servant_to_reference(callBackClient);
            ClientInterface client = ClientInterfaceHelper.narrow(ref);
            
            //Aquí se dará el menú de las opciones que se podrán realizar
            //Introducir una función que cree el socket udp y que se ponga a escuchar
            
            //Iniciar la interfaz y hacer logIn/Registro
            
            server.logIn("Marcos", "Contrasinal", "ip", client);
            
            //Después crear la pantalla con la lista de amigos (toda esta lógica iría en la interfaz ya)
            
        } catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | ServantNotActive | WrongPolicy | AdapterInactive ex) {
            ex.printStackTrace();
        }
        
    }
    
}
