package learn.nyx.com.gameanalyser.Structure;

import java.util.ArrayList;


public class Data {

    private ArrayList<String> SITUATIONS;
    // Situations can only have an even number of elements, each pair being opposite situations
    // Meaning that if one player is in a situation, the other is in the other one
    private ArrayList<String> CHARACTERS;
    private ArrayList<ArrayList<String>> BASE_OPTIONS;
    private ArrayList<ArrayList<ArrayList<String>>> OPTIONS;

    public Data() {
        this.SITUATIONS = new ArrayList<String>();
        this.CHARACTERS = new ArrayList<String>();
        this.OPTIONS = new ArrayList<ArrayList<ArrayList<String>>>();
        this.BASE_OPTIONS = new ArrayList<ArrayList<String>>();
    }

    public void addSituation(Situation s) {
        this.SITUATIONS.add(s.toString());
        this.OPTIONS.add(getOptionList());
        this.BASE_OPTIONS.add(new ArrayList<String>());
    }

    public void addCharacter(String s) {
        this.CHARACTERS.add(s);
        for (ArrayList<ArrayList<String>> l : this.OPTIONS) {
            l.add(new ArrayList<String>());
        }
    }

    /**
     * This is made for creating enough space for options to be added, one empty array list per character
     * @return
     */
    private ArrayList<ArrayList<String>> getOptionList() {
        ArrayList<ArrayList<String>> arlist = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < this.CHARACTERS.size(); i++) {
            arlist.add(new ArrayList<String>());
        }
        return arlist;
    }

    public void addOption(Option o, Situation s, Character c) {
        this.OPTIONS.get(this.SITUATIONS.indexOf(s.toString())).get(this.CHARACTERS.indexOf(c.toString())).add(o.toString());
    }

    public void addOption(Option o, Situation s, String c) {
        this.OPTIONS.get(this.SITUATIONS.indexOf(s.toString())).get(this.CHARACTERS.indexOf(c)).add(o.toString());
    }

    public void addOption(String o, Situation s, String c) {
        this.OPTIONS.get(this.SITUATIONS.indexOf(s.toString())).get(this.CHARACTERS.indexOf(c)).add(o);
    }

    public void addOption(Option o, String s, String c) {
        this.OPTIONS.get(this.SITUATIONS.indexOf(s)).get(this.CHARACTERS.indexOf(c)).add(o.toString());
    }

    public void addOption(String o, String s, String c) {
        this.OPTIONS.get(this.SITUATIONS.indexOf(s)).get(this.CHARACTERS.indexOf(c)).add(o);
    }

    public void addBaseOption(Option o, Situation s) {
        this.BASE_OPTIONS.get(this.SITUATIONS.indexOf(s.toString())).add(o.toString());
    }

    public void addBaseOption(String o, Situation s) {
        this.BASE_OPTIONS.get(this.SITUATIONS.indexOf(s.toString())).add(o);
    }

    public ArrayList<Situation> getSituations() {
        ArrayList<Situation> arlist = new ArrayList<Situation>();
        for (String s : this.SITUATIONS) {
            arlist.add(new Situation(s));
        }
        return arlist;
    }

    public ArrayList<String> getCharacters() {
        return this.CHARACTERS;
    }

    public ArrayList<Option> getOptionsForSituation (Situation s, Character c) {
        return this.getOptionsForSituation(s.toString(), c);
    }

    public ArrayList<Option> getOptionsForSituation (String s, Character c) {
        ArrayList<Option> arlist = new ArrayList<Option>();
        for (String str : BASE_OPTIONS.get(this.SITUATIONS.indexOf(s))) {
            arlist.add(new Option(str));
        }
        for (String str : this.OPTIONS.get(this.SITUATIONS.indexOf(s)).get(this.CHARACTERS.indexOf(c.toString()))) {
            arlist.add(new Option(str));
        }
        return arlist;
    }

    public ArrayList<ArrayList<Option>> getOptions(Character c) {
        ArrayList<ArrayList<Option>> arlist = new ArrayList<ArrayList<Option>>();
        for (String s : this.SITUATIONS) {
            arlist.add(this.getOptionsForSituation(s, c));
        }
        return arlist;
    }

    public void removeOption(Option o, Situation s, Character c) {
        try {
            this.OPTIONS.get(this.SITUATIONS.indexOf(s)).get(this.CHARACTERS.indexOf(c)).remove(o);
        }
        catch (Exception e) {

        }
    }

    public void removeSituation(Situation s) {
        int index = this.SITUATIONS.indexOf(s);
        this.SITUATIONS.remove(s);
        this.BASE_OPTIONS.remove(index);
        this.OPTIONS.remove(index);
    }

    public Situation getOppositeSituation(Situation s) {
        int id = this.SITUATIONS.indexOf(s.toString());
        if (id % 2 == 0) {
            return new Situation(this.SITUATIONS.get(id + 1));
        } else {
            return new Situation(this.SITUATIONS.get(id - 1));
        }
    }

    public void save() {
        // TODO
    }

}
