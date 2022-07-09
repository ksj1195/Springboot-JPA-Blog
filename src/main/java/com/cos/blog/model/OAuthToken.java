package com.cos.blog.model;

import lombok.Data;

/**
 * packageName : com.cos.blog.model
 * fileName : OAuthToken
 * author : ksj
 * date : 2022/07/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/04         ksj          최초 생성
 */
@Data
public class OAuthToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
