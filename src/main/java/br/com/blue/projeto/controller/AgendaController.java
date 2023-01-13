package br.com.blue.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blue.projeto.entity.Agenda;
import br.com.blue.projeto.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    
    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public List<Agenda> obterAgenda(){
        return this.agendaService.obterAgenda();
    }

    @PostMapping
    public ResponseEntity<Agenda> cadastrarAgenda(@RequestBody Agenda agenda){

        Agenda novaAgenda = this.agendaService.cadastrarAgenda(agenda);

        return new ResponseEntity<Agenda>(novaAgenda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizarAgenda(@PathVariable long id, @RequestBody Agenda agenda){
        Agenda agendaAtualizada = this.agendaService.atualizarAgenda(id, agenda);

        return new ResponseEntity<Agenda>(agendaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAgenda(@PathVariable long id){
        this.agendaService.deletarAgenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
