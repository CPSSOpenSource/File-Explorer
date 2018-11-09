package kr.ac.sch.whitehac97.program.explorer.util;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;
import kr.ac.sch.whitehac97.program.menu.MenuRunnable;

import java.util.Scanner;
import java.io.File;

public class Remove implements MenuRunnable
{
    @Override
    public void run(ExplorerBase base) {
        System.out.print("삭제할 파일명 또는 경로를 포함한 파일을 입력하세요: ");
        String s = new Scanner(System.in).nextLine();
        File file = new File(s);
        if(!file.exists())
        {
            System.out.println("그런 파일 또는 디렉토리가 존재하지 않습니다.");
            return;
        }

        for (File f : base.getRegisteredFile()) {
            if(f.toURI().equals(file.toURI()))
            {
                f.delete();
                return;
            }
        }
        System.out.println("그런 파일 또는 경로가 탐색된 리스트에 존재하지 않습니다.");
    }

    @Override
    public String getTitle() {
        return "파일 제거";
    }

    @Override
    public String getDescription() {
        return "파일을 제거합니다.";
    }
}
