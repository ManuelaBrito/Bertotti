package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

    public class Teste {

        @Test
        public void test() {
            Papelaria aquarela =  new Papelaria();
            aquarela.adicionarPapel(new Papel("Leve", "561"));
            assertEquals(aquarela.getPapel().size(), 1);

        }
    }

