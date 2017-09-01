
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
	public static void main(String[] args) throws Exception {
		int porta = 7777;
		byte[] dadosRecebidos = new byte[512];
		
		DatagramSocket socketServidor = new DatagramSocket(porta);
		System.out.println("Serviço de ECHO UDP rodando na porta 7777... Aguardando requisições...");
		
		DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
		 
		while(true) {
			// Recebe pacote
			socketServidor.receive(pacoteRecebido);
			
			// Extrai informações
			String mensagem = new String(pacoteRecebido.getData());
			InetAddress clientDestino = pacoteRecebido.getAddress();
			int port = pacoteRecebido.getPort();
			
			// Constrói pacote de retorno
			byte[] mensagemRetorno = mensagem.getBytes();
			DatagramPacket pacoteRetorno = new DatagramPacket(mensagemRetorno, mensagemRetorno.length, clientDestino, port);
			
			// Envia dados de volta
			socketServidor.send(pacoteRetorno);	 
		}
	}
}