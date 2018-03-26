/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import it.polito.tdp.spellchecker.model.Dictionary;

public class SpellCheckerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="insertLanguage"
    private ComboBox<String> insertLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="testoInserito"
    private TextArea testoInserito; // Value injected by FXMLLoader

    @FXML // fx:id="spellCheck"
    private Button spellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="testoDaInserire"
    private TextArea testoDaInserire; // Value injected by FXMLLoader

    @FXML // fx:id="numeroErrori"
    private Label numeroErrori; // Value injected by FXMLLoader

    @FXML // fx:id="clearText"
    private Button clearText; // Value injected by FXMLLoader

    @FXML // fx:id="tempoLavoro"
    private Label tempoLavoro; // Value injected by FXMLLoader

    @FXML
    void handleClear(ActionEvent event) {

    }

    private String language;
    
    @FXML
    void handleInsertLanguage(ActionEvent event) {
    	language = insertLanguage.getValue();
    	testoInserito.setDisable(false);
    }

    @FXML
    void handleSubmission(ActionEvent event) {
    	long startTime = System.nanoTime();
    	String[] vtemp = testoInserito.getText().split(" ");        
        Dictionary dc = new Dictionary();
        dc.loadDictionary(language);
        String s = dc.getString(dc.getWrongWords(dc.spellCheckText(dc.getInputTextList(vtemp))));        
        testoDaInserire.setText(String.format("%s", s));
        numeroErrori.setText(String.format("the text contains %d errors", dc.getNumberWrongWord()));
        long estimateTime = System.nanoTime() - startTime;
        String t = "";
    	t += estimateTime;
        tempoLavoro.setText(String.format("Spell check completed in %s seconds", t));    
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert insertLanguage != null : "fx:id=\"insertLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert testoInserito != null : "fx:id=\"testoInserito\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert spellCheck != null : "fx:id=\"spellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert testoDaInserire != null : "fx:id=\"testoDaInserire\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert numeroErrori != null : "fx:id=\"numeroErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert clearText != null : "fx:id=\"clearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert tempoLavoro != null : "fx:id=\"tempoLavoro\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    public void setModel (Dictionary model) {
    	 this.insertLanguage.getItems().addAll("Italian", "English");
    }
}
