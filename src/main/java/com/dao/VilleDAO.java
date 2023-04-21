package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles() throws Exception;
	public Ville getVilleByCodeCommune(String codeCommune) throws Exception;
	public Ville saveVille1(Ville ville) throws Exception;
	public Ville saveVille2(Ville ville) throws Exception;
	public void deleteVille(String codeCommune) throws Exception;
	public double getDistance(Ville ville1, Ville ville2) throws Exception;

}
