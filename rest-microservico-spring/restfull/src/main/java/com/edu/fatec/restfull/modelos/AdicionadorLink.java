package com.edu.fatec.restfull.modelos;

import java.util.List;


public interface AdicionadorLink<T> {
	public void adicionarLink(List<T> lista);
	public void adicionarLink(T objeto);
}