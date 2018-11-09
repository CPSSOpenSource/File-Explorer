package kr.ac.sch.whitehac97.program.menu;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;

public interface MenuRunnable
{
    void run(ExplorerBase base);

    String getTitle();

    String getDescription();
}
