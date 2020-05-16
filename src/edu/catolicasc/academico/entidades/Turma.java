/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catolicasc.academico.entidades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author glauco.scheffel
 */
public class Turma  implements Serializable {
    private int  codigo;
    private String nomeTurma;
    private int semestre;
    private int ano;
    private HashMap<Integer,Aluno> alunos = new HashMap();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LinkedList<Aluno> getAlunos() {
        return new LinkedList<Aluno>(alunos.values());
    }
   
    public void setAlunos(HashMap<Integer, Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public void incluirAluno( Aluno novo) throws ChaveExistenteException {
        final int MATRICULA_NOVO = novo.getMatricula();
        if( alunos.containsKey(MATRICULA_NOVO)) {
            throw new ChaveExistenteException(MATRICULA_NOVO);
        } else {
            alunos.put(MATRICULA_NOVO, novo);
        }
    }
    
    public Aluno procurar( int matricula ) {
        return alunos.get(matricula);
    }
    
    public void excluirAluno( int matricula ) throws ChaveInexistenteExeption {
        
        if( alunos.containsKey(matricula)) {
            alunos.remove(matricula);
            
        } else {
           throw new ChaveInexistenteExeption(matricula);
        }
        
    }
    
    
    
    
    
    public Turma() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nomeTurma);
        hash = 97 * hash + this.semestre;
        hash = 97 * hash + this.ano;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (this.semestre != other.semestre) {
            return false;
        }
        if (this.ano != other.ano) {
            return false;
        }
        if (!Objects.equals(this.nomeTurma, other.nomeTurma)) {
            return false;
        }
        return true;
    }

    public Turma(String nomeTurma, int semestre, int ano) {
        this.nomeTurma = nomeTurma;
        this.semestre = semestre;
        this.ano = ano;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
}
