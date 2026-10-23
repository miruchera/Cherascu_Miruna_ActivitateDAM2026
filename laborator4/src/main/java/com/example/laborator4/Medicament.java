package com.example.laborator4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Medicament implements Parcelable {
    private String denumire;
    private boolean areEfecteSecundare;
    private String modAdministrare;
    private String culoare;
    private float rating;
    private boolean necesitaReteta;

    public Medicament() {
    }

    public Medicament(String denumire, boolean areEfecteSecundare, String modAdministrare, String culoare, float rating, boolean necesitaReteta) {
        this.denumire = denumire;
        this.areEfecteSecundare = areEfecteSecundare;
        this.modAdministrare = modAdministrare;
        this.culoare = culoare;
        this.rating = rating;
        this.necesitaReteta = necesitaReteta;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public boolean isAreEfecteSecundare() {
        return areEfecteSecundare;
    }

    public void setAreEfecteSecundare(boolean areEfecteSecundare) {
        this.areEfecteSecundare = areEfecteSecundare;
    }

    public String getModAdministrare() {
        return modAdministrare;
    }

    public void setModAdministrare(String modAdministrare) {
        this.modAdministrare = modAdministrare;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isNecesitaReteta() {
        return necesitaReteta;
    }

    public void setNecesitaReteta(boolean necesitaReteta) {
        this.necesitaReteta = necesitaReteta;
    }

    protected Medicament(Parcel in) {
        denumire = in.readString();
        areEfecteSecundare = in.readByte() != 0;
        modAdministrare = in.readString();
        culoare = in.readString();
        rating = in.readFloat();
        necesitaReteta = in.readByte() != 0;
    }

    public static final Creator<Medicament> CREATOR = new Creator<Medicament>() {
        @Override
        public Medicament createFromParcel(Parcel in) {
            return new Medicament(in);
        }

        @Override
        public Medicament[] newArray(int size) {
            return new Medicament[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeByte((byte) (areEfecteSecundare ? 1 : 0));
        dest.writeString(modAdministrare);
        dest.writeString(culoare);
        dest.writeFloat(rating);
        dest.writeByte((byte) (necesitaReteta ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Medicament: " + denumire +
                "\nCuloare: " + culoare +
                "\nMod administrare: " + modAdministrare +
                "\nEfecte secundare: " + (areEfecteSecundare ? "Da" : "Nu") +
                "\nNecesită rețetă: " + (necesitaReteta ? "Da" : "Nu") +
                "\nRating: " + rating;
    }
}