import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class receive extends Thread{

	public void run(){
		DatagramSocket socket;

        try{
            //Creo el socket, ¿¿PUERTO CORRECTO??
            socket = new DatagramSocket(6000);
            
            byte[] mensaje_bytes = new byte[256];
            String mensaje ="";

            DatagramPacket paquete = new DatagramPacket(mensaje_bytes,256);
        
            while(true){
	            // Recibimos el paquete
	            socket.receive(paquete);
	            // Lo formateamos
	            mensaje = new String(mensaje_bytes).trim();
	            // Lo mostramos por pantalla
	            System.out.println(mensaje);
            }
            
        }catch(Exception e){
            
        }
	}
}
