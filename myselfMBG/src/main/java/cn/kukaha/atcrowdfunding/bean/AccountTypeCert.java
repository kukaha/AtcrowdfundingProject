package cn.kukaha.atcrowdfunding.bean;

import java.io.Serializable;

public class AccountTypeCert implements Serializable {
    private Integer id;

    private String accttype;

    private Integer certid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype == null ? null : accttype.trim();
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accttype=").append(accttype);
        sb.append(", certid=").append(certid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AccountTypeCert other = (AccountTypeCert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccttype() == null ? other.getAccttype() == null : this.getAccttype().equals(other.getAccttype()))
            && (this.getCertid() == null ? other.getCertid() == null : this.getCertid().equals(other.getCertid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccttype() == null) ? 0 : getAccttype().hashCode());
        result = prime * result + ((getCertid() == null) ? 0 : getCertid().hashCode());
        return result;
    }
}