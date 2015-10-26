
package br.com.projetoperiodo.model.instituto.monitor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.aluno.impl.AlunoImpl;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.disciplina.impl.DisciplinaImpl;
import br.com.projetoperiodo.model.instituto.monitor.Monitor;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.model.instituto.periodo.impl.PeriodoImpl;
import br.com.projetoperiodo.model.negocio.entidade.impl.EntidadeNegocioImpl;
import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;
import br.com.projetoperiodo.model.relatorio.frequencia.impl.RelatorioFrequenciaImpl;
import br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade;

@Entity
@Table(name = "MONITOR")
@AttributeOverrides({@AttributeOverride(name = "chavePrimaria", column = @Column(name = "MONITOR_ID") )})
public class MonitorImpl extends EntidadeNegocioImpl implements Monitor {

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('BOLSISTA', 'VOLUNTARIO')")
	private Modalidade modalidade;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = DisciplinaImpl.class)
	@JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "DISCIPLINA_ID")
	private Disciplina disciplina;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = AlunoImpl.class)
	@JoinColumn(name = "ALUNO_ID", referencedColumnName = "ALUNO_ID")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = PeriodoImpl.class)
	@JoinColumn(name = "PERIODO_ID", referencedColumnName = "PERIODO_ID")
	private Periodo periodo;

	@OneToMany(mappedBy = "monitor", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, targetEntity = RelatorioFrequenciaImpl.class)
	private List<RelatorioFrequencia> relatoriosMensais;

	@Column(name = "HABILITADO")
	private boolean habilitado;

	public MonitorImpl() {
		relatoriosMensais = new ArrayList<RelatorioFrequencia>();
	}
	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#getModalidade()
	 */

	public Modalidade getModalidade() {

		return modalidade;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#setModalidade(br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade)
	 */

	public void setModalidade(Modalidade modalidade) {

		this.modalidade = modalidade;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#getDisciplina()
	 */

	public Disciplina getDisciplina() {

		return disciplina;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#setDisciplina(br.com.projetoperiodo.model.instituto.disciplina.Disciplina)
	 */

	public void setDisciplina(Disciplina disciplina) {

		this.disciplina = disciplina;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#getRelatoriosMensais()
	 */
	@Override
	public RelatorioFrequencia getRelatoriosMensais(int index) {

		return relatoriosMensais.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.impl.Monitor#setRelatoriosMensais(java.util.Collection)
	 */
	@Override
	public void setRelatoriosMensais(RelatorioFrequencia relatorio) {

		this.relatoriosMensais.add(relatorio);
	}

	@Override
	public Periodo getPeriodo() {

		return periodo;
	}

	@Override
	public void setPeriodo(Periodo periodo) {

		this.periodo = periodo;
	}

	@Override
	public boolean isHabilitado() {

		return habilitado;
	}

	@Override
	public void setHabilitado(boolean habilitado) {

		this.habilitado = habilitado;
	}

	@Override
	public void setAluno(Aluno aluno) {

		this.aluno = aluno;

	}

	@Override
	public Aluno getAluno() {

		return aluno;
	}

}
