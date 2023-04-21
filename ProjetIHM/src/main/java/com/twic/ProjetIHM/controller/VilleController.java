package com.twic.ProjetIHM.controller;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.twic.ProjetIHM.model.Ville;
import com.twic.ProjetIHM.service.PaginationService;
import com.twic.ProjetIHM.service.VilleService;


@Controller
public class VilleController {
	
	@Autowired
	private VilleService service;
	
	@Autowired
	private PaginationService pageService;
		
	 @GetMapping("/")
	 public String home(Model model) {
		 
		try {
		    Iterable<Ville> listVilles = service.getVilles();
		    model.addAttribute("listeVilles", listVilles);

		} catch(Exception e) {
			
			model.addAttribute("error", "Impossible de se connecter à l'API.");
		}
	    
	    return "CalculDistance";
		    
	 }
	 
	 @PostMapping("/CalculDistance")
	 public String calculDistance(Model model, @RequestParam(required = false) String ville1, @RequestParam(required = false) String ville2) {
		 
		 if(ville1 != null && ville2 != null) {
			 
			 if(!(ville1.trim().equals(ville2.trim())) && !(ville1.trim().equals(""))) {
				 
				 Ville premiereVille = service.getVille(ville1);
				 
				 Ville deuxiemeVille = service.getVille(ville2);
				 
				 if(premiereVille != null && deuxiemeVille != null) {
					 
					 double distance = service.calculerDistance(premiereVille, deuxiemeVille);
					 
					 model.addAttribute("distance", distance);
					 model.addAttribute("prVille", premiereVille);
					 model.addAttribute("dxVille", deuxiemeVille);
					 
				 }
				 
			
			 } else {
				 
				 model.addAttribute("error", "Veuillez choisir deux villes différentes.");
				
			 }
			 
		 } else {
			 
			 model.addAttribute("error", "Veuillez choisir deux villes.");
		 }
		 
		 Iterable<Ville> listVilles = service.getVilles();
		 model.addAttribute("listeVilles", listVilles);
		 
		 return "CalculDistance";
		 
	 }
	 	 
	 
	 @GetMapping("/Modification")
	  public String modVille(Model model,  @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		 
	    try {
	    	
	    	
//	    	Iterable<Ville> listVilles = service.getVilles();
//		    model.addAttribute("listeVilles", listVilles);
	  	
	    	int currentPage = page.orElse(1);
	        int pageSize = size.orElse(50);
		    
		    Page<Ville> villePage = pageService.findPaginated(service, PageRequest.of(currentPage - 1, pageSize));

	        model.addAttribute("villePage", villePage);

	        int totalPages = villePage.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
	      
	    } catch (Exception e) {
	    	
	      model.addAttribute("error", e.getMessage());
	    }

	    return "Pagination";
	        
	  }
	 
	 @GetMapping("/modifierVille/{codeCommune}")
		public String updateVille(@PathVariable("codeCommune") final String codeCommune, Model model) {
		 
			Ville v = service.getVille(codeCommune);		
			model.addAttribute("ville", v);	
			return "ModifierVille";		
		}

	 @PostMapping("/saveVille")
		public ModelAndView saveEmployee(@ModelAttribute Ville ville) {
		 
			service.saveVille(ville);
			return new ModelAndView("redirect:/Modification");	
		}
}
