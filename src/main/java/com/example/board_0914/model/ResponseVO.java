package com.example.board_0914.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResponseVO<T> {
    //VO란 도메인에서 한 개 또는 그 이상의 속성들을 묶어서 특정 값을 나타내는 객체를 의미한다.
    final public static String SUCCESS_CODE = "0000";
    final public static String ERROR_CODE = "9999";

    private String code = SUCCESS_CODE;
    private String message;
    private T data;

    public static ResponseVO<Void> create() {
        return create(null);
    }

    public static <T> ResponseVO<T> create(T data) {
        return ResponseVO.<T>builder()
                .code(SUCCESS_CODE)
                .message(null)
                .data(data)
                .build();
    }

    public static <T> ResponseVO<T> create(T data, String code) {
        return ResponseVO.<T>builder()
                .code(code)
                .message(null)
                .data(data)
                .build();
    }

    public static <T> ResponseVO<T> create(T data, String code, String message) {
        return ResponseVO.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ResponseVO<T> error(String message) {
        return ResponseVO.<T>builder()
                .code(ERROR_CODE)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ResponseVO<T> error(String code, String message) {
        return ResponseVO.<T>builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }
}
