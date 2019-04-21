package br.com.aurindo.crud.repository;

import br.com.aurindo.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
