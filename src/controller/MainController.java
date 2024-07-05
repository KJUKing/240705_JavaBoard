package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import service.FreeService;
import util.ScanUtil;
import view.View;
import vo.BoardVo;

public class MainController{
	static public Map<String, Object> sessionStorage = new HashMap<>();

	FreeService freeService = FreeService.getInstance();

	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.MAIN;
		while (true) {
			switch (view) {
				case MAIN:
					view = main();
					break;
				case FREE_LIST:
					view = freeList();
					break;
				case FREE_INSERT:
					view = freeInsert();
					break;
				case FREE_VIEW:
					view = freeView();
					break;
				case FREE_UPDATE:
					view = freeUpdate();
					break;
				case FREE_DELETE:
					view = freeDelete();
					break;
				default:
					break;

			}
		}
	}

	private View freeDelete() {
		int no = (int)sessionStorage.get("BoardNo");
		List<Object> param = new ArrayList<>();
		param.add(no);
		freeService.freeDelete(param);
		return View.FREE_LIST;
	}

	private View freeUpdate() {
		int no = (int)sessionStorage.get("BoardNo");

		System.out.println("1. 제목 수정");
		System.out.println("2. 내용 수정");
		System.out.println("3. 작성자 수정");
		System.out.println("4. 전체 수정");
		System.out.println("5. 게시판 리스트");
		int sel = ScanUtil.menu();
		if (sel == 5) {
			return View.FREE_LIST;
		}
		List<Object> param = new ArrayList<>();
		if (sel == 1 || sel == 4) {
			String title = ScanUtil.nextLine("제목 : ");
			param.add(title);
		}if (sel == 2 || sel == 4) {
			String content = ScanUtil.nextLine("내용 : ");
			param.add(content);
		}if (sel == 3 || sel == 4) {
			String writer = ScanUtil.nextLine("작성자 : ");
			param.add(writer);
		}
		param.add(no);
		freeService.freeUpdate(param, sel);

		return View.FREE_VIEW;
	}

	private View freeView() {
		int no = (int)sessionStorage.get("BoardNo");
		List<Object> param = new ArrayList<>();
		param.add(no);

		BoardVo boardVo = freeService.freeView(param);
		String title = boardVo.getTitle();
		String content = boardVo.getContent();
		String writer = boardVo.getWriter();
		String wDate = boardVo.getW_date();
		System.out.println("  제목 : "+title+"  저자 : "+writer+"  날짜 : "+wDate);
		System.out.println("내용 : " + content);

		System.out.println("1. 게시판 수정");
		System.out.println("2. 게시판 삭제");
		System.out.println("3. 전체 게시판");


		int sel = ScanUtil.menu();
		switch (sel) {
			case 1: return View.FREE_UPDATE;
			case 2: return View.FREE_DELETE;
			case 3: return View.FREE_LIST;
			default: return  View.FREE_VIEW;
		}
	}

	private View freeInsert() {
		System.out.println("게시판 등록");
		String title = ScanUtil.nextLine("제목 : ");
		String content = ScanUtil.nextLine("내용 : ");
		String writer = ScanUtil.nextLine("작성자 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(title);
		param.add(content);
		param.add(writer);
		freeService.freeInsert(param);

		return View.FREE_LIST;
	}

	private View freeList() {
		List<BoardVo> boardList = freeService.freeList();
		for (BoardVo boardVo : boardList) {

			int no = boardVo.getNo();
			String title = boardVo.getTitle();
			String content = boardVo.getContent();
			String writer = boardVo.getWriter();
			String wDate = boardVo.getW_date();
			System.out.println(no+"\t"+title+"\t"+content+"\t"+writer+"\t"+wDate);

		}
		int no = ScanUtil.nextInt("상세 조회 게시판 번호 : ");
		sessionStorage.put("BoardNo", no);
		return View.FREE_VIEW;
	}


	public View main() {
		System.out.println("1. 전체 게시판 조회");
		System.out.println("2. 게시판 등록");

		int sel = ScanUtil.nextInt("메뉴 선택: ");
		switch (sel) {
			case 1:
				return View.FREE_LIST;
			case 2:
				return View.FREE_INSERT;
			default:
				return View.MAIN;
		}
	}

}
