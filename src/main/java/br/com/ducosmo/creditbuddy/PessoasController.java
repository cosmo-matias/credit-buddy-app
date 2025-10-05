package br.com.ducosmo.creditbuddy;

import br.com.ducosmo.creditbuddy.model.Pessoa;
import br.com.ducosmo.creditbuddy.service.PessoaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PessoasController {

    @FXML private TableView<Pessoa> tabelaPessoas;
    @FXML private TableColumn<Pessoa, String> colunaNome;
    @FXML private TableColumn<Pessoa, String> colunaCelular;
    @FXML private TextField campoNome;
    @FXML private TextField campoCelular;
    @FXML private Button botaoSalvar;
    @FXML private Button botaoNovo;
    @FXML private Button botaoExcluir;

    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private Pessoa pessoaSelecionada;

    @FXML
    public void initialize() {
        // Configura as colunas da tabela para saber de onde pegar os dados do objeto Pessoa
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));

        // Adiciona um "ouvinte" para quando o usuário clicar em uma linha da tabela
        tabelaPessoas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarPessoa(newValue)
        );

        atualizarTabela();
    }

    @FXML
    private void onSalvar() {
        String nome = campoNome.getText();
        String celular = campoCelular.getText();

        if (nome.isEmpty()) {
            // Futuramente, usar uma janela de alerta
            System.out.println("O nome é obrigatório!");
            return;
        }

        if (pessoaSelecionada == null) {
            // Criar nova pessoa
            Pessoa novaPessoa = new Pessoa();
            novaPessoa.setNome(nome);
            novaPessoa.setCelular(celular);
            pessoaDAO.salvar(novaPessoa);
        } else {
            // Atualizar pessoa existente
            pessoaSelecionada.setNome(nome);
            pessoaSelecionada.setCelular(celular);
            pessoaDAO.atualizar(pessoaSelecionada);
        }

        limparCampos();
        atualizarTabela();
    }

    @FXML
    private void onExcluir() {
        if (pessoaSelecionada != null) {
            pessoaDAO.excluir(pessoaSelecionada.getId());
            limparCampos();
            atualizarTabela();
        } else {
            System.out.println("Selecione uma pessoa para excluir.");
        }
    }

    @FXML
    private void onNovo() {
        limparCampos();
    }

    private void selecionarPessoa(Pessoa pessoa) {
        pessoaSelecionada = pessoa;
        if (pessoa != null) {
            campoNome.setText(pessoa.getNome());
            campoCelular.setText(pessoa.getCelular());
        } else {
            limparCampos();
        }
    }

    private void atualizarTabela() {
        tabelaPessoas.setItems(FXCollections.observableArrayList(pessoaDAO.listarTodos()));
    }

    private void limparCampos() {
        tabelaPessoas.getSelectionModel().clearSelection();
        pessoaSelecionada = null;
        campoNome.clear();
        campoCelular.clear();
    }
}