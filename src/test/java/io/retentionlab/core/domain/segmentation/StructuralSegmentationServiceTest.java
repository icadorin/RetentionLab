package io.retentionlab.core.domain.segmentation;

import io.retentionlab.core.domain.model.ScriptStructure;
import io.retentionlab.core.domain.model.segmentation.StructuralSegmentationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructuralSegmentationServiceTest {

    private final StructuralSegmentationService service =
        new StructuralSegmentationService();

    @Nested
    class Analyze {

        private static final double DELTA = 0.0001;

        @Test
        void givenNullText_whenAnalyze_thenReturnEmptyStructure() {

            String text = null;

            ScriptStructure structure = service.analyze(text);

            assertEquals(0, structure.totalWords());
            assertEquals(0.0, structure.estimatedDurationMinutes());
            assertEquals(0, structure.blocks().size());
        }

        @Test
        void givenBlankText_whenAnalyze_thenReturnEmptyStructure() {

            String text = "   ";

            ScriptStructure structure = service.analyze(text);

            assertEquals(0, structure.totalWords());
            assertEquals(0.0, structure.estimatedDurationMinutes());
            assertEquals(0, structure.blocks().size());
        }

        @Test
        void given100Words_whenAnalyze_thenCreateSinglePartialBlock() {

            String text = generateWords(100);

            ScriptStructure structure = service.analyze(text);

            assertEquals(100, structure.totalWords());
            assertEquals(1, structure.blocks().size());
            assertEquals(100, structure.blocks().getFirst().wordCount());
        }

        @Test
        void given150Words_whenAnalyze_thenCreateExactlyOneBlock() {

            String text = generateWords(150);

            ScriptStructure structure = service.analyze(text);

            assertEquals(150, structure.totalWords());
            assertEquals(1.0, structure.estimatedDurationMinutes(), DELTA);
            assertEquals(1, structure.blocks().size());
        }

        @Test
        void given151Words_whenAnalyze_thenCreateTwoBlocks() {

            String text = generateWords(151);

            ScriptStructure structure = service.analyze(text);

            assertEquals(2, structure.blocks().size());
            assertEquals(150, structure.blocks().get(0).wordCount());
            assertEquals(1, structure.blocks().get(1).wordCount());
        }

        @Test
        void given300Words_whenAnalyze_thenCreateTwoFullBlocks() {

            String text = generateWords(300);

            ScriptStructure structure = service.analyze(text);

            assertEquals(300, structure.totalWords());
            assertEquals(2.0, structure.estimatedDurationMinutes(), DELTA);
            assertEquals(2, structure.blocks().size());
            assertEquals(150, structure.blocks().get(0).wordCount());
            assertEquals(150, structure.blocks().get(1).wordCount());
        }

        private String generateWords(int amount) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < amount; i++) {
                builder.append("word ");
            }
            return builder.toString();
        }
    }
}
