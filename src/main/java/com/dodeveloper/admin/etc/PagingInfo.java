package com.dodeveloper.admin.etc;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


	@Component
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	// --------------- 실제 페이징을 만들 때 필요한 변수들 ----------------------
	public class PagingInfo {
		private int viewPostCntPerPage = 5; // 한 페이지당 보여줄 글의 갯수
		private int totalPostCnt; // 전체 데이터 갯수
		private int totalPageCnt; // 전체 페이지 수
		private int pageNo; // 유저가 클릭한 현재 페이지 번호
		private int startRowIndex; // 그 페이지에서 보여주기 시작할 row index번호

		// ----------------------- 페이징 블럭을 만들때 필요한 변수들 -------------------------
		private int pageCntPerBlock = 10; // 1개의 블럭에 몇 페이지씩 보여줄 것인지...
		private int totalPageBlockCnt; // 전체 페이지 블럭 갯수
		private int pageBlockOfCurrentPage; // 현재 페이지가 속한 페이징 블럭의 번호
		private int startNumOfCurrentPagingBlock; // 현재 페이징 블럭에서의 출력 시작 페이지 번호
		private int endNumOfCurrentPagingBlock; // 현재 페이징 블럭에서의 출력 끝 페이지 번호

		public PagingInfo(int pageNo) {
			this.pageNo = pageNo;
		}

		public void setTotalPageBlockCnt() {
			// 전체 페이지 블럭 갯수 = 전체 페이지수 / pageCntPerBlock -> 나누어떨어지않으면 몫 + 1
			if (this.totalPageCnt % this.pageCntPerBlock != 0) {
				this.totalPageBlockCnt = (this.totalPageCnt / this.pageCntPerBlock) + 1;
			} else {
				this.totalPageBlockCnt = this.totalPageCnt / this.pageCntPerBlock;
			}
		}

		public int getTotalPageBlockCnt() {
			return this.totalPageBlockCnt;
		}

		// =======================================================================================================

		public void setPageBlockOfCurrentPage() {
			// 현재 페이지 번호 / pageCntPerBlock -> 나누어 떨어지지 않으면 올림
			if (this.pageNo % this.pageCntPerBlock == 0) {
				this.pageBlockOfCurrentPage = this.pageNo / this.pageCntPerBlock;
			} else {
				this.pageBlockOfCurrentPage = (int) (Math.ceil(this.pageNo / (double) this.pageCntPerBlock));
			}
		}

		public int getPageBlockOfCurrentPage() {
			return this.pageBlockOfCurrentPage;
		}

		// =======================================================================================================

		public void setStartNumOfCurrentPagingBlock() {
			// ((현재 페이징 블럭 번호 - 1) * pageCntPerBlock) + 1
			this.startNumOfCurrentPagingBlock = ((this.pageBlockOfCurrentPage - 1) * this.pageCntPerBlock) + 1;
		}

		public int getStartNumOfCurrentPagingBlock() {
			return this.startNumOfCurrentPagingBlock;
		}

		// =======================================================================================================

		public void setEndNumOfCurrentPagingBlock() {
			// 현재 페이징 블럭 번호 * pageCntPerBlock
			this.endNumOfCurrentPagingBlock = this.pageBlockOfCurrentPage * this.pageCntPerBlock;

			if (this.endNumOfCurrentPagingBlock > this.totalPageCnt) {
				this.endNumOfCurrentPagingBlock = this.totalPageCnt;
			}

		}

		public int getEndNumOfCurrentPagingBlock() {
			return this.endNumOfCurrentPagingBlock;
		}

		// =======================================================================================================

		public int getStartRowIndex() {
			return startRowIndex;
		}

		public void setStartRowIndex() {
			// (현재페이지 번호 - 1) * 한페이지당 보여줄 글의 갯수
			this.startRowIndex = (this.pageNo - 1) * this.viewPostCntPerPage;
		}

		public int getViewPostCntPerPage() {
			return viewPostCntPerPage;
		}

		public void setViewPostCntPerPage(int viewPostCntPerPage) {
			this.viewPostCntPerPage = viewPostCntPerPage;
		}

		public int getTotalPostCnt() {
			return totalPostCnt;
		}

		public void setTotalPostCnt(int totalPostCnt) {
			this.totalPostCnt = totalPostCnt;
		}

		public int getTotalPageCnt() {
			return totalPageCnt;
		}

		public void setTotalPageCnt() {
			// 총 페이지 수 : 게시판의 글 수 / 한페이지당 보여줄 글의 갯수 -> 나누어 떨어지지 않으면 몫 + 1
			if (this.totalPostCnt % this.viewPostCntPerPage != 0) { // 나누어 떨어지지 않았다.
				this.totalPageCnt = (this.totalPostCnt / this.viewPostCntPerPage) + 1;
			} else { // 나누어 떨어졌다.
				this.totalPageCnt = this.totalPostCnt / this.viewPostCntPerPage;
			}
		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		@Override
		public String toString() {
			return "PagingInfo [viewPostCntPerPage=" + viewPostCntPerPage + ", totalPostCnt=" + totalPostCnt
					+ ", totalPageCnt=" + totalPageCnt + ", pageNo=" + pageNo + ", startRowIndex=" + startRowIndex
					+ ", pageCntPerBlock=" + pageCntPerBlock + ", totalPageBlockCnt=" + totalPageBlockCnt
					+ ", pageBlockOfCurrentPage=" + pageBlockOfCurrentPage + ", startNumOfCurrentPagingBlock="
					+ startNumOfCurrentPagingBlock + ", endNumOfCurrentPagingBlock=" + endNumOfCurrentPagingBlock + "]";
		}
	}

