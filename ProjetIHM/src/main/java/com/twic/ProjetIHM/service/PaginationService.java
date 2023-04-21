package com.twic.ProjetIHM.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.twic.ProjetIHM.model.Ville;

import lombok.Data;

@Data
@Service
public class PaginationService {
	
    public Page<Ville> findPaginated(VilleService mainService, Pageable pageable) {
    	
        List<Ville> villes = (List<Ville>) mainService.getVilles();
    	
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Ville> list;

        if (villes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, villes.size());
            list = villes.subList(startItem, toIndex);
        }

        Page<Ville> villePage = new PageImpl<Ville>(list, PageRequest.of(currentPage, pageSize), villes.size());

        return villePage;
    }
}