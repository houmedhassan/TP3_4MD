package metier;

import java.util.List;

import metier.beans.*;

import java.sql.Date;

public interface ICatalogueMetier {
	
	//PRODUIT
	public void addProduit(Produit p);
	public List<Produit> listProduits();
	public Produit getProduit(String ref);
	public void updateProduit(Produit p);
	
	//EXEMPLAIRE
	public void addExemplaire(Prod_Exemplaire pr);
	public List<Prod_Exemplaire> listsExemplaire(String ref);
	public String delExemplaire(String ref);
	
	//Produits Commander
	public void addProd_Com(Prod_Com pc);
	public List<Prod_Com> listsProd_Com(int ref_com);
	
	//COMMANDE
	public void addCommande(Commande com);
	public List<Commande> listCommande();
	public List<Commande> commandeParDate(Date date);
	
	//USER
	public void addUser(User s);
	public boolean connexion(String login, String passwd);
	public void deconnexion();
}
