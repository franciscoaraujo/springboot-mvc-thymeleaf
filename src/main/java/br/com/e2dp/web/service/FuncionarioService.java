package br.com.e2dp.web.service;

import java.time.LocalDate;
import java.util.List;

import br.com.e2dp.domain.Funcionario;

public interface FuncionarioService {

    void salvar(Funcionario funcionario);

    void editar(Funcionario funcionario);

    void excluir(Long id);

    Funcionario buscarPorId(Long id);

    List<Funcionario> buscarTodos();

	Object buscarPorNome(String nome);

	Object buscarPorCargo(Long id);

	Object buscarPorDatas(LocalDate entrada, LocalDate saida);
}
