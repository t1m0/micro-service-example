package com.t1m0.exmple.microservice.resource.domain;

import org.springframework.data.annotation.Id;

public class ToDo {

 @Id
 private String uuid    = null;
 private String title   = null;
 private String comment = null;

 public ToDo() {
  super();
 }

 public ToDo(final String title, final String comment) {
  super();
  this.title = title;
  this.comment = comment;
 }

 public String getUuid() {
  return uuid;
 }

 public void setUuid(final String uuid) {
  this.uuid = uuid;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(final String title) {
  this.title = title;
 }

 public String getComment() {
  return comment;
 }

 public void setComment(final String comment) {
  this.comment = comment;
 }

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = (prime * result) + ((comment == null) ? 0 : comment.hashCode());
  result = (prime * result) + ((uuid == null) ? 0 : uuid.hashCode());
  result = (prime * result) + ((title == null) ? 0 : title.hashCode());
  return result;
 }

 @Override
 public boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  ToDo other = (ToDo) obj;
  if (comment == null) {
   if (other.comment != null) {
    return false;
   }
  } else if (!comment.equals(other.comment)) {
   return false;
  }
  if (uuid == null) {
   if (other.uuid != null) {
    return false;
   }
  } else if (!uuid.equals(other.uuid)) {
   return false;
  }
  if (title == null) {
   if (other.title != null) {
    return false;
   }
  } else if (!title.equals(other.title)) {
   return false;
  }
  return true;
 }

 @Override
 public String toString() {
  return "ToDo [id=" + uuid + ", title=" + title + ", comment=" + comment + "]";
 }

}
