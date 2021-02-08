package br.com.e2dp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.e2dp.domain.Cargo;
import br.com.e2dp.web.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina) {

		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;

		List<Cargo> cargos = getEntityManager().createQuery("select c from Cargo c order by c.nome asc", Cargo.class)
				.setFirstResult(inicio)// recebe o numero do primeiro registro
				.setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, cargos);
	}

	private long count() {
		return getEntityManager().createQuery("select count(*) from Cargo", Long.class).getSingleResult();
	}

}
