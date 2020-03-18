package model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Board implements Serializable{
	public int boardNum;
	public String boardId;
	public String userId;
	public String subject;
	public String passwd;
	public String address;
	public int price;
	private Timestamp regDate;
	public String content;
	public String fileName;
	
	
}
