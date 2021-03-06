package br.com.e2dp.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.e2dp.domain.AbstractDao;
import br.com.e2dp.domain.model.Cargo;
import br.com.e2dp.web.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina,String direcao) {

		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;

		List<Cargo> cargos = getEntityManager().createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class)
				.setFirstResult(inicio)// recebe o numero do primeiro registro
				.setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao ,cargos);
	}

	private long count() {
		return getEntityManager().createQuery("select count(*) from Cargo", Long.class).getSingleResult();
	}

}
