package teleger;


/**
* teleger/User.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Monday, April 18, 2016 9:17:58 AM CEST
*/

public final class User implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String password = null;
  public String name = null;
  public String image = null;

  public User ()
  {
  } // ctor

  public User (String _id, String _password, String _name, String _image)
  {
    id = _id;
    password = _password;
    name = _name;
    image = _image;
  } // ctor

} // class User
