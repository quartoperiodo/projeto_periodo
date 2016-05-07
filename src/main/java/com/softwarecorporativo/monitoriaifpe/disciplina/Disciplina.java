/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.disciplina;

import com.softwarecorporativo.monitoriaifpe.disciplina.ComponenteCurricular;
import com.softwarecorporativo.monitoriaifpe.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.professor.Professor;
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
@Table(name = "TB_DISCIPLINA")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "DISCIPLINA_ID"))})
@Access(AccessType.FIELD)
public class Disciplina extends EntidadeNegocio {

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPONENTE_CURRICULAR_ID", referencedColumnName = "COMPONENTE_CURRICULAR_ID")
    private ComponenteCurricular componenteCurricular;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "PROFESSOR_ID")
    private Professor professor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PERIODO_ID", referencedColumnName = "PERIODO_ID")
    private Periodo periodo;

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

}
