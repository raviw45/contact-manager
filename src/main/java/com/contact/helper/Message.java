package com.contact.helper;

public class Message {
   private String content;
   private String type;
@Override
public String toString() {
	return "Message [content=" + content + ", type=" + type + "]";
}
public Message(String content, String type) {
	this.content = content;
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
  
  
}
