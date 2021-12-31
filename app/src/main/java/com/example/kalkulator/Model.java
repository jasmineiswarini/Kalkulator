package com.example.kalkulator;

public class Model {
    private static int id;
    private String a1, a2, op, has;

    public Model(int id, String a1, String a2, String op, String has) {
        this.id = id;
        this.a1 = a1;
        this.a2 = a2;
        this.op = op;
        this.has = has;
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getHas() {
        return has;
    }

    public void setHas(String has) {
        this.has = has;
    }
}