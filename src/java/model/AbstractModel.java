package model;

import java.util.List;

public class AbstractModel<T> {
    private int id;
    private List<T> listResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }   
}
