package io.retentionlab.core.domain.segmentation;

import io.retentionlab.core.domain.model.ScriptStructure;
import io.retentionlab.core.domain.model.segmentation.StructuralSegmentationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructuralSegmentationServiceTest {

    @Test
    void shouldGenerateCorrectScriptStructure() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 300; i++) {
            builder.append("word ");
        }

        StructuralSegmentationService service = new StructuralSegmentationService();

        ScriptStructure structure = service.analyse(builder.toString());

        assertEquals(300, structure.totalWords());
        assertEquals(2.0, structure.estimatedDurationMinutes());
        assertEquals(2, structure.blocks().size());
    }
}
