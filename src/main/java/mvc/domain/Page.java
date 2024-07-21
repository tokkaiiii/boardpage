package mvc.domain;

public class Page {
  //최소 페이지 번호
  private int min;
  //최대 페이지 번호
  private int max;
  //이전 버튼의 페이지 번호
  private int prevPage;
  //다음 버튼의 페이지 번호
  private int nextPage;
  //전체 페이지 번호
  private int pageCnt;
  //현재 페이지 번호
  private int currentPage;

  public int getMin() {
    return min;
  }
  //생성자에서 계산으로 설정
  //contentCnt : 전체글 개수, currentPage: 현재글 번호,
  //contentPageCnt : 페이지당 글의 개수
  //paginationCnt : 페이지 버튼의 개수
  public Page(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt){
    //현재 페이지번호
    this.currentPage = currentPage;
    //전체 페이지 개수
    pageCnt = contentCnt/contentPageCnt;
    if(contentCnt%contentPageCnt>0){
      pageCnt++;
    }
    //페이지네이션 최소값, 최대값
    min = ((currentPage-1)/paginationCnt)*paginationCnt+1;
    max = min+paginationCnt-1;
    //최대값이 실제 최대페이지 보다 크면 최대페이지로 설정
    if(max>pageCnt){
      max=pageCnt;
    }
    //이전페이지값, 다음페이지값
    prevPage = min-1;
    nextPage = max+1;
    //다음페이지가 최대페이지보다 크면 최대페이지로 설정
    if(nextPage>pageCnt){
      nextPage=pageCnt;
    }
  }
  public void setMin(int min) {this.min = min;}

  public int getMax() {return max;}

  public void setMax(int max) {this.max = max;}

  public int getPrevPage() {return prevPage;}

  public void setPrevPage(int prevPage) {this.prevPage = prevPage;}

  public int getNextPage() {return nextPage;}

  public void setNextPage(int nextPage) {this.nextPage = nextPage;}

  public int getPageCnt() {return pageCnt;}

  public void setPageCnt(int pageCnt) {this.pageCnt = pageCnt;}

  public int getCurrentPage() {return currentPage;}

  public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}

  public Page() {}
}
