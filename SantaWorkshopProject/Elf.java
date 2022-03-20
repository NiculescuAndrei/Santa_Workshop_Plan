import java.util.Random;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;

public class Elf extends Thread
{
	int pozitieLinie;
	int pozitieColoana;
	
	int numarElf;
	
	int numarCadouriGenerate;
	
	Fabrica fabrica;
	CyclicBarrier barieraElfi;
	
	Random rand = new Random();
	
	public Elf(Fabrica fabrica, int numar, CyclicBarrier barieraElfi)
	{
		this.fabrica = fabrica;
		this.numarElf = numar;
		this.numarCadouriGenerate = 0;
		this.barieraElfi = barieraElfi;
	}
	
	public void run()
	{
		while(fabrica.numarCadouri<fabrica.numarCadouriPropuse)
		{
			// Daca elful se afla in zona diagonalei principale, acesta va astepta pana ce toti ceilalti elfi ajung in aceeasi situatie (la bariera)
			if(Math.abs(pozitieLinie - pozitieColoana)<=1)
			{
				System.out.println("Elful " + numarElf + " asteapta la bariera pe linia " + pozitieLinie + " si coloana " + pozitieColoana);
				try {
					barieraElfi.await();
				}finally{}/*catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}*/
			}
			
			fabrica.mutaElf(this);
	
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Elful incearca sa achizitioneze un permis de la semafor pentru a se retrage
			if(Atelier.semaforRetragereElf.tryAcquire())
			{
				fabrica.retragereElf(this);
				barieraElfi.actualizareNumarElemente();
				break;
			}
			// Daca elfii au produs numarul de cadouri propuse de catre fabrica, "resetam" bariera pentru ca elfii care inca asteapta la aceasta, sa se trezeasca
			// si sa isi termine executia, deoarece nu mai trebuie generate cadouri.
			if(fabrica.numarCadouri>=fabrica.numarCadouriPropuse)
			{
				System.out.println("Elful " + numarElf + " a generat " + numarCadouriGenerate + " cadouri");
				barieraElfi.resetare();
			}
		}
	}
	
	public void plaseazaElf()
	{
		boolean ok = false;
		// Genereaza pozitii random pana cand intr-una dintre acestea nu se afla nici un elf.
		while(ok==false)
		{
			pozitieLinie = rand.nextInt(fabrica.dispunere.length);
			pozitieColoana = rand.nextInt(fabrica.dispunere.length);
			if(fabrica.dispunere[pozitieLinie][pozitieColoana]==false)
			{
				ok = true;
				fabrica.dispunere[pozitieLinie][pozitieColoana]=true;
				System.out.println("A fost creat un elf pe linia " + pozitieLinie + " si coloana " + pozitieColoana);
			}
		}
	}

}
