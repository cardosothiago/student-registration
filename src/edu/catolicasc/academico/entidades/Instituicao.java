package edu.catolicasc.academico.entidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Instituicao {

    private final String FILE = "C:\\Users\\thiago.cardoso\\Documents\\NetBeansProjects\\TrabalhoPO\\dados\\instituicao.ser";
    private HashMap<Integer, Turma> turmas = new HashMap();
    private static Instituicao instance;

    public static Instituicao getInstance() {
        if (instance == null) {
            instance = new Instituicao();
            instance.lerDoDisco();
        }
        return instance;
    }

    private Instituicao() {

    }

    public void incluirTurma(Turma novo) throws ChaveExistenteException {
        if (turmas.containsKey(novo.getCodigo())) {
            throw new ChaveExistenteException(novo.getCodigo());
        } else {
            turmas.put(novo.getCodigo(), novo);
        }
    }

    public LinkedList<Aluno> getAlunos() {
        return this.getTurmas().getFirst().getAlunos();
    }

    public Turma procurar(int codigo) {
        return turmas.get(codigo);
    }

    public void excluirAluno(int codigo) throws ChaveInexistenteExeption {
        if (turmas.containsKey(codigo)) {
            turmas.remove(codigo);

        } else {
            throw new ChaveInexistenteExeption(codigo);
        }

    }

    public LinkedList<Turma> getTurmas() {
        return new LinkedList<Turma>(turmas.values());
    }

    public void setTurmas(LinkedList<Turma> turmasTmp) {
        for (Turma turma : turmasTmp) {
            turmas.put(turma.getCodigo(), turma);
        }
    }

    private void lerDoDisco() {
        Turma turma1 = new Turma();
        turma1.setAno(2017);
        turma1.setCodigo(1);
        turma1.setNomeTurma("Engenharia de Software");
        turma1.setSemestre(2);
        Aluno aluno1 = new Aluno();
        aluno1.setMatricula(0);
        aluno1.setFone("");
        aluno1.setNome("");
        aluno1.setNota1(00);
        aluno1.setNota2(00);
        aluno1.setTurma(turma1);      

        try {
            turma1.incluirAluno(aluno1);
            this.incluirTurma(turma1);
        } catch (ChaveExistenteException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    private void lerAlunoDoDisco() {
 
        File arquivo = new File(FILE);
        if (arquivo.exists()) {
            try (FileInputStream fout = new FileInputStream(FILE)) {
                ObjectInputStream oos = new ObjectInputStream(fout);
                this.setTurmas((LinkedList<Turma>) oos.readObject());
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

  /*  public void gravarAlunoNoDisco() {
        Turma turma1 = new Turma();
        turma1.setAno(2017);
        turma1.setCodigo(1);
        turma1.setNomeTurma("Engenharia de Software");
        turma1.setSemestre(2);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(FILE, true);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            List<Turma> lista = this.getTurmas();
            oos.writeObject(lista);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(Instituicao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
*/

}
