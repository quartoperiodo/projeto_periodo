package br.com.projetoperiodo.model.relatorio.frequencia.dao;

import java.util.List;

import br.com.projetoperiodo.model.relatorio.frequencia.RelatorioFrequencia;

public interface RelatorioFrequenciaDao {
	public RelatorioFrequencia salvar(RelatorioFrequencia relatorio);
	public void atualizar(RelatorioFrequencia relatorio);
	public void remover(RelatorioFrequencia relatorio);
	public List<RelatorioFrequencia> listar(String condicao);
	public RelatorioFrequencia buscar(long primaryKey);
	void remover(String condicao);
}
