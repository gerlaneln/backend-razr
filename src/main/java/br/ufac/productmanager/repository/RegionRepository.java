package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long>{
       Region findByRegionName(String regionName);
}
