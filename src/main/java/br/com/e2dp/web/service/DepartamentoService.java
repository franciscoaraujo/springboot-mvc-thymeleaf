package br.com.e2dp.web.service;

import java.util.List;

import br.com.e2dp.domain.Departamento;
import br.com.e2dp.web.util.PaginacaoUtil;

public interface DepartamentoService {

	void salvar(Departamento departamento);

	void editar(Departamento departamento);

	void excluir(Long id);

	Departamento buscarPorId(Long id);

	List<Departamento> buscarTodos();

	boolean departamentoTemCargos(Long id);

	boolean departamentoJaCadastrado(String nome);
	
	PaginacaoUtil<Departamento> buscaPorPagina(int pagina);
	

}
