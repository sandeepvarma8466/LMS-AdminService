package com.blz.adminservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private int errorcode;
    private String message;
    private Object token;
}
