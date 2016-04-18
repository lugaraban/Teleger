package teleger;


/**
* teleger/ClientInterfacePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Monday, April 18, 2016 9:17:58 AM CEST
*/

public abstract class ClientInterfacePOA extends org.omg.PortableServer.Servant
 implements teleger.ClientInterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("notifyConnection", new java.lang.Integer (0));
    _methods.put ("receiveFriendRequest", new java.lang.Integer (1));
    _methods.put ("sendMessage", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // teleger/ClientInterface/notifyConnection
       {
         teleger.SafeUser connectedUser = teleger.SafeUserHelper.read (in);
         this.notifyConnection (connectedUser);
         out = $rh.createReply();
         break;
       }

       case 1:  // teleger/ClientInterface/receiveFriendRequest
       {
         String user = in.read_string ();
         this.receiveFriendRequest (user);
         out = $rh.createReply();
         break;
       }

       case 2:  // teleger/ClientInterface/sendMessage
       {
         String message = in.read_string ();
         String type = in.read_string ();
         boolean $result = false;
         $result = this.sendMessage (message, type);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:teleger/ClientInterface:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ClientInterface _this() 
  {
    return ClientInterfaceHelper.narrow(
    super._this_object());
  }

  public ClientInterface _this(org.omg.CORBA.ORB orb) 
  {
    return ClientInterfaceHelper.narrow(
    super._this_object(orb));
  }


} // class ClientInterfacePOA
