package it.polito.tdp.spellchecker.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	LinkedList <String> dizionario = new LinkedList <>();
	List <RichWord> paroleChecked;
	List <String> inputTextList;
	int cnt;
	
	public Dictionary() {
		paroleChecked = new LinkedList <>();
		inputTextList = new LinkedList <>();
		cnt = 0;
	}
	
	public void loadDictionary(String language) {
		try {
			if(language.compareTo("Italian") == 0) {
				FileReader fr = new FileReader ("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			}
			if(language.compareTo("English") == 0) {
				FileReader fr = new FileReader("rsc/English");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				fr.close();
			}
		} catch (IOException e){
			System.out.println("Errore nella lettura del file");
		}
	}
	
	public List <RichWord> spellCheckText (List <String> inputTextList) {
		
		RichWord rc;
		for(String s : inputTextList) {
			rc = new RichWord(s);
			if(dizionario.contains(s)) {
				rc.setCorrect(true);
			} else {
				rc.setCorrect(false);
			}
			paroleChecked.add(rc);
		}
		return paroleChecked;
	}
	
	public List <String> getInputTextList (String[] vtemp){
		for(int i = 0; i < vtemp.length; i++) {
        	inputTextList.add(vtemp[i]);
        }
		return inputTextList;
	}
	
	public List <RichWord> getWrongWords (List <RichWord> list) {
		LinkedList <RichWord> paroleErrate = new LinkedList <>();
		for (RichWord r : list) {
        	if(r.isCorrect() == false) {
        	   paroleErrate.add(r);
        	   cnt ++;
        	}
        }
		return paroleErrate;
	}
	
	public String getString (List <RichWord> list) {
		String s = "";
        for (RichWord r : list) {
        	s += r.getWord() + "\n";
        }
        return s;
	}
	
	public int getNumberWrongWord () {
		return cnt;
	}

}
