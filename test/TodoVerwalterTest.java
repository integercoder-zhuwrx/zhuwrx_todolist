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

    @Test
    void ioSpecs03() throws Exception {
        testWithIOSpecs("""
                <leer
                <3
                >3
                <aufg
                <OOP-Aufgabe 1
                >true
                <ende""");
    }

    @Test
    void ioSpecs04() throws Exception {
        testWithIOSpecs("""
                <leer
                <4
                >4
                <aufg
                <OOP-Aufgabe 1
                >true
                <ausg
                <5
                >[ ] OOP-Aufgabe 1
                >Erledigt: 0%
                >Erledigt: [     ]
                <ende""");
    }

    @Test
    void ioSpecs05() throws Exception {
        testWithIOSpecs("""
                <leer
                <4
                >4
                <aufg
                <OOP-Aufgabe 1
                >true
                <erl
                <0
                >true
                <ausg
                <5
                >[x] OOP-Aufgabe 1
                >Erledigt: 100%
                >Erledigt: [=====]
                <ende""");
    }

    @Test
    void ioSpecs06() throws Exception {
        testWithIOSpecs("""
                <leer
                <4
                >4
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <erl
                <1
                >true
                <ausg
                <2
                >[ ] OOP-Aufgabe 1
                >[x] OOP-Aufgabe 2
                >Erledigt: 50%
                >Erledigt: [= ]
                <ende""");
    }

    @Test
    void ioSpecs07() throws Exception {
        testWithIOSpecs("""
                <leer
                <4
                >4
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <erl
                <0
                >true
                <erl
                <1
                >true
                <ausg
                <2
                >[x] OOP-Aufgabe 1
                >[x] OOP-Aufgabe 2
                >Erledigt: 100%
                >Erledigt: [==]
                <ende""");
    }

    @Test
    void ioSpecs08() throws Exception {
        testWithIOSpecs("""
                <leer
                <2
                >2
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <aufg
                <OOP-Aufgabe 3
                >false
                <ende""");
    }

    @Test
    void ioSpecs09() throws Exception {
        testWithIOSpecs("""
                <leer
                <2
                >2
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <aufg
                <OOP-Aufgabe 3
                >false
                                
                <ausg
                <10
                >[ ] OOP-Aufgabe 1
                >[ ] OOP-Aufgabe 2
                >Erledigt: 0%
                >Erledigt: [          ]
                                
                <ende""");
    }

    @Test
    void ioSpecs10() throws Exception {
        testWithIOSpecs("""
                <leer
                <5
                >5
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <aufg
                <MGDI-Aufgabe 1
                >true
                                
                <erl
                <2
                >true
                                
                <aufg
                <MGDI-Aufgabe 2
                >true
                <anz0
                >4
                                
                <ende""");
    }

    @Test
    void ioSpecs11() throws Exception {
        testWithIOSpecs("""
                <leer
                <5
                >5
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <aufg
                <MGDI-Aufgabe 1
                >true
                                
                <erl
                <2
                >true
                                
                <aufg
                <MGDI-Aufgabe 2
                >true
                <anz1
                >1
                                
                <ende""");
    }

    @Test
    void ioSpecs12() throws Exception {
        testWithIOSpecs("""
                <leer
                <5
                >5
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <aufg
                <MGDI-Aufgabe 1
                >true
                                
                <erl
                <2
                >true
                <erl
                <2
                >true
                                
                                
                <aufg
                <MGDI-Aufgabe 2
                >true
                <ausg
                <10
                >[ ] OOP-Aufgabe 1
                >[ ] OOP-Aufgabe 2
                >[x] MGDI-Aufgabe 1
                >[ ] MGDI-Aufgabe 2
                >Erledigt: 25%
                >Erledigt: [==        ]
                                
                <ende""");
    }

    @Test
    void ioSpecs13() throws Exception {
        testWithIOSpecs("""
                <leer
                <5
                >5
                <aufg
                <OOP-Aufgabe 1
                >true
                <aufg
                <OOP-Aufgabe 2
                >true
                <aufg
                <OOP-Aufgabe 3
                >true
                <aufg
                <OOP-Aufgabe 4
                >true
                <entf
                <0
                <ausg
                <3
                >[ ] OOP-Aufgabe 2
                >[ ] OOP-Aufgabe 3
                >[ ] OOP-Aufgabe 4
                >Erledigt: 0%
                >Erledigt: [   ]
                                
                <ende""");
    }

    void testWithIOSpecs(String ioSpecs) throws Exception {
        Thread programThread = new Thread(() -> {
            try {
                sleep();
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
                    sleep();
                    if (line.startsWith("<")) {
                        String input = line.substring(1);
                        sie.writeLine(input);
                        sleep();
                    } else if (line.startsWith(">")) {
                        String expectedOutput = line.substring(1);
                        String output = sor.takeLine();
                        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
                        sleep();
                    }
                }
            }
        }
        programThread.join();
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(20);
    }
}