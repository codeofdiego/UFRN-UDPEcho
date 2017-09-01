
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
	public static void main(String[] args) throws Exception {
		int porta = 7776;
		Scanner reader = new Scanner(System.in);
		InetAddress hostDestino = InetAddress.getByName("localhost");
		DatagramSocket socketCliente = new DatagramSocket(porta);
		
		while (true) {
			
			// Lê mensagem
			System.out.print("CLIENTE: ");
			String message = reader.next();
			
			// Finaliza cliente caso mensagem seja -1
			if (message.equals("-1")) {
				System.out.println("(saindo)");
				break;
			}
			
			// Extrai bytes da mensagem
			byte[] dadosEnviados = message.getBytes();

			// Constroi pacote de envio
			DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, hostDestino, 7777);
			
			// Envia mensagem
			socketCliente.send(pacoteEnviado);
			
			// Constroi pacote de retorno e espera pela resposta
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosEnviados, dadosEnviados.length, hostDestino, porta);
			socketCliente.receive(pacoteRecebido);
			
			// Extrai mensagem
			String mensagem = new String(pacoteRecebido.getData());
			
			// Exibe mensagem 
			System.out.println("SERVIDOR: " + mensagem);
			
			
		}
		
		socketCliente.close();
		reader.close();
	}
}