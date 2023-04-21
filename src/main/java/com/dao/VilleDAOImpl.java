package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	
	private Connection getConnection() throws SQLException {
		
		Connection connexion = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		
			if (connexion == null) {
				
				
				connexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/maven?serverTimezone=UTC", "root", "");
				connexion.setAutoCommit(false);
			
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connexion;
	}
	
	public ArrayList<Ville> findAllVilles() throws Exception {
		
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
				
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
        	
            connexion = getConnection();
            
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT Code_commune_INSEE, Code_postal, Nom_commune, Libelle_acheminement, Ligne_5 from ville_france;");

            while (resultat.next()) {
            	
                String code_commune = resultat.getString("Code_commune_INSEE");
                String code_postal = resultat.getString("Code_postal");
                String nom_commune = resultat.getString("Nom_commune");
                String libelle = resultat.getString("Libelle_acheminement");
                String ligne = resultat.getString("Ligne_5");
                
                Ville ville = new Ville();
                
                ville.setCodeCommune(code_commune);
                ville.setCodePostal(code_postal);
                ville.setNomCommune(nom_commune);
                ville.setLibelleAcheminement(libelle);
                ville.setLigne(ligne);
                
                listVille.add(ville);

            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            throw new Exception("Impossible de communiquer avec la base de données. Réessayez.");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            	System.out.println(e.getMessage());
                throw new Exception("Impossible de communiquer avec la base de données.");
            }
        }
		
		return listVille;
		
	}
	
	
	public Ville getVilleByCodeCommune(String codeCommune) throws Exception {
		
		Ville ville = new Ville();
				
		Connection connexion = null;
        PreparedStatement pstatement = null;
        ResultSet resultat = null;

        try {
        	
            connexion = getConnection();
            
            pstatement = connexion.prepareStatement("SELECT Code_commune_INSEE, Code_postal, Nom_commune, Libelle_acheminement, Ligne_5, Latitude, Longitude from ville_france where Code_commune_INSEE=?;");

            pstatement.setString(1, codeCommune.trim());
            
            resultat = pstatement.executeQuery();
            
            while (resultat.next()) {
            	
                String code_commune = resultat.getString("Code_commune_INSEE");
                String code_postal = resultat.getString("Code_postal");
                String nom_commune = resultat.getString("Nom_commune");
                String libelle = resultat.getString("Libelle_acheminement");
                String ligne = resultat.getString("Ligne_5");
                String latitude = resultat.getString("Latitude");
                String longitude = resultat.getString("Longitude");
                             
                ville.setCodeCommune(code_commune);
                ville.setCodePostal(code_postal);
                ville.setNomCommune(nom_commune);
                ville.setLibelleAcheminement(libelle);
                ville.setLigne(ligne);
                
                Coordonnee coordonnees = new Coordonnee(latitude, longitude);
                ville.setCoordonnees(coordonnees);
                

            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            throw new Exception("Impossible de communiquer avec la base de données. Réessayez.");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            	System.out.println(e.getMessage());
                throw new Exception("Impossible de communiquer avec la base de données.");
            }
        }
		
		return ville;
		
	}
	
	public Ville saveVille1(Ville ville) throws Exception {
		
		Connection connexion = null;
		PreparedStatement pstatement = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/maven?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
        
			  
		try {
        	
        	pstatement = connexion.prepareStatement("INSERT INTO ville_france (Code_commune_INSEE, Code_postal, Ligne_5, Nom_commune, Libelle_acheminement, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?);");
        	
        	pstatement.setString(1, ville.getCodeCommune());
        	pstatement.setString(2, ville.getCodePostal());
        	pstatement.setString(3, ville.getLigne());
        	pstatement.setString(4, ville.getNomCommune());
        	pstatement.setString(5, ville.getLibelleAcheminement());
        	pstatement.setString(6, ville.getCoordonnees().getLatitude());
        	pstatement.setString(7, ville.getCoordonnees().getLongitude());
        	
        	pstatement.executeUpdate();
            	
  
            	
            } catch (SQLException e) {
    			e.printStackTrace();
    		}
    		
	
	return ville;
		
	}

	
	public Ville saveVille2(Ville ville) throws Exception {
		
		Connection connexion = null;
		PreparedStatement pstatement2 = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/maven?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
        	pstatement2 = connexion.prepareStatement("UPDATE ville_france SET Code_postal = ?, Ligne_5 = ?, Nom_commune = ?, Libelle_acheminement = ?, Latitude = ?, Longitude = ? WHERE Code_commune_INSEE = ?;");
		
    	
	    	pstatement2.setString(1, ville.getCodePostal());
	    	pstatement2.setString(2, ville.getLigne());
	    	pstatement2.setString(3, ville.getNomCommune());
	    	pstatement2.setString(4, ville.getLibelleAcheminement());
	    	pstatement2.setString(5, ville.getCoordonnees().getLatitude());
	    	pstatement2.setString(6, ville.getCoordonnees().getLongitude());
	    	pstatement2.setString(7, ville.getCodeCommune());
	    	
	    	pstatement2.executeUpdate();
    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ville;
		
	}
	
	public void deleteVille(String codeCommune) throws Exception {
		
		Connection connexion = null;
        PreparedStatement pstatement = null;

        try {
        	
            connexion = getConnection();
            
            if(getVilleByCodeCommune(codeCommune) != null) {
            	
            	pstatement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune = ?;");
            	
            	pstatement.setString(1, codeCommune);
            	
            	pstatement.executeUpdate();
            	
            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            throw new Exception("Impossible de communiquer avec la base de données. Réessayez.");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            	System.out.println(e.getMessage());
                throw new Exception("Impossible de communiquer avec la base de données.");
            }
        }
		
	}
	
	
	public double getDistance(Ville ville1, Ville ville2) throws Exception {
		
		double distance;
		
		Connection connexion = null;

        try {
        	
            connexion = getConnection();
            
           Double lat1 = Math.toRadians(Double.valueOf(ville1.getCoordonnees().getLatitude()));
           Double long1 = Math.toRadians(Double.valueOf(ville1.getCoordonnees().getLongitude()));

           Double lat2 = Math.toRadians(Double.valueOf(ville2.getCoordonnees().getLatitude()));
           Double long2 = Math.toRadians(Double.valueOf(ville2.getCoordonnees().getLongitude()));

           distance = Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(long2-long1))*6371;
           
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            throw new Exception("Impossible de communiquer avec la base de données. Réessayez.");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
            	System.out.println(e.getMessage());
                throw new Exception("Impossible de communiquer avec la base de données.");
            }
        }
		
		
		return distance;
		
	}


}
