package br.com.blue.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blue.projeto.entity.Agenda;
import br.com.blue.projeto.repository.AgendaRepository;

@Service
public class AgendaService {
    
    @Autowired
    private AgendaRepository repositoryAgenda;

    public Agenda cadastrarAgenda(Agenda AgendaNova){

        if(AgendaNova.getNome().length() > 3 && AgendaNova.getNome().length() < 190);
        if(AgendaNova.getTelefone().length() == 11);

        return this.repositoryAgenda.save(AgendaNova);
    }

    public List<Agenda> obterAgenda(){

        return this.repositoryAgenda.findAll();
    
    }

    public Agenda obterAgendaPorId(long id){
        Optional<Agenda> agenda = this.repositoryAgenda.findById(id);

        return agenda.orElseThrow(() -> new RuntimeException("Agenda não encontrada"));
    }

    public Agenda atualizarAgenda(long id, Agenda agendaAtualizada){
        Agenda agenda = this.obterAgendaPorId(id);
        
        if(agendaAtualizada.getNome().length() > 3 && agendaAtualizada.getNome().length() < 190);
        if(agendaAtualizada.getTelefone().length() == 11);
        
        agenda.setNome(agendaAtualizada.getNome());
        agenda.setEmail(agendaAtualizada.getEmail());
        agenda.setTelefone(agendaAtualizada.getTelefone());

        return this.repositoryAgenda.save(agenda);
    }

    public void deletarAgenda(long id){
        this.obterAgendaPorId(id);
        this.repositoryAgenda.deleteById(id);
    }
}
