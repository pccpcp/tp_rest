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
	
	public ArrayList<Ville> getInfoVilles(String codePostal) {
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		listVille = villeDao.findAllVilles();
		return listVille;
		
	}


}
