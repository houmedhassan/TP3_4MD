package metier.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Commande implements Serializable{
	private int reference;
	private Date date;
	private double prix;
	private int quantite;
	private String login;
	
	public Commande(){
		
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
