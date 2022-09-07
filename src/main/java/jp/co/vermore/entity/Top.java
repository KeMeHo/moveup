package jp.co.vermore.entity;

import jp.co.vermore.common.mvc.BaseEntity;

import java.util.Date;

public class Top extends BaseEntity {

    private Long id;

    private String url;

    private Byte type;

    private Integer score;

    private Byte itemType;

    private String linkUrl;

    private Date createDatetime;

    private Date updateDatetime;

    private Boolean delFlg;

    private String note;

    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.id
     *
     * @param id the value for top.id
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }


    public Byte getItemType() {
        return itemType;
    }

    public void setItemType(Byte itemType) {
        this.itemType = itemType;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.url
     *
     * @return the value of top.url
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.url
     *
     * @param url the value for top.url
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.type
     *
     * @return the value of top.type
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.type
     *
     * @param type the value for top.type
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.score
     *
     * @return the value of top.score
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public Integer getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.score
     *
     * @param score the value for top.score
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.create_datetime
     *
     * @return the value of top.create_datetime
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.create_datetime
     *
     * @param createDatetime the value for top.create_datetime
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.update_datetime
     *
     * @return the value of top.update_datetime
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.update_datetime
     *
     * @param updateDatetime the value for top.update_datetime
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.del_flg
     *
     * @return the value of top.del_flg
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public Boolean getDelFlg() {
        return delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.del_flg
     *
     * @param delFlg the value for top.del_flg
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column top.note
     *
     * @return the value of top.note
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column top.note
     *
     * @param note the value for top.note
     *
     * @mbggenerated Thu Mar 15 17:39:43 CST 2018
     */
    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}