package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {
	
	@Autowired
	private VilleDAO villeDao;
	private Ville ville, ville1, ville2;
	double distance;
	
	public ArrayList<Ville> getInfoVilles() throws Exception {
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		listVille = villeDao.findAllVilles();
		return listVille;
	}
	
	public Ville getVilleByCodeCommune(String codeCommune) throws Exception {
		
		ville = villeDao.getVilleByCodeCommune(codeCommune);
		
		return ville;
	}
	
	public Ville saveVille1(Ville ville) throws Exception {
		
		ville = villeDao.saveVille1(ville);
		
		return ville;		
	}
	
	public Ville saveVille2(Ville ville) throws Exception {
		
		ville = villeDao.saveVille2(ville);
		
		return ville;		
	}
	
	public void deleteVille(String codeCommune) throws Exception {
		
		villeDao.deleteVille(codeCommune);
		
	}
	
	public double calculerDistance(String codeCommune1, String codeCommune2) throws Exception {
		
		ville1 = villeDao.getVilleByCodeCommune(codeCommune1);
		ville2 = villeDao.getVilleByCodeCommune(codeCommune2);

		distance = villeDao.getDistance(ville1, ville2);
		
		return distance;
	}


}
