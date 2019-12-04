package com.liuchao.utils;

public class TestData {

    private String passport;
    private String password;
    private String expect;

    public TestData(String passport,String password,String expect){
        this.passport = passport;
        this.password = password;
        this.expect = expect;
    }
    public String getPassport(){
        return this.passport;
    }

    public String getPassword(){
        return this.password;
    }

    public String getExpect(){
        return this.expect;
    }

    public void setPassport(String passport){
        this.passport = passport;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setExpect(String expect){
        this.expect = expect;
    }
}
