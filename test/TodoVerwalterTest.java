import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TodoVerwalterTest {
    @Test
    void ioSpecs01() throws Exception {
        testWithIOSpecs("""
                <leer
                <9
                >9
                <ende""");
    }

    @Test
    void ioSpecs02() throws Exception {
        testWithIOSpecs("""
                <leer
                <3
                >3
                <ausg
                <4
                >Erledigt: 0%
                >Erledigt: [    ]
                <ende""");
    }

    void testWithIOSpecs(String ioSpecs) throws Exception {
        Thread programThread = new Thread(() -> {
            try {
                Thread.sleep(100);
                TodoVerwalter.main(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        programThread.start();
        try (var sie = new SystemInFeeder()) {
            try (var sor = new SystemOutRedirector()) {
                List<String> lines = ioSpecs.lines().toList();
                for (String line : lines) {
                    Thread.sleep(100);
                    if (line.startsWith("<")) {
                        String input = line.substring(1);
                        sie.writeLine(input);
                        Thread.sleep(100);
                    } else if (line.startsWith(">")) {
                        String expectedOutput = line.substring(1);
                        String output = sor.takeOutputText();
                        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
                        Thread.sleep(100);
                    }
                }
            }
        }
        programThread.join();
    }
}