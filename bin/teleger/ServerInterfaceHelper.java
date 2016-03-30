package teleger;


/**
* teleger/ServerInterfaceHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* mi�rcoles 30 de marzo de 2016 20H02' CEST
*/

abstract public class ServerInterfaceHelper
{
  private static String  _id = "IDL:teleger/ServerInterface:1.0";

  public static void insert (org.omg.CORBA.Any a, teleger.ServerInterface that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static teleger.ServerInterface extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (teleger.ServerInterfaceHelper.id (), "ServerInterface");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static teleger.ServerInterface read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ServerInterfaceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, teleger.ServerInterface value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static teleger.ServerInterface narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof teleger.ServerInterface)
      return (teleger.ServerInterface)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      teleger._ServerInterfaceStub stub = new teleger._ServerInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static teleger.ServerInterface unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof teleger.ServerInterface)
      return (teleger.ServerInterface)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      teleger._ServerInterfaceStub stub = new teleger._ServerInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
