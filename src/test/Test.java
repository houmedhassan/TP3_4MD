package test;

import java.util.List;

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
		
		System.out.println("*********************************Lister les produits***********************************");
		//possibiliter de consulter le produit 
		List <Produit> produit = metier.listProduits();
		int i=0;
		for(Produit prod : produit){
			i++;
			System.out.println(i+1 +": designation "+prod.getDesignation()+" prix "+prod.getPrix()+" quantité "+prod.getQuantite());
		}
		i=0;
		
		System.out.println("**********************************Ajout d'un produit****************************************");
		
		//*** Attention le meme produit ne peut pas etre ajouter deux.
//		Produit newProduit = new Produit();
//		newProduit.setReference("PRO20");
//		newProduit.setDesignation("Telephone Portable");
//		newProduit.setPrix(280.00);
//		newProduit.setQuantite(30);
//		
//		metier.addProduit(newProduit);
//		System.out.println("l'insertion du produit a été réalisé avec succes");
		
		System.out.println("****************************Faire une Commande**************************************");
	}
	
	

}
