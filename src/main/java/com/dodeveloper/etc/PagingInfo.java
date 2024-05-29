package com.dodeveloper.etc;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PagingInfo {

    // 실제 페이징을 만들때 필요한 변수들
    private int viewPostCntPerPage; // 한 페이지당 보여줄 게시글의 갯수
    private int totalPostCnt; // 전체 데이터(게시글)의 갯수
    private int totalPageCnt; // 전체 페이지 수
    private int pageNo; // 유저가 클릭한 (현재) 페이지 번호
    private int startRowIndex; // 유저가 클릭한 페이지에서 보여주기 시작할 row index 번호
    // -----------------------------------
    // 페이징 블럭을 만들때 필요한 변수들
    private int pageCntPerBlock; // 1개의 블럭에 몇 페이지씩 보여줄 것인지
    private int totalPageBlockCnt; // 전체 페이지 블럭 갯수
    private int pageBlockOfCurrentPage; // 현재 페이지가 속한 페이징 블럭의 번호
    private int startNumOfCurrentPagingBlock; // 현재 페이징 블럭에서의 출력 시작 페이지 번호
    private int endNumOfCurrentPagingBlock; // 현재 페이징 블럭에서의 출력 끝 페이지 번호

    // ----------------------------------------------------------

    // 인스턴스를 초기화하는 생성자 생성
    public PagingInfo(int pageNo) {
	this.pageNo = pageNo;
    }

    // ----------------------------------------------------------

    // 한 페이지당 보여줄 게시글의 갯수
    public int getViewPostCntPerPage() {
	return viewPostCntPerPage;
    }

    public void setViewPostCntPerPage(int viewPostCntPerPage) {
	this.viewPostCntPerPage = viewPostCntPerPage;
    }

    // 전체 데이터(게시글)의 갯수
    public int getTotalPostCnt() {
	return totalPostCnt;
    }

    public void setTotalPostCnt(int totalPostCnt) {
	this.totalPostCnt = totalPostCnt;
    }

    // 전체 페이지 수
    public int getTotalPageCnt() {
	return totalPageCnt;
    }

    public void setTotalPageCnt() {
	// 전체 페이지 수 = 게시판의 글 수 / 한페이지당 보여줄 글의 갯수 -> 나누어 떨어지지 않으면 몫 + 1
	if (this.totalPostCnt % this.viewPostCntPerPage != 0) {
	    // 나누어 떨어지지 않을 경우
	    // 전체 페이지 블럭 갯수 = 전체 페이지 수 / 1개의 블럭에 몇 페이지씩 보여줄 것인지 + 1
	    this.totalPageCnt = (this.totalPostCnt / this.viewPostCntPerPage) + 1;
	} else {
	    // 나누어 떨어진 경우
	    // 전체 페이지 블럭 갯수 = 전체 페이지 수 / 1개의 블럭에 몇 페이지씩 보여줄 것인지 + 1
	    this.totalPageCnt = this.totalPostCnt / this.viewPostCntPerPage;
	}
    }

    // 유저가 클릭한 (현재) 페이지 번호
    public int getPageNo() {
	return pageNo;
    }

    public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
    }

    // 유저가 클릭한 페이지에서 보여주기 시작할 row index 번호
    public int getStartRowIndex() {
	return startRowIndex;
    }

    public void setStartRowIndex() {
	// (현재페이지 번호 - 1) * 한페이지당 보여줄 글의 갯수
	this.startRowIndex = (this.pageNo - 1) * this.viewPostCntPerPage;
    }

    // ----------------------------------------------------------

    // 1개의 블럭에 몇 페이지씩 보여줄 것인지
    public int getPageCntPerBlock() {
	return pageCntPerBlock;
    }

    public void setPageCntPerBlock(int pageCntPerBlock) {
	this.pageCntPerBlock = pageCntPerBlock;
    }

    // 전체 페이지 블럭 갯수
    public int getTotalPageBlockCnt() {
	return totalPageBlockCnt;
    }

    public void setTotalPageBlockCnt() {
	// 전체 페이지 블럭 갯수 = 전체 페이지수 / pageCntPerBlock(1개의 블럭에 몇 페이지씩 보여줄 것인지) -> 나누어떨어지않으면
	// 몫 + 1
	if (this.totalPageCnt % this.pageCntPerBlock != 0) {
	    // 나누어 떨어지지 않을 경우
	    // 전체 페이지 블럭 갯수 = 전체 페이지수 / pageCntPerBlock -> 나누어떨어지않으면 몫 + 1
	    this.totalPageBlockCnt = (this.totalPageCnt / this.pageCntPerBlock) + 1;
	} else {
	    // 나누어 떨어진 경우
	    this.totalPageBlockCnt = this.totalPageCnt / this.pageCntPerBlock;
	}
    }

    // 현재 페이지가 속한 페이징 블럭의 번호
    public int getPageBlockOfCurrentPage() {
	return pageBlockOfCurrentPage;
    }

    public void setPageBlockOfCurrentPage() {
	// 현재 페이지 번호 / pageCntPerBlock -> 나누어 떨어지지 않으면 올림
	if (this.pageNo % this.pageCntPerBlock == 0) {
	    // 나누어 떨이질 경우
	    this.pageBlockOfCurrentPage = this.pageNo / this.pageCntPerBlock;
	} else {
	    // 나누어 떨어지지 않을 경우 올림을 하기위해서
	    // 계산을 한 후 ceil를 이용해 잘라버린 후 int로 변경한다.
	    this.pageBlockOfCurrentPage = (int) (Math.ceil(this.pageNo / (double) this.pageCntPerBlock));
	}
    }

    // 현재 페이징 블럭에서의 출력 시작 페이지 번호
    public int getStartNumOfCurrentPagingBlock() {
	return startNumOfCurrentPagingBlock;
    }

    public void setStartNumOfCurrentPagingBlock() {
	// ((현재 페이징 블럭 번호 - 1) * pageCntPerBlock) + 1
	this.startNumOfCurrentPagingBlock = ((this.pageBlockOfCurrentPage - 1) * this.pageCntPerBlock) + 1;
    }

    // 현재 페이징 블럭에서의 출력 끝 페이지 번호
    public int getEndNumOfCurrentPagingBlock() {
	return endNumOfCurrentPagingBlock;
    }

    public void setEndNumOfCurrentPagingBlock() {
	// 현재 페이징 블럭 번호 * pageCntPerBlock
	this.endNumOfCurrentPagingBlock = this.pageBlockOfCurrentPage * this.pageCntPerBlock;

	if (this.endNumOfCurrentPagingBlock > this.totalPageCnt) {
	    this.endNumOfCurrentPagingBlock = this.totalPageCnt;
	}
    }

    // ----------------------------------------------------------

    @Override
    public String toString() {
	return "PagingInfo [viewPostCntPerPage=" + viewPostCntPerPage + ", totalPostCnt=" + totalPostCnt
		+ ", totalPageCnt=" + totalPageCnt + ", pageNo=" + pageNo + ", startRowIndex=" + startRowIndex
		+ ", pageCntPerBlock=" + pageCntPerBlock + ", totalPageBlockCnt=" + totalPageBlockCnt
		+ ", pageBlockOfCurrentPage=" + pageBlockOfCurrentPage + ", startNumOfCurrentPagingBlock="
		+ startNumOfCurrentPagingBlock + ", endNumOfCurrentPagingBlock=" + endNumOfCurrentPagingBlock + "]";
    }
}
