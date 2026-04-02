package io.retentionlab.domain.model;

import lombok.Getter;

@Getter
public class ScriptStructure {

    private final int totalWords;

    public ScriptStructure(int totalWords) {
        this.totalWords = totalWords;
    }

}