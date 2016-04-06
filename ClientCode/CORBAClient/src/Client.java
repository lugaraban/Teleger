
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            //Iniciar el ORB
            ORB orb = ORB.init(args,null);
            
            //Conseguir la referencia al POA(PORTABLE OBJECT ADAPTER)
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            
            //Iniciar el POA manager
            rootpoa.the_POAManager().activate();
            
            //Obtener la referencia al servidor
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow((org.omg.CORBA.Object) objRef);
            
            //Obtener la referencia del objeto servidor
            ServerInterface server = ServerInterfaceHelper.narrow(ncRef.resolve_str("TestServer"));
            
            CallBackObject callBackClient = new CallBackObject();
            callBackClient.setORB(orb);
            
            Object ref = rootpoa.servant_to_reference(callBackClient);
            ClientInterface client = ClientInterfaceHelper.narrow((org.omg.CORBA.Object) ref);
            
            server.logIn("Marcos", "Contrasinal", "ip", client);
            
        } catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | ServantNotActive | WrongPolicy | AdapterInactive ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
