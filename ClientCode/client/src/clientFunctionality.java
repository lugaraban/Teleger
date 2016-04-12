import teleger.ClientInterface;
import teleger.SafeUser;
import teleger.ServerInterface;

public class clientFunctionality {
	private ServerInterface server;
	private ClientInterface client;
	
	public clientFunctionality(ServerInterface server, ClientInterface client){
		this.server=server;
	}
	
	public void logIn(){
		SafeUser[] friends;
		friends=server.logIn("Marcos", "Contrasinal", "ip", client);
		
		int i;
		for(i=0;i<friends.length;i++){
			System.out.println(friends[i].name);
		}
	}
	
	public void register(){
		//server.register(client);
	}
	
	public void logOut(){
		if(server.logOut("id", "Contrasinal")){
			System.out.println("User has sucessfully logged out");
		}
	}
	
	public void searchNewFriends(String name){
		SafeUser[] newFriends;
		newFriends=server.searchNewFriends(name);
		
		int i;
		for(i=0;i<newFriends.length;i++){
			System.out.println(newFriends[i].name);
		}
	}
	
	public void sendRequestForFriend(){
		
	}
	
	public void sendMessage(SafeUser user){
		//Utilizar sockects UDP para esto
		
	}
	
	
}
