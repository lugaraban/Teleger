package teleger;


/**
* teleger/ClientInterfaceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from teleger.idl
* mi�rcoles 30 de marzo de 2016 20H02' CEST
*/

public interface ClientInterfaceOperations 
{
  void notifyConnection (teleger.SafeUser connectedUser);
  boolean receiveFriendRequest (teleger.SafeUser user);
  void notifyAnswerRequest (teleger.SafeUser connectedUser, boolean acceptance);
} // interface ClientInterfaceOperations
