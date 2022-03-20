import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MosCraciun {

	public static void main(String[] args) throws Exception
	{
		System.out.println("Mos Craciun este gata sa primeasca cadourile");
		ServerSocket ss = new ServerSocket(9999);
		
		System.out.println("Mos Craciun asteapta ca renii sa se conecteze la conducta de comunicare");
		Socket s = ss.accept();
		
		System.out.println("Renii s-au conectat");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String line = "";
		
		while(!line.equals(null))
		{
			line = br.readLine();
			if(line==null)
			{
				break;
			}
			System.out.println(line);
		}
		
		s.close();
		ss.close();
		br.close();
	}
}
