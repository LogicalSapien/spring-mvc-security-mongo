package com.logicalsapien.service;


import com.logicalsapien.entity.Galaxy;

import java.util.List;
import java.util.Optional;

public interface GalaxyService {

    List<Galaxy> getAllGalaxies();

    Galaxy getById(String id);

    Galaxy saveGalaxy(Galaxy galaxy);

    void deleteGalaxy(String id);
}
