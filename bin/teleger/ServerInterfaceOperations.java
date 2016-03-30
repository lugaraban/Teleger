package teleger;


/**
* teleger/ServerInterfaceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* mi�rcoles 30 de marzo de 2016 20H02' CEST
*/

public interface ServerInterfaceOperations 
{
  boolean register (teleger.User userData);
  teleger.SafeUser[] logIn (String userId, String userPassword);
  boolean logOut (String userId, String userPassword);
  teleger.SafeUser[] searchNewFriends (String name);
  void sendRequestForFriend (teleger.SafeUser user, teleger.SafeUser friend);
} // interface ServerInterfaceOperations