package br.com.blue.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blue.projeto.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    
}
