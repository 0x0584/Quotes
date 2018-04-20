package objects;

import java.sql.Date;
import java.sql.SQLException;

import db.Database;

public class Quote {
	private Integer idq;
	private User author;
	private Date created;
	private String content;

	public Quote(Integer ida, String content) {
		super();
		try {
			this.author = new Database().getUser(ida);
		} catch (ClassNotFoundException | SQLException e) {
		}
		this.content = content;
	}

	public Quote(Integer idq, Integer ida, Date created, String content) {
		super();
		this.idq = idq;
		try {
			this.author = new Database().getUser(ida);
		} catch (ClassNotFoundException | SQLException e) {
		}
		this.created = created;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Quote [idq=" + idq + ", ida=" + author + ", created=" + created
				+ ", content=" + content + "]";
	}

	public String toHtmlTag(User u) {
		return "<div class=\"post grid_8\" align=\"center\">"
				+ "<div class=\"picture\">"
				+ "<img src=\"img/download.png\">"
				+ "<div>"
				+ "	<a href=\"#\">"
				+ (u == null ? this.author.getLogin() : u.getLogin())
				+ "</a> </div> </div>"
				+ "<div>"
				+ "<blockquote>"
				+ "<p>"
				+ this.content
				+ "</p>"
				+ (u == null ? ""
						: "<div> <i>posted originally by <a href=\"#\">"
								+ this.author.getLogin()) + "</a></i>"
				+ "</div>" + "</blockquote>" + "</div>" + "</div>";
	}

	/* getters setters */
	public Integer getIdQuote() {
		return idq;
	}

	public User getAuthor() {
		return author;
	}

	public Date getCreated() {
		return created;
	}

	public String getContent() {
		return content;
	}
}
