package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Chipset;


public interface ChipsetRepository extends JpaRepository<Chipset, Long>
{
    
}
