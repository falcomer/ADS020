package modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Aluno;

public class AlunoDAO implements DAO<Aluno> {

    @Override
    public List<Aluno> listar() throws DadosException {

        List<Aluno> lista = new ArrayList<Aluno>();
        try {
            String sql = "SELECT * FROM ALUNOS";
            Connection conexao = ConexaoBD.getConexao();
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultado.getInt(1));
                aluno.setMatricula(resultado.getInt(2));
                aluno.setNome(resultado.getString(3));
                lista.add(aluno);
            }
        } catch (SQLException ex) {
            throw new DadosException("Erro ao listar aluno!");
        }
        return lista;
    }

    @Override
    public void incluir(Aluno entidade) throws DadosException {
        try {
            String sql = "INSERT INTO ALUNOS(MATRICULA, NOME) VALUES(?,?)";
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, entidade.getMatricula());
            comando.setString(2, entidade.getNome());
            comando.executeUpdate();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao incluir aluno!");
        }
    }

    @Override
    public void alterar(Aluno entidade) throws DadosException {
        try {
            String sql = "UPDATE ALUNOS SET MATRICULA=?, NOME=? WHERE ID=?";
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, entidade.getMatricula());
            comando.setString(2, entidade.getNome());
            comando.setInt(3, entidade.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao alterar aluno!");
        }
    }

    @Override
    public void excluir(int id) throws DadosException {
        try {
            String sql = "DELETE FROM ALUNOS WHERE ID=?";
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            comando.executeUpdate();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao excluir aluno!");
        }
    }

    @Override
    public Aluno consultar(int id) throws DadosException {
        Aluno aluno = new Aluno();
        try {
            String sql = "SELECT * FROM ALUNOS WHERE ID=?";
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                aluno.setId(resultado.getInt(1));
                aluno.setMatricula(resultado.getInt(2));
                aluno.setNome(resultado.getString(3));
            }
        } catch (SQLException ex) {
            throw new DadosException("Erro ao consultar aluno!");
        }
        return aluno;
    }

}
