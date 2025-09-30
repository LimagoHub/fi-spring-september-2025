package de.fi.webapp.service.model;

import java.util.UUID;

public class Schwein {


    private UUID id;
    private String name;
    private int gewicht;

    public Schwein() {

    }

    public Schwein(final UUID id, final String name, final int gewicht) {
        this.id = id;
        this.name = name;
        this.gewicht = gewicht;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(final int gewicht) {
        this.gewicht = gewicht;
    }

    public void fuettern() {
        setGewicht(getGewicht()+1);
    }
}
