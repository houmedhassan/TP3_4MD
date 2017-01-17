package test;

import metier.CatalogueMetierImpl;
import metier.ICatalogueMetier;
import metier.Manager;
import metier.beans.Produit;

public class Test {

	public static void main(String[] args) {
		ICatalogueMetier metier = new CatalogueMetierImpl();
		Produit p = new Produit(); 

		// test d'anarque de vente
		p = metier.getProduit("PRO01");
		p = Manager.choix(p);
		System.out.println("des "+p.getDesignation()+" prix "+p.getPrix()+" quant "+p.getQuantite());
		
		p = metier.getProduit("PRO13");
		p = Manager.choix(p);
		System.out.println("des "+p.getDesignation()+" prix "+p.getPrix()+" quant "+p.getQuantite());
		
		p = metier.getProduit("ALA16");
		p = Manager.choix(p);	
		System.out.println("des "+p.getDesignation()+" prix "+p.getPrix()+" quant "+p.getQuantite());
	}

}
