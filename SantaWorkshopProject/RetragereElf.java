
public class RetragereElf extends Thread
{
	Fabrica fabrica;
	public RetragereElf(Fabrica fabrica)
	{
		this.fabrica =  fabrica;
	}
	public void run()
	{
		// Elibereaza un permis, pe care elfii il pot achizitiona de la semafor pentru a se retrage din fabrica, o data la 1000 milisecunde.
		while(fabrica.numarCadouri<fabrica.numarCadouriPropuse)
		{
			Atelier.semaforRetragereElf.release();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
