package metier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.beans.Commande;
import metier.beans.Prod_Com;
import metier.beans.Prod_Exemplaire;
import metier.beans.Produit;
import metier.beans.User;

public class CatalogueMetierImpl implements ICatalogueMetier{
	
	@Override
	public void addProduit(Produit p) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("insert into PRODUITS values(?,?,?,?)");
			ps.setString(1, p.getReference());
			ps.setString(2, p.getDesignation());
			ps.setDouble(3, p.getPrix());
			ps.setInt(4, p.getQuantite());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Produit> listProduits() {
		List<Produit> prods=new ArrayList<Produit>();
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("select * from PRODUITS");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Produit p=new Produit();
				p.setReference(rs.getString("REF_PROD"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				prods.add(p);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public Produit getProduit(String ref) {
		Produit p=null;
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("select * from PRODUITS where REF_PROD=?");
			ps.setString(1, ref);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				p=new Produit();
				p.setReference(rs.getString("REF_PROD"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(p==null) throw new RuntimeException ("Produit introuvable");
		return p;
	}

	@Override
	public void updateProduit(Produit p) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("update from PRODUITS set DESIGNATION=?, PRIX=?, QUANTITE=? where REF_PROD=?");
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setString(4, p.getReference());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addExemplaire(Prod_Exemplaire pr) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("insert into SERIES values(?,?)");
			ps.setString(1, pr.getNumero_serie());
			ps.setString(2, pr.getRef_prod());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Prod_Exemplaire> listsExemplaire(String ref){
		List<Prod_Exemplaire> exempl=new ArrayList<Prod_Exemplaire>();
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("select * from SERIES where ref_prod=?");
			ps.setString(1, ref);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Prod_Exemplaire e= new Prod_Exemplaire();
				e.setNumero_serie(rs.getString("numero"));
				e.setRef_prod(rs.getString("REF_PROD"));
				exempl.add(e);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exempl;
	}
	
	@Override
	public String delExemplaire(String ref) {
		Connection conn=SingletonConnection.getConnection();
		List<Prod_Exemplaire> exempl=new ArrayList<Prod_Exemplaire>();
		exempl=listsExemplaire(ref);
		try {
			PreparedStatement ps=conn.prepareStatement
					("delete from SERIES where numero=?");
			ps.setString(1, exempl.get(0).getNumero_serie());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exempl.get(0).getNumero_serie();
	}
	
	@Override
	public void addProd_Com(Prod_Com pc) {
		Connection conn=SingletonConnection.getConnection();
		Produit p = getProduit(pc.getRef_produit());
		
		pc.setNumero(delExemplaire(pc.getRef_produit()));
		p.setQuantite(p.getQuantite()-1);
		updateProduit(p);
		try {
			PreparedStatement ps=conn.prepareStatement
					("insert into PROD_COM values(?,?,?,?)");
			ps.setInt(1, pc.getRef_commande());
			ps.setString(2, pc.getRef_produit());
			ps.setString(3, pc.getNumero());
			ps.setDouble(4, pc.getPrix());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Prod_Com> listsProd_Com(int ref_com) {
		List<Prod_Com> pcl =new ArrayList<Prod_Com>();
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("select * from PROD_COM where ref_com=?");
			ps.setInt(1, ref_com);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Prod_Com e= new Prod_Com();
				e.setRef_commande(rs.getInt("ref_com"));
				e.setRef_produit(rs.getString("ref_prod"));
				e.setNumero(rs.getString("numero"));
				e.setPrix(rs.getInt("prix_prod_com"));
				pcl.add(e);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pcl;
	}
	
	@Override
	public void addCommande(Commande com) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("insert into COMMANDES values(?,?,?,?,?)");
			ps.setDate(1, null);
			ps.setDate(2, com.getDate());
			ps.setDouble(3, com.getPrix());
			ps.setInt(4, com.getQuantite());
			ps.setString(5, com.getLogin());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Override
	public List<Commande> listCommande() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> commandeParDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addUser(User s) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("insert into USERS values(?,?,?,?,?)");
			ps.setString(1, s.getLogin());
			ps.setString(2, s.getPasswd());
			ps.setString(3, s.getNom());
			ps.setString(4, s.getPrenom());
			ps.setString(4, s.getRole().toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean connexion(String login, String passwd) {
		boolean r=false;
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement
					("select * from USERS where login=?");
			ps.setString(1, login);
			ResultSet rs=ps.executeQuery();
			if(rs.getString("login").equals(login) &&
					rs.getString("passwd").equals(passwd))
			r=true;
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void deconnexion() {
		// TODO Auto-generated method stub
		
	}
}
