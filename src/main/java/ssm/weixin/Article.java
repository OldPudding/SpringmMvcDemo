package ssm.weixin;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders={"thumb_media_id","author",
		"title","content_source_url","content","digest","show_cover_pic"})
public class Article {
	private String thumb_media_id;     // 封面或缩略图ID
	private String author;             // 作者
	private String title;              // 文字标题
	private String content_source_url; // 阅读全文链接
	private String content;            // 文章内容
	private String digest;             // 文章描述
	private String show_cover_pic = "0"; // 是否限制封面（默认为不显示）
	
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getShow_cover_pic() {
		return show_cover_pic;
	}
	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	
	
}
