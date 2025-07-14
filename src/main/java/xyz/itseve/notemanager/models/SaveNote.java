package xyz.itseve.notemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import xyz.itseve.notemanager.NotePriority;

public class SaveNote {
    @JsonProperty("title")
    public String title;

    @JsonProperty("content")
    public String content;

    @JsonProperty("priority")
    public NotePriority priority;
}
