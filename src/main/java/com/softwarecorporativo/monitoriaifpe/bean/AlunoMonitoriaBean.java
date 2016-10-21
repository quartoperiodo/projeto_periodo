/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.monitoria.Monitoria;
import com.softwarecorporativo.monitoriaifpe.servico.MonitoriaService;
import com.softwarecorporativo.monitoriaifpe.servico.TurmaService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AlunoMonitoriaBean extends ViewScopedBean<Monitoria> {

    private static final long serialVersionUID = -4736071102515881964L;

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    @EJB
    private MonitoriaService monitoriaService;

    @EJB
    private TurmaService turmaService;

    private List<Monitoria> monitoriasAprovadas;

    private List<Monitoria> monitoriasSolicitadas;

    private List<Turma> turmasOfertadas;

    @Override
    void inicializarEntidades() {
        super.setEntidadeNegocio(monitoriaService.getEntidadeNegocio());

    }

    @Override
    protected void inicializarListaEntidades() {
        inicializarMonitoriasSolicitadas();
        inicializarMonitoriasAprovadas();
        inicializarTurmasOfertadas();
    }

    @Override
    protected void popularListaEntidades() {
        inicializarMonitoriasSolicitadas();
    }

    @Override
    void inicializarServico() {
        setService(monitoriaService);
    }

    public List<Turma> getTurmas() {
        return turmasOfertadas;
    }

    public List<Monitoria> getMonitoriasSolicitadas() {
        return monitoriasSolicitadas;
    }

    public List<Monitoria> getMonitoriasAprovadas() {
        return monitoriasAprovadas;
    }

    public void inicializarMonitoriasAprovadas() {
        Aluno aluno = (Aluno) usuarioBean.getUsuario();
        monitoriasAprovadas = monitoriaService.consultarMonitoriasAprovadas(aluno);
    }

    public void inicializarMonitoriasSolicitadas() {
        Aluno aluno = (Aluno) usuarioBean.getUsuario();
        monitoriasSolicitadas = monitoriaService.consultarMonitoriasSolicitadas(aluno);
    }

    public void inicializarTurmasOfertadas() {
        Aluno aluno = (Aluno) usuarioBean.getUsuario();
        Curso curso = aluno.getCurso();
        turmasOfertadas = turmaService.consultarTurmasOfertadas(curso);
    }

    public Boolean possuiTurmasOfertadas() {
        Boolean possui = Boolean.FALSE;
        if (turmasOfertadas != null && !turmasOfertadas.isEmpty()) {
            possui = Boolean.TRUE;
        }
        return possui;
    }

    @Override
    public void cadastrar() {
        Aluno aluno = (Aluno) usuarioBean.getUsuario();
        entidadeNegocio.setAluno(aluno);
        super.cadastrar();
    }

    public UsuarioBean getUserSettings() {
        return usuarioBean;
    }


}