package teleger;

/**
* teleger/ServerInterfaceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Friday, April 15, 2016 9:47:52 PM CEST
*/

public final class ServerInterfaceHolder implements org.omg.CORBA.portable.Streamable
{
  public teleger.ServerInterface value = null;

  public ServerInterfaceHolder ()
  {
  }

  public ServerInterfaceHolder (teleger.ServerInterface initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = teleger.ServerInterfaceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    teleger.ServerInterfaceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return teleger.ServerInterfaceHelper.type ();
  }

}
