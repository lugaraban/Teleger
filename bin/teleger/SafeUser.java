package teleger;


/**
* teleger/SafeUser.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Friday, April 15, 2016 9:47:52 PM CEST
*/

public final class SafeUser implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String name = null;
  public String image = null;
  public teleger.ClientInterface reference = null;

  public SafeUser ()
  {
  } // ctor

  public SafeUser (String _id, String _name, String _image, teleger.ClientInterface _reference)
  {
    id = _id;
    name = _name;
    image = _image;
    reference = _reference;
  } // ctor

} // class SafeUser
