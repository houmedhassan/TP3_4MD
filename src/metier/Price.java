package metier;

import metier.beans.Produit;

public class Price implements IPrice {
	
	public Produit arnaqueProd5(Produit p){
		p.setPrix(p.getPrix()+p.getPrix()*0.2);
		return p;
	}
	
	public Produit arnaqueProd10(Produit p){
		p.setPrix(p.getPrix()+p.getPrix()*0.2);
		return p;
	}
	
	public Produit arnaqueProdQT(Produit p){
		p.setQuantite(1);
		return p;
	}
}
