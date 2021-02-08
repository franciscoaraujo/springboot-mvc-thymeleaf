package br.com.e2dp.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.e2dp.domain.model.Departamento;
import br.com.e2dp.web.service.DepartamentoService;
import br.com.e2dp.web.util.PaginacaoUtil;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model,  @RequestParam("page") Optional<Integer> page,
										  @RequestParam("dir") Optional<String> dir) {
		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");
		
		PaginacaoUtil<Departamento>pageDepartamento = service.buscaPorPagina(paginaAtual, ordem);
		
		model.addAttribute("pageDepartamento", pageDepartamento);
		return "departamento/lista";
	}
	
	@PostMapping("/salvar")// estou usando o RedirectAttributes porque esta sendo usado o redirect 
	public String salvar(@Valid  Departamento departamento,BindingResult result ,RedirectAttributes attr) {
		/*tem que tratar o cadastro de nomes nome de departamento*/
		if(result.hasErrors()) {
			return "departamento/cadastro";
		}
		service.salvar(departamento);
		
		attr.addFlashAttribute("success","Departamento inserido com sucesso");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result ,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "departamento/cadastro";
		}
		service.editar(departamento);
		attr.addFlashAttribute("success","Departamento editado com sucesso");
		return "redirect: departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")// estou usandoo ModelMap porque nao estou usando o redirect
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if(service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		}else {
			service.excluir(id);
			model.addAttribute("success", "Departamento removido com sucesso.");
		}
		
		return "departamento/lista";
	}
}
