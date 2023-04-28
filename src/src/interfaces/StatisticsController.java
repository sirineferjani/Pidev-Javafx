/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatisticsController implements Initializable {

      @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private AnchorPane main_form;
    
     Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
     public Connection connect() {
        String USER = "root";
        String databaseName="pidev";
        String PASSWORD = "";
        String URL = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.jbdc.Driver");
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cnx;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart();
    }    
    
    public void chart(){
        String chartSql = "SELECT Date , SUM(Total) FROM statistics GROUP BY date ";
          cnx = connect();
          
          try{
              XYChart.Series chartData = new XYChart.Series();
              st = cnx.prepareStatement(chartSql);
              result = st.executeQuery();
              while(result.next()){
                  chartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
              }
              barChart.getData().add(chartData);
          }catch(Exception e){
              e.printStackTrace();
          }
        
        
    }
}
