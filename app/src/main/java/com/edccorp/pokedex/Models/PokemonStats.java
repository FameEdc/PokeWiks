package com.edccorp.pokedex.Models;

public class PokemonStats {

    private Stat stat = new Stat();
    private int base_stat;

    public String getName() {
        return stat.name;
    }

    public int getValue() {
        return base_stat;
    }

    private static class Stat {
        private String name;
    }
}
