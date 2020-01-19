package com.worldtechpoints.bd_english_gramar;

import java.util.ArrayList;
import java.util.List;

public class CategoryClass {

    List<String>categoryValue;

    public CategoryClass() {
    }

    public List<String> englishCategory() {

        categoryValue = new ArrayList<String>();
        categoryValue.add("Narration");
        categoryValue.add("Tense");
        categoryValue.add("Voice");
        categoryValue.add("Vocabulary");
        return categoryValue;

    }
 public List<String> grammarCategory() {

        categoryValue = new ArrayList<String>();
        categoryValue.add("Sentence");
        categoryValue.add("Narration");
        categoryValue.add("Tense");
        categoryValue.add("Voice");
        categoryValue.add("Vocabulary");
        return categoryValue;

    }

    public List<String> compositionCategory() {

        categoryValue = new ArrayList<String>();
        categoryValue.add("Paragraph");
        categoryValue.add("Letter");
        categoryValue.add("Application");
        categoryValue.add("Email");
        categoryValue.add("CV");
        categoryValue.add("Essay");
        categoryValue.add("Chart");
        categoryValue.add("Story");
        categoryValue.add("Dialogue");
        return categoryValue;

    }

    public List<String> languageList() {

        categoryValue = new ArrayList<String>();
        categoryValue.add("Select Language");
        categoryValue.add("ARABIAN");
        categoryValue.add("BENGALI");
        categoryValue.add("ENGLISH");
        categoryValue.add("HINDI");
        categoryValue.add("URDU");
        return categoryValue;

    }
}
