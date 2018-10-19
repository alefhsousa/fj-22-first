package br.com.caelum.ingresso.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CapaFilme {

    @JsonProperty("Poster")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
