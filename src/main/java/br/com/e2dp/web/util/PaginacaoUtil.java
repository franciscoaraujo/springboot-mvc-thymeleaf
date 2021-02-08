package br.com.e2dp.web.util;

import java.util.List;

public class PaginacaoUtil<T> {
	
	private int tamanho;//numeros de linhas.
	private int pagina;//numero da pagina atual.
	private long totalDePaginas; //armazena o valor do total de paginas.
	private List<T>registros;//resultado da consulta do banco de dados
	
	public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.registros = registros;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalDePaginas() {
		return totalDePaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}
	
	
}
