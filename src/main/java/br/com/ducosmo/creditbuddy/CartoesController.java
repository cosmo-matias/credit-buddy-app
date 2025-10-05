package br.com.ducosmo.creditbuddy;

import br.com.ducosmo.creditbuddy.model.Cartao;
import br.com.ducosmo.creditbuddy.service.CartaoDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartoesController {

    @FXML private TableView<Cartao> tabelaCartoes;
    @FXML private TableColumn<Cartao, String> colunaNomeCartao;
    @FXML private TableColumn<Cartao, String> colunaBandeira;
    @FXML private TableColumn<Cartao, Integer> colunaVencimento;
    @FXML private TableColumn<Cartao, Integer> colunaFechamento;
    @FXML private TextField campoNomeCartao;
    @FXML private TextField campoBandeira;
    @FXML private Spinner<Integer> spinnerVencimento;
    @FXML private Spinner<Integer> spinnerFechamento;

    private final CartaoDAO cartaoDAO = new CartaoDAO();
    private Cartao cartaoSelecionado;

    @FXML
    public void initialize() {
        colunaNomeCartao.setCellValueFactory(new PropertyValueFactory<>("nomeCartao"));
        colunaBandeira.setCellValueFactory(new PropertyValueFactory<>("bandeira"));
        colunaVencimento.setCellValueFactory(new PropertyValueFactory<>("diaVencimento"));
        colunaFechamento.setCellValueFactory(new PropertyValueFactory<>("diaFechamento"));

        tabelaCartoes.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> selecionarCartao(newSelection)
        );
        atualizarTabela();
    }

    @FXML
    private void onSalvar() {
        String nome = campoNomeCartao.getText();
        if (nome == null || nome.trim().isEmpty()) {
            // Alerta de validação
            System.out.println("O nome do cartão é obrigatório.");
            return;
        }

        if (cartaoSelecionado == null) { // Novo cartão
            Cartao novoCartao = new Cartao();
            preencherDadosCartao(novoCartao);
            cartaoDAO.salvar(novoCartao);
        } else { // Atualizar cartão
            preencherDadosCartao(cartaoSelecionado);
            cartaoDAO.atualizar(cartaoSelecionado);
        }
        limparCampos();
        atualizarTabela();
    }

    @FXML
    private void onExcluir() {
        if (cartaoSelecionado != null) {
            cartaoDAO.excluir(cartaoSelecionado.getId());
            limparCampos();
            atualizarTabela();
        }
    }

    @FXML
    private void onNovo() {
        limparCampos();
    }

    private void preencherDadosCartao(Cartao cartao) {
        cartao.setNomeCartao(campoNomeCartao.getText());
        cartao.setBandeira(campoBandeira.getText());
        cartao.setDiaVencimento(spinnerVencimento.getValue());
        cartao.setDiaFechamento(spinnerFechamento.getValue());
    }

    private void selecionarCartao(Cartao cartao) {
        this.cartaoSelecionado = cartao;
        if (cartao != null) {
            campoNomeCartao.setText(cartao.getNomeCartao());
            campoBandeira.setText(cartao.getBandeira());
            spinnerVencimento.getValueFactory().setValue(cartao.getDiaVencimento());
            spinnerFechamento.getValueFactory().setValue(cartao.getDiaFechamento());
        }
    }

    private void atualizarTabela() {
        tabelaCartoes.setItems(FXCollections.observableArrayList(cartaoDAO.listarTodos()));
    }

    private void limparCampos() {
        tabelaCartoes.getSelectionModel().clearSelection();
        cartaoSelecionado = null;
        campoNomeCartao.clear();
        campoBandeira.clear();
        spinnerVencimento.getValueFactory().setValue(1);
        spinnerFechamento.getValueFactory().setValue(1);
    }
}