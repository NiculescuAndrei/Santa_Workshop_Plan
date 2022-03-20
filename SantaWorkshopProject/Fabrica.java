import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Fabrica 
{
	boolean dispunere[][];
	ArrayList <Elf> listaElfi = new ArrayList<Elf>();
	HashSet<ArrayList<Integer>> listaCadouri = new HashSet<ArrayList<Integer>>();
	Random rand = new Random();
	volatile int numarCadouri=0;
	int numarCadouriPropuse;
	
	public Fabrica(int dimensiune,int numarCadouriPropuse)
	{
		dispunere = new boolean[dimensiune][dimensiune];
		this.numarCadouriPropuse = numarCadouriPropuse;
	}
	
	
	synchronized void mutaElf(Elf elf)
	{
		Elf aux = null;
		for(int i=0;i<listaElfi.size();i++)
		{
			if(listaElfi.get(i)==elf)
			{
				aux = listaElfi.get(i);
			}
		}
		
		// Coltul din stanga sus
		if(aux.pozitieLinie == 0 && aux.pozitieColoana == 0 )
		{
			// Daca locatiile din est si sud sunt ocupate, elful trebuie sa astepte
			while(dispunere[aux.pozitieLinie][aux.pozitieColoana+1] == true &&
			   dispunere[aux.pozitieLinie+1][aux.pozitieColoana] == true)
			{
				try {
					System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
										aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		// Coltul din dreapta sus
		else if(aux.pozitieLinie == 0 && aux.pozitieColoana == (dispunere.length-1))
		{
			// Daca locatiile din vest si sud sunt ocupate, elful trebuie sa astepte
			while(dispunere[aux.pozitieLinie][aux.pozitieColoana-1] == true &&
				   dispunere[aux.pozitieLinie+1][aux.pozitieColoana] == true)
			{
				try {
					System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
							aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
		// Coltul din stanga jos
		else if(aux.pozitieLinie == (dispunere.length-1) && aux.pozitieColoana == 0)
		{
			// Daca locatiile din nord si est sunt ocupate, elful trebuie sa astepte
			while(dispunere[aux.pozitieLinie-1][aux.pozitieColoana] == true &&
				   dispunere[aux.pozitieLinie][aux.pozitieColoana+1] == true)
			{
				try {
					System.out.println("Elful " + elf + " de pe linia " + aux.pozitieLinie + " si coloana " +
							aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
		// Coltul din dreapta jos
		else if(aux.pozitieLinie == (dispunere.length-1) && aux.pozitieColoana == (dispunere.length-1))
		{
			// Daca locatiile din nord si vest sunt ocupate, elful trebuie sa astepte
			while(dispunere[aux.pozitieLinie-1][aux.pozitieColoana] == true &&
						   dispunere[aux.pozitieLinie][aux.pozitieColoana-1] == true)
			{
				try {
					System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
							aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
		// Zidul din nord
	    else if(aux.pozitieLinie == 0)
		{
	    	// Daca elful se afla la peretele din nord al fabricii si nu are unde sa se deplaseze, acesta asteapta
	    	while(dispunere[aux.pozitieLinie][aux.pozitieColoana-1] == true &&
		   dispunere[aux.pozitieLinie][aux.pozitieColoana+1] == true &&
		   dispunere[aux.pozitieLinie+1][aux.pozitieColoana] == true)
	    	{
	    		try {
		    		System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
		    				aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
		}
		// Zidul din sud
	    else if(aux.pozitieLinie == (dispunere.length-1))
	 	{
	    	// Daca elful se afla la peretele din sud al fabricii si nu are unde sa se deplaseze, acesta asteapta
	    	while(dispunere[aux.pozitieLinie][aux.pozitieColoana-1] == true &&
	 		   dispunere[aux.pozitieLinie][aux.pozitieColoana+1] == true &&
	 		   dispunere[aux.pozitieLinie-1][aux.pozitieColoana] == true)
	    	{
	    		try {
		    		System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
		    				aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	 	}
		// Zidul din vest
	    else if(aux.pozitieColoana == 0)
	 	{
	    	// Daca elful se afla la peretele din vest al fabricii si nu are unde sa se deplaseze, acesta asteapta
	    	while(dispunere[aux.pozitieLinie][aux.pozitieColoana+1] == true &&
	 	 		   dispunere[aux.pozitieLinie-1][aux.pozitieColoana] == true &&
		 		   dispunere[aux.pozitieLinie+1][aux.pozitieColoana] == true)
	    	{
	    		try {
		    		System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
		    				aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	 	}
		// Zidul din est
	    else if(aux.pozitieColoana == (dispunere.length-1))
	 	{
	    	// Daca elful se afla la peretele din est al fabricii si nu are unde sa se deplaseze, acesta asteapta
	    	while(dispunere[aux.pozitieLinie][aux.pozitieColoana-1] == true &&
	 	 		   dispunere[aux.pozitieLinie-1][aux.pozitieColoana] == true &&
		 		   dispunere[aux.pozitieLinie+1][aux.pozitieColoana] == true)
	    	{
	    		try {
		    		System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
		    				aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
	    	}
	 	}
		// Oricare pozitie din interiorul fabricii
	    else
	    {
	    	// Daca elful nu se afla in zona peretilor fabricii si nu are unde sa se deplaseze, acesta asteapta
	    	while(dispunere[aux.pozitieLinie][aux.pozitieColoana+1] == true && 
	    	    dispunere[aux.pozitieLinie][aux.pozitieColoana-1] == true &&
	 		    dispunere[aux.pozitieLinie-1][aux.pozitieColoana] == true &&
	 		    dispunere[aux.pozitieLinie+1][aux.pozitieColoana] == true)
	    	{
	    		try {
		    		System.out.println("Elful " + elf.numarElf + " de pe linia " + aux.pozitieLinie + " si coloana " +
		    				aux.pozitieColoana + " trebuie sa astepte pana sa faca o miscare");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
	    	}
	    }
		
		// Variabila pentru a decide directia in care incearca sa se mute elful
		// 1 = nord, 2 = est, 3 = sud, 4 = vest
		int directieMutare;
		boolean ok=false;

		while(ok == false)
		{
			if(numarCadouri>=numarCadouriPropuse)
			{
				break;
			}

			directieMutare = rand.nextInt(4) + 1;
			
			// Directie nord
			if(directieMutare==1 && aux.pozitieLinie>0 && dispunere[aux.pozitieLinie-1][aux.pozitieColoana]==false)
			{
				// Marcheaza faptul ca vechea pozitie este libera
				dispunere[aux.pozitieLinie][aux.pozitieColoana] = false;
				aux.pozitieLinie -= 1; // actualizeaza pozitia
				ok = true;
				
				ArrayList<Integer> cautareCadou = new ArrayList<Integer>();
				cautareCadou.add(aux.pozitieLinie);
				cautareCadou.add(aux.pozitieColoana);
				
				// Verifica daca exista un cadou in lista la noua pozitie a elfului
				if(listaCadouri.contains(cautareCadou)!=true)
				{
					listaCadouri.add(cautareCadou);
					numarCadouri++;
					aux.numarCadouriGenerate++;
				}
				
				System.out.println("Elful " + aux.numarElf + " s-a mutat de pe linia " + (aux.pozitieLinie+1) + " pe linia " 
									+ (aux.pozitieLinie) + " si coloana " + aux.pozitieColoana);

				raportareElf(elf);
			}
			// Directie est
			else if(directieMutare==2 && aux.pozitieColoana<dispunere.length-1 && dispunere[aux.pozitieLinie][aux.pozitieColoana+1]==false)
			{
				// Marcheaza faptul ca vechea pozitie este libera
				dispunere[aux.pozitieLinie][aux.pozitieColoana] = false;
				aux.pozitieColoana += 1;
				ok = true;
				
				ArrayList<Integer> cautareCadou = new ArrayList<Integer>();
				cautareCadou.add(aux.pozitieLinie);
				cautareCadou.add(aux.pozitieColoana);
				
				// Verifica daca exista un cadou in lista la noua pozitie a elfului
				if(listaCadouri.contains(cautareCadou)!=true)
				{
					listaCadouri.add(cautareCadou);
					numarCadouri++;
					aux.numarCadouriGenerate++;
				}
				
				System.out.println("Elful " + aux.numarElf + " s-a mutat de pe coloana " + (aux.pozitieColoana-1) + " pe coloana "
					                   + (aux.pozitieColoana) + " si linia " + aux.pozitieLinie);

				raportareElf(elf);
			}
			// Directie sud
			else if(directieMutare==3 && aux.pozitieLinie<dispunere.length-1 && dispunere[aux.pozitieLinie+1][aux.pozitieColoana]==false)
			{
				// Marcheaza faptul ca vechea pozitie este libera
				dispunere[aux.pozitieLinie][aux.pozitieColoana] = false;
				aux.pozitieLinie += 1;
				ok = true;
				
				ArrayList<Integer> cautareCadou = new ArrayList<Integer>();
				cautareCadou.add(aux.pozitieLinie);
				cautareCadou.add(aux.pozitieColoana);
				
				// Verifica daca exista un cadou in lista la noua pozitie a elfului
				if(listaCadouri.contains(cautareCadou)!=true)
				{
					listaCadouri.add(cautareCadou);
					numarCadouri++;
					aux.numarCadouriGenerate++;
				}
				
				System.out.println("Elful " + aux.numarElf + " s-a mutat de pe linia " + (aux.pozitieLinie-1) + " pe linia " 
									 + (aux.pozitieLinie) + " si coloana " + aux.pozitieColoana);

				raportareElf(elf);
			}
			// Directie vest
			else if(directieMutare==4 && aux.pozitieColoana>0 && dispunere[aux.pozitieLinie][aux.pozitieColoana-1]==false)
			{
				// Marcheaza faptul ca vechea pozitie este libera
				dispunere[aux.pozitieLinie][aux.pozitieColoana] = false;
				aux.pozitieColoana -= 1;
				ok = true;
				
				ArrayList<Integer> cautareCadou = new ArrayList<Integer>();
				cautareCadou.add(aux.pozitieLinie);
				cautareCadou.add(aux.pozitieColoana);
				
				// Verifica daca exista un cadou in lista la noua pozitie a elfului
				if(listaCadouri.contains(cautareCadou)!=true)
				{
					listaCadouri.add(cautareCadou);
					numarCadouri++;
					aux.numarCadouriGenerate++;
				}
				
				System.out.println("Elful " + aux.numarElf + " s-a mutat de pe coloana " + (aux.pozitieColoana+1) + " pe coloana " 
									+ (aux.pozitieColoana) + " si linia " + aux.pozitieLinie);

				raportareElf(elf);
			}
				
		}
		
	}
	
	// Functie care marcheaza noua locatie a elfului in fabrica dupa ce acesta face o miscare
	void raportareElf(Elf elf)
	{
		for(int i=0;i<listaElfi.size();i++)
		{
			if(listaElfi.get(i)==elf)
			{
				dispunere[listaElfi.get(i).pozitieLinie][listaElfi.get(i).pozitieColoana]=true;
				break;
			}
		}

		// Notifica toti elfii care asteapta, deoarece sunt inconjurati de altii si nu au unde sa se mute.
		notifyAll();
		
	}
	
	// Functie care extrage primul cadou din lista si il returneaza renului
	synchronized ArrayList<Integer> pregatesteCadouPentruRen()
	{
		Iterator<ArrayList<Integer>> itr = listaCadouri.iterator();
		if(listaCadouri.size()!=0)
		{
			ArrayList<Integer> myAuxArrayList = itr.next();
			itr.remove();
			return myAuxArrayList;
		}
		return null;
	}
	
	// Atunci cand un elf reuseste sa ia un permis de la semafor, acesta se retrage, iar aceasta functie il elimina din lista fabricii.
	synchronized void retragereElf(Elf elf)
	{
		for(int i=0;i<listaElfi.size();i++)
		{
			if(listaElfi.get(i) == elf)
			{
				dispunere[listaElfi.get(i).pozitieLinie][listaElfi.get(i).pozitieColoana]=false;
				System.out.println("Elful " + elf.numarElf + " s-a retras din fabrica, generand " + elf.numarCadouriGenerate + " cadouri");
				listaElfi.remove(i);
				break;
			}
		}
	}
	
	

}
