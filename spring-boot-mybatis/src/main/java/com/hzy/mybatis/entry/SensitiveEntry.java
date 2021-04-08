package com.hzy.mybatis.entry;

import com.hzy.mybatis.common.annotation.DecryptField;
import com.hzy.mybatis.common.annotation.EncryptField;
import com.hzy.mybatis.common.annotation.SensitiveBean;

import java.util.Date;

@SensitiveBean
public class SensitiveEntry {
    private Long id;

    private String name;

    @EncryptField
    @DecryptField
    private String phone;

    @EncryptField
    @DecryptField
    private String address;

    private String idCard;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}