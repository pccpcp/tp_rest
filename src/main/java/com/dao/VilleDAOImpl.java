package com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	
	public ArrayList<Ville> findAllVilles() {
		
		System.out.println("findAllVilles");
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		Ville ville = new Ville();
		ville.setCodePostal("44000");
		ville.setLigne("ligne");
		ville.setNomCommune("test");
		
		listVille.add(ville);
		
		return listVille;
	}

}
