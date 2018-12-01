package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

//Essa classe vai ser usada como DAO
public class Function { 
    @FunctionName("createcidade")
    public Cidade create(
        @HttpTrigger(
            name = "createcidaderest",
            methods = {HttpMethod.POST},
            route = "cidade"
        ) 
        Cidade cidade){

        return cidade;
    }

    @FunctionName("readcidade")
    public List<Cidade> read(
        @HttpTrigger(
            name = "readcidaderest",
            methods = {HttpMethod.GET},
            route = "cidade"
        )
        String x){

        Estado e = new Estado(1L, "Paraná");
        Cidade c1 = new Cidade(1L, "Cornélio Procópio", e);
        Cidade c2 = new Cidade(2L, "Londrina", e);

        return Stream.of(c1,c2).collect(Collectors.toList());
    }

    @FunctionName("updatecidade")
    public Cidade update(
        @HttpTrigger(
            name = "updatecidaderest",
            methods = {HttpMethod.PUT},
            route = "cidade"
        )
        Cidade cidade){
        
        return cidade; //Cidade supostamente atualizada
    }

    @FunctionName("deletecidade")
    public int delete(
        @HttpTrigger(
            name = "deletecidaderest",
            methods = {HttpMethod.DELETE},
            route = "cidade/{id}"
        )
        @BindingName ("id") Long id){
        
        return 200;
    }

}

class Estado{
    private Long id;
    private String nome;

    public Estado(){};

    public Estado(Long id, String nome){
        this.setId(id);
        this.setNome(nome);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

class  Cidade{
    private Long id;
    private String nome;
    private Estado estado;

    public Cidade(){};

    public Cidade(Long id, String nome, Estado estado){
        this.setId(id);
        this.setNome(nome);
        this.setEstado(estado);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }
}
