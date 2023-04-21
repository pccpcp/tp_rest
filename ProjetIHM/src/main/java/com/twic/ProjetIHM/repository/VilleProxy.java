package com.twic.ProjetIHM.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.twic.ProjetIHM.CustomProperties;
import com.twic.ProjetIHM.model.Ville;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VilleProxy {

    @Autowired
    private CustomProperties props;

    
    public Iterable<Ville> getVilles() {
        String baseApiUrl = props.getApiUrl();
        String getVillesUrl = baseApiUrl + "/villes";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Ville>> response = restTemplate.exchange(
                getVillesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Ville>>() {}
                );

        log.debug("Get Villes call " + response.getStatusCode().toString());
        
        return response.getBody();
    }
    
    
	public Ville getVille(String codeCommune) {
		String baseApiUrl = props.getApiUrl();
		String getVilleUrl = baseApiUrl + "/ville?codeCommune=" + codeCommune;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ville> response = restTemplate.exchange(
				getVilleUrl, 
				HttpMethod.GET, 
				null,
				Ville.class
			);
		
		log.debug("Get Ville call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
    
    
    public Ville createVille(Ville ville) {
        String baseApiUrl = props.getApiUrl();
        String createVilleUrl = baseApiUrl + "/creerVille";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Ville> request = new HttpEntity<Ville>(ville);
        ResponseEntity<Ville> response = restTemplate.exchange(
            createVilleUrl,
            HttpMethod.POST,
            request,
            Ville.class);

        log.debug("Create Ville call " + response.getStatusCode().toString());

        return response.getBody();
    }
    
    
  
	public Ville updateVille(Ville ville) {
		String baseApiUrl = props.getApiUrl();
		String updateVilleUrl = baseApiUrl + "/updateVille/" + ville.getCodeCommune();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Ville> request = new HttpEntity<Ville>(ville);
		ResponseEntity<Ville> response = restTemplate.exchange(
				updateVilleUrl, 
				HttpMethod.PUT, 
				request, 
				Ville.class);
		
		log.debug("Update Ville call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	
	public void deleteVille(int codeCommune) {
		String baseApiUrl = props.getApiUrl();
		String deleteVilleUrl = baseApiUrl + "/ville/" + codeCommune;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteVilleUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Ville call " + response.getStatusCode().toString());
	}
	
	public double calculerDistance(Ville ville1, Ville ville2) {
		
		String baseApiUrl = props.getApiUrl();
		String getCalculDist = baseApiUrl + "/calculerDistance?ville1=" + ville1.getCodeCommune() + "&ville2=" + ville2.getCodeCommune();

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Double> response
		  = restTemplate.getForEntity(getCalculDist, Double.class);

        log.debug("Calculer Distance call " + response.getStatusCode().toString());

        return response.getBody();
	}
	
	

}