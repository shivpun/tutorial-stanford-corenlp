package com.tutorial.stanford.nlp.custom;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;

public class CustomLemmaAnnotator implements Annotator {

	HashMap<String, String> wordToLemma = new HashMap<String, String>();

	public CustomLemmaAnnotator() {
		List<String> lemmaEntries = Arrays.asList("जम@जन्म", "तारख@तारीख");// IOUtils.linesFromFile(lemmaFile);
		for (String lemmaEntry : lemmaEntries) {
			wordToLemma.put(lemmaEntry.split("@")[0], lemmaEntry.split("@")[1]);
		}
	}

	public void annotate(Annotation annotation) {
		for (CoreLabel token : annotation.get(CoreAnnotations.TokensAnnotation.class)) {
			String lemma = wordToLemma.getOrDefault(token.word(), token.word());
			token.set(CoreAnnotations.LemmaAnnotation.class, lemma);
		}
	}

	@Override
	public Set<Class<? extends CoreAnnotation>> requires() {
		return Collections.unmodifiableSet(new ArraySet<>(
				Arrays.asList(CoreAnnotations.TextAnnotation.class, CoreAnnotations.TokensAnnotation.class,
						CoreAnnotations.SentencesAnnotation.class, CoreAnnotations.PartOfSpeechAnnotation.class)));
	}

	@Override
	public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
		return Collections.singleton(CustomLemmaMain.CustomLemmaAnnotation.class);
	}
}