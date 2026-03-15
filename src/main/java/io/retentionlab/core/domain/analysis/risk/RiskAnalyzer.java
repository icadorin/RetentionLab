package io.retentionlab.core.domain.analysis.risk;

import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.List;

public class RiskAnalyzer {

    public RiskResult analyze(
        ScriptStructure structure,
        List<Double> tensionValues
    ) {

        if (tensionValues.size() != structure.blocks().size()) {
            throw new IllegalArgumentException(
                "Tension values must match blocks count"
            );
        }

        return new RiskResult(tensionValues);
    }
}