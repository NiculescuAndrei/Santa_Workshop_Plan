import java.io.PrintWriter;
import java.util.Random;
//import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Atelier 
{
	Ren listaReni[];
	Fabrica listaFabrici[];
	int numarFabrici;
	int numarReni;
	
	static volatile Semaphore semaforRetragereElf = new Semaphore(0);
	
	RetragereElf retragereElf[];
	PrintWriter writer;
	CyclicBarrier bariereElfi[];
	
	public Atelier(PrintWriter writer)
	{
		this.writer=writer;
	}
	
	public void creareFabrici()
	{
		Random rand = new Random();
		numarFabrici = rand.nextInt(4)+2;
		numarReni = rand.nextInt(10)+8;
		
		listaFabrici = new Fabrica[numarFabrici];
		listaReni = new Ren[numarReni];
		bariereElfi = new CyclicBarrier[numarFabrici];
		retragereElf = new RetragereElf[numarFabrici];
			
		System.out.println("Au fost create " + numarFabrici + " fabrici");
		System.out.println("Au fost creati " + numarReni + " reni");
		
		for(int i=0;i<numarFabrici;i++)
		{
			int dimensiuneFabrica = rand.nextInt(401) + 100;
			int numarCadouriPropuse = rand.nextInt(301) + 500;
			listaFabrici[i] = new Fabrica(dimensiuneFabrica, numarCadouriPropuse);
		}
		
		for(int i=0;i<numarReni;i++)
		{
			listaReni[i] = new Ren(listaFabrici,i+1,writer);
			listaReni[i].start();
		}
		
		for(int i=0;i<numarFabrici;i++)
		{
			int numarElfiInFabrica = rand.nextInt((listaFabrici[i].dispunere.length/2)-49) + 50;
			bariereElfi[i] = new CyclicBarrier(numarElfiInFabrica);
			retragereElf[i] = new RetragereElf(listaFabrici[i]);
			
			for(int j=0;j<numarElfiInFabrica;j++)
			{
				Elf auxiliar = new Elf(listaFabrici[i],j+1,bariereElfi[i]);
				listaFabrici[i].listaElfi.add(auxiliar);
				auxiliar.plaseazaElf();
				auxiliar.start();
				if(j==1)
				{
					retragereElf[i].start();
				}
				try {
					Thread.sleep(rand.nextInt(501)+500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			
		
	}
	

}
