package metier.beans;

public class Prod_Com {
	private int ref_commande;
	private String ref_produit;
	private String numero;
	private double prix;
	
	public Prod_Com(){
		
	}

	public int getRef_commande() {
		return ref_commande;
	}

	public void setRef_commande(int ref_commande) {
		this.ref_commande = ref_commande;
	}

	public String getRef_produit() {
		return ref_produit;
	}

	public void setRef_produit(String ref_produit) {
		this.ref_produit = ref_produit;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
}
