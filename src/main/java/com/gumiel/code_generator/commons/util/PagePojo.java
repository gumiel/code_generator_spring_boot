package com.gumiel.code_generator.commons.util;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagePojo<T> {

  private List<T> content;
  private boolean last;
  private int pageNumber;
  private int pageSize;
  private int totalPages;
  private long totalElements;

  public PagePojo(List<T> content, boolean last, int pageNumber, int pageSize, int totalPages,
      long totalElements) {
    this.content = content;
    this.last = last;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

}
