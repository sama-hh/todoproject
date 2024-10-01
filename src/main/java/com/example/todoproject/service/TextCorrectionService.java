package com.example.todoproject.service;

import org.languagetool.JLanguageTool;
import org.languagetool.Languages;

import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TextCorrectionService {
    private final JLanguageTool languageTool;

    public TextCorrectionService() {
        this.languageTool = new JLanguageTool(Languages.getLanguageForShortCode("en-GB"));
    }

    public String correctText(String text) {
        try {
            List<RuleMatch> matches = languageTool.check(text); // Get the list of rule matches
            for (RuleMatch match : matches) {
                // Get the suggested replacements for the match
                List<String> suggestions = match.getSuggestedReplacements();
                if (!suggestions.isEmpty()) {
                    // Get the incorrect word or phrase
                    String incorrectWord = text.substring(match.getFromPos(), match.getToPos());

                    // Replace the first suggested word in the original text
                    text = text.replace(incorrectWord, suggestions.get(0));
                }
            }
        } catch (Exception e) {
            // Handle any exceptions appropriately
            e.printStackTrace();
        }
        return text.trim();
    }
}