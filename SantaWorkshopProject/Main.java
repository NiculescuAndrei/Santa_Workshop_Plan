import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Main 
{
	public static void main(String[] args) throws Exception
	{
		String ip = "localhost";
		int port = 9999;
		Socket s = new Socket(ip,port);
		
		OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
		PrintWriter out = new PrintWriter(os);
		
		Atelier atelier = new Atelier(out);
		atelier.creareFabrici();
		
		// Thread-ul principal asteapta pana cand toti renii isi termina sarcinile, iar apoi afiseaza lista de cadouri pe care fiecare ren o detine
		// si lista de cadouri din fabrici pentru a verifica daca renii au preluat toate cadourile din toate fabricile.
		try {
			for(int i=0;i<atelier.listaReni.length;i++)
			{
				atelier.listaReni[i].join();
			}

			for(int i=0;i<atelier.listaReni.length;i++)
			{
				System.out.println("Lista cadouri reni");
				System.out.println(atelier.listaReni[i].listaCadouriPreluate);
			}
			
			for(int i=0;i<atelier.listaFabrici.length;i++)
			{
				System.out.println("Lista cadouri fabrici");
				System.out.println(atelier.listaFabrici[i].listaCadouri);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		s.close();
		os.close();
		out.close();
			
	}

}
