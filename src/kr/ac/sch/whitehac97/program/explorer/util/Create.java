package kr.ac.sch.whitehac97.program.explorer.util;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;
import kr.ac.sch.whitehac97.program.menu.MenuRunnable;

public class Create implements MenuRunnable
{

    @Override
    public void run(ExplorerBase base) {

    }

    @Override
    public String getTitle() {
        return "파일 생성";
    }

    @Override
    public String getDescription() {
        return "파일을 생성합니다.";
    }
}
