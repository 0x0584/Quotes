package objects;

import java.sql.Date;

public class Quote {
	private Integer idq, ida, idu;
	private Date created;
	private String content;

	public Quote(Integer ida, Integer idu, String content) {
		super();
		this.ida = ida;
		this.idu = idu;
		this.content = content;
	}

	public Quote(Integer idq, Integer ida, Integer idu, Date created,
			String content) {
		super();
		this.idq = idq;
		this.ida = ida;
		this.idu = idu;
		this.created = created;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Quote [idq=" + idq + ", ida=" + ida + ", idu=" + idu
				+ ", created=" + created + ", content=" + content + "]";
	}

	/* getters setters */
	public Integer getIdQuote() {
		return idq;
	}

	public void setIdQuote(Integer idq) {
		this.idq = idq;
	}

	public Integer getIdAuthor() {
		return ida;
	}

	public void setIdAuthor(Integer ida) {
		this.ida = ida;
	}

	public Integer getIdUser() {
		return idu;
	}

	public void setIdUser(Integer idu) {
		this.idu = idu;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
