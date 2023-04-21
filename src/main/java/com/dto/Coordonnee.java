package com.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coordonnee {
	
	private String latitude;
	private String longitude;
	
	public Coordonnee(String ltd, String lgt) {
		
		this.latitude = ltd;
		this.longitude = lgt;
	}

}