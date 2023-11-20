package com.example.exercice1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class HelloController {

    @FXML
    public Button afficher;

    @FXML
    public Label resuVote;
    @FXML
    public Button ajouter;

    @FXML
    public Button grand;

    @FXML
    public Button reset;

    @FXML
    public TextField saisie;

    @FXML
    public AnchorPane saisir;

    @FXML
    public TextArea taille;
    @FXML
    public Button blanc;
    @FXML
    public Label tailleresu;


    @FXML
    public Button non;

    @FXML
    public Button oui;

    @FXML
    public TextField prix;

    public int count;


    int ouiVotes = 0;
    int nonVotes = 0;
    int blancVotes = 0;


    @FXML
    public TextField nomProduit;


    @FXML
    public Button ajouterProduit;

    @FXML
    public ChoiceBox<Produit> listeProduit;

    @FXML
    public Button retirerProduit;

    @FXML
    public Button ticketResultat;

    private ObservableList<Produit> produits = FXCollections.observableArrayList();

    @FXML
    public Label resuTicket;


    @FXML
    void maxTab(ActionEvent event) {
        String text = saisie.getText(); // Récupère le texte du TextArea
        String[] tokens = text.split("\\s+"); // Divise le texte en mots
        double[] nbr = new double[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            try {
                nbr[i] = Double.parseDouble(tokens[i]);
            } catch (NumberFormatException e) {
                nbr[i] = 0;
            }
        }

        double max = Double.MIN_VALUE;

        for (double nbrs : nbr) {
            if (nbrs > max) {
                max = nbrs;
            }
        }

        if (max != Double.MIN_VALUE) {
            saisie.setText("Le plus grand nombre est : " + max);
        } else {
            saisie.setText("Aucun nombre valide n'a été saisi.");
        }
    }


    @FXML
    void AjouterTaille(ActionEvent event) {
        String text = taille.getText(); // Récupère le texte de la zone de texte TextArea
        String[] tokens = text.split("\\s+");
        double[] tailles = new double[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            try {
                tailles[i] = Double.parseDouble(tokens[i]);
            } catch (NumberFormatException e) {
                tailles[i] = 0;
            }
        }

        int personnesPetites = 0;

        for (double taille : tailles) {
            if (taille < 1.75) {
                personnesPetites++;
            }
        }

        count += personnesPetites;
        taille.clear();
    }

    @FXML
    void AfficherResu(ActionEvent event) {
        if (count == 0) {
            tailleresu.setText("Aucune personne à 1,75m.");
        } else if (count == 1) {
            tailleresu.setText(count + " personne à 1,75m.");
        } else {
            tailleresu.setText(count + " personnes à 1,75m.");
        }
    }


    @FXML
    public void voterOui(ActionEvent event) {
        ouiVotes++;
    }

    @FXML
    public void voterNon(ActionEvent event) {
        nonVotes++;
    }

    @FXML
    public void voterBlanc(ActionEvent event) {
        blancVotes++;
    }

    @FXML
    public void afficherVotes(ActionEvent event) {
        int totalVotes = ouiVotes + nonVotes + blancVotes;

        if (totalVotes == 0) {
            resuVote.setText("Aucun vote enregistré.");
        } else {
            double pourcentageOui = (double) ouiVotes / totalVotes * 100;
            double pourcentageNon = (double) nonVotes / totalVotes * 100;
            double pourcentageBlanc = (double) blancVotes / totalVotes * 100;

            String resultMessage = "Résultat du vote :\n";
            resultMessage += "Oui : " + ouiVotes + " votes (" + pourcentageOui + "%)\n";
            resultMessage += "Non : " + nonVotes + " votes (" + pourcentageNon + "%)\n";
            resultMessage += "Blanc : " + blancVotes + " votes (" + pourcentageBlanc + "%)\n";

            resuVote.setText(resultMessage);
        }
    }

    @FXML
    public void resetVotes(ActionEvent event) {
        ouiVotes = 0;
        nonVotes = 0;
        blancVotes = 0;
        resuVote.setText("");
    }


    @FXML
    public void initialize() {
        listeProduit.setItems(produits);
    }

    @FXML
    void ajouterProduit(ActionEvent event) {
        String nom = nomProduit.getText();
        double prixProduit = Double.parseDouble(prix.getText());
        Produit nouveauProduit = new Produit(nom, prixProduit);
        produits.add(nouveauProduit);
        nomProduit.clear();
        prix.clear();
    }

    @FXML
    void afficherTicketDeCaisse(ActionEvent event) {
        double total = 0;
        StringBuilder ticket = new StringBuilder("Ticket de Caisse\n\n");
        for (Produit produit : produits) {
            ticket.append(produit.getNom()).append(": ").append(produit.getPrix()).append(" euros\n");
            total += produit.getPrix();
        }
        ticket.append("\nTotal à payer : ").append(total).append(" euros");

        resuTicket.setText(ticket.toString());
    }

    @FXML
    void retirerProduit(ActionEvent event) {
        Produit produitSelectionne = listeProduit.getValue();
        if (produitSelectionne != null) {
            produits.remove(produitSelectionne);
        }
    }

    private static class Produit {
        private String nom;
        private double prix;

        public Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }

        public String getNom() {
            return nom;
        }

        public double getPrix() {
            return prix;
        }

        @Override
        public String toString() {
            return nom;
        }
    }
}












