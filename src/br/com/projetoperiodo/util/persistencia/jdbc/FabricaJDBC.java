package br.com.projetoperiodo.util.persistencia.jdbc;

import br.com.projetoperiodo.model.instituto.aluno.dao.AlunoDao;
import br.com.projetoperiodo.model.instituto.curso.dao.CursoDao;
import br.com.projetoperiodo.model.instituto.disciplina.dao.DisciplinaDao;
import br.com.projetoperiodo.model.instituto.monitor.dao.MonitorDao;
import br.com.projetoperiodo.model.instituto.professor.dao.ProfessorDao;
import br.com.projetoperiodo.model.relatorio.atividade.dao.AtividadeDao;
import br.com.projetoperiodo.model.relatorio.frequencia.dao.RelatorioFrequenciaDao;
import br.com.projetoperiodo.model.relatorio.semana.dao.SemanaDao;
import br.com.projetoperiodo.model.usuario.dao.UsuarioDao;
import br.com.projetoperiodo.util.persistencia.dao.FabricaDAO;

public class FabricaJDBC extends FabricaDAO
{

	@Override
	public UsuarioDao criarUsuarioDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelatorioFrequenciaDao criarRelatorioFrequenciaDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SemanaDao criarSemanaDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtividadeDao criarAtividadeDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonitorDao criarMonitorDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlunoDao criarAlunoDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisciplinaDao criarDisciplinaDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CursoDao criarCursoDAO() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessorDao criarProfessorDao() {

		// TODO Auto-generated method stub
		return null;
	}

}