package teleger;


/**
* teleger/ClientInterfaceHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Friday, April 15, 2016 7:26:50 PM CEST
*/

abstract public class ClientInterfaceHelper
{
  private static String  _id = "IDL:teleger/ClientInterface:1.0";

  public static void insert (org.omg.CORBA.Any a, teleger.ClientInterface that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static teleger.ClientInterface extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (teleger.ClientInterfaceHelper.id (), "ClientInterface");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static teleger.ClientInterface read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ClientInterfaceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, teleger.ClientInterface value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static teleger.ClientInterface narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof teleger.ClientInterface)
      return (teleger.ClientInterface)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      teleger._ClientInterfaceStub stub = new teleger._ClientInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static teleger.ClientInterface unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof teleger.ClientInterface)
      return (teleger.ClientInterface)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      teleger._ClientInterfaceStub stub = new teleger._ClientInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
