package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartCmpData implements CmpData {

    /**
     * 入参
     */
    private Object params;


    @Override
    public String getType() {
        return "start";
    }
}
