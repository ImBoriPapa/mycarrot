package com.project.carrot.domain.test.reposotory;

import java.util.List;

public class Average {

    private long first;
    private long second;
    private long tree;
    private long four;
    private long five;
    private long six;
    private long seven;
    private long eight;
    private long nine;
    private long ten;


    public Average(List<Long> list) {
        this.first = list.get(0);
        this.second = list.get(1);
        this.tree = list.get(2);
        this.four = list.get(3);
        this.five = list.get(4);
        this.six = list.get(5);
        this.seven = list.get(6);
        this.eight = list.get(7);
        this.nine = list.get(8);
        this.ten = list.get(9);
    }

    public long getAverage(){
        long result = (this.first + this.second + this.tree + this.four + this.five + this.six + this.seven + this.eight + this.nine + this.ten) / 10;
        return result;
    }
}
