package board;

public class board {
    private String name;
    private String mail;
    private String comment;
    private String time;
    private String time2;

    public board(String name, String mail, String comment,String time,String time2) {
		super();
		this.name = name;
		this.mail = mail;
		this.comment = comment;
		this.time = time;
		this.time2 = time2;
	}

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime2() {
        return time2;
    }
    public void setTime2(String time) {
        this.time2 = time2;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}