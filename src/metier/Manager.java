package metier;

import metier.beans.Produit;

public class Manager {
	
	public static Produit choix(Produit p){
		IPrice pri = new Price();

		if(p.getQuantite()<=5){
			p = pri.arnaqueProd5(p);
		} else if(5<p.getQuantite()&&p.getQuantite()<=10){
			p = pri.arnaqueProd10(p);
		} else if(5>p.getQuantite()){
			p = pri.arnaqueProdQT(p);
		}
		return p;
	}
}
