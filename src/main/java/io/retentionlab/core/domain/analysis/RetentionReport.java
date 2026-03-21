package io.retentionlab.core.domain.analysis;

import io.retentionlab.core.domain.analysis.loop.LoopDensityResult;
import io.retentionlab.core.domain.analysis.opening.OpeningAnalysisResult;
import io.retentionlab.core.domain.analysis.risk.RiskResult;
import io.retentionlab.core.domain.analysis.tension.TensionResult;
import io.retentionlab.core.domain.model.ScriptStructure;
import java.util.ArrayList;
import java.util.List;

public record RetentionReport(
    ScriptStructure structure,
    OpeningAnalysisResult opening,
    LoopDensityResult loops,
    TensionResult tension,
    RiskResult risks,
    double finalScore
) {
    public List<String> getInsights(double riskThreshold) {
        List<String> insights = new ArrayList<>();

        if (finalScore < 40) {
            insights.add("Estrutura fraca: necessidade de revisão profunda");
        } else if (finalScore < 65) {
            insights.add("Estrutura mediana: pontos específicos podem ser melhorados");
        } else if (finalScore < 85) {
            insights.add("Estrutura forte: boa base narrativa");
        } else {
            insights.add("Estrutura otimizada: excelente potencial de retenção");
        }

        // Opening é 0–1 → converter para %
        if (opening.scoreAggregate().finalScore() * 100 < 60) {
            insights.add("Abertura pode ser mais forte: considere adicionar conflito ou curiosidade");
        }

        if (loops.openLoops() == 0) {
            insights.add("Sem loops abertos: a narrativa pode ficar linear demais");
        }

        if (!tension.hasPeak()) {
            insights.add("Sem pico de tensão claro: considere um clímax mais definido");
        }

        long riskBlocks = risks.riskBlockCount(riskThreshold);

        if (riskBlocks > structure.blocks().size() / 3) {
            insights.add("Muitos blocos de risco: identifique e reforce pontos fracos");
        }

        return insights;
    }
}