package com.example.journal;

public class Entry {
    private int id;
    private String body;
    private Long time;

    public Entry() {}

    public Entry(String body, Long time) {
        this.body = body;
        this.time = time;
    }

    public int getId() { return id; }
    public String getBody() { return body; }
    public Long getTime() { return time; }

    public void setId(int id) { this.id = id; }
    public void setBody(String body) { this.body = body; }
    public void setTime(Long time) { this.time = time; }
}
