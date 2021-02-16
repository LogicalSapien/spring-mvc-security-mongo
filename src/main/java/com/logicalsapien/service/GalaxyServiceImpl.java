package com.logicalsapien.service;

import com.logicalsapien.entity.Galaxy;
import com.logicalsapien.repository.GalaxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalaxyServiceImpl implements GalaxyService {

    @Autowired
    private GalaxyRepository galaxyRepository;

    @Override
    public List<Galaxy> getAllGalaxies() {
        return galaxyRepository.findAll();
    }

    @Override
    public Galaxy getById(String id) {
        return galaxyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid galaxy Id:" + id));
    }

    @Override
    public Galaxy saveGalaxy(Galaxy galaxy) {
        return galaxyRepository.save(galaxy);
    }

    @Override
    public void deleteGalaxy(String id) {
        Galaxy galaxy = getById(id);
        galaxyRepository.delete(galaxy);
    }
}
