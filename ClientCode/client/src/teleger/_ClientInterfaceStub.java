package teleger;


/**
* teleger/_ClientInterfaceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Friday, April 15, 2016 7:26:50 PM CEST
*/

public class _ClientInterfaceStub extends org.omg.CORBA.portable.ObjectImpl implements teleger.ClientInterface
{

  public void notifyConnection (teleger.SafeUser connectedUser)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("notifyConnection", true);
                teleger.SafeUserHelper.write ($out, connectedUser);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                notifyConnection (connectedUser        );
            } finally {
                _releaseReply ($in);
            }
  } // notifyConnection

  public boolean receiveFriendRequest (teleger.SafeUser user)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("receiveFriendRequest", true);
                teleger.SafeUserHelper.write ($out, user);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return receiveFriendRequest (user        );
            } finally {
                _releaseReply ($in);
            }
  } // receiveFriendRequest

  public boolean sendMessage (String message, String type)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendMessage", true);
                $out.write_string (message);
                $out.write_string (type);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return sendMessage (message, type        );
            } finally {
                _releaseReply ($in);
            }
  } // sendMessage

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:teleger/ClientInterface:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ClientInterfaceStub
