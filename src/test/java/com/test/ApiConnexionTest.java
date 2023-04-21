package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiConnexionTest {
	
	@Test
	void testGetConnexion() {
		
		Connection connexion = null;
		
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
		
		Assertions.assertNotNull(connexion);
	}
	
	
	@Test
	void testInsertion() {
		
		Connection connexion = null;
		PreparedStatement pstatement = null;
		int resultat = 0;
		
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
		
    	
	    	pstatement.setString(1, "test");
	    	pstatement.setString(2, "test");
	    	pstatement.setString(3, "test");
	    	pstatement.setString(4, "test");
	    	pstatement.setString(5, "test");
	    	pstatement.setString(6, "test");
	    	pstatement.setString(7, "test");
	    	
	    	resultat = pstatement.executeUpdate();
    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Assertions.assertNotEquals(resultat, 0, "error");
	}
	
	@Test
	void testUpdate() {
		
		Connection connexion = null;
		PreparedStatement pstatement2 = null;
		int resultat = 0;
		
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
		
    	
	    	pstatement2.setString(1, "testing");
	    	pstatement2.setString(2, "testing");
	    	pstatement2.setString(3, "testing");
	    	pstatement2.setString(4, "test");
	    	pstatement2.setString(5, "test");
	    	pstatement2.setString(6, "test");
	    	pstatement2.setString(7, "test");
	    	
	    	resultat = pstatement2.executeUpdate();
    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Assertions.assertNotEquals(resultat, 0, "error");
	}
	

}
