package com.ffk.springBootDemo.domain;

/**
 * https://km.sankuai.com/page/52715455
 * MWS接入方案-后端接入步骤
 */
public class MWSUser {
    private int id;                //sso信息，sso定义的id
    private String login;          //sso信息，mis账号
    private String name;           //sso信息，员工名称
    private String code;           //sso信息，sso定义的code
    private String email;          //sso信息，员工邮箱
    private String tenantId;       //sso信息，sso定义的tenantId
    private String organization;   //IAM信息： 登录组织（当前线下为MTDP:dev，线上为MTDP，公司内部组织不会变化）
    private String organizationId; //IAM信息：登录组织ID（当前线下为MTDP:dev的id，线上为MTDP的id，公司内部组织不会变化）
    private String ssoId;          //sso信息，私有云统一ssoid（用于iam鉴权或其它用途）

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MWSUser{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", tenantId='").append(tenantId).append('\'');
        sb.append(", organization='").append(organization).append('\'');
        sb.append(", organizationId='").append(organizationId).append('\'');
        sb.append(", ssoId='").append(ssoId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }
}
