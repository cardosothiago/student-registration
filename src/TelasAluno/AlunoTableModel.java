package TelasAluno;
import edu.catolicasc.academico.entidades.Aluno;
import edu.catolicasc.academico.entidades.Instituicao;
import edu.catolicasc.academico.entidades.Turma;
  import java.util.ArrayList;
import java.util.LinkedList;
  import java.util.List;
  import javax.swing.table.AbstractTableModel;
   
   
  public class AlunoTableModel extends AbstractTableModel {
      
      private List<Aluno> alunos;
       private String[] colunas = new String[]{  
          "Matricula","Nome", "Fone","Media","Nota 1","Nota2","Turma"};
      
      /** Creates a new instance of DevmediaTableModel */
      public AlunoTableModel(List<Aluno> aluno) {
          this.alunos = aluno;
      }
      
      public AlunoTableModel(){
          alunos = new LinkedList();
          Instituicao instituicao = Instituicao.getInstance();
          LinkedList<Turma> turmas = instituicao.getTurmas();
          for (Turma turma : turmas) {
              LinkedList<Aluno> tmpAlunos = turma.getAlunos();
              for (Aluno tmpAluno : tmpAlunos) {
                  alunos.add(tmpAluno);
              }
          }
      }
   
      public int getRowCount() {
          return alunos.size();
      }
   
      public int getColumnCount() {
          return colunas.length;
      }
      
      @Override
      public String getColumnName(int columnIndex){
        return colunas[columnIndex];
      }    
      
       @Override  
      public Class<?> getColumnClass(int columnIndex) {  
          return String.class;  
      }
    
      
      public void setValueAt(Aluno aValue, int rowIndex) {  
          Aluno aluno = alunos.get(rowIndex);
          
          aluno.setMatricula(aValue.getMatricula());
          aluno.setNome(aValue.getNome());
          aluno.setFone(aValue.getFone());        
    
          fireTableCellUpdated(rowIndex, 0);  // matricula
          fireTableCellUpdated(rowIndex, 1);  // nome
          fireTableCellUpdated(rowIndex, 2);  // fone
          fireTableCellUpdated(rowIndex, 3);  // media
          fireTableCellUpdated(rowIndex, 4);  // nota1
          fireTableCellUpdated(rowIndex, 5);  // nota2
          fireTableCellUpdated(rowIndex, 6);  // turma
    
      }
      
      @Override  
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
        Aluno aluno = alunos.get(rowIndex);
    
       switch (columnIndex) {
           case 0:  
               aluno.setMatricula(Integer.parseInt(aValue.toString()));
               break;
           case 1:  
               aluno.setNome(aValue.toString());             
               break;
           case 2:  
               aluno.setFone(aValue.toString());      
               break;
           case 3:  
               break;
           case 4:  
               aluno.setNota1(Float.parseFloat(aValue.toString()));      
               break;
           case 5:  
               aluno.setNota2(Float.parseFloat(aValue.toString()));      
               break;
           default:  
               System.err.println("Índice da coluna inválido");
       }  
       fireTableCellUpdated(rowIndex, columnIndex);  
       }      
      
   
      public Object getValueAt(int rowIndex, int columnIndex) {
          Aluno alunoSelecionado = alunos.get(rowIndex);
          String valueObject = null;

          switch(columnIndex){
              case 0:  valueObject = "" + alunoSelecionado.getMatricula(); break;
              case 1:  valueObject = alunoSelecionado.getNome(); break;
              case 2 : valueObject = alunoSelecionado.getFone(); break;
              case 3 : valueObject = "" + alunoSelecionado.getMedia(); break;
              case 4 : valueObject = "" + alunoSelecionado.getNota1(); break;
              case 5 : valueObject = "" + alunoSelecionado.getNota2(); break;
              case 6 : valueObject = alunoSelecionado.getTurma().getNomeTurma(); break;
              default: System.err.println("Índice inválido para propriedade do bean Usuario.class");
          }
          
          return valueObject;
      }
      
      @Override  
      public boolean isCellEditable(int rowIndex, int columnIndex) {  
          return false;  
      }  
    
    
      public Aluno getAluno(int indiceLinha) {  
          return alunos.get(indiceLinha);  
      }  
      
      public void addAluno(Aluno u) {      
          alunos.add(u);  
    
    
          int ultimoIndice = getRowCount() - 1;  
    
          fireTableRowsInserted(ultimoIndice, ultimoIndice);  
      }  
    
      
      public void removeAluno(int indiceLinha) {  
          alunos.remove(indiceLinha);  
    
          fireTableRowsDeleted(indiceLinha, indiceLinha);  
      }  
      
      
      public void addListaDeAlunos(List<Aluno> novosAlunos) {  
          
          int tamanhoAntigo = getRowCount();      
          alunos.addAll(novosAlunos);    
          fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);  
      }  
      
      public void limpar() {  
          alunos.clear();    
          fireTableDataChanged();  
      }  
    
      public boolean isEmpty() {  
          return alunos.isEmpty();  
      }  
      
  }