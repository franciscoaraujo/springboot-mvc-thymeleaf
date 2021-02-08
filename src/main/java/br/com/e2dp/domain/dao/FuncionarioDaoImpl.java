package br.com.e2dp.domain.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.e2dp.domain.AbstractDao;
import br.com.e2dp.domain.model.Departamento;
import br.com.e2dp.domain.model.Funcionario;
import br.com.e2dp.web.util.PaginacaoUtil;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	public List<Funcionario> findByNome(String nome) {
		return createQuery("select f from Funcionario f where f.nome like concat('%',?1,'%') ", nome);
	}

	@Override
	public List<Funcionario> findByCargoId(Long id) {
		return createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
	}

	@Override
	public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
		String jpql = new StringBuilder("select f from Funcionario f ")
				.append("where f.dataEntrada >= ?1 and f.dataSaida <= ?2 ").append("order by f.dataEntrada asc")
				.toString();
		return createQuery(jpql, entrada, saida);
	}

	@Override
	public List<Funcionario> findByDataEntrada(LocalDate entrada) {
		String jpql = new StringBuilder("select f from Funcionario f ").append("where f.dataEntrada = ?1 ")
				.append("order by f.dataEntrada asc").toString();
		return createQuery(jpql, entrada);
	}

	@Override
	public List<Funcionario> findByDataSaida(LocalDate saida) {
		String jpql = new StringBuilder("select f from Funcionario f ").append("where f.dataSaida = ?1 ")
				.append("order by f.dataEntrada asc").toString();
		return createQuery(jpql, saida);
	}

	@Override
	public PaginacaoUtil<Funcionario> buscaPaginada(int pagina, String direcao) {
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Funcionario> funcionarios = getEntityManager().createQuery("select f from Funcionario f order by f.nome "+direcao, Funcionario.class)
				.setFirstResult(inicio)// recebe o numero do primeiro registro
				.setMaxResults(tamanho).getResultList();
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao,funcionarios);
	}
	
	private long count() {
		return getEntityManager().createQuery("select count(*) from Funcionario", Long.class).getSingleResult();
	}

}
