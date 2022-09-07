package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.text.DecimalFormat;
import java.util.SortedSet;
import java.util.TreeSet;

public class Controller {
    public TextField sumOther;
    public TextField sumEur;
    public ComboBox comboBox;
    String currentCurrency;

    public Controller() {

    }
    public void initialize() {

        System.out.println("initialize "+ XMLParser.rates.isEmpty());
        ObservableList<String> items = FXCollections.observableArrayList();
        SortedSet<String> keys = new TreeSet<>(XMLParser.rates.keySet());
        items.addAll(keys );
       // System.out.println( XMLParser.rates.keySet());



        comboBox.setItems(items);
        comboBox.getSelectionModel().select(0);
        currentCurrency=  comboBox.getValue().toString();

    }

    public void calculate(KeyEvent keyEvent) {

        //Delete non digits chars
        if (!sumEur.getText().isEmpty()){
            sumEur.setText(sumEur.getText().replaceAll("[^\\d.]", ""));
            sumEur.positionCaret(sumEur.getText().length());
        }

    refresh();
            //sumOther.setText(String.valueOf(Integer.parseInt(sumEur.getText())*10));

    }

    public void changeCombo(ActionEvent actionEvent) {
        currentCurrency = comboBox.getValue().toString();
        refresh();
    }
    public void refresh(){
        //Exchange
        if (!sumEur.getText().isEmpty()){

            Double eur = Double.valueOf(sumEur.getText());
            System.out.println(eur);

            Double calc = eur*XMLParser.rates.get(currentCurrency);
            sumOther.setText(String.format("%.2f",calc)+" "+comboBox.getValue().toString());
        }
        else
            sumOther.setText("");



    }


//    public void calculate(KeyEvent keyEvent) {
//                System.out.println("hereeee");
//
//                Double eur = Double.valueOf(sumEur.getText());
//        System.out.println(eur);
//            //sumOther.setText(String.valueOf(Integer.parseInt(sumEur.getText())*10));
//
//    }

//    public void calculate(InputMethodEvent inputMethodEvent) {
//        System.out.println("hereeee");
//        if (sumEur!=null)
//        {
//            sumOther.setText(String.valueOf(Integer.parseInt(sumEur.getText())*10));
//        }
//    }
}
