module br.com.ducosmo.creditbuddy {
    // Módulos que nosso projeto PRECISA para funcionar
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // <-- A LINHA QUE FALTAVA! Permite o uso de classes de banco de dados.
    requires org.controlsfx.controls; // Permissão para a biblioteca ControlsFX que adicionamos


    // Módulos que nosso projeto ABRE para serem acessados por outros
    opens br.com.ducosmo.creditbuddy to javafx.fxml;
    exports br.com.ducosmo.creditbuddy;

    // Abre o pacote de serviço para acesso, se necessário
    opens br.com.ducosmo.creditbuddy.service to javafx.fxml;
    exports br.com.ducosmo.creditbuddy.service;
}