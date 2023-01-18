<template>
    <div class="container">
        <h1 class="text-center">Agenda</h1>
        <form>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="hidden" id="id" name="id">
                <input type="text" class="form-control" name="nome" id="nome" placeholder="Marcoantonio Manso">
            </div>
            <div class="form-group">
                <label for="telefone">Telefone</label>
                <input type="tel" class="form-control" name="telefone" id="telefone" placeholder="(XX) XXXXX-XXXX">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" name="email" id="email" placeholder="fulano@gmail.com">
            </div>
            <button v-on:click="cadastrar()" type="button" class="btn btn-primary">Enviar</button>
        </form>
        <hr>
        <table class="table table-striped">
            <thead>
                <th>Id</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Email</th>
                <th colspan="2"></th>
            </thead>
            <tbody>
                <tr v-for="agenda in agendas" v-bind:key="agenda.id">
                    <td> {{ agenda.id }} </td>
                    <td> {{ agenda.nome }} </td>
                    <td> {{ agenda.telefone }} </td>
                    <td> {{ agenda.email }} </td>
                    <td>
                        <button class="btn btn-primary" v-on:click="salvarInformacoes(agenda)">Atualizar</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" v-on:click="deletar(agenda.id)">Deletar</button>
                    </td>
                </tr>
            </tbody>    
        </table>
    </div>

</template>

<script>
import axios from 'axios';
const URL = `http://localhost:8090/agendas`
    export default{
        name: 'AgendasComponent',
        data(){
            return{
                agendas : [],
                agenda : undefined 
            }
        },
        methods: {
            listar(){
                axios.get(URL).then((response) => {
                    console.log(response)
                    this.agendas = response.data;
                })
            },
            
            cadastrar(){
                if(this.agenda){
                    this.atualizar()
                    return
                }
                else{
                    axios.post(URL,
                    { 
                        nome: document.getElementById("nome").value,
                        telefone: document.getElementById("telefone").value,
                        email: document.getElementById("email").value
                    }).then(() => {
                        alert("Cadastrado Com Sucesso!!")
                        this.listar(this)
                    })
                }
                
            },

            deletar(id) {
                if(confirm("Você deseja excluir este Contato??"))
                axios.delete(`http://localhost:8090/agendas/${id}`).then(() =>{
                    this.listar()
                })
            },
            salvarInformacoes(agenda){
                document.getElementById("nome").value = agenda.nome;
                document.getElementById("telefone").value = agenda.telefone;
                document.getElementById("email").value = agenda.email;
                this.agenda = agenda;
            },
            atualizar() {
                alert("Atualizado Com Sucesso!!")
                this.agenda.nome = document.getElementById("nome").value;
                this.agenda.telefone = document.getElementById("telefone").value;
                this.agenda.email = document.getElementById("email").value;

                axios.put(`http://localhost:8090/agendas/${this.agenda.id}`, this.agenda).then(() =>{
                    this.listar()
                    this.agenda = undefined;
                })
            }
        },  
        created() {
            this.listar(this)
        }
    }

</script>

<style>
.form-group{
    text-align: left;
    margin: 20px;
}
</style>