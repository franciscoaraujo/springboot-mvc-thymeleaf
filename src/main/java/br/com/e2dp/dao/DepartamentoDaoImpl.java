package br.com.e2dp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.e2dp.domain.Departamento;
import br.com.e2dp.util.PaginacaoUtil;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {

	public PaginacaoUtil<Departamento> buscaPaginada(int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;

		List<Departamento> departamentos = getEntityManager().createQuery("select d from Departamento d order by d.nome asc", Departamento.class)
				.setFirstResult(inicio)// recebe o numero do primeiro registro
				.setMaxResults(tamanho).getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, departamentos);
	}

	private long count() {
		return getEntityManager().createQuery("select count(*) from Departamento", Long.class).getSingleResult();
	}


}
