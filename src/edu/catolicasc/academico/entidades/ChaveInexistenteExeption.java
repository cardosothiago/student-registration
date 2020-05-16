/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catolicasc.academico.entidades;

/**
 *
 * @author glauco.scheffel
 */
public class ChaveInexistenteExeption extends Exception {

    public ChaveInexistenteExeption(int MATRICULA_NOVO) {
        super("A chave procurada não existe");
    }
    
}
