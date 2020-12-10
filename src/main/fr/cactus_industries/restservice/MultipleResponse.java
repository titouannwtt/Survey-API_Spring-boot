package fr.cactus_industries.restservice;

import java.util.List;

public class MultipleResponse<T> extends Response {

    private List<T> list;

    public MultipleResponse(List<T> list) {
        super("OK");
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }
}
