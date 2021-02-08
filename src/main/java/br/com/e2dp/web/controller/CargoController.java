package br.com.e2dp.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.e2dp.domain.model.Cargo;
import br.com.e2dp.domain.model.Departamento;
import br.com.e2dp.web.service.CargoService;
import br.com.e2dp.web.service.DepartamentoService;
import br.com.e2dp.web.util.PaginacaoUtil;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		
		PaginacaoUtil<Cargo>pageCargo = cargoService.buscarPorPagina(paginaAtual);
		
		model.addAttribute("pageCargo", pageCargo);
		return "cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.buscarTodos();
	}

}
