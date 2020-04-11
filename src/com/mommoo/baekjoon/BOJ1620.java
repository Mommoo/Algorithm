package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BOJ1620 {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int pokemonCount = Integer.parseInt(tokenizer.nextToken());
        int questionCount = Integer.parseInt(tokenizer.nextToken());

        PokemonDictionary pokemonDictionary = new PokemonDictionary();
        IntStream.rangeClosed(1, pokemonCount)
                 .mapToObj(code -> createPokemon(code, reader))
                 .filter(Objects::nonNull)
                 .forEach(pokemonDictionary::register);

        while (questionCount -- > 0) {
            String question = reader.readLine();
            boolean isNumberCode = '0' <= question.charAt(0) && question.charAt(0) <= '9';
            Object answer
                    = isNumberCode ? pokemonDictionary.findPokemonName(Integer.parseInt(question)) :
                    pokemonDictionary.findNumberCode(question);

            write(answer);
        }

        System.out.println(BUILDER);
    }

    private static Pokemon createPokemon(int numberCode, BufferedReader reader) {
        try {
            return new Pokemon(numberCode, reader.readLine());
        } catch (IOException e) {
            return null;
        }
    }

    private static void write(Object obj) {
        BUILDER.append(obj).append("\n");
    }

    private static class PokemonDictionary {
        private final Map<Integer, String> nameFinder = new HashMap<>();
        private final Map<String, Integer> codeFinder = new HashMap<>();

        public void register(Pokemon pokemon) {
            nameFinder.put(pokemon.numberCode, pokemon.name);
            codeFinder.put(pokemon.name, pokemon.numberCode);
        }

        public int findNumberCode(String pokemonName) {
            return codeFinder.get(pokemonName);
        }

        public String findPokemonName(int numberCode) {
            return nameFinder.get(numberCode);
        }
    }

    private static class Pokemon {
        private final int numberCode;
        private final String name;

        public Pokemon(int numberCode, String name) {
            this.numberCode = numberCode;
            this.name = name;
        }
    }
}
