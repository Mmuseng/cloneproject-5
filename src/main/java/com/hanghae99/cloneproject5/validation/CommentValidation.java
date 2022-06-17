package com.hanghae99.cloneproject5.validation;

import com.hanghae99.cloneproject5.dto.CommentRegisterDto;

public class CommentValidation {
    public static void validationCommentRegister(CommentRegisterDto registerDto) {
        String content = registerDto.getContent();

        if (content.trim().isEmpty()) {
            try {
                throw new IllegalAccessException("내용을 입력해주세요.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
