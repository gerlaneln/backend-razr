package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
