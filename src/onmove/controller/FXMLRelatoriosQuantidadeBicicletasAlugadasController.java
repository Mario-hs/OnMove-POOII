package onmove.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import onmove.model.dao.ItemDeAluguelDAO;
import onmove.model.database.Database;
import onmove.model.database.DatabaseFactory;
import onmove.model.domain.Aluguel;
import onmove.model.domain.ItemDeAluguel;

public class FXMLRelatoriosQuantidadeBicicletasAlugadasController implements Initializable {
    @FXML
    private TableView<ItemDeAluguel> tableViewBicicletas;
    @FXML
    private TableColumn<ItemDeAluguel, Integer> tableColumnBicicletaCodigo;
    @FXML
    private TableColumn<ItemDeAluguel, String> tableColumnBicicletaNome;
    @FXML
    private TableColumn<Aluguel, Double> tableColumnBicicletaPreco;
    @FXML
    private TableColumn<ItemDeAluguel, Integer> tableColumnBicicletaQuantidade;
    
    @FXML
    private Button buttonImprimir;
    
    private List<ItemDeAluguel> listItensAlugados;
    private ObservableList<ItemDeAluguel> observableListAlugados;
    
    // Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemDeAluguelDAO.setConnection(connection);
        
        carregarTableViewAlugueis();
    }    
    public void carregarTableViewAlugueis(){
        tableColumnBicicletaCodigo.setCellValueFactory(new PropertyValueFactory<>("cdItemDeAluguel"));
        tableColumnBicicletaNome.setCellValueFactory(new PropertyValueFactory<>("bicicleta"));
        tableColumnBicicletaPreco.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnBicicletaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        listItensAlugados = itemDeAluguelDAO.listar();
        
        observableListAlugados = FXCollections.observableList(listItensAlugados);
        tableViewBicicletas.setItems(observableListAlugados);
    }
    
    public void handleImprimir() throws JRException{
        URL url = getClass().getResource("/onmove/relatorios/FXMLRelatoriosQuantidadeBicicletasAlugadas.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
}
