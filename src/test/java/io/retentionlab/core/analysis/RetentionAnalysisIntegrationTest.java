package io.retentionlab.core.analysis;

import io.retentionlab.core.domain.analysis.RetentionAnalysisService;
import io.retentionlab.core.domain.analysis.RetentionReport;
import io.retentionlab.core.domain.parameters.ScoringParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RetentionAnalysisIntegrationTest {

    @Autowired
    private RetentionAnalysisService analysisService;

    @Autowired
    private ScoringParameters parameters;

    @Test
    void shouldAnalyzeCompleteScript() {
        String script = loadFixture("sample-script.txt");

        RetentionReport report = analysisService.analyze(script);

        assertAll(
            () -> assertNotNull(report.structure()),
            () -> assertNotNull(report.opening()),
            () -> assertNotNull(report.loops()),
            () -> assertNotNull(report.tension()),
            () -> assertNotNull(report.risks()),
            () -> assertTrue(report.finalScore() >= 0 && report.finalScore() <= 100)
        );
    }

    @Test
    void shouldGenerateInsights() {
        String script = loadFixture("weak-script.txt");

        RetentionReport report = analysisService.analyze(script);

        List<String> insights =
            report.getInsights(parameters.riskThreshold());

        assertFalse(insights.isEmpty());
        assertTrue(insights.stream().anyMatch(i -> i.contains("fraca")));
    }

    private String loadFixture(String path) {
        try (var input = getClass().getClassLoader().getResourceAsStream(path)) {
            if (input == null) {
                throw new IllegalArgumentException("File not found: " + path);
            }
            return new String(input.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}