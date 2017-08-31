package ssm.system.entity;

import java.sql.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Article {
	private Integer id;
    private String  localUrl;
    private String  externalUrl;
    private Integer size; // 稿件大小
    private String  title; // 稿件标题
    private String  classification; // 分类-针对推送的第三方系统
    private String  mediumTag;   // 内部系统分类标签
    private String  brief;       // 稿件描述或者摘要
    private Integer type;        // 稿件的类型（0纯图片，1纯音频，2纯视频，3纯文字，4文图混合，5文图音视频混合...）
    private String  author;      // 稿件作者，上传者
    private Integer Pending;     // 判断标志：审核过/审核中/未审核
    private Date    updateTime;  // 更新时间按
    private Date    createTime;  // 创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocalUrl() {
		return localUrl;
	}
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	public String getExternalUrl() {
		return externalUrl;
	}
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getMediumTag() {
		return mediumTag;
	}
	public void setMediumTag(String mediumTag) {
		this.mediumTag = mediumTag;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPending() {
		return Pending;
	}
	public void setPending(Integer pending) {
		Pending = pending;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
