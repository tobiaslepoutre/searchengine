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
import java.util.Collections;

public class rechercheInfo extends Noeud {
	
	
	rechercheInfo(String m, int v, Noeud p) {
		super(m, v, p);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main (String[] args) {
		
		
		LinkedList<Noeud> myList1 = new LinkedList<Noeud>();
		LinkedList<Noeud> myList2 = new LinkedList<Noeud>();
		

		
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
			        // mettre la liste des mots dans le sens décroissant alphabétiquement 
			        Collections.sort(tab, Collections.reverseOrder());
			        
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

				
				ArrayList<String> tabRef = new ArrayList<String>();
				
				for(Noeud premier: myList1) {
					Noeud n = premier;
					while (n!=null) {
						if (tabRef.contains(n.mot)==false) {
							tabRef.add(n.mot);
						}
						n=n.prochain;
					}
				}
				Collections.sort(tabRef, Collections.reverseOrder());
				

				
				
				
				
				for (String ref: tabRef) {
					Noeud premier = null;
					int iDoc=1;
					for(Noeud n: myList1) {
						while (n!=null) {
							if (n.mot.equals(ref)) {
								premier=ajoutDebut("d"+String.valueOf(iDoc), n.valeur, premier);
								break;
							}
							else {
							n=n.prochain;
							}
							
						}
						iDoc+=1;			
					}
					premier=ajoutDebut(ref,-1,premier);
					myList2.add(premier);
				}
				
			} 
			else {
				  System.out.println("LE FICHIER EST VIDE, IL N'Y A DONC AUCUN TEXTE A TRAITER");
			}			
			
			
			
			
			
			//for(Noeud premier: myList1) {
				//Noeud n = premier;
				//while (n!=null) {
					//if (dejaVu2.contains(n.mot)==false) {
						//ajoutDebut(n.mot, 1, premier);
						
					//}
					//else {
						//n.valeur+=1;
						
						
					//}
					
					
				//}
				
				
				
			//}
			
		
		
		} catch (IOException e) {
	        e.printStackTrace();
		}

		//print(premier);
		
		//for(Noeud n: myList1) {
			//print(n);
			//System.out.println("\n");	
		//}

		
		
		
		
		
		
		for(Noeud n: myList2) {
			print(n);
			System.out.println("\n");
			
		}
		
		


		
		String requete = "of the";
		ArrayList<String> tabTxt = new ArrayList<String>(Arrays.asList(requete.split("\\W+")));
		ArrayList<String> search=recherche(myList2, tabTxt);
		

		Noeud ListReponse=frequency(myList1,search,tabTxt);	
		ListReponse=trier(ListReponse);
		

		print(ListReponse);

	}
	
	
	
	public static Noeud trier(Noeud node){
		Noeud n=node;
		int max=0;
		while (n!=null) {
			if(n.valeur>max) {
				max=n.valeur;
			}
			n = n.prochain;
		}
		System.out.println("max:"+max);
		Noeud premier = null;
		int i=1;
		while (i<=max) {
			n=node;
			while (n!=null) {
				if (n.valeur==i) {
					premier=ajoutDebut(n.mot,n.valeur,premier);
				}
				n = n.prochain;
			}
			i++;
		}
		return premier;
	}
	
	public static Noeud frequency(LinkedList<Noeud> myList, ArrayList<String> tab, ArrayList<String> ref){
		Noeud premier = null;
		for (String doc: tab) {
			int freq=0;
			for(Noeud n: myList) {
				if (doc.equals(n.mot)) {
					n = n.prochain;
					while (n!=null) {
						if (ref.contains(n.mot)) {
							freq+=n.valeur;
						}
						n = n.prochain;
					}
				}
			}
			premier=ajoutDebut(doc, freq, premier);
			Noeud n=premier;
			while (n!=null) {
				if(freq<n.valeur) {
					
				}
				n = n.prochain;
			}
		}
		return premier;
	}
	
	
	public static ArrayList<String> recherche(LinkedList<Noeud> myList, ArrayList<String> tabTxt){
		ArrayList<String> tabMemoir = new ArrayList<String>();
		ArrayList<String> ListReponse = new ArrayList<String>();
		//TEST######
		LinkedList<Noeud> myListTest = new LinkedList<Noeud>();
		//######
		System.out.println(tabTxt);
		for (String word: tabTxt) {
			for(Noeud n: myList) {
				if (n.mot.equals(word)) {
					myListTest.add(n);
					n = n.prochain;
					while (tabMemoir.size()==0) {
						while (n!=null) {
							tabMemoir.add(n.mot);
							n = n.prochain;
						}
						if (tabMemoir.size()==0) {
							return tabMemoir;
						}
						continue;
					}
					while (n!=null) {
						if (tabMemoir.contains(n.mot)) {
							tabMemoir.add(n.mot);
						}
						n = n.prochain;
					}
				}	
			}
		}		
		
		//TEST########
		for(Noeud n: myListTest) {
			System.out.println("TEST######################");
			print(n);
			System.out.println("\n");
		}
		//##############
	
		for(String m: tabMemoir) {
			int freq = Collections.frequency(tabMemoir, m);
			if (freq==tabTxt.size() && ListReponse.contains(m)==false) {
				ListReponse.add(m);
			}	
		}
		return ListReponse;
	}
	

	// ajoute un nouvel element au noeud "premier"
	public static Noeud ajoutDebut(String s, int v, Noeud premier) {
	premier = new Noeud(s, v, premier);
	return premier;
	}

	
	
    // affiche le contenu de la liste
	public static void print(Noeud premier) {
	Noeud n = premier;
    System.out.println("**************Contenu de la liste chainee :");
	while (n!=null) {
		System.out.print(n.mot+":"+String.valueOf(n.valeur)+"->");   // traitement
		n = n.prochain;
		}
	System.out.println(n);
	}
	
	
}
