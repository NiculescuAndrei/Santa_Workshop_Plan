import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrier 
{
	ReentrantLock zavorBariera = new ReentrantLock();
	int contor = 0;
	volatile int numarElemente; // numarul de elfi din fabrica
	
	public CyclicBarrier(int numarElemente)
	{
		this.numarElemente = numarElemente;
	}
	
	// Accesul la bariera este securizat de catre un zavor, astfel incat mai multi elfi sa nu poata modifica contorul barierei in acelasi timp
	public void await()
	{
		try 
		{
			zavorBariera.lock();
			contor++;
		}finally {
			zavorBariera.unlock();
		}
		
		// Atata timp cat nu au ajuns toti elfii la bariera, acestia asteapta
		while(contor%numarElemente!=0)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Dupa ce toti elfii au ajuns la bariera resetam contorul
		if(contor!=0)
		{
			contor=0;
		}
		
	}
	
	public void resetare()
	{
		contor = numarElemente;
	}
	
	// In cazul in care un elf se retrage din fabrica in urma achizitionarii unui permis de la semafor, trebuie sa actualizam numarul de elfi ramasi in fabrica.
	public void actualizareNumarElemente()
	{
		numarElemente--;
	}
}
