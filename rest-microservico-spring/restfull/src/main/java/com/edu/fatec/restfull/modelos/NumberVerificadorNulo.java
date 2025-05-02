package com.edu.fatec.restfull.modelos;

public class NumberVerificadorNulo {

    public boolean verificar(Double dado) {
        return dado != null && dado != 0.0;
    }
}
