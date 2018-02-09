package com.lequ.login.bootstrap.model;

/**封装一些公用信息
 * @author gaoxu
 *
 */
public class CommonRequestObject {
    public CommonRequestObject() {

    }

    public CommonRequestObject(Object scn) {
        this.scn = scn;
    }

    /**
     * 网站的cookie存放
     */
    private Object scn;


    public String getScn() {
        if (scn == null) {
            return null;
        }
        return scn.toString();
    }

    public void setScn(Object scn) {
        this.scn = scn;
    }
}
