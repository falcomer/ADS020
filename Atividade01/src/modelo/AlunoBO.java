package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Aluno;
import modelo.persistencia.AlunoDAO;
import modelo.persistencia.DadosException;

public class AlunoBO implements BO<Aluno> {

    @Override
    public List<Aluno> listar() throws NegocioException {
        List<Aluno> lista = new ArrayList<Aluno>();
        AlunoDAO dao = new AlunoDAO();

        try {
            lista = dao.listar();
            if (lista.isEmpty()) {
                throw new NegocioException("Nenhum aluno cadastrado!");
            }
        } catch (DadosException ex) {
            throw new NegocioException("Ocorreu um erro", ex);
        }

        return lista;
    }

    @Override
    public void incluir(Aluno entidade) throws NegocioException {
        validar(entidade);
        AlunoDAO dao = new AlunoDAO();

        try {
            dao.incluir(entidade);
        } catch (DadosException ex) {
            throw new NegocioException("Ocorreu um erro", ex);
        }
    }

    @Override
    public void alterar(Aluno entidade) throws NegocioException {
        consultar(entidade);
        validar(entidade);
        AlunoDAO dao = new AlunoDAO();

        try {
            dao.alterar(entidade);
        } catch (DadosException ex) {
            throw new NegocioException("Ocorreu um erro", ex);
        }
    }

    @Override
    public void excluir(Aluno entidade) throws NegocioException {
        consultar(entidade);
        AlunoDAO dao = new AlunoDAO();

        try {
            dao.excluir(entidade.getId());
        } catch (DadosException ex) {
            throw new NegocioException("Ocorreu um erro", ex);
        }
    }

    @Override
    public Aluno consultar(Aluno entidade) throws NegocioException {
        Aluno aluno = new Aluno();
        AlunoDAO dao = new AlunoDAO();

        try {
            aluno = dao.consultar(entidade.getId());
            if (aluno.getId() == 0) {
                throw new NegocioException("Aluno não encontrado!");
            }
        } catch (DadosException ex) {
            throw new NegocioException("Ocorreu um erro", ex);
        }

        return aluno;
    }

    @Override
    public void validar(Aluno entidade) throws NegocioException {
        if (entidade.getMatricula() == 0) {
            throw new NegocioException("Campo obrigatório MATRÍCULA não informado!");
        }

        if (entidade.getNome().isEmpty()) {
            throw new NegocioException("Campo obrigatório NOME não informado!");
        }
    }

}
