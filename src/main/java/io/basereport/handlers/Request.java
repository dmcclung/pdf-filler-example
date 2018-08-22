package io.basereport.handlers;

import java.util.List;

public class Request {
    private List<ClientEntity> entities;

    public Request(List<ClientEntity> entities) {
        this.entities = entities;
    }

    public List<ClientEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<ClientEntity> entities) {
        this.entities = entities;
    }

}
