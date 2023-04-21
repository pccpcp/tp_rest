package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {
	
	public ArrayList<Ville> getInfoVilles() throws Exception;
	public Ville getVilleByCodeCommune(String codeCommune) throws Exception;
	public Ville saveVille1(Ville ville) throws Exception;
	public Ville saveVille2(Ville ville) throws Exception;
	public void deleteVille(String codeCommune) throws Exception;
	public double calculerDistance(String codeCommune1, String codeCommune2) throws Exception;

}
