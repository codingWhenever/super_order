package com.e_at.super_order.mvp.entity;


/**
 * LoginEntity
 * @desc 登录实体
 * @author leo
 * @date 2017/11/4
 * @email lei.lu@e-at.com
 */
public class LoginEntity extends BaseEntity {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public static class Response {
        private String phone;
        private String nickname;
        private int platformMemberId;
        private String token;

        public String getPhone() {
            return phone;
        }

        public String getNickname() {
            return nickname;
        }

        public int getPlatformMemberId() {
            return platformMemberId;
        }

        public String getToken() {
            return token;
        }
    }
}
