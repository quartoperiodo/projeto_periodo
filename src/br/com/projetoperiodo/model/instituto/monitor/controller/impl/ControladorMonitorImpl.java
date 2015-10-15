package br.com.projetoperiodo.model.instituto.monitor.controller.impl;

import br.com.projetoperiodo.model.instituto.aluno.Aluno;
import br.com.projetoperiodo.model.instituto.disciplina.Disciplina;
import br.com.projetoperiodo.model.instituto.monitor.Monitor;
import br.com.projetoperiodo.model.instituto.monitor.controller.ControladorMonitor;
import br.com.projetoperiodo.model.instituto.monitor.dao.MonitorDao;
import br.com.projetoperiodo.model.instituto.monitor.impl.MonitorImpl;
import br.com.projetoperiodo.model.instituto.periodo.Periodo;
import br.com.projetoperiodo.model.instituto.periodo.controller.ControladorPeriodo;
import br.com.projetoperiodo.model.negocio.controlador.ControladorNegocioImpl;
import br.com.projetoperiodo.model.negocio.entidade.EntidadeNegocio;
import br.com.projetoperiodo.util.Fachada;
import br.com.projetoperiodo.util.constantes.enumeracoes.Modalidade;
import br.com.projetoperiodo.util.exception.NegocioException;

public class ControladorMonitorImpl extends ControladorNegocioImpl implements ControladorMonitor
{
	private MonitorDao dao;

	public ControladorMonitorImpl() {
		dao = fabrica.criarMonitorDAO();
	}
	
	/* (non-Javadoc)
	 * @see br.com.projetoperiodo.model.instituto.monitor.controller.impl.ControladorMonitor#criarEntidadeNegocio()
	 */
	@Override
	public EntidadeNegocio criarEntidadeNegocio() {
		return new MonitorImpl();
	}

	public Monitor buscarMonitoriasDeAluno(Aluno aluno) throws NegocioException {
		Monitor monitor = dao.buscar(aluno.getChavePrimaria());
		return monitor;
	}

	@Override
	public Monitor cadastrarMonitoria(Aluno aluno, Disciplina disciplina, Modalidade modalidade) {
		ControladorPeriodo controladorPeriodo = Fachada.getInstance().getControladorPeriodo();
		Monitor monitor = (Monitor) criarEntidadeNegocio();
		monitor.setDisciplina(disciplina);
		monitor.setModalidade(modalidade);
		monitor.setChavePrimaria(aluno.getChavePrimaria());
		Periodo periodoCorrente = controladorPeriodo.gerarNovoPeriodoCorrente();
		monitor.setPeriodo(periodoCorrente);
		dao.salvar(monitor);
		return monitor;
	}

	@Override
	public boolean validaCadastroMonitoria(Aluno aluno) {
		// TODO Auto-generated method stub
		return false;
	}
	
}