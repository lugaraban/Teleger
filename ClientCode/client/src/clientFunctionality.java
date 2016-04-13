import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import teleger.ClientInterface;
import teleger.SafeUser;
import teleger.ServerInterface;
import teleger.User;

public class clientFunctionality {
	private ServerInterface server;
	private ClientInterface client;
	
	public clientFunctionality(ServerInterface server, ClientInterface client){
		this.server=server;
	}
	
	//En todas las funciones en las que hay llamadas al servidor queda hacer
	//la l�gica de presentaci�n de la interfaz, pues una vez que te loggeas
	//pasas a la interfaz principal, etc.
	
	public void logIn(){
		SafeUser[] friends;
		friends=server.logIn("Marcos", "Contrasinal", "ip", client);
		
		//Una vez que inicia sesi�n se pone a escuchar
		receiveMessage();
		
		//Se iniciar�a la interfaz con todos sus amigos reflejados
		int i;
		for(i=0;i<friends.length;i++){
			System.out.println(friends[i].name);
		}
	}
	
	public void register(String id, String password, String name, String image){
		User user = new User(id, password, name, image);
		server.register(user);
		
		//Seguir en la misma pantalla, el usuario tiene que iniciar sesi�n
		//para entrar a la aplicaci�n
	}
	
	public void logOut(){
		if(server.logOut("id", "Contrasinal")){
			System.out.println("User has sucessfully logged out");
		}
		
		//Ir a la pantalla de inicio sesi�n/registro
		//Parar el hilo de recibimiento de mensajes y cerrar el socket
	}
	
	public void searchNewFriends(String name){
		SafeUser[] newFriends;
		newFriends=server.searchNewFriends(name);
		
		//Se ofrecen los resultados coincidentes
		int i;
		for(i=0;i<newFriends.length;i++){
			System.out.println(newFriends[i].name);
		}
	}
	
	public void sendRequestForFriend(SafeUser friend){
		//server.sendRequestForFriend(client, friend);
		
	}
	
	public void receiveMessage(){
		//Esta funci�n se tiene que ejecutar desde el momento
		//en el que se conecta el usuario y seguir as� hasta
		//que se desconecte
		receive r = new receive();
		r.start();
	}
	
	public void sendMessage(SafeUser user){
		//Se ejecuta cada vez que un usuario manda un mensaje
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 
        //Definimos el socket, n�mero de bytes del buffer, y mensaje.
        DatagramSocket socket;
        InetAddress address;
        byte[] mensaje_bytes = new byte[256];
        String mensaje="";

        //Paquete
        DatagramPacket paquete;
        
        try{
            
            socket = new DatagramSocket();
            
            //VER SI FUNCIONA BIEN ESTE M�TODO AL INTRODUCIRLE LA IP
            address = InetAddress.getByName(user.ip);
            
            System.out.println("Introduce el mensaje: ");
            mensaje = in.readLine();
            
            mensaje_bytes = mensaje.getBytes();
            
            //VER SI HAY QUE CAMBIAR EL PUERTO DE ENV�O
            paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
            socket.send(paquete);
            
        }catch(Exception e){
            
        }
		
	}
	
	
}
