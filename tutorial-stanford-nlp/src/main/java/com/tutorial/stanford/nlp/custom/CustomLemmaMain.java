package com.tutorial.stanford.nlp.custom;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class CustomLemmaMain {
	
	public static String text = "जम तारख";
	
	public static class CustomLemmaAnnotation implements CoreAnnotation<String> {
		@Override
		public Class<String> getType() {
			return String.class;
		}
	}

	public static void main(String[] args) {
		Properties props = new Properties();
	    // set the list of annotators to run
		props.put("customAnnotatorClass.custom.lemma", "com.tutorial.stanford.nlp.custom.CustomLemmaAnnotator");
	    props.setProperty("annotators", "tokenize,ssplit,pos,custom.lemma");
	    // build pipeline
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    // create a document object
	    CoreDocument document = pipeline.processToCoreDocument(text);
	    // display tokens
	    List<CoreLabel> drs = document.annotation().get(TokensAnnotation.class);
	    for(CoreLabel dr: drs) {
	    	System.out.println(dr.originalText()+ "| " + dr.get(CustomLemmaAnnotation.class));
	    }
	}
}
