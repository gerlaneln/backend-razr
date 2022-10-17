package br.ufac.productmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.PhoneCompany;
import br.ufac.productmanager.model.Region;

public interface PhoneCompanyRepository extends JpaRepository<PhoneCompany, Long>{
    List<PhoneCompany>findByRegion(Region region);
}
