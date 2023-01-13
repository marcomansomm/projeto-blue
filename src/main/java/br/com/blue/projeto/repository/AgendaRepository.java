package br.com.blue.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blue.projeto.entity.Agenda;


public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    
}
