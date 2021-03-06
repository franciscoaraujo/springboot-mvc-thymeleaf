package br.com.e2dp.domain.dao;

import java.util.List;

import br.com.e2dp.domain.model.Departamento;
import br.com.e2dp.web.util.PaginacaoUtil;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);
    
    List<Departamento> findAll();
    
    PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao);
}
