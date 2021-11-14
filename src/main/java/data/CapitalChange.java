package data;

import common.CommonUtils;

import java.math.BigDecimal;
import java.util.Date;

/* @description: 资金变动
 * @author: Sherwin Liang
 * @timestamp: 2021/11/14 11:47
*/
public class CapitalChange {
    private long id;
    private String changeType;
    private Date changeDate;
    private String inAccount;
    private BigDecimal inAmount;
    private String outAccount;
    private BigDecimal outAmount;

    public CapitalChange(String changeType, Date changeDate, String inAccount, BigDecimal inAmount, String outAccount, BigDecimal outAmount) {
        this.id = CommonUtils.getCapitalChangeID();
        this.changeType = changeType;
        this.changeDate = changeDate;
        this.inAccount = inAccount;
        this.inAmount = inAmount;
        this.outAccount = outAccount;
        this.outAmount = outAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public String getOutAccount() {
        return outAccount;
    }

    public void setOutAccount(String outAccount) {
        this.outAccount = outAccount;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }
}
