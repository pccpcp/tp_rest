package com.twic.ProjetIHM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twic.ProjetIHM.model.Ville;
import com.twic.ProjetIHM.repository.VilleProxy;

import lombok.Data;

@Data
@Service
public class VilleService {

    @Autowired
    private VilleProxy VilleProxy;

    public Ville getVille(final String codeCommune) {
        return VilleProxy.getVille(codeCommune);
    }

    public Iterable<Ville> getVilles() {
        return VilleProxy.getVilles();
    }

    public void deleteVille(final int id) {
        VilleProxy.deleteVille(id);
    }

    public Ville saveVille(Ville ville) {
        Ville savedVille;
        
        if(ville.getCodeCommune() == null) {
            // Si le code commune est nul -> nouvelle ville.
            savedVille = VilleProxy.createVille(ville);
        } else {
            savedVille = VilleProxy.updateVille(ville);
        }
    
        return savedVille;
    }
     
    public double calculerDistance(Ville ville1, Ville ville2) {
    	
    	return VilleProxy.calculerDistance(ville1, ville2);
    }

}