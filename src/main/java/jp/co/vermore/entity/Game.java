package jp.co.vermore.entity;

import jp.co.vermore.common.mvc.BaseEntity;

import java.util.Date;

public class Game extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.id
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.uuid
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private String uuid;



    private Integer entryType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.date
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.type
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.title
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.excerpt
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private String excerpt;

    private int sortScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.publish_start
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Date publishStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.publish_end
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Date publishEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.create_datetime
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Date createDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.update_datetime
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Date updateDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.del_flg
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private Boolean delFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column game.note
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.id
     *
     * @return the value of game.id
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.id
     *
     * @param id the value for game.id
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.uuid
     *
     * @return the value of game.uuid
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.uuid
     *
     * @param uuid the value for game.uuid
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.date
     *
     * @return the value of game.date
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */

    public Integer getEntryType() {
        return entryType;
    }

    public void setEntryType(Integer entryType) {
        this.entryType = entryType;
    }

    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.date
     *
     * @param date the value for game.date
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.type
     *
     * @return the value of game.type
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.type
     *
     * @param type the value for game.type
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column games.title
     *
     * @return the value of game.title
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.title
     *
     * @param title the value for game.title
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.excerpt
     *
     * @return the value of game.excerpt
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public String getExcerpt() {
        return excerpt;
    }

    public int getSortScore() { return sortScore; }

    public void setSortScore(int sortScore) { this.sortScore = sortScore; }
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.excerpt
     *
     * @param excerpt the value for game.excerpt
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.publish_start
     *
     * @return the value of game.publish_start
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Date getPublishStart() {
        return publishStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.publish_start
     *
     * @param publishStart the value for game.publish_start
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setPublishStart(Date publishStart) {
        this.publishStart = publishStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.publish_end
     *
     * @return the value of game.publish_end
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Date getPublishEnd() {
        return publishEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.publish_end
     *
     * @param publishEnd the value for game.publish_end
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setPublishEnd(Date publishEnd) {
        this.publishEnd = publishEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.create_datetime
     *
     * @return the value of game.create_datetime
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.create_datetime
     *
     * @param createDatetime the value for game.create_datetime
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.update_datetime
     *
     * @return the value of game.update_datetime
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.update_datetime
     *
     * @param updateDatetime the value for game.update_datetime
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.del_flg
     *
     * @return the value of game.del_flg
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public Boolean getDelFlg() {
        return delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.del_flg
     *
     * @param delFlg the value for game.del_flg
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column game.note
     *
     * @return the value of game.note
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column game.note
     *
     * @param note the value for game.note
     *
     * @mbggenerated Wed Feb 28 15:42:55 CST 2018
     */
    public void setNote(String note) {
        this.note = note;
    }
}