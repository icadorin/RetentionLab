package io.retentionlab.core.domain.analysis.tension;

import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.List;

public interface TensionExtractor {
    List<Double> extractTension(ScriptStructure structure, String fullText);
}