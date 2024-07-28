package org.example.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityDto<T> implements Serializable {
    private String status;
    private String msg;
    private T data;
}
