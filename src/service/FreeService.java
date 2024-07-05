package service;

import dao.FreeDao;
import vo.BoardVo;

import java.util.List;

public class FreeService {

    private static FreeService instance;

    private FreeService() {

    }

    public static FreeService getInstance() {
        if (instance == null) {
            instance = new FreeService();
        }
        return instance;
    }
    FreeDao dao = FreeDao.getInstance();

    public List<BoardVo> freeList() {
        List<BoardVo> list = dao.freeList();
        for (BoardVo boardVo : list) {
            String content = boardVo.getContent();
            if (content.length() > 10) {
                content = content.substring(0, 10).trim()+"...";
            }
            boardVo.setContent(content);
        }
        return list;
    }

    public void freeInsert(List<Object> param) {
        dao.freeInsert(param);
    }

    public BoardVo freeView(List<Object> param) {
        return dao.freeView(param);
    }

    public void freeUpdate(List<Object> param, int sel) {
        dao.freeUpdate(param, sel);
    }

    public void freeDelete(List<Object> param) {
        dao.freeDelete(param);
    }
}
