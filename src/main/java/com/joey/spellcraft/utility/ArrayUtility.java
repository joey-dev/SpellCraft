package com.joey.spellcraft.utility;

import com.joey.spellcraft.spells.Spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtility {
    public static Integer[] addItemToArray(Integer[] array, int item) {
        List<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(array));
        arrayList.add(item);

        return arrayList.toArray(array);
    }
    public static Spell[] addItemToArray(Spell[] array, Spell item) {
        List<Spell> arrayList = new ArrayList<Spell>(Arrays.asList(array));
        arrayList.add(item);

        return arrayList.toArray(array);
    }
}
