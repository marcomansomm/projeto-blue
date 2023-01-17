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

    public boolean verificaEmail (String email){ 
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; 
        return email.matches(regex); 
    }

    public boolean verificaTelefone (String telefone){ 
        String regex = "^((\\+\\d{2}\\s)?\\(\\d{2}\\)\\s?\\d{4}\\d?\\-\\d{4})?$"; 
        return telefone.matches(regex); 
    }

    public Agenda cadastrarAgenda(Agenda agendaNova){

        if(agendaNova.getNome().length() < 3 || agendaNova.getNome().length() > 190) throw new RuntimeException("O Nome precisa ter mais de 3 letras");
        if(!this.verificaTelefone(agendaNova.getTelefone())) throw new RuntimeException("O numero Precisa ser escrito nesse modelo = (**) *****-****");
        if(!this.verificaEmail(agendaNova.getEmail())) throw new RuntimeException("Escreva o email assim nome@dominio.com");

        return this.repositoryAgenda.save(agendaNova);
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
        
        if(agendaAtualizada.getNome().length() < 3 || agendaAtualizada.getNome().length() > 190) throw new RuntimeException("O Nome precisa ter mais de 3 letras");
        if(!this.verificaTelefone(agendaAtualizada.getTelefone())) throw new RuntimeException("O numero Precisa ser escrito nesse modelo = (**) *****-****");
        if(!this.verificaEmail(agendaAtualizada.getEmail())) throw new RuntimeException("Escreva o email assim nome@dominio.com");
        
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
