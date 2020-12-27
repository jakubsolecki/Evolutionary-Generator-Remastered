package pl.jakubsolecki.model;


import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.Arrays;

@RequiredArgsConstructor
public class Genotype {

    private final int SIZE = 32;
    private final int genes[] = new int[SIZE];
    private final int GENE_TYPES_COUNT;
}
