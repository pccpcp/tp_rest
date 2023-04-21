package com.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
	VilleBLO villeBLOService;
	
	@RequestMapping(value="/villes", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getAllVilles() {
				
		ArrayList<Ville> listeVille = null;
		
		try {
			listeVille = villeBLOService.getInfoVilles();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeVille;
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	@ResponseBody
	public Ville getVilleByID(@RequestParam(required = true, value = "codeCommune") String codeCommune) {
		
		Ville ville = null;
		
		try {
			ville = villeBLOService.getVilleByCodeCommune(codeCommune);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ville;
	}
	
	@PostMapping("/creerVille")
	public Ville createVille(@RequestBody Ville ville) throws Exception {
		return villeBLOService.saveVille1(ville);
	}
	
	@PutMapping("/updateVille/{codeCommune}")
	public Ville updateVille(@PathVariable("codeCommune") final String codeCommune, @RequestBody Ville ville) throws Exception {
		
		Optional<Ville> v = Optional.of(villeBLOService.getVilleByCodeCommune(codeCommune));
		
		if(v.isPresent()) {
			
			Ville currentVille = v.get();
			
			String codePostal = ville.getCodePostal();
			if(codePostal != null) {
				currentVille.setCodePostal(codePostal);
			}
			String ligne = ville.getLigne();
			if(ligne != null) {
				currentVille.setLigne(ligne);;
			}
			String libelleAcheminement = ville.getLibelleAcheminement();
			if(libelleAcheminement != null) {
				currentVille.setLibelleAcheminement(libelleAcheminement);
			}
			String nomCommune = ville.getNomCommune();
			if(nomCommune != null) {
				currentVille.setNomCommune(nomCommune);;
			}
			String latitude = ville.getCoordonnees().getLatitude();
			if(latitude != null) {
				currentVille.getCoordonnees().setLatitude(latitude);;
			}
			String longitude = ville.getCoordonnees().getLongitude();
			if(longitude != null) {
				currentVille.getCoordonnees().setLongitude(longitude);;
			}
			
			villeBLOService.saveVille2(currentVille);
			
			return currentVille;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/ville/{codeCommune}")
	public void deleteVille(@PathVariable("codeCommune") final String codeCommune) throws Exception {
		villeBLOService.deleteVille(codeCommune);
	}
	
	@RequestMapping(value="/calculerDistance", method=RequestMethod.GET)
	@ResponseBody
	public double calculerDistance(@RequestParam(required = true, value = "ville1") String codeCommune1,
			@RequestParam(required = true, value = "ville2") String codeCommune2) {
		
		double distance = 0;
		
		try {
			distance = villeBLOService.calculerDistance(codeCommune1, codeCommune2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return distance;
	}
}
