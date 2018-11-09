package kr.ac.sch.whitehac97.program.explorer.util;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;
import kr.ac.sch.whitehac97.program.menu.MenuRunnable;

import java.util.Scanner;
import java.io.File;

public class Search implements MenuRunnable
{
    @Override
    public void run(ExplorerBase base) {
        if(base.getRegisteredFile().size() == 0)
        {
            System.out.println("scan() 함수를 이용하여 파일 경로들을 먼저 탐색하세요.");
        }
        else
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("검색할 문자열: ");
            String word = scanner.nextLine();
            for(File file : base.search(word))
            {
                System.out.println("Found -> " + file.getAbsolutePath());
            }
        }
    }

    @Override
    public String getTitle() {
        return "자료 검색";
    }

    @Override
    public String getDescription() {
        return "원하는 키워드를 입력하여 특정한 자료를 검색합니다.";
    }
}
