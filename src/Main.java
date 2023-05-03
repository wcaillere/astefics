import com.sun.source.tree.Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Affichage du menu
        renderMenu();
    }

    private static void renderMenu() {

        IDal dal = new DalMySQL();

        String[] menuMsg = {"1. Initialiser la base", "2. Gérer formations", "3. Gérer catégories",
                "4. Gérer formateurs", "5. Gérer stagiaires", "6. Quitter l'application"};

        System.out.println("\nMenu");
        for (String msg : menuMsg) {
            System.out.println(msg);
        }

        String saisie;

        while (true) {
            Scanner clavier = new Scanner(System.in);

            saisie = clavier.nextLine();

            switch (saisie) {
                case "1" -> {
                    dal.initializeDB();
                    renderMenu();
                }
                case "2" -> {
                    renderChoices("formation");
                }
                case "3" -> {
                    renderChoices("catégorie");
                }
                case "4" -> {
                    renderChoices("formateur");
                }
                case "5" -> {
                    renderChoices("stagiaire");
                }
                case "6" -> System.exit(0);
                default -> System.out.println("Commande inconnue, veuillez sélectionner un nombre correct");
            }

        }
    }

    private static void renderChoices(String chosenMenu) {

        IDal dal = new DalMySQL();

        String[] choosenMsg = {"1. Afficher la liste des ", "2. Afficher ", "3. Créer ", "4. Modifier ",
                "5. Supprimer ", "6. Menu", "7. Quitter l'application"};

        HashMap<String, String> tables = new HashMap<>();
        tables.put("formation", "formations");
        tables.put("catégorie", "categories");
        tables.put("formateur", "teachers");
        tables.put("stagiaire", "students");


        System.out.println("\n" + chosenMenu + " :");
        // Si un espace était laissé à la fin du message, on rajoute le nom de l'onglet
        // choisi dans le menu. Un "s" est rajouté au nom de l'onglet pour le msg 1
        for (String msg : choosenMsg) {
            System.out.println(msg.endsWith(" ") ? msg + chosenMenu + (msg.startsWith("1") ? "s" : "") : msg);
        }

        String saisie;

        while (true) {
            Scanner clavier = new Scanner(System.in);

            saisie = clavier.nextLine();

            switch (saisie) {
                case "1" -> {
                    List<HashMap<String, Object>> results = dal.getAll(tables.get(chosenMenu));
                    System.out.println("\n-------------------------\nListe des " + chosenMenu + "s : ");
                    renderVector(results);
                    renderChoices(chosenMenu);
                }
                case "2" -> {

                    System.out.println("\nAffichage de l'élément n de la liste");

                    renderChoices(chosenMenu);
                }
                case "3" -> {
                    System.out.println("\nCréation d'un élément");
                    renderChoices(chosenMenu);
                }
                case "4" -> {
                    System.out.println("\nModification d'un élément");
                    renderChoices(chosenMenu);
                }
                case "5" -> {
                    System.out.println("\nSuppression d'un élément");
                    renderChoices(chosenMenu);
                }
                case "6" -> renderMenu();
                case "7" -> System.exit(0);
                default -> System.out.println("Commande inconnue, veuillez sélectionner un nombre correct");
            }
        }
    }

    private static void renderVector(List<HashMap<String, Object>> list) {
        for (HashMap<String, Object> item : list) {
            Object[] hashKeys = item.keySet().toArray();
            for (Object key : hashKeys) {
                System.out.print(key + " : " + item.get(key) + ", ");
            }
            System.out.println();
        }
    }
}