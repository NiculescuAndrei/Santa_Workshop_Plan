import java.io.PrintWriter;
import java.util.ArrayList;

public class Ren extends Thread
{
	int numarRen;
	ArrayList<ArrayList<Integer>> listaCadouriPreluate = new ArrayList<ArrayList<Integer>>();
	PrintWriter printwriter;
	Fabrica listaFabrici[];
	boolean terminat = false;
	
	public Ren(Fabrica listaFabrici[], int numarRen, PrintWriter printwriter)
	{
		this.listaFabrici = listaFabrici;
		this.numarRen = numarRen;
		this.printwriter = printwriter;
	}
	
	public void run()
	{
		while(terminat==false)
		{
			// Parcurge lista de fabrici
			for(int i=0;i<listaFabrici.length;i++)
			{
				// Daca fabrica nu a generat atatea cadouri cate si-a propus, atunci renul incepe sa extraga cate unul din lista de cadouri.
				if(listaFabrici[i].numarCadouri<listaFabrici[i].numarCadouriPropuse)
				{
					ArrayList<Integer> aux = listaFabrici[i].pregatesteCadouPentruRen();
					if(aux!=null)
					{
						System.out.println("Renul " + numarRen + " a primit cadoul de pe linia " + aux.get(0) + " si coloana " + aux.get(1));
						listaCadouriPreluate.add(aux);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// Daca fabrica a generat cadourile propuse, dar in lista de cadouri au mai ramas obiecte care nu au fost preluate de reni
				// atunci acestea vor fi luate.
				if(listaFabrici[i].numarCadouri>=listaFabrici[i].numarCadouriPropuse)
				{
					if(listaFabrici[i].listaCadouri.size()!=0)
					{
						ArrayList<Integer> aux = listaFabrici[i].pregatesteCadouPentruRen();
						if(aux!=null)
						{
							System.out.println("Renul " + numarRen + " a primit cadoul de pe linia " + aux.get(0) + " si coloana " + aux.get(1));
							listaCadouriPreluate.add(aux);
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					int contor=0;
					
					// Parcurge lista de fabrici si verifica pentru fiecare daca lista de cadouri a acesteia este goala
					// si daca a fost generat numarul de cadouri propuse.
					for(int j=0;j<listaFabrici.length;j++)
					{
						if(listaFabrici[j].listaCadouri.size()==0 && listaFabrici[j].numarCadouri>=listaFabrici[j].numarCadouriPropuse)
						{
							contor++;
						}
					}
					// Daca toate fabricile indeplinesc conditiile de mai sus, atunci renul poate sa isi termine executia si sa ii transmita lui MosCraciun cadourile
					if(contor==listaFabrici.length)
					{
						terminat = true;
						for(int j=0;j<listaCadouriPreluate.size();j++)
						{
							String s = "Cadoul de pe linia " + listaCadouriPreluate.get(j).get(0).toString() + " si coloana " + listaCadouriPreluate.get(j).get(1).toString() + 
										" de la renul " + numarRen;
							printwriter.println(s);
							printwriter.flush();
						}
						break;
					}
				}
			}
		
		}
		
	}

}
