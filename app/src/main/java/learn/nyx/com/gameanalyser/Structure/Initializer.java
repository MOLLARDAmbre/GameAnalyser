package learn.nyx.com.gameanalyser.Structure;

import learn.nyx.com.gameanalyser.Constants;

public class Initializer {

    public static void initialize() {

        // Adds the Options
        Constants.DATA.addSituation(new Situation("Lands"));
        Constants.DATA.addSituation(new Situation("Catches a landing"));
        Constants.DATA.addSituation(new Situation("Ledgetraps"));
        Constants.DATA.addSituation(new Situation("Is ledgetrapped"));

        // Adds the characters
        String[] chars = new String[]{"Mario", "Luigi", "Peach", "Bowser", "Dr Mario", "Rosalina & Luma", "Bowser Jr", "Yoshi", "Donkey Kong" ,
        "Diddy Kong", "Link", "Zelda", "Sheik", "young Link", "Ganondorf", "Toon link", "Samus", "Zero Suit Samus", "Kirby", "Meta Knight",
        "King Dedede", "Fox", "Falco", "Wolf", "Pikachu", "Jigglypuff", "Pichu", "Mewtwo", "Pokemon trainer", "Lucario", "Greninja", "Captain Falcon",
        "Ness", "Lucas", "Ice climbers", "Marth", "Roy", "Ike", "Lucina", "Robin","Corrin", "Mr Game & Watch", "Pit", "Palutena", "Dark Pit", "Wario",
        "Olimar", "R.O.B.", "Villager", "Wii Fit Trainer", "Little Mac", "Shulk", "Duck Hunt Duo", "Snake", "Sonic", "Mega Man", "Pac-Man",
        "Ryu", "Cloud", "Bayonetta", "Daisy", "Piranha Plant", "King K. Rool", "Ridley", "Dark Samus", "Incineroar", "Chrom", "Isabelle",
        "Inkling", "Ken", "Simon", "Richter", "Joker", "Hero", "Banjo & Kazooie", "Terry"};
        for (String s : chars) {
            Constants.DATA.addCharacter(s);
        }

        // Adds a few options for Zelda
        Constants.DATA.addOption("Nayru's Love", Constants.DATA.getSituations().get(0), Constants.DATA.getCharacters().get(11));
        Constants.DATA.addOption("Pivot Grab", Constants.DATA.getSituations().get(1), Constants.DATA.getCharacters().get(11));
        Constants.DATA.addOption("Dash Attack", Constants.DATA.getSituations().get(1), Constants.DATA.getCharacters().get(11));

        // Adds a few options for everyone
        Constants.DATA.addBaseOption("Attack", Constants.DATA.getSituations().get(0));
        Constants.DATA.addBaseOption("Air dodge", Constants.DATA.getSituations().get(0));
        Constants.DATA.addBaseOption("Double jump away", Constants.DATA.getSituations().get(0));

        Constants.DATA.addBaseOption("Anti air", Constants.DATA.getSituations().get(1));
        Constants.DATA.addBaseOption("Shield", Constants.DATA.getSituations().get(1));
        Constants.DATA.addBaseOption("Wait", Constants.DATA.getSituations().get(1));

        Constants.DATA.addBaseOption("Dash grab", Constants.DATA.getSituations().get(2));
        Constants.DATA.addBaseOption("Anti air", Constants.DATA.getSituations().get(2));
        Constants.DATA.addBaseOption("Wait", Constants.DATA.getSituations().get(2));

        Constants.DATA.addBaseOption("Neutral getup", Constants.DATA.getSituations().get(3));
        Constants.DATA.addBaseOption("Roll", Constants.DATA.getSituations().get(3));
        Constants.DATA.addBaseOption("Getup attack", Constants.DATA.getSituations().get(3));
        Constants.DATA.addBaseOption("Ledge jump", Constants.DATA.getSituations().get(3));
        Constants.DATA.addBaseOption("Drop down double jump", Constants.DATA.getSituations().get(3));

    }

}
