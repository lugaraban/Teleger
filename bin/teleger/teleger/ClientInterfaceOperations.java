package teleger;


/**
* teleger/ClientInterfaceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* Saturday, April 2, 2016 6:44:53 PM CEST
*/

public interface ClientInterfaceOperations 
{
  void notifyConnection (teleger.SafeUser connectedUser);
  boolean receiveFriendRequest (teleger.SafeUser user);
  void notifyAnswerRequest (teleger.SafeUser connectedUser, boolean acceptance);
} // interface ClientInterfaceOperations