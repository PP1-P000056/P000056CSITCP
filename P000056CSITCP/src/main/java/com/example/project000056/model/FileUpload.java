package com.example.project000056.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileUpload {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String type;

  @Lob
  private byte[] data;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;

  public FileUpload() {
  }

  public FileUpload(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

}
