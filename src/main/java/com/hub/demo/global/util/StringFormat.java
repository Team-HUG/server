package com.hub.demo.global.util;

import org.springframework.stereotype.Component;
import java.text.DecimalFormat;

@Component
public class StringFormat {
    public String wonFormat(int value) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(value);
    }
}
