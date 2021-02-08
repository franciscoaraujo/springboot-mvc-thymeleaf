package br.com.e2dp.dao;

import java.util.List;

import br.com.e2dp.domain.Departamento;
import br.com.e2dp.util.PaginacaoUtil;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);
    
    List<Departamento> findAll();
    
    PaginacaoUtil<Departamento> buscaPaginada(int pagina);
}
