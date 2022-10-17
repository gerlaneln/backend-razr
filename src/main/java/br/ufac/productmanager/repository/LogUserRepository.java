package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.LogUser;

public interface LogUserRepository extends JpaRepository<LogUser, Long>{

}
