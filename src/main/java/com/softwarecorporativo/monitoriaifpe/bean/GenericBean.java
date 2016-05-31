/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Edmilson Santana
 * @param <T>
 */
public abstract class GenericBean<T extends EntidadeNegocio> implements Serializable {
    
    protected T entidadeNegocio;
   
    
    public GenericBean() {
        
    }
    
    @PostConstruct
    public void inicializar() {
        System.out.println("Construindo ManagedBean");
    }
    
    public void adicionarMensagemView(String mensagem) {
        this.adicionarMensagemComponente(null, mensagem);
    }
    
    public void adicionarMensagemComponente(String componente, String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(mensagem));
    }
    
    public T getEntidadeNegocio() {
        return entidadeNegocio;
    }
    
    public void setEntidadeNegocio(T entidadeNegocio) {
        this.entidadeNegocio = entidadeNegocio;
    }
    
    
    
}
