package br.com.e2dp.domain.dao;

import java.time.LocalDate;
import java.util.List;

import br.com.e2dp.domain.model.Funcionario;
import br.com.e2dp.web.util.PaginacaoUtil;

public interface FuncionarioDao {
    
	void save(Funcionario funcionario);

    void update(Funcionario funcionario);

    void delete(Long id);

    Funcionario findById(Long id);

    List<Funcionario> findAll();
    
    List<Funcionario> findByNome(String nome);

	List<Funcionario> findByCargoId(Long id);

	List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

	List<Funcionario> findByDataEntrada(LocalDate entrada);

	List<Funcionario> findByDataSaida(LocalDate saida);
	
	PaginacaoUtil<Funcionario> buscaPaginada(int pagina, String direcao);
	
}
