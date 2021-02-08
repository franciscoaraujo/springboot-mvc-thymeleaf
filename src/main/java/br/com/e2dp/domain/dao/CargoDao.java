package br.com.e2dp.domain.dao;

import java.util.List;

import br.com.e2dp.domain.model.Cargo;
import br.com.e2dp.web.util.PaginacaoUtil;

public interface CargoDao {

    void save(Cargo cargo );

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();
    
    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
