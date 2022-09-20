package com.project.carrot.api.trade.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateTradeForm {

    private String title;
    private String category;
    private String price;
    private boolean offer;
    private boolean share;
    private String location;
    private String context;


}
