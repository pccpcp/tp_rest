package com.twic.ProjetIHM.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ville {
	
	private String codePostal;
	private String ligne;
	private String codeCommune;
	private String nomCommune;
	private String libelleAcheminement;
	private Coordonnee coordonnees;

}
