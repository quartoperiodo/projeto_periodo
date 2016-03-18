package com.softwarecorporativo.monitoriaifpe.instituto.curso.impl;

import com.softwarecorporativo.monitoriaifpe.util.constantes.Grau;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.instituto.aluno.impl.AlunoImpl;
import com.softwarecorporativo.monitoriaifpe.instituto.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.Disciplina;
import com.softwarecorporativo.monitoriaifpe.instituto.disciplina.impl.DisciplinaImpl;
import com.softwarecorporativo.monitoriaifpe.negocio.impl.EntidadeNegocioImpl;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CURSO")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "CURSO_ID"))})
public class CursoImpl extends EntidadeNegocioImpl implements Curso {

    @Column(name = "CURSO_DS")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TECNICO', 'SUPERIOR')")
    private Grau grau;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = DisciplinaImpl.class)
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = AlunoImpl.class)
    private List<Aluno> alunos;

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#getDisciplinas()
     */
    public Disciplina getDisciplinas(int index) {

        return disciplinas.get(index);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#setDisciplinas(java.util.Collection)
     */
    public void setDisciplinas(Disciplina disciplina) {

        this.disciplinas.add(disciplina);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#getDescricao()
     */
    public String getDescricao() {

        return descricao;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#setDescricao(java.lang.String)
     */
    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#getAlunos()
     */
    public Aluno getAlunos(int index) {

        return alunos.get(index);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#setAlunos(java.util.Collection)
     */
    public void setAlunos(Aluno aluno) {

        this.alunos.add(aluno);
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#getModalidade()
     */
    public Grau getModalidade() {

        return grau;
    }

    /*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.curso.impl.Curso#setModalidade(br.com.projetoperiodo.util.constantes.enumeracoes.Grau)
     */
    public void setModalidade(Grau modalidade) {

        this.grau = modalidade;
    }

}