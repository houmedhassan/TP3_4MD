package metier;

import metier.beans.Produit;

public interface IPrice {
	
	public Produit arnaqueProd10(Produit p);
	public Produit arnaqueProd5(Produit p);
	public Produit arnaqueProdQT(Produit p);
}
