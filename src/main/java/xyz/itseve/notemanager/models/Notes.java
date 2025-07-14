package xyz.itseve.notemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Notes {
    @JsonProperty("notes")
    public List<SaveNote> data;
}
