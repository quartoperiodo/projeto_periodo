/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.servico;

import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DisciplinaService extends GenericService<Disciplina> {

    @EJB
    private PeriodoService periodoService;

    @Override
    public Disciplina getEntidadeNegocio() {
        return new Disciplina();
    }

    @Override
    public Class<Disciplina> getClasseEntidade() {
        return Disciplina.class;
    }

    public Disciplina salvarDisciplinaComPeriodoAtual(Disciplina disciplina) throws NegocioException {
        Long contadorDisciplinasCadastraNoSemestreAtual = 0L;
        Periodo periodo = periodoService.obterPeriodoAtual();
        disciplina.setPeriodo(periodo);
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT COUNT(d) FROM ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as d ");
        jpql.append("WHERE d.periodo = :paramPeriodo ");
        jpql.append(" AND d.componenteCurricular = :paramComponente ");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter("paramPeriodo", periodo);
        query.setParameter("paramComponente", disciplina.getComponenteCurricular());
        contadorDisciplinasCadastraNoSemestreAtual += (Long) query.getSingleResult();
        if (contadorDisciplinasCadastraNoSemestreAtual > 0L) {
            throw new NegocioException(NegocioException.DISCIPLINA_JA_CADASTRADA);
        } else {
            return super.salvar(disciplina);
        }
    }

    public List<Disciplina> obterDisciplinasDoCursoPorPeriodo(Curso curso, Periodo periodo) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" select disciplina from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as disciplina ");
        jpql.append(" join disciplina.componenteCurricular as cc ");
        jpql.append(" where disciplina.periodo = :paramPeriodo ");
        jpql.append(" and cc.curso = :paramCurso ");
        TypedQuery<Disciplina> query = super.entityManager
                .createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramPeriodo", periodo);
        query.setParameter("paramCurso", curso);

        return query.getResultList();
    }

    public List<Disciplina> obterDisciplinasPorCursoDoPeriodoAtual(Curso curso) {
        Periodo periodo = periodoService.obterPeriodoAtual();
        return this.obterDisciplinasDoCursoPorPeriodo(curso, periodo);
    }

    public List<Disciplina> obterDisciplinasPorCursoDePeriodoNaoAtual(Curso curso) {
        Periodo periodo = periodoService.obterPeriodoAtual();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select disciplina from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as disciplina ");
        jpql.append(" join disciplina.componenteCurricular as cc ");
        jpql.append(" where disciplina.periodo != :paramPeriodo ");
        jpql.append(" and cc.curso = :paramCurso ");
        TypedQuery<Disciplina> query = super.entityManager
                .createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramPeriodo", periodo);
        query.setParameter("paramCurso", curso);

        return query.getResultList();
    }

    public Disciplina salvarDisciplinaComPeriodoAntigo(Disciplina entidadeNegocio, String ano, String semestre) {
        Periodo periodo = periodoService.criarPeriodoAnterior(ano, semestre);
        entidadeNegocio.setPeriodo(periodo);
        return super.salvar(entidadeNegocio);
    }

    /*TODO: Refatorar para a entidade de Professor*/
    public List<Disciplina> obterDisciplinasDoProfessor(Professor professor) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select d from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as d ");
        jpql.append(" where d.professor = :paramProfessor ");
        Query query = super.entityManager.createQuery(jpql.toString(), getClasseEntidade());
        query.setParameter("paramProfessor", professor);
        return query.getResultList();
    }

    /*TODO: Pensar em um nome melhor*/
    public boolean vericarSeDisciplinaJaExiste(Disciplina disciplina) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" AS d ");
        jpql.append(" WHERE d.periodo.ano = :paramAno ");
        jpql.append(" AND d.periodo.semestre = :paramSemestre ");
        Query query = super.entityManager.createQuery(jpql.toString(),
                getClasseEntidade());
        query.setParameter("paramAno", disciplina.getPeriodo().getAno());
        query.setParameter("paramSemestre", 
                disciplina.getPeriodo().getSemestre());

        if (query.getMaxResults() > 0) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }
}
