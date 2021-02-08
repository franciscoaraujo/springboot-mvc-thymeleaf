package br.com.e2dp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2dp.domain.dao.DepartamentoDao;
import br.com.e2dp.domain.model.Departamento;
import br.com.e2dp.web.util.PaginacaoUtil;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	private DepartamentoDao dao;

	@Transactional(readOnly = false)
	@Override
	public void salvar(Departamento departamento) {
		dao.save(departamento);		
	}

	@Transactional(readOnly = false)
	@Override
	public void editar(Departamento departamento) {
		dao.update(departamento);		
	}

	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		dao.delete(id);		
	}

	@Transactional(readOnly = true)
	@Override
	public Departamento buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Departamento> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	public boolean departamentoTemCargos(Long id) {
		
		if(buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public boolean departamentoJaCadastrado(String nome) {
		
		return false;
	}

	@Override
	public PaginacaoUtil<Departamento> buscaPorPagina(int pagina,String direcao) {
		return dao.buscaPaginada(pagina, direcao);
	}


	

}
