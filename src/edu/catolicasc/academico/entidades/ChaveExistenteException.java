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
public class ChaveExistenteException extends Exception {

    public ChaveExistenteException(int valor) {
        super("Chave duplicada " + valor );
    }
    
}
