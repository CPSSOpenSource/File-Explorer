package kr.ac.sch.whitehac97.program.menu;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class ConsoleMenu implements Runnable
{
    private ExplorerBase base;
    public ConsoleMenu(ExplorerBase base)
    {
        this.base = base;
    }

    private Map<Integer, MenuRunnable> worker = new HashMap<>();
    public Map<Integer, MenuRunnable> getWorker()
    {
        return this.worker;
    }

    public void addSelectMenu(int number, MenuRunnable work)
    {
        this.worker.put(number, work);
    }

    @Override
    public void run()
    {
        while(true)
        {
            System.out.println("원하시는 항목의 번호를 입력하세요. (-1 입력시 종료)");
            System.out.println("--------------------------------");
            for (int selectNumber : this.worker.keySet()) {
                MenuRunnable work = this.worker.get(selectNumber);
                System.out.println(String.format("[%d] %s - %s", selectNumber, work.getTitle(), work.getDescription()));
            }
            System.out.println("--------------------------------");

            System.out.print("입력하실 번호: ");
            Scanner scanner = new Scanner(System.in);
            int select = scanner.nextInt();
            if(select == -1)
                break;
            if (this.worker.containsKey(select))
                this.worker.get(select).run(this.base);
            else
                System.out.println("해당되는 항목이 없습니다. 다시 입력하세요.");

        }
    }

}
