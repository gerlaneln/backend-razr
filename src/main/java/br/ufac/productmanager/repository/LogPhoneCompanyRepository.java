package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.LogPhoneCompany;

public interface LogPhoneCompanyRepository extends JpaRepository<LogPhoneCompany,Long>{
    
}
