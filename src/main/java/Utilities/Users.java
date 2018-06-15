package Utilities;

import java.util.Random;

import org.thymeleaf.util.StringUtils;

public interface Users {
	String[] colors = {"silver", "gray", "black", "red", "maroon", "olive", "lime", "green", "teal", "blue", "navy", "fuchsia", "purple"};
	String[] animals = {"pigeon", "seagull", "bat", "owl", "sparrows", "robin", "bluebird", "cardinal", "hawk", "fish", "shrimp", "frog", "whale", "shark", "eel", "seal", "lobster", "octopus", "mole", "shrew", "rabbit", "chipmunk", "armadillo", "dog", "cat", "lynx", "mouse", "lion", "moose", "horse", "deer", "raccoon", "zebra", "goat", "cow", "pig", "tiger", "wolf", "pony", "antelope", "buffalo", "camel", "donkey", "elk", "fox", "monkey", "gazelle", "impala", "jaguar", "leopard", "lemur", "yak", "elephant", "giraffe", "hippopotamus", "rhinoceros", "grizzlybear"};
	//Generate a random username
	public static String generateUsername() {
		Random rand = new Random();
		return StringUtils.capitalize(colors[rand.nextInt(colors.length)]) + " " + StringUtils.capitalize(animals[rand.nextInt(colors.length)]);
	}
}
