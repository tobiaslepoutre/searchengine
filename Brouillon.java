package components;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.File;


public class Brouillon extends Noeud {
	
	
	rechercheInfo(String m, int v, Noeud p) {
		super(m, v, p);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main (String[] args) {
		
		
		LinkedList<Noeud> myList1 = new LinkedList<Noeud>();
		
		
		
		try {
			
			
			File dir = new File("src/components/textes");
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				int i=1;
				for (File child : directoryListing) {

					Noeud premier = null;
			        String txt = Files.readString(Paths.get(child.toString()));
			        ArrayList<String> tab = new ArrayList<String>(Arrays.asList(txt.split("\\W+")));
					LinkedList<String> dejaVu = new LinkedList<String>();
			        
			        //System.out.println(tab);
			        
					for (String mot: tab) {
						int nbMot=1;
						if (dejaVu.contains(mot)==false) {
							for(String elem: tab) {
								if (elem.equals(mot)) {
								nbMot+=1;
								}
							}
							dejaVu.add(mot);
							//String res=mot+":"+String.valueOf(nbMot);
							//String d = "d"+String.valueOf(i);
							premier=ajoutDebut(mot,nbMot,premier);

							//System.out.println("mot:"+mot+"-nbMot:"+nbMot+"-res:"+res);
							//myList.add(mot+":"+String.valueOf(nbMot));
						}
					}

					premier=ajoutDebut("d"+String.valueOf(i),-1,premier);
					myList1.add(premier);
					i++;
					
					
			    }
			  } else {
				  System.out.println("LE FICHIER EST VIDE, IL N'Y A DONC AUCUN TEXTE A TRAITER");
			  }			
			
		
		} catch (IOException e) {
	        e.printStackTrace();
		}


		//print(premier);
		
		for(Noeud n: myList1) {
			print(n);
			System.out.println("\n");
			
		}

		//System.out.println("MA TCHOIIIINNN:"+myList);

		
	}

	// ajoute un nouvel element au noeud "premier"
	public static Noeud ajoutDebut(String s, int v, Noeud premier) {
	premier = new Noeud(s, v, premier);
	return premier;
	}

	
    // affiche le contenu de la liste
	public static void print(Noeud premier) {
	Noeud n = premier;
    System.out.println("**************Contenu de la liste chainee :"+premier.valeur);
	while (n!=null) {
		System.out.print(n.mot+":"+String.valueOf(n.valeur)+"->");   // traitement
		n = n.prochain;
		}
	System.out.println(n);
	}
	
	public static void inverser(LinkedList<Noeud> list) {
		LinkedList<String> dejaVu = new LinkedList<String>();
		for(Noeud premier: list) {
			Noeud n = premier;
			while (n!=null) {
				if (dejaVu.contains(n.mot)==false) {
					//ajoutDebut(mot, -1, premier)
					
				}
				else {
					
					
				}
				
				
			}
			
			
			
		}
		
		
		
	}
	
	public static void trierMots(LinkedList<Noeud> list) {
		for(Noeud premier: list) {
			Noeud n = premier;
			
			
			
		}
		
		
	}
	
	
	
}




public Noeud enlever(int v, Noeud premier) {
	Noeud n = premier;
	// cas 1: Si le premier est null
	if (premier == null) return premier;
	// cas 2: Si le premier est à enlever
	if (premier.valeur == v)
	{
	premier = premier.prochain;
	return premier;
	}
	// cas 3: sinon, tester sur les autres éléments
	while (n.prochain != null && n.prochain.valeur != v) n = n.prochain;
	if (n.prochain != null) n.prochain = n.prochain.prochain;
	return n;
	}


public static Noeud ajoutFin(String s, int v, Noeud premier) {
	Noeud n=premier;
// cas 1: aucun autre élément ajouté
	if (premier == null) premier = new Noeud(s,v,null);
// cas 2: il y a déjà des éléments
	else {
		while (n.prochain != null) n = n.prochain;
		n.prochain = new Noeud(s,v,null);
	}
	return premier;
}
