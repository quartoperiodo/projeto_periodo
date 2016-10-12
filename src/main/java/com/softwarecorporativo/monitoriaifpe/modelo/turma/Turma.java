/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.turma;

import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Edmilson
 */
@Entity
@Table(name = "tb_turma")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_turma"))})
@Access(AccessType.FIELD)
public class Turma extends EntidadeNegocio {

    private static final long serialVersionUID = -7788698676039962643L;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_componente_curricular", referencedColumnName = "id_componente_curricular")
    private ComponenteCurricular componenteCurricular;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_professor", referencedColumnName = "id_professor")
    private Professor professor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    private Periodo periodo;

    @NotNull
    @Column(name = "turma_ofertada" , nullable = false)
    private Boolean ofertada;
    
    public int obterAnoTurma() {

        return getPeriodo().getAno();
    }

    public Professor getProfessor() {

        return this.professor;
    }

    public void setProfessor(Professor professor) {

        this.professor = professor;

    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;

    }

    public ComponenteCurricular getComponenteCurricular() {
        return this.componenteCurricular;
    }

    public void setComponenteCurricular(ComponenteCurricular componenteCurricular) {
        this.componenteCurricular = componenteCurricular;
    }

    /**
     * Recebe um Componente Curricular para verificar se a disciplina possui o
     * mesmo
     *
     * @param disciplina
     * @return 
     *
     */
    public Boolean verificarIgualdadeComponenteCurricular(Turma disciplina) {
        return this.getComponenteCurricular().equals(disciplina.getComponenteCurricular());
    }

    public Boolean isOfertada() {
        return ofertada;
    }

    public void setOfertada(Boolean ofertada) {
        this.ofertada = ofertada;
    }
    
    

}