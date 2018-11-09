package kr.ac.sch.whitehac97.program;

import kr.ac.sch.whitehac97.program.explorer.util.Create;
import kr.ac.sch.whitehac97.program.explorer.util.Remove;
import kr.ac.sch.whitehac97.program.explorer.util.Search;
import kr.ac.sch.whitehac97.program.menu.*;

import java.io.File;

/**
 * 자바 프로젝트 샘플 예제 소스 코드입니다.
 * <pre>
 *
 * 문제
 *
 * 1. 파일 검색기
 * 특정 폴더 아래에 있는 모든 파일 및 폴더를 찾아서 등록
 * 추가, 수정, 삭제된 경우 추적해서 반영
 * 검색어를 입력하면 해당 검색어를 포함하는 모든 폴더나 파일 출력
 * 검색 결과는 가능한 한 빨리 제공함
 * 파일 리스트는 프로그램을 종료할 때 저장하고, 실행 시 불러옴
 *
 * 2. 텍스트 검색기
 * 특정 폴더 아래에 있는 모든 텍스트 파일을 찾아서 등록
 * 추가, 수정, 삭제된 경우 추적해서 반영
 * 검색어를 입력하면 해당 검색어를 포함하는 파일명을 출력하고 검색어가 포함된 문장 출력
 * 검색 결과는 가능한 한 빨리 제공
 * 텍스트 파일 관련 정보는 프로그램 종료시 저장하고, 실행히 불러옴
 * </pre>
 */
public class Program
{

    private static void initialize(ConsoleMenu menu)
    {
        menu.addSelectMenu(1, new Search());
        menu.addSelectMenu(2, new Create());
        menu.addSelectMenu(3, new Remove());
    }

    public static void main(String[] args)
    {
        // 탐색기 기능을 수행 할 수 있는 객체를 만듭니다.
        FileExplorer explorer = new FileExplorer();
        explorer.scanCurrentDirectory();

        // 메뉴를 담당하는 콘솔 메뉴 객체를 만듭니다.
        ConsoleMenu menu = new ConsoleMenu(explorer);
        initialize(menu);

        menu.run();

        // 프로그램이 실행하고 있는 경로를 기준으로 하여 모든 파일을 스캔합니다.
        explorer.scanCurrentDirectory();

        // 탐색한 파일 리스트에서 하나씩 데이터를 꺼내와서 경로를 출력합니다.
        for(File file : explorer.getRegisteredFile())
           System.out.println(file.getAbsolutePath());

        // 검색하려는 문자열이 포함된 파일의 경로를 출력합니다.
        System.out.println("Searching for \".class\" files...");
        for(File result : explorer.search(".class"))
        {
            System.out.println("Found: " + result.getAbsolutePath());
        }

        // 파일 리스트를 파일로 저장합니다.
        // 작업이 성공했는지 여부에 대한 반환값입니다.
        Boolean successful = explorer.saveToFileList("./list.txt");
    }
}


